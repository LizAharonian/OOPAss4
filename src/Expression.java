import java.util.List;
import java.util.Map;

/**
 * Expression interface.
 * extends ExpressionCommonFunc.
 */

    public interface Expression extends ExpressionCommonFunc {
    /**
     * evaluate function.
     * Evaluate the expression using the variables' values provided
       in the assignment, and return the result.
     * @param assignment - Map of strings and Doubles.
     * @return double number equals the expression based on assignments' variables.
     * @throws Exception - in case the expression
       contains a variable which is not in the assignment.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * evaluate function.
     * A convenience method. Like the `evaluate(assignment)` method,
       but uses an empty assignment.
     * @return evaluate the expression.
     * @throws Exception in case expression has variables.
     */
    double evaluate() throws Exception;
    /**
     * getVariables function.
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();
    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * assign function.
     * @param var - for replace.
     * @param expression - new expression to insert instead of var.
     * @return a new expression in which all occurrences of the variable
    var are replaced with the provided expression (Does not modify the
    current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     * differentiate function.
     * @param var - string for differentiate.
     * @return the expression tree resulting from differentiating
    the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * simplify function.
     * @return - a simplified version of the current expression.
     */
    Expression simplify();




}

