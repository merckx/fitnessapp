package com.fitness.model;

/**
 * Created by yani on 23.11.2016 г..
 */

public class Exercise {

    public static final String MUSCLE_GROUP_CHILD = "exercises";

    private String name;

    public Exercise()
    {}

    public Exercise(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
