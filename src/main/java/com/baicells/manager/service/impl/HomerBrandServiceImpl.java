package com.baicells.manager.service.impl;

import com.baicells.manager.mapper.HomeBrandDao;
import com.baicells.manager.model.dto.HomeBannerDataDto;
import com.baicells.manager.model.dto.HomeQuery4WebDto;
import com.baicells.manager.model.entity.HomeBanner;
import com.baicells.manager.model.entity.HomeBrand;
import com.baicells.manager.service.HomerBrandService;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class HomerBrandServiceImpl implements HomerBrandService {
    @Autowired
    private HomeBrandDao homeBrandDao;

    @Override
    public PageInfo<HomeBrand> listByQuery(HomeQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(HomeBrand.class);
        List<HomeBrand> productPages = homeBrandDao.selectByExample(example);
        PageInfo<HomeBrand> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void delete(int id) {
        homeBrandDao.deleteByPrimaryKey(id);
    }

    @Override
    public void addByDto(HomeBannerDataDto dto) {
        HomeBrand homeBrand = new HomeBrand();
        homeBrand.setImgUrl(dto.getImgUrl());
        homeBrand.setTitle(dto.getTitle());
        homeBrand.setEnable(false);
        homeBrand.setCreateTime(DateUtils.now());
        homeBrandDao.insert(homeBrand);
    }

    @Override
    public void updateBrandStatus(int id) {
        HomeBrand homeBrand = homeBrandDao.selectByPrimaryKey(id);
        if (homeBrand !=null){
            //
            List<HomeBrand> notInHomeBrands = notInId(id);
            notInHomeBrands.forEach(notInHomeBrand->{
                notInHomeBrand.setEnable(false);
                homeBrandDao.updateByPrimaryKey(notInHomeBrand);
            });
            homeBrand.setEnable(true);
            homeBrandDao.updateByPrimaryKey(homeBrand);
        }
    }

    public List<HomeBrand> notInId(int id){
        Example example = new Example(HomeBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id",id);
        List<HomeBrand> homeBrands = homeBrandDao.selectByExample(example);
        return homeBrands;
    }

    @Override
    public HomeBrand getByEnable() {
        Example example = new Example(HomeBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enable",true);
        return homeBrandDao.selectOneByExample(example);
    }
}
