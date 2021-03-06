import java.util.Map;

/**
 * Cos class.
 */
public class Cos extends UnaryExpression implements Expression {
    //member
    private Expression expression;
    /**
     * Cos constructor.
     * @param expression - this.expression.
     */
    public Cos(Expression expression) {
        this.expression = expression;
    }

    /**
     * Cos constructor.
     * @param var - string var - this.expression.
     */
    public Cos(String var) {
        this(new Var(var));
    }

    /**
     * Cos constructor.
     * @param num - double num - this.expression.
     */
    public Cos(double num) {
        this(new Num(num));
    }

    /**
     * getExpression function.
     * @return this.expression.
     */
    public Expression getExpression() {
        return this.expression;
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
        return Math.cos(Math.toRadians(this.expression.evaluate(assignment)));

    }
    /**
     * returnThis.
     * @return current class.
     */
    public Expression returnThis() {
        return this;
    }

    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "cos(" + this.expression.toString() + ")";
    }

    /**
     * assign function.
     * @param var - for replace.
     * @param expression1 - new expression to insert instead of var.
     * @return a new expression in which all occurrences of the variable
       var are replaced with the provided expression (Does not modify the
       current expression).
     */

    public Expression assign(String var, Expression expression1) {
        return new Cos(this.expression.assign(var, expression));

    }

    /**
     * differentiate function.
     * @param var - string for differentiate.
     * @return the expression tree resulting from differentiating
       the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Neg(new Mult(new Sin(this.expression), this.expression.differentiate(var)));
    }

    /**
     * getSimplify function.
     * @return a simplified version of the current expression.
     */
    @Override
    public Expression getSimplify() {
        Double temp = null;
        String exe;

        try {
            temp = this.expression.evaluate();
        } catch (Exception ex) {
            exe = ex.toString();
        }
        if (temp != null) {

            return new Num(Math.cos(Math.toRadians(temp)));
        } else {
            return new Cos(this.expression.simplify());
        }
    }
}


