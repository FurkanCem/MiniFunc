package minifunc;

abstract class LogicExpression extends Expression implements canBeUsedAsParameter {

    protected final Expression firstExpression, secondExpression;

    LogicExpression(Expression firstExpression) {
        checkIfSuitable(firstExpression, firstExpression);
        this.firstExpression = firstExpression;
        this.secondExpression = null;

    }

    LogicExpression(Expression firstExpression, Expression secondExpression) {
        checkIfSuitable(firstExpression, secondExpression);
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    private void checkIfSuitable(Expression ex1, Expression ex2) {
        if (!(ex1 instanceof canBeUsedAsParameter && ex2 instanceof canBeUsedAsParameter)) {
            throw new RuntimeException("Invalid parameter in LogicExpression!");
        }
    }
}

/**
 * If Expression is String return false If Expression is Number,typecaste to
 * int,bitwise not,return as IntegerLiteral Else return not of Boolean as
 * Booleanliteral
 */
class NotExpression extends LogicExpression {

    NotExpression(Expression firstExpression) {
        super(firstExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();

        if (valueOfFirstExpression instanceof Number firstExpressionAsNumber) {
            return new IntegerLiteral(-firstExpressionAsNumber.intValue() - 1);

        }

        if (valueOfFirstExpression instanceof String) {
            return new BooleanLiteral(false);
        }

        return new BooleanLiteral(!(Boolean) valueOfFirstExpression);

    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        return "!(" + str1 + ")";
    }
}

/**
 * If at least one expression is String return false If both of expressions are
 * Number,typecaste into int,bitwise AND and return as IntegerLiteral Else and
 * both boolean expression and return as BooleanLiteral
 */
class AndExpression extends LogicExpression {

    AndExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (valueOfFirstExpression instanceof Number && valueOfSecondExpression instanceof Number) {
            return new IntegerLiteral(((Number) valueOfFirstExpression).intValue() & ((Number) valueOfSecondExpression).intValue());
        }

        if (!(valueOfFirstExpression instanceof Boolean) || !(valueOfSecondExpression instanceof Boolean)) {
            return new BooleanLiteral(false);
        }

        return new BooleanLiteral((Boolean) valueOfFirstExpression && (Boolean) valueOfSecondExpression);

    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " && " + str2 + ")";
    }
}

/**
 * If at least one expression is String return false If both of expressions are
 * Number,typecaste into int,bitwise OR and return as IntegerLiteral Else OR
 * both boolean expression and return as BooleanLiteral
 */
class OrExpression extends LogicExpression {

    OrExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (valueOfFirstExpression instanceof Number && valueOfSecondExpression instanceof Number) {
            return new IntegerLiteral(((Number) valueOfFirstExpression).intValue() | ((Number) valueOfSecondExpression).intValue());
        }

        if (!(valueOfFirstExpression instanceof Boolean) || !(valueOfSecondExpression instanceof Boolean)) {
            return new BooleanLiteral(false);
        }

        return new BooleanLiteral((Boolean) valueOfFirstExpression || (Boolean) valueOfSecondExpression);
    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " || " + str2 + ")";
    }
}

/**
 * If at least one expression is String return false If both of expressions are
 * Number,typecaste into int,bitwise XOR and return as IntegerLiteral Else XOR
 * both boolean expression and return as BooleanLiteral
 */
class XorExpression extends LogicExpression {

    XorExpression(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    Expression execute() {

        Expression executedFirstExpression = firstExpression.execute();
        Expression executedSecondExpression = secondExpression.execute();
        Object valueOfFirstExpression = executedFirstExpression.getValue();
        Object valueOfSecondExpression = executedSecondExpression.getValue();

        if (valueOfFirstExpression instanceof Number && valueOfSecondExpression instanceof Number) {
            return new IntegerLiteral(((Number) valueOfFirstExpression).intValue() ^ ((Number) valueOfSecondExpression).intValue());
        }

        if (!(valueOfFirstExpression instanceof Boolean) || !(valueOfSecondExpression instanceof Boolean)) {
            return new BooleanLiteral(false);
        }

        return new BooleanLiteral((Boolean) valueOfFirstExpression ^ (Boolean) valueOfSecondExpression);
    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return "(" + str1 + " ^ " + str2 + ")";
    }
}
