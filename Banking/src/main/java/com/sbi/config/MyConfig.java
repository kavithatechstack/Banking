package com.sbi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

	public ModelMapper mapper;

	@Bean
	public ModelMapper modelMapper() {
		mapper = new ModelMapper();
		return mapper;
	}

}
