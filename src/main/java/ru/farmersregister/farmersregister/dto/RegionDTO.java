package ru.farmersregister.farmersregister.dto;

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

  private Long id;

  private String name;

  private Integer codeRegion;

  private Status status;

}
