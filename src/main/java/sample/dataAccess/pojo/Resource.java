package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Daniel on 2015-01-04.
 */

@Entity
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    private int amount;
    
    @OneToOne
    @JoinColumn(name="productId")
    private Product product;
}
