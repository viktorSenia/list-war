package com.leo.test.list.war.controller;

import com.leo.test.list.war.model.BadRequestException;
import com.leo.test.list.war.model.Video;
import com.leo.test.list.war.service.ServiceVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by Senchenko Viktor on 19.09.2016.
 */
@Controller
@RequestMapping(path = "youtube")
public class YoutubeController {
    @Autowired
    private ServiceVideo serviceVideo;

    @GetMapping("list")
    public String home() {
        return "youtube/list";
    }

    @PostMapping(path = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Set<Video> list(@RequestBody Map<String, String> map) {
        if (map.containsKey("url")) {
            return serviceVideo.list(map.get("url"));
        }
        throw new BadRequestException();
    }
}
