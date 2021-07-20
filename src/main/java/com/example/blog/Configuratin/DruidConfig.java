package com.example.blog.Configuratin;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author QianMo
 * @date 2021/7/19 13:43
 */
@Configuration
public class DruidConfig {
    /**
     * 将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
     * 绑定全局配置文件中的 Druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource 从而让它们生效
     * @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
     * 前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        // 这些参数可以在 com.alibaba.druid.support.http.StatViewServlet
        // 的父类 com.alibaba.druid.support.http.ResourceServlet 中找到
        Map<String, String> initParams = new HashMap<>();
        // 后台管理界面的登录账号和密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");

        // allow：Druid 后台允许谁可以访问
        // initParams.put("allow", "localhost")：表示只有本机可以访问
        // 为空或者为null时，表示允许所有访问
        initParams.put("allow", "");
        // deny：Druid 后台拒绝谁访问
        // 表示禁止此ip访问
        initParams.put("deny", "192.168.1.1");

        // 设置初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean
    public FilterRegistrationBean Filter() {
        // FilterRegistrationBean bean = new FilterRegistrationBean();
        // bean.setFilter(new WebStatFilter());
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());

        // 忽略过滤格式，exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,*.jpg,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);

        // 添加过滤规则，"/*" 表示过滤所有请求
        // bean.setUrlPatterns(Arrays.asList("/*"));
        bean.addUrlPatterns("/*");

        return bean;
    }
}
