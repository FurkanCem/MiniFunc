package minifunc;

public class DoubleVariable extends Variable {

    private double value;

    DoubleVariable(String name) {
        super(name);
        this.value = 0.0;
    }

    DoubleVariable(String name, double value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, double value) {
        return new DoubleVariable(name, value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new DoubleLiteral(value);
    }

    @Override
    void assign(Variable var) {
        try {
            double doubleVar = ((Number) var.getValue()).doubleValue();
            value = doubleVar;
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void assign(Expression var) {
        try {
            double booleanVar = ((Number) var.execute().getValue()).doubleValue();
            value = booleanVar;
        } catch (Exception e) {
            System.out.println("Assigning non numeric value to double variable!");
        }
    }

    @Override
    public boolean equals(Object o) {
        try{
            if(o instanceof DoubleLiteral || o instanceof DoubleVariable 
                    || o instanceof IntegerLiteral || o instanceof IntegerVariable){
                o = ((Expression)o).getValue();
            }
            return ((Number) o).doubleValue() == value;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }
    
    @Override
    public void getInputFromStdIn() {
       value = sc.nextDouble();
    }

}
