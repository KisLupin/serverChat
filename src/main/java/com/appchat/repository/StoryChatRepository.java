package com.appchat.repository;

import com.appchat.model.response.StoryChatResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryChatRepository extends JpaRepository<StoryChatResponse,Integer> {
    @Query(nativeQuery = true,
    value = "SELECT " +
            "friend.id as id, " +
            "user_profile.id as friend_id, " +
            "user_profile.avatar as friend_avatar, " +
            "user_profile.avatar as friend_image, " +
            "user_profile.nameofchat as friend_nameofchat, " +
            "friend.created_time as created_time " +
            "FROM " +
            "friend JOIN user_profile ON " +
            "(friend.sender_id = :userId AND friend.receive_id = user_profile.id) OR " +
            "(friend.receive_id = :userId AND friend.sender_id = user_profile.id)")
    List<StoryChatResponse> findAllFriendStory(
            @Param(value = "userId") int userId
    );
}
