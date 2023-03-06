package test.domain;

import test.domain.ReviewCreated;
import test.domain.ReviewModified;
import test.domain.ReviewDeleted;
import test.ReviewApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Review_table")
@Data

public class Review  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long reviewId;
    
    
    
    
    
    private Long movieId;
    
    
    
    
    
    private String content;
    
    
    
    
    
    private Long customerId;

    @PostPersist
    public void onPostPersist(){


        ReviewCreated reviewCreated = new ReviewCreated(this);
        reviewCreated.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){


        ReviewModified reviewModified = new ReviewModified(this);
        reviewModified.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
    }
    @PreRemove
    public void onPreRemove(){


        ReviewDeleted reviewDeleted = new ReviewDeleted(this);
        reviewDeleted.publishAfterCommit();

    }

    public static ReviewRepository repository(){
        ReviewRepository reviewRepository = ReviewApplication.applicationContext.getBean(ReviewRepository.class);
        return reviewRepository;
    }






}
