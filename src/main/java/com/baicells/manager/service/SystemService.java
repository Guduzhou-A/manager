package com.baicells.manager.service;

import com.baicells.manager.model.entity.SysPageConfig;

public interface SystemService {
    SysPageConfig execGetOne();

    void update5gContent(String content);
}
