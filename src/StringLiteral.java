package minifunc;

import java.util.Objects;

public class StringLiteral extends Literal implements Str {

    public final String value;

    StringLiteral(String value) {
        this.value = value;
    }

    static Literal create(String value) {
        return new StringLiteral(value);
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
        return value;
    }

    @Override
    public String getStr() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        try{
            if(o instanceof StringLiteral || o instanceof StringVariable){
                o = ((Expression)o).getValue();
            }
            return o instanceof Str && o.equals(value);
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.value);
        return hash;
    }

    

}
