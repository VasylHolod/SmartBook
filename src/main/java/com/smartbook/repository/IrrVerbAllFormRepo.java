package com.smartbook.repository;

import com.smartbook.entity.IrrVerbAllForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IrrVerbAllFormRepo extends JpaRepository<IrrVerbAllForm, Long> {

    Optional<IrrVerbAllForm> findByPresentSimpleLike(String presentSimple);

}
