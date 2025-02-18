package minifunc;

public class IntegerVariable extends Variable {

    private int value;

    IntegerVariable(String name) {
        super(name);
        this.value = 0;
    }

    IntegerVariable(String name, int value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, int value) {
        return new IntegerVariable(name, value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new IntegerLiteral(value);
    }

    @Override
    void assign(Variable var) {
        IntegerVariable intVar = (IntegerVariable) var;
        value = intVar.value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void assign(Expression var) {
        try {
            int booleanVar = ((Number) var.getValue()).intValue();
            value = booleanVar;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        try {
            if(o instanceof DoubleLiteral || o instanceof DoubleVariable 
                    || o instanceof IntegerLiteral || o instanceof IntegerVariable){
                o = ((Expression)o).getValue();
            }
            return ((Number) o).doubleValue() == (double) value;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.value;
        return hash;
    }
    
    @Override
    public void getInputFromStdIn() {
       value = sc.nextInt();
    }

}
