package ru.itis.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Word;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class WordRepositoryJdbcImpl implements WordRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public WordRepositoryJdbcImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }
    //language=SQL
    private static final String SQL_FIND_WORD="SELECT * FROM words WHERE word=?";
    //language=SQL
    private static final String SQL_UPDATE_WORD="UPDATE words SET word_count=? WHERE id=?";
    //language=SQL
    private static final String SQL_FIND_ALL_WORDS="SELECT * FROM words ORDER BY word_count DESC";
    //language=SQL
    private static final String SQL_FIND_BY_ID="SELECT * FROM words WHERE id=?";

    private RowMapper<Word> rowMapper=(row, i) -> Word.builder()
            .id(row.getInt("id"))
            .value(row.getString("word"))
            .count(row.getInt("word_count"))
            .build();

    @Override
    public Optional<Word> findById(Integer id) {
        Word word;
        try {
            word=jdbcTemplate.queryForObject(SQL_FIND_BY_ID,rowMapper,id);
        } catch (EmptyResultDataAccessException e){
            word=null;
        }
        return Optional.ofNullable(word);
    }

    @Override
    public void save(Word entity) {
            Word word;
            try {
                word=jdbcTemplate.queryForObject(SQL_FIND_WORD,rowMapper,entity.getValue());
                update(word);
            } catch (EmptyResultDataAccessException e){
                Integer wordId=saveWord(entity);
                entity.setId(wordId);
            }
    }
    private Integer saveWord(Word entity){
        if (!simpleJdbcInsert.isCompiled()){
            simpleJdbcInsert.withTableName("words").usingGeneratedKeyColumns("id")
                    .usingColumns("word","word_count");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("word",entity.getValue());
        map.put("word_count",1);
        return Integer.parseInt(simpleJdbcInsert.executeAndReturnKey(map).toString());
    }

    @Override
    public void update(Word entity) {
        jdbcTemplate.update(SQL_UPDATE_WORD, entity.getCount()+1,entity.getId());
    }

    @Override
    public void delete(Word entity) {

    }

    @Override
    public List<Word> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_WORDS, rowMapper);
    }
}
