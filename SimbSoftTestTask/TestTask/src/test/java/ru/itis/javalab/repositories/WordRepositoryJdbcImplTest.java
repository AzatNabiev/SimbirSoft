package ru.itis.javalab.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.configuration.TestApplicationConfig;
import ru.itis.javalab.models.Word;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordRepositoryJdbcImplTest {

    WordRepositoryJdbcImpl wordRepository;

    private static final int WORDS_COUNT=4;
    private static final int WORD_COUNT_AFTER_UPDATE=2;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        wordRepository = new WordRepositoryJdbcImpl(context.getBean(DataSource.class));
    }

    @Test
    void update() {
        Word wordBeforeUpdate = wordRepository.findById(4).get();
        wordRepository.update(wordBeforeUpdate);
        Word wordAfterUpdate = wordRepository.findById(4).get();
        assertEquals(WORD_COUNT_AFTER_UPDATE,wordAfterUpdate.getCount());
    }

    @Test
    void save() {
        Word word= Word.builder()
                .value("TEST")
                .build();
        wordRepository.save(word);
        assertEquals(5,word.getId());
    }


    @Test
    void findAll() {
        List<Word> words=wordRepository.findAll();
        assertEquals(WORDS_COUNT,words.size());
    }
}