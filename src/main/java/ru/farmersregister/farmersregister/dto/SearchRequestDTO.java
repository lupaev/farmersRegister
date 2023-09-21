package ru.farmersregister.farmersregister.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

    String column;
    String value;
    Operation operation;

    public enum Operation {
        EQUAL, LIKE, IN, GT, LT, BT
    }

}
