import java.util.List;
import java.util.Map;

/**
 * Log class.
 */

public class Log extends BinaryExpression implements Expression {
    //members
    private Expression leftExpression;
    private Expression rightExpression;
    /**
     * Log constructor.
     * @param leftExpression - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Log(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    /**
     * Log constructor.
     * @param leftExpression - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Log(Expression leftExpression, String var) {
        this(leftExpression, new Var(var));
    }
    /**
     * Log constructor.
     * @param leftExpression - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Log(Expression leftExpression, double num) {
        this(leftExpression, new Num(num));
    }
    /**
     * Log constructor.
     * @param var - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Log(String var, Expression rightExpression) {
        this(new Var(var), rightExpression);
    }
    /**
     * Log constructor.
     * @param var1 - this.leftExpression.
     * @param var2 - this.rightExpression.
     */
    public Log(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }
    /**
     * Log constructor.
     * @param var - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Log(String var, double num) {
        this(new Var(var), new Num(num));
    }
    /**
     * Log constructor.
     * @param num - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Log(double num, Expression rightExpression) {
        this(new Num(num), rightExpression);
    }
    /**
     * Log constructor.
     * @param num - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Log(double num, String var) {
        this(new Num(num), new Var(var));
    }
    /**
     * Log constructor.
     * @param num1 - this.leftExpression.
     * @param num2 - this.rightExpression.
     */
    public Log(double num1, double num2) {
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
     * returnThis.
     * @return current class.
     */
    public Expression returnThis() {
        return this;
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
        double leftNew = leftExpression.evaluate(assignment);
        double rightNew = rightExpression.evaluate(assignment);
        //check if log valid
        if (leftNew < 0 || leftNew  == 1 || rightNew < 0) {
            throw new Exception("Log values invalid!");
        } else {
            return Math.log(rightNew) / Math.log(leftNew);
        }

    }

    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "log(" + leftExpression.toString() + ", " + rightExpression.toString() + ")";

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
        return new Log(this.leftExpression.assign(var, expression), this.rightExpression.assign(var, expression));
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
        List<String> temp = this.leftExpression.getVariables();
        if (temp != null && temp.contains(var)) {
            return new Div(new Log(new Var("e"), this.rightExpression),
                    new Log(new Var("e"), this.leftExpression)).differentiate(var);
        } else {
            return new Div(rightExp, new Mult(this.rightExpression, new Log(new Var("e"), this.leftExpression)));
        }
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
        //numbers
        if (tempLeft != null && tempRight != null) {

            return new Num(Math.log(tempRight) / Math.log(tempLeft));

        } else if (tempLeft == null && tempRight == null) {
            //check if equal
            if (this.isEqual()) {
                return new Num(1);
            }
        }
        return new Log(this.leftExpression.simplify(), this.rightExpression.simplify());

    }
    /**
     * isEqual function.
     * @return true if equal, false otherwise.
     */
    public boolean isEqual() {

        if (this.leftExpression.toString().equals(this.rightExpression.toString())) {
            return true;
        } else if (this.leftExpression.isCommutative() && this.rightExpression.isCommutative()) {
            if (this.leftExpression.toString().equals(this.rightExpression.swap().toString())) {
                return true;
            } else if (this.rightExpression.toString().equals(this.leftExpression.swap().toString())) {
                return true;
            }
        }
        return false;
    }
}


