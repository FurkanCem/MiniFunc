package minifunc;

public class FunctionAssign extends Expression implements canBeUsedAsParameter{

    private final FunctionExpression func;
    private final ArrayExpression parameters;

    public FunctionAssign(FunctionExpression func, ArrayExpression parameters) {
        this.func = func;
        this.parameters = parameters;
    }

    @Override
    Expression execute() {
        return func.executeForFunctionAssign(parameters);
    }

    @Override
    public String toString() {
        return "#" + func.name + "(" + parameters.elementsAsString() + ")";
    }

}
