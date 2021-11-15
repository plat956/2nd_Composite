package by.latushko.training.reader;

import by.latushko.training.exception.InputFileReadException;

import java.util.List;

public interface DataReader {
    String read(String filePath) throws InputFileReadException;
}
