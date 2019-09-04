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
public interface AcceptFriendRepository extends JpaRepository<AddFriendResponse,Integer> {
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value =
            "update friend set isaccept = 1 " +
                    "where sender_id =:sender_id and receive_id =:receive_id ")
    @Transactional
    void accepted(
            @Param(value = "sender_id") int sender_id,
            @Param(value = "receive_id") int receive_id
    );
}
