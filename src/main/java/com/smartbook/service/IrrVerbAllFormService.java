package com.smartbook.service;

import com.smartbook.entity.IrrVerbAllForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IrrVerbAllFormService {
    Optional<IrrVerbAllForm> findByWord(String word);
}
