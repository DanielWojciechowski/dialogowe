package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Daniel on 2014-11-14.
 */
@Entity
@Data
public class DictCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(unique=true)
    private String name;
    @Column(unique=true)
    private String code;
}
