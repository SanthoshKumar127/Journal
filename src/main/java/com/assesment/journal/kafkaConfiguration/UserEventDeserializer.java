package com.assesment.journal.kafkaConfiguration;


import com.assesment.journal.model.UserJournalEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class UserEventDeserializer implements Deserializer<UserJournalEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserJournalEvent deserialize(String topic, byte[] data) {
        try {
            String dataString = new String(data, StandardCharsets.UTF_8);
            return objectMapper.readValue(dataString, UserJournalEvent.class);
        } catch (IOException e) {
            // Handle the exception appropriately
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
