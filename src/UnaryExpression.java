import java.util.ArrayList;
import java.util.List;

/**
 * UnaryExpression class.
 */
public abstract class UnaryExpression extends BaseExpression  {

    /**
     * getVariables function.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {

        List<String> variables = new ArrayList<String>();
        List expressionVars = this.getExpression().getVariables();
        this.getDistinctList(expressionVars, variables);
        return variables;
    }
    /**
     * getExpression function.
     * @return this.expression.
     */
    public abstract Expression getExpression();

}
