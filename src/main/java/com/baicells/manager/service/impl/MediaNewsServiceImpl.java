package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.MediaNewsDao;
import com.baicells.manager.model.dto.MediaNewsDataDto;
import com.baicells.manager.model.dto.MediaQuery4WebDto;
import com.baicells.manager.model.entity.MediaNews;
import com.baicells.manager.model.entity.SolutionLetPage;
import com.baicells.manager.service.MediaNewsService;
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
public class MediaNewsServiceImpl implements MediaNewsService {

    @Autowired
    private MediaNewsDao mediaNewsDao;
    @Autowired
    private ConfigProperties configProperties;

    @Override
    public void delete(int id) {
        mediaNewsDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<MediaNews> listByQuery(MediaQuery4WebDto dto) {
        PageHelper.startPage(dto.getIndex(), dto.getSize());
        Example example = new Example(MediaNews.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(dto.getTitle()))
            criteria.andLike("title", dto.getTitle() + "%");
        if (StringUtils.isNotBlank(dto.getBeginTime()))
            criteria.andGreaterThanOrEqualTo("createTime", dto.getBeginTime());
        if (StringUtils.isNotBlank(dto.getEndTime()))
            criteria.andLessThan("createTime", dto.getEndTime());
        List<MediaNews> productPages = mediaNewsDao.selectByExample(example);
        PageInfo<MediaNews> pagePageInfo = new PageInfo<>(productPages, dto.getSize());
        return pagePageInfo;
    }

    @Override
    public void addOrUpdateByDto(MediaNewsDataDto dto) throws Exception {
        String htmlName = "media_news_" + CommonUtil.getUUID(16);
        Map<String, Object> fltMap = new HashMap<>();
        fltMap.put("data", dto);
        CreateHtmlUtils.createHtml(configProperties.getWebRoot(), "media_news_template.ftl", htmlName, fltMap);
        String forwardUrl = configProperties.getNginxWebRoot() + htmlName + ".html";

        MediaNews mediaNews;
        if (dto.getId() > 0) {
            mediaNews = mediaNewsDao.selectByPrimaryKey(dto.getId());
            if (mediaNews == null) {
                mediaNews = new MediaNews();
                mediaNews.setCreateTime(DateUtils.now());
            }
        } else {
            mediaNews = new MediaNews();
            mediaNews.setCreateTime(DateUtils.now());
        }

        mediaNews.setNavTitle(dto.getNavTitle());
        mediaNews.setNavPicUrl(dto.getNavPicUrl());
        mediaNews.setNavDesc(dto.getNavDesc());
        mediaNews.setForwardUrl(forwardUrl);
        mediaNews.setNavPicUrl(dto.getNavPicUrl());
        mediaNews.setContent(dto.getContent());
        if (mediaNews.getId() != null && mediaNews.getId() > 0) {
            mediaNewsDao.updateByPrimaryKey(mediaNews);
        } else {
            mediaNewsDao.insert(mediaNews);
        }

    }

    @Override
    public MediaNews getById(int id) {
        return mediaNewsDao.selectByPrimaryKey(id);
    }
}
