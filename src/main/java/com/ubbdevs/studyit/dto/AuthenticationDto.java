package com.ubbdevs.studyit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class AuthenticationDto {

    @NotNull
    @ToString.Exclude
    @JsonProperty("access_token")
    private String accessToken;

    @NotNull
    @JsonProperty("token_type")
    private String tokenType;

    @NotNull
    @JsonProperty("expires_in")
    private int expiresIn;

    @NotNull
    @JsonProperty("scope")
    private Set<String> scope;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    @JsonProperty("jti")
    private String jti;

}
