package com.appchat.repository;

import com.appchat.model.data.Friend;
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
@EnableJpaRepositories
public interface Unfriend extends JpaRepository<Friend,Integer> {
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value =
            "delete from friend" +
                    " where (sender_id =:sender_id and receive_id = :receive_id) " +
                    "or (sender_id = :receive_id and receive_id =:sender_id)")
    @Transactional
    void removeFriend(
            @Param("sender_id") int sender_id,
            @Param("receive_id") int receive_id
    );
}
