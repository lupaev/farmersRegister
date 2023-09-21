package ru.farmersregister.farmersregister.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class RequestDTO {

    private Collection<SearchRequestDTO> searchRequestDTO;

    private GlobalOperator globalOperator;

    public enum GlobalOperator {
        AND, OR
    }

}
