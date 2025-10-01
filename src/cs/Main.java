package cs;

import cs.sequences.DynamicArray;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DynamicArray<Integer> x = new DynamicArray<>();

        x.insertBack(2);
        x.insertBack(3);
        x.insertBack(4);
        x.insertBack(5);
        x.insertFront(1);

        x.popFront();
        x.popFront();
        x.popBack();
        x.printArray();

    }

}
