package miu.ea.realestateapimonolithic.congifuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cloudinary")
public record CloudinaryConfigProperties(String cloudName, String apiKey, String apiSecret) {
}
