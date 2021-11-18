import by.latushko.training.entity.TextComposite;
import by.latushko.training.exception.InputFileReadException;
import by.latushko.training.exception.TextAnalyzingException;
import by.latushko.training.parser.impl.ParagraphParser;
import by.latushko.training.reader.DataReader;
import by.latushko.training.reader.impl.DataReaderImpl;
import by.latushko.training.service.impl.TextAnalyzeService;
import by.latushko.training.service.impl.TextAnalyzeServiceImpl;

public class Main {
    public static void main(String[] args) throws InputFileReadException, TextAnalyzingException {
        DataReader reader = new DataReaderImpl();
        String source = reader.read("data/source.txt");

        ParagraphParser parser = new ParagraphParser();
        TextComposite composite = parser.parse(source);

        String q = composite.toString();

        TextAnalyzeService textAnalyzeService = new TextAnalyzeServiceImpl();
        //textAnalyzeService.sortByParagraphSentencesCount(composite);

        int qwe = textAnalyzeService.calculateConsonants(composite, 4, 1);

        q = composite.toString();

        q = null;
    }
}
