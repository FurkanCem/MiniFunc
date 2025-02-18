package minifunc;

public class ConditionalExpression extends Expression implements canBeUsedAsParameter {

    private final Expression firstExpression, secondExpression;
    private final ConditionalOperator opCode;

    ConditionalExpression(Expression firstExpression, Expression secondExpression, ConditionalOperator opCode) {
        checkIfSuitable(firstExpression, secondExpression);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.opCode = opCode;
    }

    private void checkIfSuitable(Expression ex1, Expression ex2) {
        if (!(ex1 instanceof canBeUsedAsParameter && ex2 instanceof canBeUsedAsParameter)) {
            throw new RuntimeException("Invalid parameter in in ConditionalExpression!");
        }
    }

    @Override
    Expression execute() {
        Expression executedFirstExpression = firstExpression.execute();
        Expression executedScondExpression = secondExpression.execute();

        switch (opCode) {
            case Equal -> {
                return new BooleanLiteral(isEq(executedFirstExpression, executedScondExpression));
            }
            case Less -> {
                return new BooleanLiteral(isLss(executedFirstExpression, executedScondExpression));
            }
            case LessEqual -> {
                return new BooleanLiteral(isEq(executedFirstExpression, executedScondExpression) || isLss(executedFirstExpression, executedScondExpression));
            }
            case Greater -> {
                return new BooleanLiteral(isGrt(executedFirstExpression, executedScondExpression));
            }
            default -> {
                return new BooleanLiteral(isEq(executedFirstExpression, executedScondExpression) || isGrt(executedFirstExpression, executedScondExpression));
            }

        }
    }

    private static boolean isEq(Expression firstExpression, Expression secondExpression) {
        return firstExpression.equals(secondExpression.getValue());
    }

    /**
     * Handles String and Boolean as String If at least one of expression is
     * String,compare them as string,return true if first expression is greater
     * alphabetical If both expressions are Number,typecaste in to
     * double,compare as double,return true if expression1 is greater
     * numerically
     */
    private static boolean isGrt(Expression firstExpression, Expression secondExpression) {
        if (firstExpression instanceof Str || secondExpression instanceof Str) {
            return firstExpression.getValue().toString().compareTo(secondExpression.getValue().toString()) > 0;
        }
        double firstValue = ((Number) firstExpression.getValue()).doubleValue();
        double secondValue = ((Number) secondExpression.getValue()).doubleValue();
        return firstValue > secondValue;
    }

    /**
     * Handles String and Boolean as String If at least one of expression is
     * String,compare them as string,return true if first expression is lesser
     * alphabetical If both expressions are Number,typecaste in to
     * double,compare as double,return true if expression1 is lesser numerically
     */
    private static boolean isLss(Expression firstExpression, Expression secondExpression) {
        if (firstExpression instanceof Str || secondExpression instanceof Str) {
            return firstExpression.getValue().toString().compareTo(secondExpression.getValue().toString()) < 0;
        }
        double firstValue = ((Number) firstExpression.getValue()).doubleValue();
        double secondValue = ((Number) secondExpression.getValue()).doubleValue();
        return firstValue < secondValue;
    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " " + opCode + " " + str2 + ")";
    }

}
