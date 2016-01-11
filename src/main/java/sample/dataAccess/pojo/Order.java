package sample.dataAccess.pojo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Daniel on 2014-12-06.
 */
@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private boolean done=false;

    @NotNull
    private String type;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="roomId")
    private Room room;


}
