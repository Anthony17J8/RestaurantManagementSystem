package ru.icoltd.rvs.dao;

import java.io.Serializable;
import java.util.Optional;

public interface GenericDAO<T, ID extends Serializable> {

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    T makePersistent(T entity);

    void remove(T entity);
}
