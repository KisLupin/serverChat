package com.appchat.repository;

import com.appchat.model.response.AddFriendResponse;
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
public interface DeclineFriend extends JpaRepository<AddFriendResponse,Integer> {
    @Modifying()
    @Query(nativeQuery = true,
            value = "delete from friend " +
                    "where sender_id =:sender_id and receive_id =:receive_id")
    void declined(
            @Param(value = "sender_id") int sender_id,
            @Param(value = "receive_id") int receive_id
    );
}
