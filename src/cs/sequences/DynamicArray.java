package cs.sequences;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Sequence<T> {

    private Object[] staticArray = new Object[2];
    private int lastIndex = -1;


    @Override
    public void build(Iterable<T> X) {
        T currentElement = null;
        while (X.iterator().hasNext()) {
            currentElement = X.iterator().next();
            this.insertBack(currentElement);
        }
    } // O(n)

    @Override
    public int len() {
        return this.lastIndex + 1;
    }


    @Override
    public T getAt(int index) {
        validateIndex(index);
        return (T) this.staticArray[index];
    }

    @Override
    public T setAt(int index, T value) {
        validateIndex(index);
        T oldValue = (T) this.staticArray[index];
        this.staticArray[index] = value;
        return oldValue;
    }

    @Override
    public T insertBack(T value) {
        return this.insertAtIndex(this.lastIndex + 1, value);
    }

    @Override
    public T insertFront(T value) {
        return this.insertAtIndex(0, value);
    }

    @Override
    public T popBack() {
        return this.popAtIndex(this.lastIndex);
    }

    @Override
    public T popFront() {
        return this.popAtIndex(0);
    }

    @Override
    public T insertAtIndex(int index, T value) {
        if (lastIndex + 1 >= this.staticArray.length) {
            this.resize(2);
        }

        T prevElem = (T) staticArray[index];
        staticArray[index] = value;
        for (int i = index + 1; i <= lastIndex + 1; i++) {
            T currElem = (T) staticArray[i];
            staticArray[i] = prevElem;
            prevElem = currElem;
        }

        lastIndex++;
        return value;
    }

    @Override
    public T popAtIndex(int index) {
        validateIndex(index);
        T returnElement = (T) staticArray[index];
        this.staticArray[index] = null;

        for (int i = index; i <= lastIndex - 1; i++) {
            this.staticArray[i] = this.staticArray[i + 1];
        }
        lastIndex--;

        if (lastIndex + 1 < staticArray.length / 2) {
            this.resize(0.5f);
        }
        return returnElement;
    }

    private void validateIndex(int index) {
        if (0 > index || index > this.lastIndex)
            throw new IndexOutOfBoundsException("Index passed is more than the Array size.");
    }

    private void resize(float factor) {
        // create a new array or change the index
        // but when size drops by a threshold create array with reduced size?

        // [ 1 2 3 4 5 6 7 8 9 10]
        // [ 1 2 3 4 _ _ _ _ _ _ ]

        this.staticArray = Arrays.copyOf(staticArray, (int) (staticArray.length * factor));
    }

    public void printArray() {
        StringBuilder outputString = new StringBuilder();
        outputString.append("[");
        for (int i = 0; i <= lastIndex; i++) {
            Object elem = staticArray[i];
            outputString.append(elem).append(", ");
        }
        if (len() > 0) outputString = new StringBuilder(outputString.substring(0, outputString.length() - 2));
        outputString.append("]");
        System.out.println(outputString);
    }
}
