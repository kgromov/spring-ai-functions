package guru.springframework.springaifunctions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.ninjas")
public record ApiNinjaSettings(String apiKey, String weatherUrl, String stockUrl) {}
