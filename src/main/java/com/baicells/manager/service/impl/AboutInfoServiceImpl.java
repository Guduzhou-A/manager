package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.AboutInfoDao;
import com.baicells.manager.model.dto.AboutPageQuery4WebDto;
import com.baicells.manager.model.entity.AboutInfo;
import com.baicells.manager.service.AboutInfoService;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AboutInfoServiceImpl implements AboutInfoService {

    @Autowired
    private AboutInfoDao aboutInfoDao;

    @Override
    public PageInfo<AboutInfo> listByQuery(AboutPageQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(AboutInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getNickName()))
            criteria.andLike("firstName", dto.getNickName() + "%");
        if (StringUtils.isNotBlank(dto.getNickName()))
            criteria.orLike("lastName", dto.getNickName() + "%");
        if (StringUtils.isNotBlank(dto.getCountry()))
            criteria.andEqualTo("country", dto.getCountry());

        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<AboutInfo> productPages = aboutInfoDao.selectByExample(example);
        PageInfo<AboutInfo> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public AboutInfo getById(int id) {
        return aboutInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public void deleteDataById(int id) {
        aboutInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateByEntity(AboutInfo aboutInfo) {
        aboutInfo.setCreateTime(DateUtils.now());
        aboutInfoDao.insert(aboutInfo);
    }
}
