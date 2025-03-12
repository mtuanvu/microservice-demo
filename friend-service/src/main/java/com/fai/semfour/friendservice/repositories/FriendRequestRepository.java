package com.fai.semfour.friendservice.repositories;

import com.fai.semfour.friendservice.entities.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, String> {

    @Query("SELECT CASE WHEN COUNT(fr) > 0 THEN true ELSE false END FROM FriendRequest fr WHERE fr.senderId = :senderId AND fr.receiverId = :receiverId")
    boolean existsBySenderIdAndReceiverId(@Param("senderId") String senderId, @Param("receiverId") String receiverId);

    @Query("SELECT fr FROM FriendRequest fr WHERE fr.receiverId = :receiverId AND fr.status = :status")
    List<FriendRequest> findByReceiverIdAndStatus(@Param("receiverId") String receiverId, @Param("status") FriendRequest.Status status);
}
