package com.smartbook.repository;

import com.smartbook.entity.IrrVerbArrange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IrrVerbArrangeRepo extends JpaRepository<IrrVerbArrange, Long> {

//    @Query("select c from CaseStudy c where c.ffCaseNumber <= :caseNumber order by c.ffVerbGroupMarkId ASC, c.ffWord ASC")
//    List<IrrVerbArranged> findAllByCaseNumber(@Param("caseNumber") Integer caseNumber);

    List<IrrVerbArrange> findAllByCaseNumber(Integer caseNumber);

    @Query("select c from IrrVerbArrange c where c.caseNumber <= :caseNumber")
    Page<IrrVerbArrange> findAllByCaseNumberPageable(@Param("caseNumber") Integer caseNumber, Pageable pageable);

    @Query("select c from IrrVerbArrange c where c.caseNumber <= :caseNumber and c.groupId = :groupNumber")
    Page<IrrVerbArrange> findAllByFfCaseNumberGroupNumberPage(
            @Param("caseNumber") Integer caseNumber,
            @Param("groupNumber") Integer groupNumber,
            Pageable pageable);

    List<IrrVerbArrange> findByWordStartingWithIgnoreCase(String firstChar);

    List<IrrVerbArrange> findByWordContainingIgnoreCase(String containChars);

    Optional<IrrVerbArrange> findByWord(String word);
}
