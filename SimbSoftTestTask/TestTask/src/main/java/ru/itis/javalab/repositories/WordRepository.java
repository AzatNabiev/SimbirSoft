package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends CrudRepository<Word> {

    List<Word> findAll();
    Optional<Word> findById(Integer id);
}
