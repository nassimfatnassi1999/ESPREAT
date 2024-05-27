package com.saccess.eventAndDonation.repositories;

import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.Type;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT u FROM Event u WHERE u.title LIKE %:title% ")
    List<Event> findBytitle(@Param("title") String title);

    @Query("select u from Event u where u.type=:type")
    public List<Event> getEventByType(@org.springframework.data.repository.query.Param("type") Type type);

    @Query("select u from Event u where u.date=:date")
    public List<Event> getEventByDate(@org.springframework.data.repository.query.Param("date") LocalDate date);

    @Query("SELECT  u from Event u where u.user_id =:userid")
    public List<Event> getAllEventbyUserId(@org.springframework.data.repository.query.Param("userid") Long userid);
}
