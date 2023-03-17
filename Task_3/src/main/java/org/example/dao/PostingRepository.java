package org.example.dao;

import org.example.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, String> {

    @Query("SELECT p FROM Posting p WHERE p.isAuthorized = ?1 AND p.docDate >= ?2 AND p.docDate <= ?3")
    List<Posting> getPostingsByAuthorizedAndDocDateBeforeAndDocDateAfter(boolean isAuthorized, LocalDate startDate, LocalDate endDate);
}
