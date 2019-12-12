package enums;

import utils.OperatorParser;

public enum OperatorType {

    ADD,
    SUBTRACT,
    MULTIPLY,
    POWER,
    ROOT,
    PERCENTAGE,
    LOG,
    SIN,
    COS,
    TAN,
    FACTORIAL,
    DIVIDE;

    @Override
    public String toString() {
        return OperatorParser.valueOf(this);
    }
}
