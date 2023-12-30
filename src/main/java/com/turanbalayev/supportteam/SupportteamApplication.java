package com.turanbalayev.supportteam;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

import static com.turanbalayev.supportteam.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class SupportteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportteamApplication.class, args);
		new File(USER_FOLDER).mkdirs();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
