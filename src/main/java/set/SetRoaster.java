package set;

import abstract_algebra.InvalidGroupException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;

public class SetRoaster<T> extends Set_M<T> {

    public SetRoaster() {
        super();
    }

    public SetRoaster(HashSet<T> elems) {
        super();
        this.elements = elems;
    }

    SetRoaster(Set_M<T> elems) {
        super();
        this.elements = elems.getElements();
    }

    public boolean add(T t) {
        return this.elements.add(t);
    }

    public static <X, Y> SetRoaster<Tuple<X, Y>> crossProduct(SetRoaster<X> setA, SetRoaster<Y> setB) {
        SetRoaster<Tuple<X, Y>> outputSet = new SetRoaster<>();
        for (X a : setA.getElements()) {
            for (Y b : setB.getElements()) {
                outputSet.add(new Tuple<>(a, b));
            }
        }
        return outputSet;
    }

    public SetRoaster<List<T>> getNCombination(int n) {
        SetRoaster<List<T>> outputSet = new SetRoaster<>();
        if (n == 1) {
            for (T elem : this.getElements()) {
                outputSet.add(List.of(elem));
            }
            return outputSet;
        }

        SetRoaster<List<T>> prev_comb = this.getNCombination(n - 1);

        for (T elem : this.getElements()) {
            for (List<T> combs : prev_comb.getElements()) {
                List<T> _comb = new ArrayList<>(combs);
                _comb.add(elem);
                outputSet.add(_comb);
            }
        }

        return outputSet;
    }

    public T getFirst() throws IndexOutOfBoundsException {
        return Objects.requireNonNull(this.getElements().stream().findFirst().orElse(null));
    }

    public boolean isClosed(BinaryOperator<T> operator) {
        SetRoaster<Tuple<T, T>> cross_product = SetRoaster.crossProduct(this, this);
        for (Tuple<T, T> pair : cross_product.getElements()) {
            T result = operator.apply(pair.first, pair.second);
            if (!this.getElements().contains(result)) {
                return false;
            }
        }
        return true;
    }

    public T hasIdentity(BinaryOperator<T> operator) {
        outer:
        for (T candidate : this.getElements()) {
            for (T a : this.getElements()) {
                T right = operator.apply(a, candidate);
                T left = operator.apply(candidate, a);
                if (!Objects.equals(right, a) || !Objects.equals(left, a)) {
                    continue outer;
                }
            }
            return candidate;
        }
        return null;
    }

    public Map<T, T> hasInverse(BinaryOperator<T> operator) {
        T foundIdentity = this.hasIdentity(operator);
        if (foundIdentity == null) return null;

        Map<T, T> inverses = new HashMap<>();
        for (T a : this.getElements()) {
            T inv = null;
            for (T b : this.getElements()) {
                if (Objects.equals(operator.apply(a, b), foundIdentity) && Objects.equals(operator.apply(b, a), foundIdentity)) {
                    inv = b;
                    break;
                }
            }
            if (inv == null) {
                return null;
            }
            inverses.put(a, inv);
        }
        return inverses;
    }

    public boolean isAssociative(BinaryOperator<T> operator) {
        SetRoaster<List<T>> triplets = this.getNCombination(3);
        for (List<T> triplet : triplets.getElements()) {
            T a = triplet.get(0);
            T b = triplet.get(1);
            T c = triplet.get(2);
            T leftGrouping = operator.apply(operator.apply(a, b), c);
            T rightGrouping = operator.apply(a, operator.apply(b, c));
            if (!Objects.equals(leftGrouping, rightGrouping)) {
                return false;
            }
        }
        return true;
    }

}
