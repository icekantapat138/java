package ku.cs.service.account;

public interface AccountDataSource<T> {
    T readData();
    void writeData(T t);
}
