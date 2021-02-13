package ru.itis.javalab.repositories;

public interface CrudRepository<T> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);
}
