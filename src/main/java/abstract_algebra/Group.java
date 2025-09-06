package abstract_algebra;

import set.*;

import java.util.*;
import java.util.function.BinaryOperator;

public class Group<T> {

    final BinaryOperator<T> groupOperation;
    final SetRoaster<T> groupSet;
    private T identityElement;
    private Map<T, T> inverseMap;
    private boolean isAbelian;

    public Group(SetRoaster<T> groupSet, BinaryOperator<T> operator) throws InvalidGroupException, NullPointerException {
        this.groupOperation = Objects.requireNonNull(operator);
        this.groupSet = Objects.requireNonNull(groupSet);
        validateAndInitialize();
    }

    private void validateAndInitialize() throws InvalidGroupException {
        final BinaryOperator<T> op = this.groupOperation;
        final SetRoaster<T> gSet = this.groupSet;

        // Closure
        SetRoaster<Tuple<T, T>> cross_product = SetRoaster.crossProduct(gSet, gSet);
        for (Tuple<T, T> pair : cross_product.getElements()) {
            T result = op.apply(pair.first, pair.second);
            if (!gSet.getElements().contains(result)) {
                throw new InvalidGroupException(
                        "Closure violated: a*b not in set",
                        String.format("a=%s, b=%s, a*b=%s not in S", pair.first, pair.second, result)
                );
            }
        }

        // Identity element (two-sided)
        T foundIdentity = null;
        outer:
        for (T candidate : gSet.getElements()) {
            for (T a : gSet.getElements()) {
                T right = op.apply(a, candidate);
                T left = op.apply(candidate, a);
                if (!Objects.equals(right, a) || !Objects.equals(left, a)) {
                    continue outer;
                }
            }
            foundIdentity = candidate;
            break;
        }
        if (foundIdentity == null) {
            throw new InvalidGroupException(
                    "No identity element found",
                    "No e in S satisfies a*e=a and e*a=a for all a in S"
            );
        }

        // Inverses
        Map<T, T> inverses = new HashMap<>();
        for (T a : gSet.getElements()) {
            T inv = null;
            for (T b : gSet.getElements()) {
                if (Objects.equals(op.apply(a, b), foundIdentity) && Objects.equals(op.apply(b, a), foundIdentity)) {
                    inv = b;
                    break;
                }
            }
            if (inv == null) {
                throw new InvalidGroupException(
                        "Missing inverse",
                        String.format("No b in S such that a*b=e and b*a=e for a=%s, e=%s", a, foundIdentity)
                );
            }
            inverses.put(a, inv);
        }

        // Associativity
        SetRoaster<List<T>> triplets = gSet.getNCombination(3);
        for (List<T> triplet : triplets.getElements()) {
            T a = triplet.get(0);
            T b = triplet.get(1);
            T c = triplet.get(2);
            T leftGrouping = op.apply(op.apply(a, b), c);
            T rightGrouping = op.apply(a, op.apply(b, c));
            if (!Objects.equals(leftGrouping, rightGrouping)) {
                throw new InvalidGroupException(
                        "Associativity violated",
                        String.format("(a*b)*c != a*(b*c) for a=%s, b=%s, c=%s; left=%s, right=%s",
                                a, b, c, leftGrouping, rightGrouping)
                );
            }
        }

        // Set derived properties on success
        this.identityElement = foundIdentity;
        this.inverseMap = inverses;
    }

    private void isCommutative() {

    }

    public T getIdentityElement() {
        return identityElement;
    }

    public T getInverse(T element) {
        if (inverseMap == null) return null;
        return inverseMap.get(element);
    }

    public boolean isAbelian() {
        return this.isAbelian;
    }

    public void printCayleyTable() {
        HashSet<T> elements = groupSet.getElements();

        // Print header row
        System.out.print("   | ");
        for (T col : elements) {
            System.out.print(col + "  ");
        }
        System.out.println();
        System.out.println("---+" + "---".repeat(elements.size()));

        // Print table body
        for (T row : elements) {
            System.out.print(row + "  | ");
            for (T col : elements) {
                T result = groupOperation.apply(row, col);
                System.out.print(result + "  ");
            }
            System.out.println();
        }
    }

}
