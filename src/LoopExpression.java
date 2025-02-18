package minifunc;

import java.util.*;

public class LoopExpression extends Expression {

    private final IntegerVariable indexVariable;
    private final ConditionalExpression loopCondition;
    private final ArithmeticExpression expressionToChangeIndexVariable;
    private final ArrayList<Expression> block = new ArrayList<>();

    public LoopExpression(IntegerVariable indexVariable, ConditionalExpression loopCondition, ArithmeticExpression expressionToChangeIndexVariable, Expression... block) {
        this.indexVariable = indexVariable;
        this.loopCondition = loopCondition;
        this.expressionToChangeIndexVariable = expressionToChangeIndexVariable;
        Collections.addAll(this.block, block);
    }

    /**
     * While condition is met execute block and call method again Whenever it is
     * not met return last value of i
     */
    @Override
    Expression execute() {

        Boolean condAsBoolean = (Boolean) loopCondition.execute().getValue();

        if (condAsBoolean) {
            for (Expression e : block) {
                e.execute();
            }
            indexVariable.assign(expressionToChangeIndexVariable.execute());
            return this.execute();
        }

        return indexVariable.execute();
    }

    @Override
    public String toString() {
        return "for ( " + indexVariable + " = " + indexVariable.getValue() + " ; " + loopCondition.toString() + " ; " + expressionToChangeIndexVariable + " ){\n\t"
                + block
                + "\n}";
    }

}
