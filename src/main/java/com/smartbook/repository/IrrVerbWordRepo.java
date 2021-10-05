package com.smartbook.repository;

import com.smartbook.entity.IrrVerbAllForm;
import com.smartbook.entity.IrrVerbWord;
import com.smartbook.entity.enums.Tenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IrrVerbWordRepo extends JpaRepository<IrrVerbWord, Long> {

    List<IrrVerbWord> findAllByIrrVerbAllForm(IrrVerbAllForm verbAllForm);

    Optional<IrrVerbWord> findByWordAndAndTensesAndAndIrrVerbAllForm(String word, Tenses tenses, IrrVerbAllForm irrVerbAllForm);
//    IrrVerbWord findByIrrVerbPhonetics(List<IrrVerbPhonetic> list);
    void deleteById(Long id);

}
