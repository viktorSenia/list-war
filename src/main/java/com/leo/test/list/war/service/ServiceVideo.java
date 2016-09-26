package com.leo.test.list.war.service;

import com.leo.test.list.war.model.BadRequestException;
import com.leo.test.list.war.model.Video;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Senchenko Viktor on 19.09.2016.
 */
@Service
public class ServiceVideo {
    private static final Logger LOGGER = Logger.getLogger(ServiceVideo.class.getName());

    public Set<Video> list(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BadRequestException();
        }
        if (doc == null)
            return null;
        Set<Video> href = new HashSet<>();
        //        Elements elements = doc.select("#pl-load-more-destination tr td.pl-video-title a.pl-video-title-link");
        Elements elements = doc.select("#pl-load-more-destination tr");
        elements.stream().parallel().forEach(element -> href.add(getVideo(element.attr("data-video-id"))));
        return href;
    }

//    private String getId(String url) {
//        StringBuilder builder = new StringBuilder(url);
//        return getParameter(builder, "v=");
//    }

    protected String getTitle(StringBuilder builder) {
        return getParameter(builder, "title=");
    }

    protected String getUrlEncoded(StringBuilder builder) {
        return getParameter(builder, "url_encoded_fmt_stream_map=");
    }

    private String getParameter(StringBuilder builder, String parameter) {
        int start = builder.indexOf(parameter) + parameter.length();
        if (start > -1) {
            int end = builder.indexOf("&", start);
            if (end > -1)
                return decode(builder.substring(start, end));
            return decode(builder.substring(start));
        }
        LOGGER.log(Level.INFO, "Doesn't have the parameter \"" + parameter + "\"");
        return null;
    }

    protected String[] filterParameter(String[] strings, String parameter) {
        return Arrays.stream(strings).parallel().filter(s1 -> s1.startsWith(parameter)).
                map(s -> s.replaceFirst(parameter + "=", "")).map(ServiceVideo::decode).toArray(String[]::new);
    }

    private Video getVideo(String id) {
        try {
            URL url = new URL("http://youtube.com/get_video_info?video_id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return getVideo(response);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.WARNING, "Bad URL", e);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException", e);
        }
        return null;
    }

    protected Video getVideo(StringBuilder response) {
        String result = getUrlEncoded(response);
        Set<Video.UrlVideo> urlVideos = null;
        if (result != null) {
            String[] parameters = result.split("[&,]");
            String[] type = filterParameter(parameters, "type");
            String[] urls = filterParameter(parameters, "url");
            if (type.length != urls.length) {
                LOGGER.log(Level.WARNING, "Different quantity of type {0} and URL {1}", new Object[]{type.length, urls.length});
            } else {
                urlVideos = new HashSet<>();
                for (int i = 0; i < type.length; i++)
                    urlVideos.add(new Video.UrlVideo(type[i], urls[i]));
            }
        }
        return new Video(getTitle(response), urlVideos);
    }

    private static String decode(String string) {
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.WARNING, "UnsupportedEncodingException", e);
        }
        return null;
    }
}
