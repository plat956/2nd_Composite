package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static by.latushko.training.entity.TextComponentType.CHARACTER;
import static by.latushko.training.entity.TextComponentType.LETTER;

public class TextAnalyzeServiceImpl implements TextAnalyzeService{
    private static final String VOWEL_PATTERN = "[aeiouауоыиэяюёе]+";

    @Override
    public void sortByParagraphSentencesCount(TextComposite composite) {
        composite.getComponents().sort(Comparator.comparingInt(c -> c.getComponents().size()));
    }

    @Override
    public int countConsonants(TextComposite composite, int paragraphNumber, int sentenceNumber) {
        return countLetters(composite, paragraphNumber, sentenceNumber, false);
    }

    @Override
    public int countVowels(TextComposite composite, int paragraphNumber, int sentenceNumber) {
        return countLetters(composite, paragraphNumber, sentenceNumber, true);
    }

    @Override
    public List<TextComponent> findSentencesWithTheLongestWord(TextComposite composite) {
        return null; //todo
    }

    @Override
    public Map<String, Integer> findRepeatedWordsCount(TextComposite composite) {
        return null; //todo
    }

    @Override
    public void deleteSentencesByWordsCountLessThan(TextComposite composite, int count) {
        //todo
    }

    private int countLetters(TextComposite composite, int paragraphNumber, int sentenceNumber, boolean countVowels) {
        TextComponent paragraph = composite.getComponents().get(paragraphNumber - 1);
        TextComponent sentence = paragraph.getComponents().get(sentenceNumber - 1);

        int vowelCounter = 0;
        int consonantCounter = 0;
        for(TextComponent lexeme: sentence.getComponents()) {
            if(lexeme.getType() != CHARACTER) {
                for (TextComponent word : lexeme.getComponents()) {
                    for (TextComponent letter : word.getComponents()) {
                        if (letter.getType() == LETTER) {
                            if(letter.toString().toLowerCase().matches(VOWEL_PATTERN)) {
                                vowelCounter++;
                            } else {
                                consonantCounter++;
                            }
                        }
                    }
                }
            }
        }

        return countVowels ? vowelCounter : consonantCounter;
    }
}
