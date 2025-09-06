package set;

import java.util.Objects;

public class Tuple<X, Y> {
    public final X first;
    public final Y second;

    public Tuple(X first, Y second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple<?, ?>)) {
            return false;
        }
        return (((Tuple<?, ?>) obj).first == this.first) && (((Tuple<?, ?>) obj).second == this.second);
    }
}
