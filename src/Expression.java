package minifunc;

public abstract class Expression {

    public Object getValue() {
        return execute();
    }

    abstract Expression execute();

}
