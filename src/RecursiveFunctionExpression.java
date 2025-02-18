package minifunc;

public class RecursiveFunctionExpression extends FunctionExpression implements canBeUsedAsParameter{

    private final ConditionalExpression conditionToStopRecursion;
    private final Expression recursiveBreakBlock;

    RecursiveFunctionExpression(String name, ArrayExpression parameters, Variable resultVariable, ConditionalExpression conditionToStopRecursion, Expression recursiveBreakBlock, Expression... block) {
        super(name, parameters, resultVariable, block);
        this.conditionToStopRecursion = conditionToStopRecursion;
        this.recursiveBreakBlock = recursiveBreakBlock;
    }

    RecursiveFunctionExpression(String name, Variable resultVariable, ConditionalExpression breakCond, Expression recursiveBreakBlock, Expression... block) {
        super(name, resultVariable, block);
        this.conditionToStopRecursion = breakCond;
        this.recursiveBreakBlock = recursiveBreakBlock;
    }

    @Override
    Expression execute() {
        Boolean condAsBoolean = (Boolean) conditionToStopRecursion.execute().getValue();

        if (!condAsBoolean) {
            for (Expression ex : block) {
                ex.execute();
            }
            return execute();
        }
        if (recursiveBreakBlock != null) {
            recursiveBreakBlock.execute();
        }
        return resultVariable.execute();
    }

    @Override
    public String toString() {
        String blockAsStr = "";
        String parametersString = "";
        if (parameters != null) {
            for (Expression e : parameters) {
                parametersString = parametersString.concat(e.toString() + ", ");
            }
            if (!parametersString.isEmpty()) {
                parametersString = parametersString.substring(0, parametersString.length() - 2);
            }

        }

        /*
            double tab if a line is tabbed once
         */
        for (Expression e : block) {
            String strToAdd = e.toString().replaceAll("\n", "\n\t");
            blockAsStr = blockAsStr.concat("\t" + strToAdd + "\n");
        }
        return "func " + name + "("
                + parametersString
                + ")\n{\n\t" + conditionToStopRecursion.toString() + " --> {\n\t"
                + (recursiveBreakBlock == null ? "\tbreak\n" : "\t" + recursiveBreakBlock + "\n\t\t break\n")
                + "\t}\n"
                + blockAsStr
                + "\treturn " + name + "(" + parametersString + ")\n" + "}";
    }

}
