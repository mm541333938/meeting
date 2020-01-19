package com.harman.meeting_management.config;

import com.harman.meeting_management.component.JwtAuthenticationTokenFilter;
import com.harman.meeting_management.component.RestAuthenticationEntryPoint;
import com.harman.meeting_management.component.RestfulAccessDeniedHandler;
import com.harman.meeting_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 配置
 *
 * @author L.Willian
 * @date 2020/1/14
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;

    /**
     * 当用户没有访问权限时的处理器，用于返回JSON格式的处理结果
     */
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;//访问拒绝处理器
    /**
     * 当未登录或token失效时，返回JSON格式的结果
     */
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;//认证回调点

    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器
     */
    @Override //重写超类的configure方法
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//由于使用的是jwt，不需要csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//基于token，所以不需要session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,// 允许对网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/admin/login", "/admin/register")//对登录注册允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
                /*
                .antMatchers("/**")//测试时全部运行访问
                .permitAll()
                */
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();

        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * 用于配置UserDetailsService及PasswordEncoder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口，目前使用的是BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SpringSecurity定义的核心接口，用于根据用户名获取用户信息，需要自行实现
     */

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
