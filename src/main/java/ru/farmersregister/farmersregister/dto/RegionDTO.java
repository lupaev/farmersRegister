package ru.farmersregister.farmersregister.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.farmersregister.farmersregister.entity.Farmer;
import ru.farmersregister.farmersregister.entity.Status;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegionDTO {

//  @JsonIgnore
  private Long id;

  private String name;

  private Integer codeRegion;

  private Status status;

  @JsonIgnore
  private Collection<Farmer> farmers;

  @JsonIgnore
  private Collection<Farmer> farmerCollection;

}
