package com.smartbook.service;

import com.smartbook.entity.VerbGroupMark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VerbGroupMarkService {
    List<VerbGroupMark> findAllGroup();

    void addGroup(VerbGroupMark verbGroupMark);
}
