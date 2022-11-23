package repository;

import org.bson.types.ObjectId;

public abstract class RepositoryDecorator<T> implements IRepository<T>{
    protected IRepository<T> repository;
}
