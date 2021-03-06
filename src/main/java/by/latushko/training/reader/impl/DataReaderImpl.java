package by.latushko.training.reader.impl;

import by.latushko.training.exception.InputFileReadException;
import by.latushko.training.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataReaderImpl implements DataReader {
    private static final Logger logger = LogManager.getLogger();

    public String read(String filePath) throws InputFileReadException {
        logger.info("Reading the \"{}\" file", filePath);

        ClassLoader loader = getClass().getClassLoader();
        URL resource = loader.getResource(filePath);
        if(resource == null) {
            logger.error("Path {} doesn't exist", filePath);
            throw new InputFileReadException("Path " + filePath + " doesn't exist");
        }

        try {
            return Files.readString(Paths.get(resource.toURI()));
        } catch (IOException | URISyntaxException ex) {
            logger.error("Error reading from file: {}", filePath, ex);
            throw new InputFileReadException("That's impossible to read from file: " + filePath, ex);
        }
    }
}
