package com.github.AlissonMartin.ong.serializers.institution;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.AlissonMartin.ong.models.Institution;

import java.io.IOException;

public class InstitutionSerializer extends JsonSerializer<Institution> {
  @Override
  public void serialize(Institution institution, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartObject();

    gen.writeNumberField("id", institution.getId());
    gen.writeStringField("full_name", institution.getFull_name());
    gen.writeStringField("name", institution.getName());
    gen.writeStringField("description", institution.getDescription());
    gen.writeStringField("address", institution.getAddress());
    gen.writeNumberField("number", institution.getNumber());
    gen.writeStringField("complement", institution.getComplement());
    gen.writeStringField("district", institution.getDistrict());
    gen.writeNumberField("zip", institution.getZip());
    gen.writeStringField("city", institution.getCity().getName());
    gen.writeStringField("state", institution.getState().getName());
    gen.writeStringField("latitude", institution.getLatitud());
    gen.writeStringField("longitude", institution.getLongitud());

    gen.writeEndObject();
  }
}
