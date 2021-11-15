package by.latushko.training.parser.impl;

import by.latushko.training.entity.TextComponentType;
import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import by.latushko.training.parser.TextParser;

public class SentenceParser implements TextParser {
    private static final String SENTENCE_DELIMITER_PATTERN = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s";
    private final TextParser lexemeParser = new LexemeParser();

    @Override
    public TextComposite parse(String text) {
        String[] sentences = text.split(SENTENCE_DELIMITER_PATTERN);
        TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);

        for(String s: sentences) {
            TextComponent lexemeComponent = lexemeParser.parse(s);
            sentenceComposite.add(lexemeComponent);
        }
        return sentenceComposite;
    }
}
