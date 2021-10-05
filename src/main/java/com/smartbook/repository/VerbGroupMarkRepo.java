package com.smartbook.repository;

import com.smartbook.entity.VerbGroupMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerbGroupMarkRepo extends JpaRepository <VerbGroupMark, Long> {
}
