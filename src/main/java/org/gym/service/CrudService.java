package org.gym.service;

import java.util.List;

public interface CrudService<T, Dto, I> {
    List<Dto> getAll();

    Dto getById(I id);

    void save(T t);

    void update(I id, Dto d);

    void deleteById(I id);
}
