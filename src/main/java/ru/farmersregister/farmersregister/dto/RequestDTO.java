package ru.farmersregister.farmersregister.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

  private Collection<SearchRequestDTO> searchRequestDTO;

  private GlobalOperator globalOperator;

  public enum GlobalOperator {
    AND, OR
  }

}
