package by.latushko.training.parser.impl;

import by.latushko.training.entity.TextComponentType;
import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import by.latushko.training.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_DELIMITER_PATTERN = "\\r\\n";
    private final TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER_PATTERN);
        TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);

        for(String p: paragraphs) {
            TextComponent sentenceComponent = sentenceParser.parse(p);
            paragraphComposite.add(sentenceComponent);
        }
        return paragraphComposite;
    }
}
