package FinTrackAPI.com.br.FinTrackAPI;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinTrackApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinTrackApiApplication.class, args);
    }

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE).setFullTypeMatchingRequired(true).setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
