package by.latushko.training.parser;

import by.latushko.training.entity.TextComposite;
import by.latushko.training.exception.InputFileReadException;
import by.latushko.training.parser.impl.ParagraphParser;
import by.latushko.training.reader.DataReader;
import by.latushko.training.reader.impl.DataReaderImpl;
import by.latushko.training.service.TextAnalyzeService;
import by.latushko.training.service.impl.TextAnalyzeServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextParserTest {
    private static final String INPUT_FILE_PATH = "data/source.txt";
    private TextParser parser;
    private DataReader reader;
    private TextAnalyzeService textAnalyzeService;
    private String text;

    @BeforeClass
    public void setUp() throws InputFileReadException {
        reader = new DataReaderImpl();
        parser = new ParagraphParser();
        textAnalyzeService = new TextAnalyzeServiceImpl();
        text = reader.read(INPUT_FILE_PATH);
    }

    @Test
    public void testParse() {
        TextComposite composite = parser.parse(text);
        String actual = composite.toString();
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\r\n" +
                "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\r\n" +
                "\tIt is a established fact that a reader will be of a page when looking at its layout...\r\n" +
                "\tBye бандерлоги.";
        assertEquals(actual, expected);
    }
}