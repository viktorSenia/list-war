package com.leo.test.list.war.service;

import com.leo.test.list.war.model.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Senchenko Viktor on 20.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource("classpath:testData.properties")
public class ServiceVideoTest {
    @Autowired
    private ServiceVideo serviceVideo;

    @Autowired
    private Environment environment;

    @Test
    public void testGetTitle() throws Exception {
        String data = environment.getProperty("full.data.video");
        StringBuilder builder = new StringBuilder(data);
        assertEquals(environment.getProperty("title.encoded"), serviceVideo.getTitle(builder));
    }

    @Test
    public void testGetUrlEncoded() throws Exception {
        String data = environment.getProperty("full.data.video");
        StringBuilder builder = new StringBuilder(data);
        assertEquals(environment.getProperty("url.encoded"), serviceVideo.getUrlEncoded(builder));
    }

    @Test
    public void testFilterParameter() throws Exception {
        String data = environment.getProperty("url.encoded");
        String[] array = data.split("[,&]");
        assertTrue(serviceVideo.filterParameter(array, "url").length == 5);
        assertTrue(serviceVideo.filterParameter(array, "type").length == 5);
    }

    @Test
    public void testGetVideo() throws Exception {
        String data = environment.getProperty("full.data.video");
        StringBuilder builder = new StringBuilder(data);
        Video video = serviceVideo.getVideo(builder);
        assertTrue(video.getUrls().size() == 5);
        assertEquals(environment.getProperty("title.encoded"), video.getTitle());
    }
}