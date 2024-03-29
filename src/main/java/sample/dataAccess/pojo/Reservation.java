package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Daniel on 2014-11-14.
 */
@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date startDate;
    private Date endDate;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="clientId")
    private Client owner;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="reservationStatusId")
    private DictReservationStatus reservationStatus;
}
