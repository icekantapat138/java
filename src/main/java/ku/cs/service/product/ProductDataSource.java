package ku.cs.service.product;

public interface ProductDataSource<T> {
    T readData();
    void writeData(T t);
}
