package minifunc;

public class BooleanLiteral extends Literal implements Str {

    private final boolean value;

    BooleanLiteral(boolean value) {
        this.value = value;
    }

    static Literal create(boolean value) {
        return new BooleanLiteral(value);
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
    public String getStr() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        try{
            if(o instanceof BooleanLiteral || o instanceof BooleanVariable){
                o = ((Expression)o).getValue();
            }
            return o.equals(value);
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.value ? 1 : 0);
        return hash;
    }

    

}
