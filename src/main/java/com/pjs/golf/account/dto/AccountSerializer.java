package com.pjs.golf.account.dto;

import com.pjs.golf.account.entity.Account;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AccountSerializer extends JsonSerializer<Account> {

    @Override
    public void serialize(Account value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("username", value.getUsername());
        gen.writeStringField("joinDate", value.getJoinDate().toString());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("birth", value.getBirth());
        gen.writeStringField("portrait", value.getProtrait());
        gen.writeStringField("gender", value.getGender().toString());
        gen.writeEndObject();
    }
}
