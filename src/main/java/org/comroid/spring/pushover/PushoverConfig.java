package org.comroid.spring.pushover;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@Data
@Builder
@AllArgsConstructor
@ConfigurationProperties(prefix = "pushover")
public class PushoverConfig {
    private String token;
    private String user;
    @Nullable
    private String device;
    @Nullable
    private String title;

    @Order
    @Bean(name = "pushover")
    public PushoverService createPushoverService() {
        return new PushoverService(this);
    }

    PushoverMessage createMessage(String message) {
        return new PushoverMessage(token, user, device, title, message);
    }
}
