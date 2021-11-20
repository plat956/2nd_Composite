package by.latushko.training.reader;

import by.latushko.training.exception.InputFileReadException;
import by.latushko.training.reader.impl.DataReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataReaderTest {
    private static final String INPUT_FILE_PATH = "data/source.txt";
    private static final String INPUT_WRONG_FILE_PATH = "dataa/sourcce.txt";
    private DataReader dataReader;

    @BeforeClass
    public void setUp() {
        dataReader = new DataReaderImpl();
    }

    @Test
    public void testRead() {
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\r\n" +
                "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\r\n" +
                "\tIt is a established fact that a reader will be of a page when looking at its layout...\r\n" +
                "\tBye бандерлоги.";
        String actual = null;

        try {
            actual = dataReader.read(INPUT_FILE_PATH);
        } catch (InputFileReadException e) {
            fail("Failed on data reading: " + INPUT_FILE_PATH, e);
        }

        assertEquals(actual, expected);
    }

    @Test
    public void testReadLinesException() {
        assertThrows(InputFileReadException.class, ()-> dataReader.read(INPUT_WRONG_FILE_PATH));
    }
}