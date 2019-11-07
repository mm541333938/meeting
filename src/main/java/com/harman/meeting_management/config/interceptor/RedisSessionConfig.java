package com.harman.meeting_management.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 添加@EnableRedisHttpSession来开启spring session支持
 * @author L.Willian
 * @date 11/6/2019 4:17 PM
 */

@Configuration
//maxInactiveIntervalInSeconds 默认是1800秒过期，这里测试修改为60秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class RedisSessionConfig {
}
