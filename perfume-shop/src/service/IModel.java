package service;

public interface IModel<T> {
    T parseData(String line);
}
