package me.zdziszkee.isfsystem.loaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.zdziszkee.isfsystem.model.StoreConfiguration;

import java.io.IOException;
import java.io.InputStream;


public class DefaultConfigLoader implements Loader<StoreConfiguration> {
    private final ObjectMapper objectMapper = JsonMapper.builder()
                                                        .addModule(new JavaTimeModule())
                                                        .build();


    @Override
    public StoreConfiguration load(InputStream source) {
        try {
            return objectMapper.readValue(source,
                    StoreConfiguration.class);
        }
        catch (IOException exception) {
            throw new RuntimeException("Error occurred while trying to read store configuration",
                    exception);
        }

    }
}
