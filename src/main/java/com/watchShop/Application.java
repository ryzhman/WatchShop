package com.watchShop;

import com.watchShop.DAO.WatchRepository;
import com.watchShop.entity.Status;
import com.watchShop.entity.Watch;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Oleksandr Ryzhkov on 28.10.2017.
 */
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(WatchRepository repository) {
        return (args) -> {
            for (int i = 0; i < 11; i++) {
                Watch watch = new Watch("Watch" + i, "Manufacturer" + (i * i), i % 2 == 0, Status.AVAILABLE);
                repository.save(watch);
            }
        };
    }
}
