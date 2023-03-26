package org.comroid.spring.pushover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class PushoverAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(PushoverService.class)
    public PushoverService pushover(@Autowired(required = false) PushoverConfig config) {
        if (config == null)
            return null;
        return new PushoverService(config);
    }
}
