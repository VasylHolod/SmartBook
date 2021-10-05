package com.smartbook.repository;

import com.smartbook.entity.IrrVerbPhonetic;
import com.smartbook.entity.IrrVerbWord;
import com.smartbook.entity.enums.Dialect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IrrVerbPhoneticRepo extends JpaRepository<IrrVerbPhonetic, Long> {
    List<IrrVerbPhonetic> findByIrrVerbWord(IrrVerbWord irrVerbWord);

    List<IrrVerbPhonetic> findTop10ByOxfordStatus(HttpStatus status);

    List<IrrVerbPhonetic> findByIrrVerbWord_WordAndDialect(String word, Dialect di);

    IrrVerbPhonetic findByIrrVerbWord_Id(Long id);
}
