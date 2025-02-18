package minifunc;

import java.util.*;

public class FunctionExpression extends Expression implements canBeUsedAsParameter{

    protected final Variable resultVariable;
    protected final ArrayExpression parameters;
    protected final Expression[] block;
    protected final String name;
    private ArrayList<Expression> initialParameters;
    private final ArrayList<Expression> defaultParameters;
    protected final Expression initialResult;

    public FunctionExpression(String name, ArrayExpression parameters, Variable res, Expression... block) {
        this.name = name;
        this.parameters = parameters;
        this.resultVariable = res;
        this.block = block;
        this.initialResult = res.execute();
        this.initialParameters = new ArrayList<>();
        this.defaultParameters = new ArrayList<>();

        for (Expression e : parameters) {
            defaultParameters.add(e.execute());
        }
    }

    public FunctionExpression(String name, Variable res, Expression... block) {
        this.name = name;
        this.resultVariable = res;
        this.block = block;
        this.initialResult = res.execute();
        this.parameters = null;
        this.initialParameters = null;
        this.defaultParameters = null;
    }

    /**
     * reset function parameters and resValue to the default value
     */
    protected void reset() {
        resultVariable.assign(initialResult);
        if (parameters == null) {
            return;
        }
        for (int i = 0; i < (int) parameters.size().getValue(); i++) {
            ((Assignable) parameters.get(new IntegerLiteral(i))).assign(defaultParameters.get(i));
        }
    }

    /**
     * Assign values of parameters to the parameters in parameters list with
     * same order
     *
     * @param parameters
     */
    private void assignParameters(Expression... parameters) {
        if (parameters == null) {
            return;
        }
        int minParameter = Math.min((int) this.parameters.size().getValue(), parameters.length);
        for (int i = 0; i < minParameter; i++) {
            ((Assignable) this.parameters.get(new IntegerLiteral(i))).assign(parameters[i]);
        }
    }

    /**
     * Deep copy first given parameters to use in outStr()
     *
     * @param parameters
     */
    private void setInitialParameters(Expression... parameters) {
        initialParameters = new ArrayList<>();
        Collections.addAll(initialParameters, parameters);
    }

    /**
     * If this.parameters lesser than parameters then excess parameters is
     * ignored if vice versa then missing parameters are treated as default
     */
    Expression execute(Expression... parameters) {
        reset();
        setInitialParameters(parameters);
        assignParameters(parameters);
        return execute();
    }

    Expression executeForFunctionAssign(ArrayExpression arr) {
        reset();
        setInitialParameters(parameters);

        for (int i = 0; i < (int) parameters.size().getValue(); i++) {
            ((Assignable) this.parameters.get(new IntegerLiteral(i))).assign(arr.get(new IntegerLiteral(i)));
        }

        return execute();
    }

    /*
        if function has parameters and you execute without it, it uses last used value
     */
    @Override
    Expression execute() {
        for (Expression ex : block) {
            ex.execute();
        }
        return resultVariable.execute();
    }

    public String outStr() {
        String parameterValues = "";

        if (initialParameters != null) {
            for (Expression e : initialParameters) {
                if (e instanceof ArrayExpression arr) {
                    parameterValues = parameterValues.concat(arr.name + "[" + arr.elementsAsString() + "], ");
                } else {
                    parameterValues = parameterValues.concat(e.execute().toString() + ", ");
                }

            }
            if (!parameterValues.isEmpty()) {
                parameterValues = parameterValues.substring(0, parameterValues.length() - 2);
            }
        }
        return name + "(" + parameterValues + ") = " + resultVariable.execute().toString();
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
            only used to fix toString of for loop inside a function
         */
        for (Expression e : block) {
            String strToAdd = e.toString().replaceAll("\n", "\n\t");
            blockAsStr = blockAsStr.concat("\t" + strToAdd + "\n");
        }
        return "func " + name + "("
                + parametersString
                + ")\n{\n"
                + blockAsStr
                + "\treturn " + resultVariable + "\n" + "}";
    }

}
