package com.example.konustest.Exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppError {
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public AppError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public AppError(Integer code, Map<String, String> errors) {
        this.code = code;
        this.errors = errors;
    }
}
