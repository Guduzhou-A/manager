package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.Solution5gPageDao;
import com.baicells.manager.model.dto.Solution5gDataDto;
import com.baicells.manager.model.dto.SolutionQuery4WebDto;
import com.baicells.manager.model.entity.ProductPage;
import com.baicells.manager.model.entity.Solution5gPage;
import com.baicells.manager.service.Solution5gService;
import com.baicells.manager.utils.CommonUtil;
import com.baicells.manager.utils.CreateHtmlUtils;
import com.baicells.manager.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Solution5gServiceImpl implements Solution5gService {

    @Autowired
    private Solution5gPageDao solution5gPageDao;
    @Autowired
    private ConfigProperties configProperties;

    @Override
    public PageInfo<Solution5gPage> listByQuery(SolutionQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(Solution5gPage.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andLike("title", dto.getTitle() + "%");
        if (dto.getStatus() >= 0)
            criteria.andEqualTo("enable", dto.getStatus());
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<Solution5gPage> productPages = solution5gPageDao.selectByExample(example);
        PageInfo<Solution5gPage> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void addOrUpdateByDto(Solution5gDataDto dto) throws Exception {

        String htmlName = "solution_5g_" + CommonUtil.getUUID(16);
        Map<String, Object> fltMap = new HashMap<>();
        fltMap.put("data", dto);
        CreateHtmlUtils.createHtml(configProperties.getWebRoot(), "solution_5g_templadte.ftl", htmlName, fltMap);
        String forwardUrl = configProperties.getNginxWebRoot() + htmlName + ".html";

        Solution5gPage solution5gPage;
        if (dto.getId() != null && dto.getId() > 0) {
            solution5gPage = solution5gPageDao.selectByPrimaryKey(dto.getId());
            if (solution5gPage == null) {
                solution5gPage = new Solution5gPage();
                solution5gPage.setEnable(false);
                solution5gPage.setCreateTime(DateUtils.now());
            } else {
                solution5gPage.setUpdateTime(DateUtils.now());
            }
        } else {
            solution5gPage = new Solution5gPage();
            solution5gPage.setEnable(false);
            solution5gPage.setCreateTime(DateUtils.now());
        }
        solution5gPage.setTitle(dto.getTitle());
        solution5gPage.setBgPicUrl(dto.getBgPicUrl());
        solution5gPage.setContentDesc(dto.getContentDesc());
        solution5gPage.setContentTop(dto.getContentTop());
        solution5gPage.setNavPicUrl(dto.getNavPicUrl());
        solution5gPage.setNavDesc(dto.getNavDesc());
        solution5gPage.setMiddleTitle(dto.getMiddleTitle());
        solution5gPage.setMiddleBgPic(dto.getMiddleBgUrl());
        solution5gPage.setMiddlePic1(dto.getMiddlePic1());
        solution5gPage.setMiddlePic2(dto.getMiddlePic2());
        solution5gPage.setMiddlePic3(dto.getMiddlePic3());
        solution5gPage.setMiddleText1(dto.getMiddleTitle1());
        solution5gPage.setMiddleText2(dto.getMiddleTitle2());
        solution5gPage.setMiddleText3(dto.getMiddleTitle3());
        solution5gPage.setPortalForwardUrl(forwardUrl);
        if (solution5gPage.getId() != null && solution5gPage.getId() > 0) {
            solution5gPageDao.updateByPrimaryKey(solution5gPage);
        } else {
            solution5gPageDao.insert(solution5gPage);
        }


    }

    @Override
    public void delete(int id) {
        solution5gPageDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(Solution5gPage solution5gPage) {
        solution5gPageDao.updateByPrimaryKey(solution5gPage);
    }

    @Override
    public Solution5gPage getById(int id) {
        return solution5gPageDao.selectByPrimaryKey(id);
    }
}
