package repository;

import org.bson.types.ObjectId;

public interface IRepository <T> {
    T get(ObjectId id);
    boolean add(T item);
    void update(T item);
    T remove(ObjectId id);
    boolean setConnection();
}
