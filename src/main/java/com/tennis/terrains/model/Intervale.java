package com.tennis.terrains.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
/**
 * allow to set the time per hour or per minutes for the reservation in minutes
 */
@Getter
public enum Intervale {

    NUMBER_15(15),

    NUMBER_30(30),

    NUMBER_60(60);

    private Integer value;

    Intervale(Integer value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static Intervale fromValue(Integer value) {
        for (Intervale b : Intervale.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
