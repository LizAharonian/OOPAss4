/**
 * Created by shimon on 5/11/2017.
 */
public class ExcecutePartThree {

    public static void main(String args[]) throws Exception {
        Expression e33 = new Plus(new Var("x"), new Var("y"));
        //System.out.println(e33.differentiate("x"));

        Expression g = new Div(new Num(0),new Var("X"));

       // Expression o =new Mult(new Plus(new Plus(new Plus(new Plus(new Num(0), new Num(1)),new Var("x")),new Num(0)),new Var("y")),new Mult(new Num(0),new Var("1")));
        Expression o =new Mult(new Plus(new Plus(new Plus(new Plus(new Num(0), new Num(1)),new Var("x")),new Num(0)),new Var("y")),new Mult(new Num(0),new Var("1")));
        //System.out.println(o);
       // System.out.println(o.simplify());
        Expression e = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        System.out.println(e.differentiate("x"));
        // the result is:
        // (((x + y) ^ 2.0) * (((1.0 + 0.0) * (2.0 / (x + y))) + (0.0 * log(e, (x + y)))))
        System.out.println(e.differentiate("x").simplify());
        // the result is:
        // (((x + y) ^ 2.0) * (2.0 / (x + y)))
        e = new Pow(new Var("e"), new Var("x"));
        System.out.println(e.differentiate("x"));
        // ((e ^ x) * ((0.0 * (x / e)) + (1.0 * log(e, e))))
        System.out.println(e.differentiate("x").simplify());
        // (e ^ x)
        e = new Plus(new Sin(new Pow(new Var("e"), new Var("x"))), new Sin(new Var("y")));
        System.out.println(e.differentiate("x"));
        System.out.println(e.differentiate("x").simplify());
       // e = new Minus(5, -4);
        System.out.println(e.simplify());//9
     //   e = new Mult(5, -4);
        System.out.println(e);//5*(-4)
        System.out.println(e.simplify());//(-20)
    }
}

