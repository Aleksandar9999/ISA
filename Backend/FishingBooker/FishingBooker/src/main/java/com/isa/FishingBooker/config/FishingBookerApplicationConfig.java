package com.isa.FishingBooker.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FishingBookerApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {return new ModelMapper();}
}
