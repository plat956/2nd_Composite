package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComponent;
import by.latushko.training.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextAnalyzeService {
    TextComponent findSentenceWithTheLongestWord(TextComposite composite);

    Map<String, Integer> findRepeatedWordsCount(TextComposite composite);

    void sortByParagraphSentencesCount(TextComposite composite);

    int countConsonants(TextComposite composite, int paragraphNumber, int sentenceNumber);

    int countVowels(TextComposite composite, int paragraphNumber, int sentenceNumber);

    void deleteSentencesByWordsCountLessThan(TextComposite composite, int minCount);
}
