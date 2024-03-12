package org.example.run;

import org.example.user_interfaces.text_interface.TextInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunConfig {
    @Bean
    CommandLineRunner commandLineRunner(TextInterface textInterface) {
        return args -> textInterface.run();
    }
}
