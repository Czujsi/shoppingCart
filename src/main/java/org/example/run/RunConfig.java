package org.example.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunConfig {
    @Bean
    CommandLineRunner commandLineRunner(Run run){
        return args -> {
            run.textInterface.run();
        };
    }
}
