package ru.farmersregister.farmersregister.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "region")
@Entity
public class Region {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "code_region")
  private Integer codeRegion;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private Status status;



  @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
  @JsonIgnore
  private Collection<Farmer> farmers;



//  @ManyToMany(mappedBy = "regionCollection")
//  private Collection<Farmer> farmerCollection;


  @ManyToOne
  @JoinColumn(name = "farmer_id")
  @JsonIgnore
  private Farmer farmer;

}
