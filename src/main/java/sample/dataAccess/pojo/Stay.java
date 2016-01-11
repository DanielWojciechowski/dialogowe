package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Daniel on 2014-12-06.
 */
@Entity
@Data
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="clientId")
    private Client client;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="reservationId")
    private Reservation reservation;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="roomsInReservationId")
    private RoomsInReservation roomsInReservation;
}
