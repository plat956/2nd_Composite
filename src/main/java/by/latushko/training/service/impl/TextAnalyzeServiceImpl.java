package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.latushko.training.entity.TextComponentType.*;

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
        return null; //todo 2 Найти предложения с самым длинным словом.
    }

    @Override
    public Map<String, Integer> findRepeatedWordsCount(TextComposite composite) {
        Map<String, Integer> words = new HashMap<>();

        for(TextComponent p: composite.getComponents()) {
            for(TextComponent s: p.getComponents()) {
                for(TextComponent l: s.getComponents()) {
                    if(l.getType() != CHARACTER) {
                        for(TextComponent w: l.getComponents()) {
                            String word = w.toString();
                            words.put(word, words.getOrDefault(word, 0) + 1);
                        }
                    }
                }
            }
        }
        return words;
    }

    @Override
    public void deleteSentencesByWordsCountLessThan(TextComposite composite, int minCount) {
        composite.getComponents().forEach(p -> p.getComponents()
                .removeIf(s -> s.getComponents().stream()
                        .filter(l -> l.getType() != CHARACTER).count() < minCount)
        );
    }

    private int countLetters(TextComposite composite, int paragraphNumber, int sentenceNumber, boolean isVowelsCounting) {
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

        return isVowelsCounting ? vowelCounter : consonantCounter;
    }
}
