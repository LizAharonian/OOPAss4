import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Var class.
 */
public class Var implements Expression {
//member
    private String var;

    /**
     * Var constructor.
     * @param var - this.var.
     */
    public Var(String var) {
        this.var = var;
    }
    /**
     * evaluate function.
     * Evaluate the expression using the variables' values provided
     in the assignment, and return the result.
     * @param assignment - Map of strings and Doubles.
     * @return double number equals the expression based on assignments' variables.
     * @throws Exception - in case the expression
    contains a variable which is not in the assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Double valueOfVar = null;
        for (Map.Entry<String, Double> item : assignment.entrySet()) {
            if (item.getKey().equals(this.var)) {
                valueOfVar = item.getValue();
            }
        }
        if (valueOfVar == null) {
            throw new Exception("The expression (Var) contains a variable which is not in the assignment!");
        } else {
            return valueOfVar;
        }

    }

    /**
     * evaluate function.
     * A convenience method. Like the `evaluate(assignment)` method,
     but uses an empty assignment.
     * @return evaluate the expression.
     * @throws Exception in case expression has variables.
     */
    public double evaluate() throws Exception {
        throw new Exception("Expression has variables, use correct func for evaluate!");
    }

    /**
     * getVariables function.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        variables.add(this.var);
        return variables;

    }

    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return this.var;

    }

    /**
     * assign function.
     * @param var1 - for replace.
     * @param expression - new expression to insert instead of var.
     * @return a new expression in which all occurrences of the variable
    var are replaced with the provided expression (Does not modify the
    current expression).
     */
    public Expression assign(String var1, Expression expression) {
        if (this.var.equals(var1)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * differentiate function.
     * @param var1 - string for differentiate.
     * @return the expression tree resulting from differentiating
    the current expression relative to variable `var`.
     */
    public Expression differentiate(String var1) {
        if (this.var.equals(var1)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * simplify function.
     * @return - a simplified version of the current expression.
     */
    public Expression simplify() {
        return this;
    }
    /**
     * getSimplify function.
     * @return - a simplified version of the current expression.
     */
    public Expression getSimplify() {
        return this;
    }
    /**
     * returnThis.
     * @return current class.
     */
    public Expression returnThis() {
        return this;
    }
    /**
     * isCommutative function.
     * @return true if commutative, else otherwise.
     */
    @Override
    public Boolean isCommutative() {
        return false;
    }
    /**
     * swap function.
     * @return swap version of the expression.
     */
    @Override
    public Expression swap() {
        return null;
    }




}
