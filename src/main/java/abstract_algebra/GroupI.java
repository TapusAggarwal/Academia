package abstract_algebra;

import set.SetRoaster;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class GroupI<S> extends SetRoaster<S> {

    final BinaryOperator<S> groupOperation;
    private S identityElement;
    private Map<S, S> inverseMap;
    private boolean isAbelian;


    GroupI(BinaryOperator<S> operator) throws Exception {
        super();
        this.groupOperation = operator;
        this.identityElement = Objects.requireNonNull(this.hasIdentity(operator));
        this.inverseMap = Objects.requireNonNull(this.hasInverse(operator));
        if (!this.isAssociative(operator)) throw new Exception("Not Associative!");
    }
}
