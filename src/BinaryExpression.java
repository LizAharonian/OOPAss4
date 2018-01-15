import java.util.ArrayList;
import java.util.List;

/**
 * BinaryExpression class.
 */
public abstract class BinaryExpression extends BaseExpression  {

    /**
     * getVariables function.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {

        List<String> variables = new ArrayList<String>();
        List leftVar = this.getLeftExpression().getVariables();
        List rightVar = this.getRightExpression().getVariables();
        this.getDistinctList(leftVar, variables);
        this.getDistinctList(rightVar, variables);
        return variables;
    }

    /**
     * differentiate function.
     * @param var - string for differentiate.
     * @return the expression tree resulting from differentiating
       the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        Expression leftExp = this.getLeftExpression().differentiate(var);
        Expression rightExp = this.getRightExpression().differentiate(var);
        return findDifferentiate(leftExp, rightExp, var);

    }

    /**
     * findDifferentiate function.
     * @param leftExp - differentiate of left expression.
     * @param rightExp - differentiate of right expression.
     * @param var - - string for differentiate.
     * @return differentiate expression of two expressions.
     */
    public abstract Expression findDifferentiate(Expression leftExp, Expression rightExp, String var);

    /**
     * getLeftExpression function.
     * @return this.leftExpression.
     */
    public abstract Expression getLeftExpression();
    /**
     * getRightExpression function.
     * @return this.rightExpression.
     */
    public abstract Expression getRightExpression();


}
