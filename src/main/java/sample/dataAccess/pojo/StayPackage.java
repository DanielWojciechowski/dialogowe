package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Daniel on 2014-12-06.
 */

@Entity
@Data
public class StayPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    private String description;
    private double roomDiscountPercentage;
    private double serviceCost;
}
