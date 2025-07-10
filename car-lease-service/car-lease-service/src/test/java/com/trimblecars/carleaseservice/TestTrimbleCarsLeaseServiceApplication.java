package com.trimblecars.carleaseservice;

import org.springframework.boot.SpringApplication;

public class TestTrimbleCarsLeaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TrimbleCarsLeaseServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
