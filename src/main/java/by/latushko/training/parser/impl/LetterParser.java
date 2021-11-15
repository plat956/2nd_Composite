package by.latushko.training.parser.impl;

import by.latushko.training.entity.TextComponentType;
import by.latushko.training.entity.CharacterNode;
import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import by.latushko.training.parser.TextParser;

public class LetterParser implements TextParser {
    private static final String LETTER_DELIMITER_PATTERN = "";

    @Override
    public TextComposite parse(String text) {
        String[] symbols = text.split(LETTER_DELIMITER_PATTERN);
        TextComposite letterComposite = new TextComposite(TextComponentType.LETTER);

        for(String s: symbols) {
            TextComponent letterComponent = new CharacterNode(
                    Character.isLetter(s.charAt(0)) ? TextComponentType.LETTER : TextComponentType.CHARACTER,
                    s.charAt(0));

            letterComposite.add(letterComponent);
        }
        return letterComposite;
    }
}
