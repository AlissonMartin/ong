package com.github.AlissonMartin.ong.serializers.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.AlissonMartin.ong.models.User;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserSerializer extends JsonSerializer<User> {

  @Override
  public void serialize(User user, JsonGenerator jsonGenerator,
                        SerializerProvider serializerProvider) throws
          JsonProcessingException, IOException {

    jsonGenerator.writeStartObject();

    jsonGenerator.writeNumberField("id", user.getId());
    jsonGenerator.writeStringField("name", user.getName());
    jsonGenerator.writeStringField("username", user.getUsername());
    jsonGenerator.writeStringField("email", user.getEmail());
    jsonGenerator.writeObjectField("role", user.getRole());
    jsonGenerator.writeStringField("photoUrl", user.getPhotoUrl());

    jsonGenerator.writeArrayFieldStart("userAchievements");
    if (user.getUserAchievements() != null) {
      for (var achievement : user.getUserAchievements()) {
        jsonGenerator.writeObject(achievement);
      }
    }
    jsonGenerator.writeEndArray();

    if (user.getInstitution() != null) {
      jsonGenerator.writeObjectField("institution", user.getInstitution());
    } else {
      jsonGenerator.writeNullField("institution");
    }

    if (user.getVerificationCode() != null) {
      jsonGenerator.writeStringField("verificationCode", user.getVerificationCode());
    }

    jsonGenerator.writeEndObject();
  }

}
