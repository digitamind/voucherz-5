package com.interswitch.voucherz.gift.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Setter
@Getter
public class Response {
    private final Timestamp timestamp;

    private final Integer status;

    @JsonProperty("Response Phrase")
    private final HttpStatus responsePhrase;

    private final String message;

    private final List<Error> errors;

    public Response(HttpStatus responsePhrase, String message, List<Error> errors) {
        this.timestamp = timestamp();
        this.status = responsePhrase.value();
        this.responsePhrase = responsePhrase;
        this.message = message;
        this.errors = errors;
    }

    public Timestamp timestamp(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
