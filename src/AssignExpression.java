package minifunc;

public class AssignExpression extends Expression implements canBeUsedAsParameter{

    private final Variable var;
    private final Expression ex;

    public AssignExpression(Variable var, Expression ex) {
        this.var = var;
        this.ex = ex;
    }

    @Override
    Expression execute() {
        var.assign(ex.execute());
        return var;
    }

    @Override
    public String toString() {
        return var + " = " + ex.toString();
    }

}
