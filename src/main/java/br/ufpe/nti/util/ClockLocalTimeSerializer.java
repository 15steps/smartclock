package br.ufpe.nti.util;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClockLocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeString(value.format(DateTimeFormatter.ofPattern("HH:mm")));

	}

}
