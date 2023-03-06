package test.domain;

import test.domain.*;
import test.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class ReservationConfirmed extends AbstractEvent {

    private Long rsvId;
    private Long movieId;
    private String status;
    private Long payId;
    private Long customerId;

    public ReservationConfirmed(Reservation aggregate){
        super(aggregate);
    }
    public ReservationConfirmed(){
        super();
    }
}
