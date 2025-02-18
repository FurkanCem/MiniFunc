package minifunc;

public abstract class ArithmeticExpression extends Expression implements canBeUsedAsParameter{

    protected final Expression firstExpression, secondExpression;

    ArithmeticExpression(Expression ex1, Expression ex2) {
        checkIfSuitable(ex1, ex2);
        this.firstExpression = ex1;
        this.secondExpression = ex2;
    }

    ArithmeticExpression(Expression ex1) {
        checkIfSuitable(ex1, ex1);
        this.firstExpression = ex1;
        this.secondExpression = null;
    }
    
    private void checkIfSuitable(Expression ex1, Expression ex2) {
        if (!(ex1 instanceof canBeUsedAsParameter && ex2 instanceof canBeUsedAsParameter)) {
            throw new RuntimeException("Invalid parameter in ArithmeticExpression!");
        }
    }
   

}

/**
 * Handles Boolean and String literals and variables as String. If at least one
 * string expression is used, both are added as strings and returned as a
 * StringLiteral. If one of them is a double, both are added as doubles and
 * returned as a DoubleLiteral. If neither, both are added as doubles and
 * typecasted to int and returned as an IntegerLiteral.
 */
class AddExpression extends ArithmeticExpression {

    AddExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfScondExpression = executedSecondExpression.getValue();

        if (firstExpression.execute() instanceof Str || secondExpression.execute() instanceof Str) {
            return new StringLiteral(valueOfFirstExpression + "" + valueOfScondExpression);
        }

        double firstValue = ((Number) valueOfFirstExpression).doubleValue();
        double secondValue = ((Number) valueOfScondExpression).doubleValue();

        if (valueOfFirstExpression instanceof Double || valueOfScondExpression instanceof Double) {
            return new DoubleLiteral(firstValue + secondValue);
        }

        return new IntegerLiteral((int) (firstValue + secondValue));

    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " + " + str2 + ")";
    }
}

/**
 * Handles Boolean and String literals and variables as String. If at least one
 * string expression is used, both are converted to strings and any occurrences
 * of string2 in string1 are replaced with "" and returned as StringLiteral. If
 * one of them is a double, both are subtracted as doubles and returned as a
 * DoubleLiteral. If neither, both are subtracted as doubles, typecasted to int,
 * and returned as an IntegerLiteral.
 */
class SubtractExpression extends ArithmeticExpression {

    SubtractExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (executedFirstExpression instanceof Str || executedSecondExpression instanceof Str) {
            String firstStr = valueOfFirstExpression.toString();
            String secondStr = valueOfSecondExpression.toString();
            return new StringLiteral(firstStr.replace(secondStr, ""));
        }

        double firstValue = ((Number) valueOfFirstExpression).doubleValue();
        double secondValue = ((Number) valueOfSecondExpression).doubleValue();

        if (valueOfFirstExpression instanceof Double || valueOfSecondExpression instanceof Double) {
            return new DoubleLiteral(firstValue - secondValue);
        }

        return new IntegerLiteral((int) (firstValue - secondValue));

    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " - " + str2 + ")";
    }
}

/**
 * Handles Boolean and String literals and variables as String. If both
 * expressions are strings, they are concatenated and returned as a
 * StringLiteral. If there is one string and one number, the number is
 * typecastinto an integer, and the string is multiplied by that number. The
 * result is returned as a StringLiteral. If at least one expression is a
 * double, they are multiplied as doubles and returned as a DoubleLiteral. Else,
 * they are multiplied as doubles, then typecast into an integer, and returned
 * as an IntegerLiteral.
 */
class MultiplyExpression extends ArithmeticExpression {

    MultiplyExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (executedFirstExpression instanceof Str && executedSecondExpression instanceof Str) {
            return new AddExpression(firstExpression, secondExpression).execute();
        }

        if (executedFirstExpression instanceof Str || executedSecondExpression instanceof Str) {
            int val = (valueOfFirstExpression instanceof Number) ? ((Number) valueOfFirstExpression).intValue()
                    : ((Number) valueOfSecondExpression).intValue();
            String stringToSend = (valueOfFirstExpression instanceof Number) ? valueOfSecondExpression.toString()
                    : valueOfFirstExpression.toString();
            return new StringLiteral(strMulp(stringToSend, val));
        }

        double firstValue = ((Number) valueOfFirstExpression).doubleValue();
        double secondValue = ((Number) valueOfSecondExpression).doubleValue();

        if (valueOfFirstExpression instanceof Double || valueOfSecondExpression instanceof Double) {
            return new DoubleLiteral(firstValue * secondValue);
        }

        return new IntegerLiteral((int) (firstValue * secondValue));

    }

    private String strMulp(String str, int num) {
        if (num == 0) {
            return "";
        }
        String returnStr = "";
        if (num < 0) {
            StringBuilder reverseStr = new StringBuilder(str);
            reverseStr.reverse();
            while (num++ < 0) {
                returnStr = returnStr.concat(reverseStr.toString());
            }
        } else {
            while (num-- > 0) {
                returnStr = returnStr.concat(str);
            }
        }

        return returnStr;
    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " * " + str2 + ")";
    }
}

/**
 * Handles Boolean and String literals and variables as String. If at least one
 * string expression is used, both are converted to strings and any occurrences
 * of string2 in string1 are replaced with string2 + " " and returned as
 * StringLiteral. If at least one expression is a double, they are divided as
 * doubles and returned as a DoubleLiteral. Else, they are divided as doubles,
 * then typecast into an integer, and returned as an IntegerLiteral.
 */
class DivisionExpression extends ArithmeticExpression {

    DivisionExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (executedFirstExpression instanceof Str || executedSecondExpression instanceof Str) {
            String firstStr = valueOfFirstExpression.toString();
            String secondStr = valueOfSecondExpression.toString();
            return new StringLiteral(firstStr.replace(secondStr, secondStr + " "));
        }

        double firstValue = ((Number) valueOfFirstExpression).doubleValue();
        double secondValue = ((Number) valueOfSecondExpression).doubleValue();

        if (valueOfFirstExpression instanceof Double || valueOfSecondExpression instanceof Double) {
            return new DoubleLiteral(firstValue / secondValue);
        }

        return new IntegerLiteral((int) (firstValue / secondValue));

    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " / " + str2 + ")";
    }
}

/**
 * Handles Boolean and String literals and variables as String. If at least one
 * string expression is used, both are converted to strings and any occurrences
 * of string2 in string1 are replaced with string2 + " " and returned as
 * StringLiteral. If at least one expression is a double, return expressiona1
 * modulo expression2 as a DoubleLiteral. Else, get module as doubles, then
 * typecast into an integer, and return as an IntegerLiteral.
 */
class ModuloExpression extends ArithmeticExpression {

    ModuloExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {
        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (executedFirstExpression instanceof Str || executedSecondExpression instanceof Str) {
            String firstStr = valueOfFirstExpression.toString();
            String secondStr = valueOfSecondExpression.toString();
            return new StringLiteral(firstStr.replace(secondStr, secondStr + " "));
        }

        double firstValue = ((Number) valueOfFirstExpression).doubleValue();
        double secondValue = ((Number) valueOfSecondExpression).doubleValue();

        if (valueOfFirstExpression instanceof Double || valueOfSecondExpression instanceof Double) {
            return new DoubleLiteral(firstValue % secondValue);
        }

        return new IntegerLiteral((int) (firstValue % secondValue));
    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " % " + str2 + ")";
    }
}

/**
 * Handles Boolean and String literals and variables as String. If Expression is
 * String reverse String and return as StringLiteral If Expression is Double
 * negate number and return as DoubleLiteral Else negate number as
 * double,typecast into int,return as IntegerLiteral
 */
class NegateExpression extends ArithmeticExpression {

    NegateExpression(Expression firstExpression) {
        super(firstExpression);
    }

    @Override
    Expression execute() {

        if (firstExpression.execute() instanceof Str) {
            return new MultiplyExpression(firstExpression.execute(), new IntegerLiteral(-1)).execute();
        }

        double val = ((Number) firstExpression.execute().getValue()).doubleValue();

        if (firstExpression.execute().getValue() instanceof Double) {
            return DoubleLiteral.create(-val);
        }
        return IntegerLiteral.create(-(int) val);

    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        return "-(" + str1 + ")";
    }

}
