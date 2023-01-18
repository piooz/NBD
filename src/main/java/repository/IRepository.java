package repository;

import org.bson.types.ObjectId;

import java.util.List;

public interface IRepository <T> {

    T get(Object element);

    void add(T elements);

    void remove(T  elements);

    void update(T elements);

    List<T> find(Object elements);

    List<T> getAll();

}
