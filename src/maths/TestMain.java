package maths;

import maths.abstract_algebra.Group;
import maths.set.SetRoaster;

import java.util.HashSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestMain {

    public static void main(String[] args) {

        int mod_num = 4;


        SetRoaster<Integer> groupSet = new SetRoaster<>(new HashSet<>(
                IntStream.range(0, mod_num).boxed().collect(Collectors.toList())
        ));

        BinaryOperator<Integer> operator = (a, b) -> (a + b) % mod_num;

        Group<Integer> Z_5 = new Group<>(groupSet, operator);

        Z_5.printCayleyTable();

    }

}
