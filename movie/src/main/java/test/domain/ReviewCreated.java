package test.domain;

import test.domain.*;
import test.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReviewCreated extends AbstractEvent {

    private Long reviewId;
    private String content;
    private Long movieId;
}


