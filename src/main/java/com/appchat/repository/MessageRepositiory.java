package com.appchat.repository;

import com.appchat.model.data.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepositiory extends JpaRepository<Message, Integer> {

}
