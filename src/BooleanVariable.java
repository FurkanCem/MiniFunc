package minifunc;

public class BooleanVariable extends Variable implements Str {

    private boolean value;

    BooleanVariable(String name) {
        super(name);
        this.value = false;
    }

    BooleanVariable(String name, boolean value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, boolean value) {
        return new BooleanVariable(name, value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new BooleanLiteral(value);
    }

    @Override
    void assign(Variable var) {
        BooleanVariable booleanVar = (BooleanVariable) var;
        value = booleanVar.value;
    }

    @Override
    public void assign(Expression var) {
        try {
            boolean booleanVar = (Boolean) var.getValue();
            value = booleanVar;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        try {
            if(o instanceof BooleanLiteral || o instanceof BooleanVariable){
                o = ((Expression)o).getValue();
            }
            return o.equals(value);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.value ? 1 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getStr() {
        return String.valueOf(value);
    }

    @Override
    public void getInputFromStdIn() {
       value = sc.nextBoolean();
    }

}
