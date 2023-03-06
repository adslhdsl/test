package test.domain;

import test.domain.ReservationCreated;
import test.domain.ReservationCancelRequested;
import test.domain.ReservationConfirmed;
import test.domain.ReservationCancelled;
import test.ReservationApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Reservation_table")
@Data

public class Reservation  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long rsvId;
    
    
    
    
    
    private Long movieId;
    
    
    
    
    
    private Long payId;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private Long customerId;
    
    
    
    
    
    private Date screeningDate;

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.




        ReservationCreated reservationCreated = new ReservationCreated(this);
        reservationCreated.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){


        ReservationCancelRequested reservationCancelRequested = new ReservationCancelRequested(this);
        reservationCancelRequested.publishAfterCommit();



        ReservationConfirmed reservationConfirmed = new ReservationConfirmed(this);
        reservationConfirmed.publishAfterCommit();



        ReservationCancelled reservationCancelled = new ReservationCancelled(this);
        reservationCancelled.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreUpdate
    public void onPreUpdate(){
    }

    public static ReservationRepository repository(){
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(ReservationRepository.class);
        return reservationRepository;
    }




    public static void confirmReserve(PaymentApproved paymentApproved){

        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);


         });
        */

        
    }
    public static void confirmCancel(PaymentCancelled paymentCancelled){

        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCancelled.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);


         });
        */

        
    }


}
