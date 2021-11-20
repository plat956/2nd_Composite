package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import by.latushko.training.exception.InputFileReadException;
import by.latushko.training.parser.TextParser;
import by.latushko.training.parser.impl.ParagraphParser;
import by.latushko.training.reader.DataReader;
import by.latushko.training.reader.impl.DataReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class TextAnalyzeServiceTest {
    private static final String INPUT_FILE_PATH = "data/source.txt";
    private TextParser parser;
    private TextComposite composite;
    private DataReader reader;
    private TextAnalyzeService textAnalyzeService;

    @BeforeClass
    public void setUp() throws InputFileReadException {
        reader = new DataReaderImpl();
        parser = new ParagraphParser();
        textAnalyzeService = new TextAnalyzeServiceImpl();

        String text = reader.read(INPUT_FILE_PATH);
        composite = parser.parse(text);
    }

    @Test
    public void testFindSentenceWithTheLongestWord() {
        TextComponent sentence = textAnalyzeService.findSentenceWithTheLongestWord(composite);
        String expected = "Bye бандерлоги.";
        String actual = sentence.toString().trim();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindRepeatedWordsWithCountContaining() {
        Map<String, Integer> words = textAnalyzeService.findRepeatedWordsWithCount(composite);
        int expected = 5;
        int actual = words.get("of");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindRepeatedWordsWithCountSize() {
        Map<String, Integer> words = textAnalyzeService.findRepeatedWordsWithCount(composite);
        int expected = 82;
        int actual = words.size();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByParagraphSentencesCount() {
        textAnalyzeService.sortByParagraphSentencesCount(composite);

        String expected = "\tIt is a established fact that a reader will be of a page when looking at its layout...\r\n" +
                "\tBye бандерлоги.\r\n" +
                "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\r\n" +
                "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?";

        String actual = composite.toString();
        assertEquals(actual, expected);
    }

    @Test
    public void testCountConsonants() {
        int actual = textAnalyzeService.countConsonants(composite, 2, 1);
        int expected = 62;
        assertEquals(actual, expected);
    }

    @Test
    public void testCountVowels() {
        int actual = textAnalyzeService.countVowels(composite, 2, 1);
        int expected = 40;
        assertEquals(actual, expected);
    }
}