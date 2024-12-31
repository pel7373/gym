package org.gym.dao;

import org.gym.exception.EntityNotFoundException;

import java.util.List;

public interface CrudDAO<T, I> {
    List<T> findAll();

    T findById(I id) throws EntityNotFoundException;

    T save(T t);

    void update(I id, T t);

    void deleteById(I id);
}