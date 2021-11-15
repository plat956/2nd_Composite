package by.latushko.training.parser.impl;

import by.latushko.training.entity.TextComponentType;
import by.latushko.training.entity.CharacterNode;
import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import by.latushko.training.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String LEXEME_DELIMITER_PATTERN = "\\S+";
    private static final String PUNCTUATION_PATTERN = "\\p{Punct}";
    private static final String WORD_PATTERN = "[А-я\\w]+";
    private final TextParser wordParser = new WordParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
        Pattern lexemePattern = Pattern.compile(LEXEME_DELIMITER_PATTERN);
        Matcher lexemes = lexemePattern.matcher(text);

        while (lexemes.find()) {
            String lexeme = lexemes.group();
            if(lexeme.matches(WORD_PATTERN)) {
                TextComponent wordComponent = wordParser.parse(lexeme);
                lexemeComposite.add(wordComponent);
            } else {
                if(lexeme.matches(PUNCTUATION_PATTERN)) {
                    lexemeComposite.add(new CharacterNode(TextComponentType.PUNCTUATION, lexeme.charAt(0)));
                } else {
                    String possibleWord = lexeme.substring(0, lexeme.length() - 1);
                    if (possibleWord.matches(WORD_PATTERN)) {
                        TextComponent wordComponent = wordParser.parse(possibleWord);
                        lexemeComposite.add(wordComponent);
                        lexemeComposite.add(new CharacterNode(TextComponentType.CHARACTER, lexeme.charAt(possibleWord.length())));
                    } else {
                        int i = 0; //todo
                    }
                }
            }
        }

        return lexemeComposite;
    }
}
