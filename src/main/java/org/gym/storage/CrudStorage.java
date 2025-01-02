package org.gym.storage;

import org.gym.exception.EntityNotFoundException;

import java.util.List;

public interface CrudStorage<T, I> {
    List<T> findAll();

    T findById(I id) throws EntityNotFoundException;

    void save(T t);

    void update(I id, T t);

    void deleteById(I id);
}