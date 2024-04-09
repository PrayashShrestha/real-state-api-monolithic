package miu.ea.realestateapimonolithic;

import miu.ea.realestateapimonolithic.congifuration.CloudinaryConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(CloudinaryConfigProperties.class)
@SpringBootApplication
public class RealEstateApiMonolithicApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealEstateApiMonolithicApplication.class, args);
    }

}
