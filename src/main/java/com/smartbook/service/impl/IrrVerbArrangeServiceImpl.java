package com.smartbook.service.impl;

import com.smartbook.entity.IrrVerbArrange;
import com.smartbook.repository.IrrVerbArrangeRepo;
import com.smartbook.service.IrrVerbArrangeService;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IrrVerbArrangeServiceImpl implements IrrVerbArrangeService {
    private final IrrVerbArrangeRepo irrVerbArrangeRepo;

    @Override
    public List<IrrVerbArrange> findAll() {
        return Lists.newArrayList(irrVerbArrangeRepo.findAll())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<IrrVerbArrange> findByCaseNumber(Integer caseNumber) {
        return irrVerbArrangeRepo.findAllByCaseNumber(caseNumber);

    }

    @Override
    public List<IrrVerbArrange> findByCaseNumberPageable(Integer caseNumber, Pageable pageable) {
        Page<IrrVerbArrange> page = irrVerbArrangeRepo.findAllByCaseNumberPageable(caseNumber, pageable);
        return page.getContent();
    }

    @Override
    public List<IrrVerbArrange> findByCaseNumberAndGroupNumberPageable(Integer caseNumber, Integer groupNumber, Pageable pageable) {
        Page<IrrVerbArrange> page = irrVerbArrangeRepo.findAllByFfCaseNumberGroupNumberPage(caseNumber, groupNumber, pageable);
        return page.getContent();

    }

    @Override
    public List<IrrVerbArrange> searchByFirstChar(String firstChar) {
        return irrVerbArrangeRepo.findByWordStartingWithIgnoreCase(firstChar);
    }

    @Override
    public List<IrrVerbArrange> searchByContainChars(String containChars) {
        return irrVerbArrangeRepo.findByWordContainingIgnoreCase(containChars);
    }

    @Override
    public Optional<IrrVerbArrange> findByWord(String word) {
        return irrVerbArrangeRepo.findByWord(word);
    }

    @Override
    public Optional<IrrVerbArrange> findById(Long id) {
        return irrVerbArrangeRepo.findById(id);
    }
}
