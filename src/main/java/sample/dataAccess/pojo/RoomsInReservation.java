package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Daniel on 2014-12-06.
 */
@Entity
@Data
public class RoomsInReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="reservationId")
    private Reservation reservation;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
    @JoinColumn(name="roomId")
    private Room room;


}
