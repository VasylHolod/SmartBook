package com.smartbook.service.impl;

import com.smartbook.repository.IrrVerbWordRepo;
import com.smartbook.service.IrrVerbWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IrrVerbWordServiceImpl implements IrrVerbWordService {
    private final IrrVerbWordRepo irrVerbWordRepo;

}
