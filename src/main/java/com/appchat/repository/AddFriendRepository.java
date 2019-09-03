package com.appchat.repository;

import com.appchat.model.request.AddFriendRequest;
import com.appchat.model.response.BaseResponse;
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
public interface AddFriendRepository extends JpaRepository<AddFriendRequest,Integer> {
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,
            value = "insert into friend(sender_id,receive_id,send_addfriend) " +
                    "values (:sender_id,:receiver_id,:is_send)")
    @Transactional
    void sendRequestAddFriend(
            @Param("sender_id") int sender_id,
            @Param("receiver_id") int receiver_id,
            @Param("is_send") int is_send
    );
}
