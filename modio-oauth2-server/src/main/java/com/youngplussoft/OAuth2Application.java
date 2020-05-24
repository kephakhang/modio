package com.youngplussoft;

import com.youngplussoft.common.RequestInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan
public class OAuth2Application extends SpringBootServletInitializer {

	protected static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OAuth2Application.class);

	@Autowired
	public RequestInterceptor requestInterceptor ;

	public static void main(String[] args) {

    	new SpringApplicationBuilder(OAuth2Application.class)
				.properties("spring.config.name:oauth2-application")
        		.run(args);
    }
    
    @Bean(name = "localeResolver")
	public SessionLocaleResolver sessionLocaleResolver() {
		// 세션 기준으로 로케일을 설정 한다.
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 쿠키 기준(세션이 끊겨도 브라우져에 설정된 쿠키 기준으로)
		// CookieLocaleResolver localeResolver = new CookieLocaleResolver();

		// 최초 기본 로케일을 강제로 설정이 가능 하다.
		localeResolver.setDefaultLocale(new Locale("ko_KR"));
		return localeResolver;
	}

	@Bean
	public MappedInterceptor myMappedInterceptor() {
		LOGGER.info("myMappedInterceptor") ;
		return new MappedInterceptor(new String[]{"/oauth/token", "/oauth/token/check", "/oauth/refresh"}, requestInterceptor);
	}

}