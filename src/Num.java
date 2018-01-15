import java.util.List;
import java.util.Map;

/**
 * Num class.
 */
public class Num implements Expression {
    //member
    private double num;

    /**
     * Num constructor.
     * @param num - this.num.
     */
    public Num(double num) {
        this.num = num;
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
        return this.num;

    }

    /**
     * evaluate function.
     * A convenience method. Like the `evaluate(assignment)` method,
     but uses an empty assignment.
     * @return evaluate the expression.
     * @throws Exception in case expression has variables.
     */
    public double evaluate() throws Exception {
        return this.num;

    }

    /**
     * getVariables function.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return null;

    }

    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return String.valueOf(this.num);

    }
    /**
     * assign function.
     * @param var - for replace.
     * @param expression - new expression to insert instead of var.
     * @return a new expression in which all occurrences of the variable
    var are replaced with the provided expression (Does not modify the
    current expression).
     */
    public Expression assign(String var, Expression expression) {
        //nothing to assign
        return new Num(this.num);

    }
    /**
     * differentiate function.
     * @param var - string for differentiate.
     * @return the expression tree resulting from differentiating
       the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Num(0);
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




}
