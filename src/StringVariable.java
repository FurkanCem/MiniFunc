package minifunc;

import java.util.Objects;

public class StringVariable extends Variable implements Str {

    public String value;

    StringVariable(String name) {
        super(name);
        this.value = "";
    }

    StringVariable(String name, String value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, String value) {
        return new StringVariable(name, value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new StringLiteral(value);
    }

    @Override
    void assign(Variable var) {
        StringVariable strVar = (StringVariable) var;
        value = strVar.value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getStr() {
        return value;
    }

    @Override
    public void assign(Expression var) {
        String booleanVar = var.getValue().toString();
        value = booleanVar;
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
        hash = 19 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
    @Override
    public void getInputFromStdIn() {
       value = sc.next();
    }
    

}
