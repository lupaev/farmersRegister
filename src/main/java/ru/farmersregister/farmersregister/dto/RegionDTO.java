package ru.farmersregister.farmersregister.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.farmersregister.farmersregister.entity.Status;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegionDTO {

  @JsonIgnore
  private long id;

  private String name;

  private int codeRegion;

  private Status status;

}