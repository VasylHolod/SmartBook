package com.smartbook.service.impl;

import com.smartbook.entity.VerbGroupMark;
import com.smartbook.repository.VerbGroupMarkRepo;
import com.smartbook.service.VerbGroupMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VerbGroupMarkServiceImpl implements VerbGroupMarkService {
    private final VerbGroupMarkRepo verbGroupMarkRepo;

    @Override
    public List<VerbGroupMark> findAllGroup() {
        return verbGroupMarkRepo.findAll();
    }

    @Override
    public void addGroup(VerbGroupMark verbGroupMark) {
        verbGroupMarkRepo.save(verbGroupMark);
    }
}
