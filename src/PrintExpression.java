package minifunc;

import java.util.*;

public class PrintExpression extends Expression {

    private final ArrayList<Expression> exps = new ArrayList<>();

    public PrintExpression(Expression... expsToWrite) {
        Collections.addAll(exps, expsToWrite);
    }

    @Override
    Expression execute() {
        String strToReturn = "";

        for (Expression e : exps) {
            strToReturn = strToReturn.concat(e.execute().toString());
        }

        System.out.println(strToReturn);

        return new StringLiteral(strToReturn);
    }

    public static void print(Expression... exps) {
        String strToReturn = "";

        for (Expression e : exps) {
            strToReturn = strToReturn.concat(e.toString());
        }

        System.out.println(strToReturn);
    }

    public static void print(StringLiteral expAsString) {
        System.out.println(expAsString.toString());
    }

    @Override
    public String toString() {
        String strToReturn = "";
        for (Expression e : exps) {
            strToReturn = strToReturn.concat(e.toString());
        }
        return "print("
                + strToReturn
                + ")";
    }

}
