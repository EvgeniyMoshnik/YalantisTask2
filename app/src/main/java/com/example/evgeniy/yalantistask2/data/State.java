package com.example.evgeniy.yalantistask2.data;


public enum State {

    WAIT(0),
    IN_WORK(1),
    DONE(2);

    private int mStateValue;

    State(int stateValue) {
        mStateValue = stateValue;
    }

    public int getValue() {
        return mStateValue;
    }

    public static State getByValue(int value) {
        switch (value) {
            case 1:
                return IN_WORK;
            case 2:
                return DONE;
            default:
                return WAIT;
        }
    }

}