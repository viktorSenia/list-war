package com.leo.test.list.war.model;

import java.util.List;
import java.util.Set;

/**
 * Created by Senchenko Viktor on 19.09.2016.
 */
public class Video {
    private String title;

    private Set<UrlVideo> urls;

    public Video(String title, Set<UrlVideo> urls) {
        this.title = title;
        this.urls = urls;
    }

    public String getTitle() {
        return title;
    }

    public Set<UrlVideo> getUrls() {
        return urls;
    }

    public static class UrlVideo {
        private String type;

        private String url;

        public UrlVideo(String type, String url) {
            this.type = type;
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }
    }
}
