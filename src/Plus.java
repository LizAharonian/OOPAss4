import java.util.Map;

/**
 * Plus class.
 */
public class Plus extends BinaryExpression implements Expression {
    //members
    private Expression leftExpression;
    private Expression rightExpression;
    /**
     * Plus constructor.
     * @param leftExpression - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Plus(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    /**
     * Plus constructor.
     * @param leftExpression - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Plus(Expression leftExpression, String var) {
        this(leftExpression, new Var(var));
    }
    /**
     * Plus constructor.
     * @param leftExpression - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Plus(Expression leftExpression, double num) {
        this(leftExpression, new Num(num));
    }
    /**
     * Plus constructor.
     * @param var - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Plus(String var, Expression rightExpression) {
        this(new Var(var), rightExpression);
    }
    /**
     * Plus constructor.
     * @param var1 - this.leftExpression.
     * @param var2 - this.rightExpression.
     */
    public Plus(String var1, String var2) {
        this(new Var(var1), new Var(var2));
    }
    /**
     * Plus constructor.
     * @param var - this.leftExpression.
     * @param num - this.rightExpression.
     */
    public Plus(String var, double num) {
        this(new Var(var), new Num(num));
    }
    /**
     * Plus constructor.
     * @param num - this.leftExpression.
     * @param rightExpression - this.rightExpression.
     */
    public Plus(double num, Expression rightExpression) {
        this(new Num(num), rightExpression);
    }
    /**
     * Plus constructor.
     * @param num - this.leftExpression.
     * @param var - this.rightExpression.
     */
    public Plus(double num, String var) {
        this(new Num(num), new Var(var));
    }
    /**
     * Plus constructor.
     * @param num1 - this.leftExpression.
     * @param num2 - this.rightExpression.
     */
    public Plus(double num1, double num2) {
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

        return leftExpression.evaluate(assignment) + rightExpression.evaluate(assignment);

    }

    /**
     * toString function.
     * @return a nice string representation of the expression.
     */
    public String toString() {
        return "(" + leftExpression.toString() + " + " + rightExpression.toString() + ")";

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
        return new Plus(this.leftExpression.assign(var, expression), this.rightExpression.assign(var, expression));
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
        return new Plus(leftExp, rightExp);
    }
    /**
     * getSimplify function.
     * @return - a simplified version of the current expression.
     */
    public Expression getSimplify() {
        if (isInstanceEqual() && isEqual()) {
            return new Mult(2, this.leftExpression);
        }
        Expression returnExp = bonusSimplify();
        if (returnExp != null) {
            return returnExp;
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

            if (tempLeft == 0.0 && tempRight == 0.0) {
                return new Num(0);
            } else if (tempLeft == 0 && tempRight != 0) {
                return new Num(tempRight);
            } else if (tempLeft != 0 && tempRight == 0) {
                return new Num(tempLeft);
            } else {
                return new Num(tempLeft + tempRight);
            }
        } else if (tempLeft != null && tempRight == null) {
            if (tempLeft == 0) {
                return this.rightExpression.simplify();
            }
        } else if (tempRight != null && tempLeft == null) {
            if (tempRight == 0) {
                return this.leftExpression.simplify();
            }
        }
        return new Plus(this.leftExpression.simplify(), this.rightExpression.simplify());


    }
    /**
     * returnThis.
     * @return current class.
     */
    @Override
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

        return new Plus(this.rightExpression, this.leftExpression);
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

    /**
     * isInstanceEqual function.
     * @return true if instance of both expressions equal.
     */
    public boolean isInstanceEqual() {
        if (this.leftExpression instanceof  Plus && this.rightExpression instanceof Plus) {
            return true;
        } else if (this.leftExpression instanceof  Mult && this.rightExpression instanceof Mult) {
            return true;
        } else if (this.leftExpression instanceof  Div && this.rightExpression instanceof Div) {
            return true;
        } else if (this.leftExpression instanceof  Log && this.rightExpression instanceof Log) {
            return true;
        } else if (this.leftExpression instanceof  Neg && this.rightExpression instanceof Neg) {
            return true;
        } else if (this.leftExpression instanceof  Pow && this.rightExpression instanceof Pow) {
            return true;
        } else if (this.leftExpression instanceof  Cos && this.rightExpression instanceof Cos) {
            return true;
        } else if (this.leftExpression instanceof  Sin && this.rightExpression instanceof Sin) {
            return true;
        }
        return false;
    }

    /**
     * bonusSimplify function.
     * @return new simplified expression.
     */
    private Expression bonusSimplify() {
        if (this.leftExpression instanceof Mult && this.rightExpression instanceof Mult) {

            Mult leftExp = (Mult) this.leftExpression;
            Mult rightExp = (Mult) this.rightExpression;
            if (leftExp.getRightExpression().toString().equals(rightExp.getRightExpression().toString())
                    && leftExp.getLeftExpression() instanceof Num && rightExp.getLeftExpression() instanceof Num) {
                try {
                    double num = new Plus(leftExp.getLeftExpression(), rightExp.getLeftExpression()).evaluate();
                    return new Mult(num, leftExp.getRightExpression());
                } catch (Exception ex) {
                    String x = ex.toString();
                }
            } else if (leftExp.getRightExpression().toString().equals(rightExp.getLeftExpression().toString())
                    && leftExp.getLeftExpression() instanceof Num && rightExp.getRightExpression() instanceof Num) {
                try {
                    double num = new Plus(leftExp.getLeftExpression(), rightExp.getRightExpression()).evaluate();
                    return new Mult(num, leftExp.getRightExpression());
                } catch (Exception ex) {
                    String x = ex.toString();
                }
            } else if (leftExp.getLeftExpression().toString().equals(rightExp.getRightExpression().toString())
                    && leftExp.getRightExpression() instanceof Num && rightExp.getLeftExpression() instanceof Num) {
                try {
                    double num = new Plus(leftExp.getRightExpression(), rightExp.getLeftExpression()).evaluate();
                    return new Mult(num, leftExp.getLeftExpression());
                } catch (Exception ex) {
                    String x = ex.toString();
                }
            } else if (leftExp.getLeftExpression().toString().equals(rightExp.getLeftExpression().toString())
                    && leftExp.getRightExpression() instanceof Num && rightExp.getRightExpression() instanceof Num) {
                try {
                    double num = new Plus(leftExp.getRightExpression(), rightExp.getRightExpression()).evaluate();
                    return new Mult(num, leftExp.getLeftExpression());
                } catch (Exception ex) {
                    String x = ex.toString();
                }
            }

        }
        return null;
    }

}





