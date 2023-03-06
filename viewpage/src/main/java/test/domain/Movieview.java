package test.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Entity
@Table(name="Movieview_table")
@Data
public class Movieview {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long movieId;
        private String desc;
        private Long reviewCnt;
        private String movieStatus;
        private Long rsvId;
        private String rsvStatus;
        private Long customerId;
        private Integer numberOfSeats;


}
