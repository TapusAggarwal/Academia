package cs.sequences;

public interface Sequence<T> {

    // container
    void build(Iterable<T> X);

    int len();

    // static

    T getAt(int index);

    T setAt(int index, T value);

    // dynamic
    T insertBack(T value);

    T insertFront(T value);

    T insertAtIndex(int i, T value);

    T popBack();

    T popFront();

    T popAtIndex(int i);
}
