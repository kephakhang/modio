package com.youngplussoft.modio;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;

import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ImportResource("classpath:application-context.xml")
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String[] RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}


	@Bean
	public WriteConcernResolver writeConcernResolver() {
		return action -> {
			System.out.println("Using Write Concern of Acknowledged");
			return WriteConcern.ACKNOWLEDGED; //It means WriteResultChecking.EXCEPTION
		};
	}



	/*
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializerByType(ObjectId.class, new ToStringSerializer());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
		converters.add(converter);
	}
	*/
	
//	@Autowired
//	RequestInterceptor  requestHandler;
//

	/*
	 * spring-boot 리소스 추가 설정 함수
	 * 
	 * @param registry
	 *            ResourceHandlerRegistry
	 * @return void
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");



		if (!registry.hasMappingForPattern("/*")) {
			registry.addResourceHandler("/*")
			         .addResourceLocations(RESOURCE_LOCATIONS);
		}
	}

//	@Bean
//	public RepositoryRestConfigurerAdapter repositoryRestConfigurer() {
//
//		return new RepositoryRestConfigurerAdapter() {
//
//			@Override
//			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//				config.setBasePath("/api/v3/");
//			}
//		};
//	}

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
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

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
