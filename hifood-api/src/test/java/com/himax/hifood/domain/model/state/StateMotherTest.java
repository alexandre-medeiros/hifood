package com.himax.hifood.domain.model.state;

import com.google.gson.Gson;
import com.himax.hifood.domain.model.State;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StateMotherTest {

    private static final Long stateId = 1l;
    private static final String newName = "New Name State";

    public static State getNewState(){
        return StateBuilderTest.builder()
                .withName(newName)
                .build()
                .getState();
    }

    public static List<State> getStatesList() {
        State s1 = new State(1l, "Existing State 1");
        State s2 = new State(2l, "Existing State 2");
        State s3 = new State(3l, "Existing State 3");
        State s4 = new State(4l, "Existing State 4");
        State s5 = new State(5l, "Existing State 5");

        return Arrays.asList(s1,s2,s3,s4,s5);
    }
    public static void deepComparation(List<State> expected, List<State> actual) {
        Gson gson = new Gson();
        String expectedJson =  gson.toJson(expected);
        String actualJson =  gson.toJson(actual);
        assertEquals(expectedJson, actualJson);
    }
}
