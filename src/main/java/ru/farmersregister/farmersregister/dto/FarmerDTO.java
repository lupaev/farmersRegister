package ru.farmersregister.farmersregister.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.farmersregister.farmersregister.entity.LegalForm;
import ru.farmersregister.farmersregister.entity.Region;
import ru.farmersregister.farmersregister.entity.Status;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FarmerDTO {

//  @JsonIgnore
  private Long id;

  private String name;

  private LegalForm legalForm;

  private Integer inn;

  private Integer kpp;

  private Integer ogrn;

//  @JsonIgnore
//  @Basic(fetch = FetchType.LAZY)
  @JsonBackReference
  private Region region;

  @JsonIgnore
  private Collection<Region> regionCollection;

//  @JsonFormat(pattern="yyyy-MM-dd")
  @DateTimeFormat(pattern= "yyyy-MM-dd")
  private LocalDate dateRegistration;

  private Status status;




}
