package com.appchat.repository;

import com.appchat.model.data.Message;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Configuration
@Repository
public interface DeletMessWhenUnfriend extends JpaRepository<Message,Integer> {
    @Modifying()
    @Query(nativeQuery = true, value = "delete from message " +
            "where (sender_id = :senderId and receiver_id = :receiverId) " +
            "or (sender_id =:receiverId and receiver_id = :senderId)")
    void unfriend(
            @Param("senderId") int senderId,
            @Param("receiverId") int receiverId
    );
}
