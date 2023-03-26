package org.comroid.spring.pushover;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
@JsonSerialize
@AllArgsConstructor
public class PushoverMessage {
    private String token;
    private String user;
    @Nullable
    private String device;
    @Nullable
    private String title;
    private String message;
}
