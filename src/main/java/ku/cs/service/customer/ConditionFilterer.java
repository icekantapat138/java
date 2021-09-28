package ku.cs.service.customer;

public interface ConditionFilterer<T> {

    boolean check(T t);
}
