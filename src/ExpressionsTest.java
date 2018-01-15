import java.util.Map;
import java.util.TreeMap;

/**
 * ExpressionsTest for ass4.
 */
public class ExpressionsTest {
    /**
     * main function.
     * @param args - list of string - not used.
     */
  public static void main(String[] args)  {
      try {
          //Create the expression (2x) + (sin(4y)) + (e^x).
          Expression expression = new Plus(new Plus(new Mult(2, "x"),
                  new Sin(new Mult(4, "y"))), new Pow("e", "x"));
          System.out.println(expression); //  Print the expression
          Map<String, Double> assignment = new TreeMap<String, Double>();
          assignment.put("x", 2.0);
          assignment.put("y", 0.25);
          assignment.put("e", 2.71);
          System.out.println(expression.evaluate(assignment));
          System.out.println(expression.differentiate("x")); //Print the differentiated expression according to x.
          System.out.println(expression.differentiate("x").evaluate(assignment));
          System.out.println(expression.differentiate("x").simplify());
      } catch (Exception ex) {
          System.out.println(ex.toString());
      }

  }
}
