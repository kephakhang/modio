package com.youngplussoft;

import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
@ImportResource("classpath:oauth2-application-context.xml")
public class OAuth2MvcConfig implements WebMvcConfigurer {

	private static final String[] RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };

	

	/*
	 * spring-boot 리소스 추가 설정 함수
	 * 
	 * @param registry
	 *            ResourceHandlerRegistry
	 * @return void
	 */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if (!registry.hasMappingForPattern("/*")) {
			registry.addResourceHandler("/*").addResourceLocations(RESOURCE_LOCATIONS);
		}
	}

	/*
	@Bean
	public RepositoryRestConfigurerAdapter repositoryRestConfigurer() {

		return new RepositoryRestConfigurerAdapter() {

			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.setBasePath("/api/v3/");
			}
		};
	}
	*/

	/*
	 * spring-boot 설정 enable 함수
	 * 
	 * @param configurer
	 *            DefaultServletHandlerConfigurer
	 * @return void
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/*
	 * spring-boot default view 타입 설정
	 * 
	 * @return viewResolver(InternalResourceViewResolver)
	 */
	/*
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	*/

	/*
	 * spring-boot locale 변경 인터셉터
	 * 
	 * @return LocaleChangeInterceptor
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// request로 넘어오는 language parameter를 받아서 locale로 설정 한다.
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
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

}
