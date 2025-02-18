package minifunc;

import java.util.*;

public class ArrayExpression extends Expression implements Iterable<Expression>, Assignable {

    protected final String name;
    private int index;
    private ArrayList<Expression> list = new ArrayList<>();

    public ArrayExpression() {
        this.name = "";
    }

    public ArrayExpression(String name) {
        this.name = name;
    }

    public ArrayExpression(Expression... expressionsToAdd) {
        Collections.addAll(list, expressionsToAdd);
        this.name = "";
    }

    public ArrayExpression(String name, Expression... expressionsToAdd) {
        Collections.addAll(list, expressionsToAdd);
        this.name = name;
    }

    public void copy(ArrayExpression arrayExpressionToCopy) {
        if (arrayExpressionToCopy == null) {
            System.out.println("Can not copy null array!");
            return;
        }
        list = new ArrayList<>();
        for (Expression e : arrayExpressionToCopy) {
            list.add(e);
        }
    }

    public Expression size() {
        return new IntegerLiteral(list.size());
    }

    private int getIndexFromExpression(Expression ex) {
        try {
            return ((Number) ex.execute().getValue()).intValue();
        } catch (ClassCastException e) {
            throw new RuntimeException("Non numeric index is used");
        }
    }

    public void add(Expression e) {
        list.add(e);
    }

    public void remove(Expression e) {
        list.remove(e);
    }

    public void remove(IntegerLiteral index) {

        int indexAsInt = getIndexFromExpression(index);

        if (indexAsInt < list.size()) {
            list.remove(indexAsInt);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();

    }

    Expression get(Expression indexExpression) {
        index = getIndexFromExpression(indexExpression);
        if (index < list.size()) {
            return list.get(index);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(Expression indexExpression, Expression ex) {
        index = getIndexFromExpression(indexExpression);
        if (index < list.size()) {
            list.set(index, ex);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void assign(Expression expressionToAssign) {
        if (expressionToAssign instanceof ArrayExpression expressionToAssignAsArrayExpression) {
            copy(expressionToAssignAsArrayExpression);
        } else {
            copy(new ArrayExpression(expressionToAssign));
        }
    }

    @Override
    Expression execute() {
        return this;
    }

    @Override
    public Iterator<Expression> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < list.size();
            }

            @Override
            public Expression next() {
                if (!hasNext()) {
                    throw new RuntimeException("No more elements in the array");
                }
                return list.get(currentIndex++);
            }
        };
    }

    public Expression arrayExpressionValueGetter(IntegerVariable var) {
        class arrayExpressionValueGetter extends Expression implements canBeUsedAsParameter{
            private final ArrayExpression pointedArrayExpression = ArrayExpression.this;
            private final IntegerVariable indexVariable = var;

            @Override
            public Expression execute() {
                return pointedArrayExpression.get(indexVariable.execute());
            }

            @Override
            public String toString() {
                if (name.isEmpty()) {
                    return "";
                }
                return name + "[" + indexVariable + "]";
            }
        }
        return new arrayExpressionValueGetter();
    }

    public Expression arrayExpressionSizeGetter() {
        class arrayExpressionSizeGetter extends Expression implements canBeUsedAsParameter {
            private final ArrayExpression pointedArrayExpression = ArrayExpression.this;

            @Override
            Expression execute() {
                return pointedArrayExpression.size();
            }

            @Override
            public String toString() {
                return name + ".len";
            }
        }
        return new arrayExpressionSizeGetter();
    }

    public String elementsAsString() {
        String elementsAsString = "";
        if (!list.isEmpty()) {
            for (Expression e : list) {
                elementsAsString = elementsAsString.concat(e.toString() + ", ");
            }
            elementsAsString = elementsAsString.substring(0, elementsAsString.length() - 2);
        }
        return elementsAsString;
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return "";
        }
        return name + "[]";
    }

}
