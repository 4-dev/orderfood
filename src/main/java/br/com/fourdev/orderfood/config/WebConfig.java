package br.com.fourdev.orderfood.config;

import java.util.Locale;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container ->
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
							new ErrorPage(HttpStatus.FORBIDDEN, "/403"),
							new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500")));
	}
	
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter(
			FormattingConversionService conversionService) {
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}
}
