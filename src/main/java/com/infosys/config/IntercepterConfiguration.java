package com.infosys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.infosys.intercepters.RequestLoggingIntercepter;

@Component
public class IntercepterConfiguration implements WebMvcConfigurer {

	@Autowired
	private RequestLoggingIntercepter logIntercepter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logIntercepter);
	}

}
