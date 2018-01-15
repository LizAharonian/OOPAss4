import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * BaseExpression abstract class.
 */
public abstract class BaseExpression {
    /**
     * evaluate function.
     * A convenience method. Like the `evaluate(assignment)` method,
       but uses an empty assignment.
     * @return evaluate the expression.
     * @throws Exception in case expression has variables.
     */
    public double evaluate() throws Exception {
        if (this.getVariables().isEmpty()) {
            Map<String, Double> assignment = new TreeMap<String, Double>();
            return this.evaluate(assignment);
        } else {
            throw new Exception("Expression has variables, use correct func for evaluate!");
        }
    }

    /**
     * getDistinctList function.
     * @param expressionVars - list of strings.
     * @param variables - list of strings to fill.
     */
    public void getDistinctList(List<String> expressionVars, List<String> variables) {
        if (expressionVars != null) {
            for (String item: expressionVars) {
                if (!variables.contains(item)) {
                    variables.add(item);
                }
            }

        }
    }

    /**
     * simplify function.
     * @return - a simplified version of the current expression.
     */
    public Expression simplify() {
        Expression e1 = returnThis();
        Expression e2 = returnThis().getSimplify();
        while (!e1.toString().equals(e2.toString())) {
            e1 = e2;
            e2 = e2.getSimplify();
        }
        return e2;
    }

    /**
     * isCommutative function.
     * @return true if commutative, else otherwise.
     */
    public Boolean isCommutative() {
        return false;
    }

    /**
     * swap function.
     * @return swap version of the expression.
     */
    public Expression swap() {
        return null;
    }
    /**
     * returnThis.
     * @return current class.
     */
    public abstract Expression returnThis();
    /**
     * getVariables function.
     * @return a list of the variables in the expression.
     */
    public abstract List<String> getVariables();

    /**
     * evaluate function.
     * Evaluate the expression using the variables' values provided
     in the assignment, and return the result.
     * @param assignment - Map of strings and Doubles.
     * @return double number equals the expression based on assignments' variables.
     * @throws Exception - in case the expression
       contains a variable which is not in the assignment.
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;



}
