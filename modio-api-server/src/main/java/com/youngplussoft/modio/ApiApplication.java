package com.youngplussoft.modio;

import java.util.Arrays;
import com.youngplussoft.modio.common.ApiRequestInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer ;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.handler.MappedInterceptor;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan
public class ApiApplication extends SpringBootServletInitializer {

	protected static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ApiApplication.class);

	@Value("${modio.api.base.url}")
	protected String basePath  ;
	
//	@Autowired
//	private RepositoryRestConfiguration repositoryRestConfiguration;
//
	@Autowired
	public ApiRequestInterceptor apiRequestInterceptor ;

//    @PostConstruct
//    public void exposeIds() {
//        this.repositoryRestConfiguration.setReturnBodyForPutAndPost(true);
//    }

	/*
	@Bean
	public ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
		return new ResourceServerConfigurerAdapter() {
			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.headers().frameOptions().disable();
				http.authorizeRequests().antMatchers("/apv/v2", "/api/v2/*", "/apv/v3", "/api/v3/*").access("#oauth2.hasScope('read')")
						.anyRequest().authenticated();
			}
		};
	}
	*/

	/*
	 * spring-boot 서블렛 초기화 설정 Overrding 함수
	 * 
	 * @param application
	 *            SpringApplicationBuilder
	 * @return void
	 * /
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	*/

	/*
	 * spring-boot 실행 main 함수
	 * 
	 * @param args
	 *            spring-boot main commadline argument
	 * @return void
	 */
	public static void main(String[] args) {
		// ApplicationContext ctx = SpringApplication.run(ApiApplication.class,
		// args);

		ApplicationContext ctx = new SpringApplicationBuilder(ApiApplication.class)
				//.initializers(new ApiApplicationContextInitializer())
				.run(args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
	
	@Bean
	public MappedInterceptor myMappedInterceptor() {
		LOGGER.info("myMappedInterceptor") ;
	    return new MappedInterceptor(new String[]{"/login", "mobile", "/mobile/**", "/singup", "/logout", "/mobileauth", basePath+"/**"}, apiRequestInterceptor);
	}
}