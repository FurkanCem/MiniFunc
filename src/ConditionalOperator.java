package minifunc;

enum ConditionalOperator {
    Equal,
    NotEqual,
    Less,
    LessEqual,
    Greater,
    GreaterEqual;

    @Override
    public String toString() {
        switch (this) {
            case Equal -> {
                return "==";
            }
            case NotEqual -> {
                return "!=";
            }
            case Less -> {
                return "<";
            }
            case LessEqual -> {
                return "<=";
            }
            case Greater -> {
                return ">";
            }
            case GreaterEqual -> {
                return ">=";
            }
        }

        return super.toString();
    }
}
