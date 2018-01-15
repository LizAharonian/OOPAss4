import java.util.List;
import java.util.Map;

/**
 * Pow class.
 */
public class Pow extends BinaryExpression implements Expression  {
    //members
    private Expression leftExpression;
    private Expression rightExpression;
    /**
     * Pow constructor.
     * @param leftExpression - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Pow(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    /**
     * Pow constructor.
     * @param leftExpression - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Pow(Expression leftExpression, String var) {
        this(leftExpression, new Var(var));
    }
    /**
     * Pow constructor.
     * @param leftExpression - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Pow(Expression leftExpression, double num) {
        this(leftExpression, new Num(num));
    }
    /**
     * Pow constructor.
     * @param var - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Pow(String var, Expression rightExpression) {
        this(new Var(var), rightExpression);
    }
    /**
     * Pow constructor.
     * @param var1 - this.leftExpression.
     * @param var2 - this.rightExpression.
     */
    public Pow(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }
    /**
     * Pow constructor.
     * @param var - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Pow(String var, double num) {
        this(new Var(var), new Num(num));
    }
    /**
     * Pow constructor.
     * @param num - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Pow(double num, Expression rightExpression) {
        this(new Num(num), rightExpression);
    }
    /**
     * Pow constructor.
     * @param num - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Pow(double num, String var) {
        this(new Num(num), new Var(var));
    }
    /**
     * Pow constructor.
     * @param num1 - this.leftExpression.
     * @param num2 - this.rightExpression.
     */
    public Pow(double num1, double num2) {
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

        return Math.pow(leftExpression.evaluate(assignment), rightExpression.evaluate(assignment));
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

        List<String> variablesRight = this.rightExpression.getVariables();
        List<String> variablesLeft = this.leftExpression.getVariables();
        if ((variablesRight != null) && (variablesRight.contains(var))) {
            Expression tempExponent = new Mult(this.rightExpression, new Log(new Var("e"), this.leftExpression));
            Expression temp = new Pow(new Var("e"), tempExponent);
            return new Mult(temp, tempExponent.differentiate(var));
        } else  {
            return new Mult(new Mult(this.rightExpression, new Pow(this.leftExpression,
                    new Minus(this.rightExpression, new Num(1)))), this.leftExpression.differentiate(var));
        }
    }
    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + leftExpression.toString() + "^" + rightExpression.toString() + ")";

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
        return new Pow(this.leftExpression.assign(var, expression), this.rightExpression.assign(var, expression));

    }
    /**
     * getSimplify function.
     * @return - a simplified version of the current expression.
     */
    @Override
    public Expression getSimplify() {
        if (this.leftExpression instanceof Pow) {
            Pow powExp = (Pow) this.leftExpression;
            return new Pow(powExp.getLeftExpression(), new Mult(powExp.getRightExpression(), this.rightExpression));
        }
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
            return new Num(Math.pow(tempLeft, tempRight));

        } else {
            return new Pow(this.leftExpression.simplify(), this.rightExpression.simplify());
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
