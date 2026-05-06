package com.chan.Conifg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConifgApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConifgApplication.class, args);
  }

}
