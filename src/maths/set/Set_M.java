package maths.set;

import java.util.HashSet;

abstract public class Set_M<T> {

    HashSet<T> elements;

    protected Set_M() {
        this.elements = new HashSet<>();
    }

    public HashSet<T> getElements() {
        return elements;
    }

}