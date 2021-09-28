package ku.cs.service.customer;

public interface CustomerDataSource<T> {
    T readData();
    void writeData(T t);
}
