package me.zdziszkee.isfsystem.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zdziszkee.isfsystem.model.StoreConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultConfigLoader implements Loader<Path> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public StoreConfiguration load(Path source) {
        try {
            return objectMapper.readValue(Files.readAllBytes(source), StoreConfiguration.class);
        }
        catch (IOException exception) {
            throw new RuntimeException("Error occurred while trying to read store configuration",
                    exception);
        }

    }
}
