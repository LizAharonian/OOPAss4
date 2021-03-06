import java.util.Map;

/**
 * Div class.
 * used to divide expressions.
 */

public class Div extends BinaryExpression implements Expression {
    //members
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     * Div constructor.
     * @param leftExpression - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Div(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * Div constructor.
     * @param leftExpression - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Div(Expression leftExpression, String var) {
        this(leftExpression, new Var(var));
    }

    /**
     * Div constructor.
     * @param leftExpression - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Div(Expression leftExpression, double num) {
        this(leftExpression, new Num(num));
    }
    /**
     * Div constructor.
     * @param var - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Div(String var, Expression rightExpression) {
        this(new Var(var), rightExpression);
    }
    /**
     * Div constructor.
     * @param var1 - this.leftExpression.
     * @param var2 - this.rightExpression.
     */
    public Div(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }
    /**
     * Div constructor.
     * @param var - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Div(String var, double num) {
        this(new Var(var), new Num(num));
    }
    /**
     * Div constructor.
     * @param num - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Div(double num, Expression rightExpression) {
        this(new Num(num), rightExpression);
    }
    /**
     * Div constructor.
     * @param num - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Div(double num, String var) {
        this(new Num(num), new Var(var));
    }
    /**
     * Div constructor.
     * @param num1 - this.leftExpression.
     * @param num2 - this.rightExpression.
     */
    public Div(double num1, double num2) {
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

        double rightExpNew = rightExpression.evaluate(assignment);
        if (rightExpNew != 0.0) {
            return leftExpression.evaluate(assignment) / rightExpNew;
        } else {
            throw new Exception("Divide by 0 is illegal!");
        }

    }
    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + leftExpression.toString() + " / " + rightExpression.toString() + ")";

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
        return new Div(this.leftExpression.assign(var, expression), this.rightExpression.assign(var, expression));
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
        return new Div(new Minus(new Mult(leftExp, this.rightExpression), new Mult(this.leftExpression, rightExp)),
                new Pow(this.rightExpression, new Num(2.0)));
    }
    /**
     * getSimplify function.
     * @return a simplified version of the current expression.
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
            if (tempRight == 1.0 && tempLeft != 1.0) {
                return new Num(tempLeft);
            } else if (tempLeft / tempRight == 1) { //equals numbers
                return new Num(1);
            } else if (tempLeft == 0) {
                return new Num(0);
            } else {
                return new Num(tempLeft / tempRight);
            }
        } else if (tempLeft == null && tempRight != null) {
            if (tempRight == 1) {
                return this.leftExpression.simplify();
            }
        } else if (tempLeft != null && tempRight == null) {
            if (tempLeft == 0) {
                return new Num(0);
            }
        } else if (tempLeft == null && tempRight == null) {
            if (isEqual()) { //check if equal
                return new Num(1);
            }
        }
        return new Div(this.leftExpression.simplify(), this.rightExpression.simplify());
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


