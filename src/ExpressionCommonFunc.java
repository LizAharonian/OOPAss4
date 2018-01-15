/**
 * ExpressionCommonFunc interface.
 */
public interface ExpressionCommonFunc {
    /**
     * getSimplify function.
     * @return - a simplified version of the current expression.
     */
    Expression getSimplify();

    /**
     * isCommutative function.
     * @return true if commutative, else otherwise.
     */
    Boolean isCommutative();
    /**
     * swap function.
     * @return swap version of the expression.
     */
    Expression swap();



}
