package by.latushko.training.parser.impl;

import by.latushko.training.entity.TextComponentType;
import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import by.latushko.training.parser.TextParser;

public class WordParser implements TextParser {
    private final LetterParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
        TextComponent lexemeComponent = letterParser.parse(text);
        wordComposite.add(lexemeComponent);
        return wordComposite;
    }
}
