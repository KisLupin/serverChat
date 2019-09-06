package com.appchat.repository;

import com.appchat.model.data.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessAllImage extends JpaRepository<Message,Integer> {
    @Query(nativeQuery = true ,
              value = "select * from message where type = :type and (sender_id = :senderId and receiver_id = :receiverId) " +
                      "union " +
                      "select * from message where type = :type and (sender_id = :receiverId and receiver_id = :senderId)")
    List<Message> allImage(
            @Param("type") String type,
            @Param("senderId") int senderId,
            @Param("receiverId") int receiverId
    );
}
