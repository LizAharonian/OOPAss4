import java.util.Map;

/**
 * Mult class.
 */

public class Mult extends BinaryExpression implements Expression {
    //members
    private Expression leftExpression;
    private Expression rightExpression;
    /**
     * Mult constructor.
     * @param leftExpression - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Mult(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    /**
     * Mult constructor.
     * @param leftExpression - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Mult(Expression leftExpression, String var) {
        this(leftExpression, new Var(var));
    }
    /**
     * Mult constructor.
     * @param leftExpression - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Mult(Expression leftExpression, double num) {
        this(leftExpression, new Num(num));
    }
    /**
     * Mult constructor.
     * @param var - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Mult(String var, Expression rightExpression) {
        this(new Var(var), rightExpression);
    }
    /**
     * Mult constructor.
     * @param var1 - this.leftExpression.
     * @param var2 - this.rightExpression.
     */
    public Mult(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }
    /**
     * Mult constructor.
     * @param var - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Mult(String var, double num) {
        this(new Var(var), new Num(num));
    }
    /**
     * Mult constructor.
     * @param num - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Mult(double num, Expression rightExpression) {
        this(new Num(num), rightExpression);
    }
    /**
     * Mult constructor.
     * @param num - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Mult(double num, String var) {
        this(new Num(num), new Var(var));
    }
    /**
     * Mult constructor.
     * @param num1 - this.leftExpression.
     * @param num2 - this.rightExpression.
     */
    public Mult(double num1, double num2) {
        this(new Num(num1), new Num(num2));
    }

    /**
     * getLeftExpression function.
     * @return this.leftExpression.
     */
    @Override
    public Expression getLeftExpression() {
        return this.leftExpression;
    }

    /**
     * getRightExpression function.
     * @return this.rightExpression.
     */
    @Override
    public Expression getRightExpression() {
        return this.rightExpression;
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
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        return leftExpression.evaluate(assignment) * rightExpression.evaluate(assignment);

    }
    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + leftExpression.toString() + " * " + rightExpression.toString() + ")";

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
        return new Mult(this.leftExpression.assign(var, expression), this.rightExpression.assign(var, expression));
    }

    /**
     * findDifferentiate function.
     * @param leftExp - differentiate of left expression.
     * @param rightExp - differentiate of right expression.
     * @param var - - string for differentiate.
     * @return differentiate expression of two expressions.
     */
    @Override
    public Expression findDifferentiate(Expression leftExp, Expression rightExp, String var) {
        return new Plus(new Mult(leftExp, this.rightExpression), new Mult(this.leftExpression, rightExp));
    }
    /**
     * getSimplify function.
     * @return - a simplified version of the current expression.
     */
    @Override
    public Expression getSimplify() {
        Double tempLeft = null;
        Double tempRight = null;
        String exe;
        try {
            tempLeft = this.getLeftExpression().evaluate();
        } catch (Exception ex) {
            exe = ex.toString();
        }
        try {
            tempRight = this.getRightExpression().evaluate();
        } catch (Exception ex) {
            exe = ex.toString();
        }
        if (tempLeft != null && tempRight != null) {
            if (tempLeft == 0.0 || tempRight == 0.0) {
                return new Num(0);
            } else if (tempLeft == 1.0 && tempRight != 1.0) {
                return new Num(tempRight);
            } else if (tempRight == 1.0 && tempLeft != 1.0) {
                return new Num(tempLeft);
            } else if (tempRight == 1.0 && tempLeft == 1.0) {
                return new Num(1);
            } else {
                return new Mult(new Num(tempLeft), new Num(tempRight));
            }
        } else if (tempLeft != null && tempRight == null) {
            if (tempLeft == 0) {
                return new Num(0);
            } else if (tempLeft == 1) {
                return this.rightExpression.simplify();
            }
        } else if (tempRight != null && tempLeft == null) {
            if (tempRight == 0) {
                return new Num(0);
            } else if (tempRight == 1) {
                return this.leftExpression.simplify();
            }
        }
        return new Mult(this.leftExpression.simplify(), this.rightExpression.simplify());

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
        return true;
    }
    /**
     * swap function.
     * @return swap version of the expression.
     */
    @Override
    public Expression swap() {
        return new Mult(this.rightExpression, this.leftExpression);
    }

}





