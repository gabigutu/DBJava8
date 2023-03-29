package java8;

@FunctionalInterface
public interface MyConsumer<T> {
    void accept(T t);
}
