package test.external;

import lombok.Data;
import java.util.Date;
@Data
public class Payment {

    private Long payId;
    private Long rsvId;
    private Long movieId;
    private String status;
}


