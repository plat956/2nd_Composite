package by.latushko.training.service.impl;

import by.latushko.training.entity.TextComposite;

public interface TextAnalyzeService {
    void sortByParagraphSentencesCount(TextComposite composite);

    int countConsonants(TextComposite composite, int paragraphNumber, int sentenceNumber);

    int countVowels(TextComposite composite, int paragraphNumber, int sentenceNumber);


}
