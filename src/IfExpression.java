package minifunc;

public class IfExpression extends Expression implements canBeUsedAsParameter{

    private final Expression firstExpression, secondExpression;
    private final Expression condition;

    public IfExpression(Expression condition, Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.condition = condition;
    }

    public IfExpression(Expression condition, Expression ex1) {
        this.firstExpression = ex1;
        this.secondExpression = null;
        this.condition = condition;
    }

    @Override
    Expression execute() {
        try {
            Boolean conditionAsBoolean = (Boolean) condition.execute().getValue();
            if (conditionAsBoolean) {
                return firstExpression.execute();
            } else {
                //Possible null value
                return secondExpression == null ? null : secondExpression.execute();
            }
        } catch (ClassCastException ex) {
            throw new RuntimeException("Non boolean value for condition! Null expression returned");
        }
    }

    @Override
    public String toString() {
        String str1 = firstExpression.toString();
        String str2 = secondExpression.toString();
        return (secondExpression == null) ? ("if " + condition.toString() + " { " + str1 + " }")
                : ("if " + condition.toString() + " { " + str1 + " } else { " + str2 + " }");
    }

}
