package maths.set;

import java.util.function.Function;

public class SetBuilder<T> extends Set_M<T> {

    private final Function<T, Boolean> containsMethod;

    SetBuilder(Function<T, Boolean> containsMethod) {
        super();
        this.containsMethod = containsMethod;
    }

    boolean contains(T t) {
        return this.containsMethod.apply(t);
    }
}