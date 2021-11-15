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
        String[] letters = text.split(LETTER_DELIMITER_PATTERN);
        TextComposite letterComposite = new TextComposite(TextComponentType.LETTER);

        for(String l: letters) {
            char character = l.charAt(0);
            TextComponentType type = Character.isLetter(character) ? TextComponentType.LETTER : TextComponentType.CHARACTER;
            TextComponent letterComponent = new CharacterNode(type, character);

            letterComposite.add(letterComponent);
        }
        return letterComposite;
    }
}
