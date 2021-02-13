package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.Word;
import ru.itis.javalab.repositories.WordRepository;

import java.util.List;

@Service
public class TextServiceImpl implements TextService {


    private final WordRepository wordRepository;
    @Autowired
    public TextServiceImpl(WordRepository wordRepository){
        this.wordRepository=wordRepository;
    }

    @Override
    public void saveWord(Word word) {
        wordRepository.save(word);
    }

    @Override
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public void testMethod(){
        System.out.println("test");
    }
}
