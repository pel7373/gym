package org.gym.service;

import java.util.List;

public interface CrudService<T, I> {
    List<T> getAll();

    T getById(I id);

    void save(T t);

    void update(I id, T t);

    void deleteById(I id);
}
