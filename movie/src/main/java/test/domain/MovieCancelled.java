package test.domain;

import test.domain.*;
import test.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class MovieCancelled extends AbstractEvent {

    private Long movieId;
    private String status;
    private String desc;
    private Long reviewCnt;
    private Integer numberOfSeats;

    public MovieCancelled(Movie aggregate){
        super(aggregate);
    }
    public MovieCancelled(){
        super();
    }
}
