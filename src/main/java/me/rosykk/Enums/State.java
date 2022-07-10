package me.rosykk.Enums;

public class State {

    private Type type;

    public State(final Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        NONE,
        SETUP,
        DROP
    }
}
