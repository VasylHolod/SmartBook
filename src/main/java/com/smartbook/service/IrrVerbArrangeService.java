package com.smartbook.service;

import com.smartbook.entity.IrrVerbArrange;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IrrVerbArrangeService {

    List<IrrVerbArrange> findAll();

    List<IrrVerbArrange> findByCaseNumber(Integer caseNumber);

    List<IrrVerbArrange> findByCaseNumberPageable(Integer caseNumber, Pageable pageable);

    List<IrrVerbArrange> findByCaseNumberAndGroupNumberPageable(Integer caseNumber, Integer groupNumber, Pageable pageable);

    ///Search
    List<IrrVerbArrange> searchByFirstChar(String firstChar);
    List<IrrVerbArrange> searchByContainChars(String containChars);

    Optional<IrrVerbArrange> findByWord(String word);

    Optional<IrrVerbArrange> findById(Long id);


}
