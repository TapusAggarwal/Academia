package maths.linear_algebra;

import maths.abstract_algebra.Field;
import maths.set.SetRoaster;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class VectorSpace<F, V> {

    private final BinaryOperator<V> vectorAddition;
    private final BiFunction<F, V, V> scalarMultiplication;
    private final Field<F> field;
    private final SetRoaster<V> vectorSet;

    public VectorSpace(BinaryOperator<V> vectorAddition, BiFunction<F, V, V> scalarMultiplication, Field<F> field, SetRoaster<V> vectorSet) {
        this.vectorAddition = vectorAddition;
        this.scalarMultiplication = scalarMultiplication;
        this.field = field;
        this.vectorSet = vectorSet;
    }

//    void validate

}
