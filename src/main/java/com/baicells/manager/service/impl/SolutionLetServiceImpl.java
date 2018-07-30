package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.SolutionLetPageDao;
import com.baicells.manager.service.SolutionLETService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionLetServiceImpl implements SolutionLETService {

    @Autowired
    private SolutionLetPageDao solutionLetPageDao;
}
