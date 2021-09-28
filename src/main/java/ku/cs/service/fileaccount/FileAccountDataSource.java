package ku.cs.service.fileaccount;

public interface FileAccountDataSource<T> {
    T readData();
    void writeData(T t);
}
