package com.harman.meeting_management.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录状态拦截器RedisSessionInterceptor
 *
 * @author L.Willian
 * @date 11/6/2019 4:19 PM
 */
public class RedisSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;


}
