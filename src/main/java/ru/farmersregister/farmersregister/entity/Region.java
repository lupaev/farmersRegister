package ru.farmersregister.farmersregister.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Сущность района
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "region")
@Entity
public class Region {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование
     */
    @Column(name = "name")
    private String name;

    /**
     * Код района
     */
    @Column(name = "code_region")
    private Integer codeRegion;


}
