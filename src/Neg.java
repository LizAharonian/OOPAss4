import java.util.Map;

/**
 * Neg class.
 */
public class Neg extends UnaryExpression implements Expression {
    //member
    private Expression expression;
    /**
     * Neg constructor.
     * @param expression - this.expression.
     */
    public Neg(Expression expression) {
        this.expression = expression;
    }
    /**
     * Neg constructor.
     * @param var - this.expression.
     */
    public Neg(String var) {
        this(new Var(var));
    }
    /**
     * Neg constructor.
     * @param num - this.expression.
     */
    public Neg(double num) {
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
        return -(this.expression.evaluate(assignment));

    }

    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "-" + this.expression.toString();

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
        return new Neg(this.expression.assign(var, expression));

    }

    /**
     * differentiate function.
     * @param var - string for differentiate.
     * @return the expression tree resulting from differentiating
    the current expression relative to variable `var`.
     */
    public Expression differentiate(String var) {
        return new Neg(this.expression.differentiate(var));
    }
    /**
     * getSimplify function.
     * @return - a simplified version of the current expression.
     */
    @Override
    public Expression getSimplify() {
        //for bonus
        if (this.expression instanceof Neg) {
            return ((Neg) this.expression).getExpression();
        }
        Double temp = null;
        String exe;

        try {
            temp = this.expression.evaluate();
        } catch (Exception ex) {
            exe = ex.toString();
        }
        if (temp != null) {

            return new Num(-temp);
        } else {
            return new Neg(this.expression.simplify());
        }

    }
    /**
     * returnThis.
     * @return current class.
     */
    public Expression returnThis() {
        return this;
    }

}


