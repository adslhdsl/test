package test.infra;

import test.domain.*;
import test.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieviewViewHandler {

    @Autowired
    private MovieviewRepository movieviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMovieRegistered_then_CREATE_1 (@Payload MovieRegistered movieRegistered) {
        try {

            if (!movieRegistered.validate()) return;

            // view 객체 생성
            Movieview movieview = new Movieview();
            // view 객체에 이벤트의 Value 를 set 함
            movieview.setMovieId(movieRegistered.getMovieId());
            movieview.setDesc(movieRegistered.getDesc());
            movieview.setMovieStatus(movieRegistered.getStatus());
            movieview.setNumberOfSeats(movieRegistered.getNumberOfSeats());
            movieview.setReviewCnt(0);
            // view 레파지 토리에 save
            movieviewRepository.save(movieview);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationConfirmed_then_UPDATE_1(@Payload ReservationConfirmed reservationConfirmed) {
        try {
            if (!reservationConfirmed.validate()) return;
                // view 객체 조회
            Optional<Movieview> movieviewOptional = movieviewRepository.findByMovieId(reservationConfirmed.getMovieId());

            if( movieviewOptional.isPresent()) {
                 Movieview movieview = movieviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                movieview.setRsvId(reservationConfirmed.getRsvId());    
                movieview.setCustomerId(reservationConfirmed.getCustomerId());    
                movieview.setRsvStatus(reservationConfirmed.getStatus());    
                movieview.setNumberOfSeats(movieview.getNumberOfSeats() - 1);
                // view 레파지 토리에 save
                 movieviewRepository.save(movieview);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCancelled_then_UPDATE_2(@Payload ReservationCancelled reservationCancelled) {
        try {
            if (!reservationCancelled.validate()) return;
                // view 객체 조회

                List<Movieview> movieviewList = movieviewRepository.findByRsvId(reservationCancelled.getRsvId());
                for(Movieview movieview : movieviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    movieview.setRsvStatus(reservationCancelled.getStatus());
                    movieview.setNumberOfSeats(movieview.getNumberOfSeats() + 1);
                // view 레파지 토리에 save
                movieviewRepository.save(movieview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenMovieModified_then_UPDATE_3(@Payload MovieModified movieModified) {
        try {
            if (!movieModified.validate()) return;
                // view 객체 조회
            Optional<Movieview> movieviewOptional = movieviewRepository.findByMovieId(movieModified.getMovieId());

            if( movieviewOptional.isPresent()) {
                 Movieview movieview = movieviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                movieview.setDesc(movieModified.getDesc());    
                movieview.setMovieStatus(movieModified.getStatus());    
                movieview.setNumberOfSeats(movieModified.getNumberOfSeats());    
                // view 레파지 토리에 save
                 movieviewRepository.save(movieview);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMovieDeleted_then_DELETE_1(@Payload MovieDeleted movieDeleted) {
        try {
            if (!movieDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            movieviewRepository.deleteById(movieDeleted.getMovieId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

