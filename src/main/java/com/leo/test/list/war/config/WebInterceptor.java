package com.leo.test.list.war.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Senchenko Viktor on 14.09.2016.
 */
public class WebInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView!=null&&!modelAndView.getViewName().startsWith("redirect"))
            modelAndView.addObject("isLogedIn", request.getUserPrincipal() != null /*? request.getUserPrincipal().getName() : false*/);
        super.postHandle(request, response, handler, modelAndView);
    }
}
