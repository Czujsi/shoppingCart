package org.example.run;

import org.example.user_interfaces.text_interface.TextInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RunConfig {
    @Bean
    @ConditionalOnProperty(prefix = "runCommandLineRunner", name = "enable", havingValue = "true", matchIfMissing = true)
    CommandLineRunner commandLineRunner(TextInterface textInterface) {
        return args -> textInterface.run();
    }
}
