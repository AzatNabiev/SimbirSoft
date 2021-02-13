package ru.itis.javalab.services;

import ru.itis.javalab.models.Word;

import java.util.List;

public interface TextService {
    void saveWord(Word word);
    List<Word> getAllWords();
}
