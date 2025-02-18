package minifunc;

public class DoubleLiteral extends Literal {

    private final double value;

    DoubleLiteral(double value) {
        this.value = value;
    }

    static Literal create(double value) {
        return new DoubleLiteral(value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return this;
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
            return ((Number) o).doubleValue() == value;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }

}
