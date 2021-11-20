package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static by.latushko.training.entity.TextComponentType.*;

public class TextAnalyzeServiceImpl implements TextAnalyzeService{
    private static final Logger logger = LogManager.getLogger();
    private static final String VOWEL_PATTERN = "[aeiouауоыиэяюёе]+";

    @Override
    public void sortByParagraphSentencesCount(TextComposite composite) {
        composite.getComponents().sort(Comparator.comparingInt(c -> c.getComponents().size()));

        logger.info("TextComposite {} has been sorted by sentences count", composite.hashCode());
    }

    @Override
    public int countConsonants(TextComposite composite, int paragraphNumber, int sentenceNumber) {
        int count = countLetters(composite, paragraphNumber, sentenceNumber, false);

        logger.info("TextComposite {}: Sentence number {} in paragraph {} contains {} consonants",
                composite.hashCode(), sentenceNumber, paragraphNumber, count);

        return count;
    }

    @Override
    public int countVowels(TextComposite composite, int paragraphNumber, int sentenceNumber) {
        int count = countLetters(composite, paragraphNumber, sentenceNumber, true);

        logger.info("TextComposite {}: Sentence number {} in paragraph {} contains {} vowels",
                composite.hashCode(), sentenceNumber, paragraphNumber, count);

        return count;
    }

    @Override
    public TextComponent findSentenceWithTheLongestWord(TextComposite composite) {
        Map<TextComponent, Long> sentenceMap = new HashMap<>();

        for(TextComponent p: composite.getComponents()) {
            for(TextComponent s: p.getComponents()) {
                for(TextComponent l: s.getComponents()) {
                    if(l.getType() != PUNCTUATION) {
                        for(TextComponent w: l.getComponents()) {
                            long wordLength = w.getComponents().stream()
                                    .filter(c -> c.getType() == LETTER)
                                    .count();
                            sentenceMap.put(s, wordLength);
                        }
                    }
                }
            }
        }

        Map.Entry<TextComponent, Long> longestWordEntry = sentenceMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get();

        logger.info("TextComposite {}: The sentence containing the longest word is: \"{}\"",
                composite.hashCode(), longestWordEntry.getKey());

        return longestWordEntry.getKey();
    }

    @Override
    public Map<String, Integer> findRepeatedWordsWithCount(TextComposite composite) {
        Map<String, Integer> words = new HashMap<>();

        for(TextComponent p: composite.getComponents()) {
            for(TextComponent s: p.getComponents()) {
                for(TextComponent l: s.getComponents()) {
                    if(l.getType() != PUNCTUATION) {
                        for(TextComponent w: l.getComponents()) {
                            String word = w.getComponents().stream()
                                    .filter(c -> c.getType() == LETTER)
                                    .map(TextComponent::toString)
                                    .collect(Collectors.joining());

                            words.put(word, words.getOrDefault(word, 0) + 1);
                        }
                    }
                }
            }
        }

        logger.info("TextComposite {}: Repeated words and their frequencies: \"{}\"",
                composite.hashCode(),
                words.keySet().stream()
                        .map(key -> key + "=" + words.get(key))
                        .collect(Collectors.joining(", ", "{", "}")));

        return words;
    }

    @Override
    public void deleteSentencesByWordsCountLessThan(TextComposite composite, int minCount) {
        composite.getComponents().forEach(p -> p.getComponents()
                .removeIf(s -> s.getComponents().stream()
                        .filter(l -> l.getType() != PUNCTUATION).count() < minCount)
        );

        logger.info("TextComposite {}: Sentences consisting less than {} words have been removed", composite.hashCode(), minCount);
    }

    private int countLetters(TextComposite composite, int paragraphNumber, int sentenceNumber, boolean isVowelsCounting) {
        TextComponent paragraph = composite.getComponents().get(paragraphNumber - 1);
        TextComponent sentence = paragraph.getComponents().get(sentenceNumber - 1);

        int vowelCounter = 0;
        int consonantCounter = 0;
        for(TextComponent lexeme: sentence.getComponents()) {
            if(lexeme.getType() != PUNCTUATION) {
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
