package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.SolutionLetPageDao;
import com.baicells.manager.model.dto.SolutionLetDataDto;
import com.baicells.manager.model.dto.SolutionQuery4WebDto;
import com.baicells.manager.model.entity.Solution5gPage;
import com.baicells.manager.model.entity.SolutionLetPage;
import com.baicells.manager.service.SolutionLETService;
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
public class SolutionLetServiceImpl implements SolutionLETService {

    @Autowired
    private SolutionLetPageDao solutionLetPageDao;
    @Autowired
    private ConfigProperties configProperties;

    @Override
    public PageInfo<SolutionLetPage> listByQuery(SolutionQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(SolutionLetPage.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andLike("title", dto.getTitle() + "%");
        if (dto.getStatus() >= 0)
            criteria.andEqualTo("enable", dto.getStatus());
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<SolutionLetPage> productPages = solutionLetPageDao.selectByExample(example);
        PageInfo<SolutionLetPage> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public SolutionLetPage getById(int id) {
        return solutionLetPageDao.selectByPrimaryKey(id);
    }

    @Override
    public void addOrUpdateByDto(SolutionLetDataDto dto) throws Exception {
        String htmlName = "solution_let_" + CommonUtil.getUUID(16);
        Map<String, Object> fltMap = new HashMap<>();
        fltMap.put("data", dto);
        String oldUrl = null;
        SolutionLetPage solutionLetPage;
        if (dto.getId() != null && dto.getId() > 0) {
            solutionLetPage = solutionLetPageDao.selectByPrimaryKey(dto.getId());
            if (solutionLetPage == null) {
                solutionLetPage = new SolutionLetPage();
                solutionLetPage.setEnable(false);
                solutionLetPage.setCreateTime(DateUtils.now());
            } else {
                oldUrl = solutionLetPage.getPortalForwardUrl();
                solutionLetPage.setUpdateTime(DateUtils.now());
            }
        } else {
            solutionLetPage = new SolutionLetPage();
            solutionLetPage.setEnable(false);
            solutionLetPage.setCreateTime(DateUtils.now());
        }

        CreateHtmlUtils.createHtml(configProperties.getWebRoot(), "solution_let_template.ftl", htmlName, fltMap,oldUrl);
        String forwardUrl = configProperties.getNginxWebRoot() + htmlName + ".html";


        solutionLetPage.setTitle(dto.getTitle());
        solutionLetPage.setBgPicUrl(dto.getBgPicUrl());
        solutionLetPage.setPortalForwardUrl(forwardUrl);
        solutionLetPage.setPortalPicUrl(dto.getPortalPicUrl());
        solutionLetPage.setNavPicUrl(dto.getNavPicUrl());
        solutionLetPage.setContent(dto.getContent());

        if (solutionLetPage.getId() != null && solutionLetPage.getId() > 0) {
            solutionLetPageDao.updateByPrimaryKey(solutionLetPage);
        } else {
            solutionLetPageDao.insert(solutionLetPage);
        }

    }

    @Override
    public void delete(int id) {
        solutionLetPageDao.deleteByPrimaryKey(id);

    }

    @Override
    public void updateById(SolutionLetPage solutionLetPage) {
        solutionLetPageDao.updateByPrimaryKey(solutionLetPage);
    }

    @Override
    public List<SolutionLetPage> listByEnable() {
        Example example = new Example(SolutionLetPage.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enable", true);
        return solutionLetPageDao.selectByExample(example);
    }
}
