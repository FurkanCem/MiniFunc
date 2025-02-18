package minifunc;

public class IntegerLiteral extends Literal {

    private final int value;

    IntegerLiteral(int value) {
        this.value = value;
    }

    static Literal create(int value) {
        return new IntegerLiteral(value);
    }

    @Override
    Expression execute() {
        return this;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object o) {
        try{
            if(o instanceof DoubleLiteral || o instanceof DoubleVariable 
                    || o instanceof IntegerLiteral || o instanceof IntegerVariable){
                o = ((Expression)o).getValue();
            }
            return ((Number) o).doubleValue() == (double)value;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.value;
        return hash;
    }

}
