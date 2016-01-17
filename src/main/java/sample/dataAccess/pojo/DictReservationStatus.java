package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Daniel on 2014-11-14.
 */
@Entity
@Data
public class DictReservationStatus {

    public static final String UNCONFIRMED = "NP";
    public static final String CONFIRMED = "P";
    public static final String CANCELED = "A";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String status;

    @Column(unique = true)
    private String code;
}
