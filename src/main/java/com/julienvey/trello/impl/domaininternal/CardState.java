package com.julienvey.trello.impl.domaininternal;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CardState {
    ALL("all"),
    CLOSED("closed"),
    NONE("none"),
    OPEN("open"),
    VISIBLE("visible");

    private final String state;

    CardState(String state) {
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }
}
