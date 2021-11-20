package by.latushko.training.service;

import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextAnalyzeService {
    TextComponent findSentenceWithTheLongestWord(TextComposite composite);

    Map<String, Integer> findRepeatedWordsWithCount(TextComposite composite);

    void sortByParagraphSentencesCount(TextComposite composite);

    int countConsonants(TextComposite composite, int paragraphNumber, int sentenceNumber);

    int countVowels(TextComposite composite, int paragraphNumber, int sentenceNumber);

    void deleteSentencesByWordsCountLessThan(TextComposite composite, int minCount);
}
