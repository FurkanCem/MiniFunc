package minifunc;

import java.util.Scanner;

public abstract class Variable extends Expression implements Assignable,canBeUsedAsParameter {

    protected final Scanner sc = new Scanner(System.in);
    
    public final String name;

    Variable(String name) {
        this.name = name;
    }

    abstract void assign(Variable var);

    @Override
    public abstract void assign(Expression var);
    
    public abstract void getInputFromStdIn();

}
