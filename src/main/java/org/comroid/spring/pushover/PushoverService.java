package org.comroid.spring.pushover;

import org.comroid.api.Polyfill;
import org.comroid.spring.util.ObjectToUrlEncodedConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Order
@Component
@ConditionalOnBean(PushoverConfig.class)
public class PushoverService {
    public static final URI PublishMessageUri = Polyfill.uri("https://api.pushover.net/1/messages.json");
    private final PushoverConfig config;

    public PushoverService(@Autowired PushoverConfig config) {
        this.config = config;
    }

    public UUID send(String message) {
        var headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        var entity = new HttpEntity<>(config.createMessage(message), headers);
        var template = new RestTemplate(List.of(new ObjectToUrlEncodedConverter(), new MappingJackson2HttpMessageConverter()));
        var resp = template.postForObject(PublishMessageUri, entity, Map.class);
        return UUID.fromString(resp.get("request").toString());
    }
}
