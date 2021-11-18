package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComposite;
import by.latushko.training.exception.TextAnalyzingException;

public interface TextAnalyzeService {
    void sortByParagraphSentencesCount(TextComposite composite) throws TextAnalyzingException;

    int calculateConsonants(TextComposite composite, int paragraphNumber, int sentenceNumber);

    int calculateVowels(TextComposite composite, int paragraphNumber, int sentenceNumber);
}
