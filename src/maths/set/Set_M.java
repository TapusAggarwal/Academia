package set;

import abstract_algebra.InvalidGroupException;

import java.util.HashSet;
import java.util.function.BinaryOperator;

abstract public class Set_M<T> {

    HashSet<T> elements;

    protected Set_M() {
        this.elements = new HashSet<>();
    }

    public HashSet<T> getElements() {
        return elements;
    }

}