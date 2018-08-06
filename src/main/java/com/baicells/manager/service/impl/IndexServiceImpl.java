package com.baicells.manager.service.impl;

import com.baicells.manager.config.properties.ConfigProperties;
import com.baicells.manager.mapper.*;
import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.IndexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    private HomeBannerDao homeBannerDao;
    @Autowired
    private MediaBrandDao mediaBrandDao;
    @Autowired
    private MediaCustomerDao mediaCustomerDao;
    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private MediaNewsDao mediaNewsDao;


    @Autowired
    private ProductPageDetailDao productPageDetailDao;


    @Autowired
    private ProductPageDao productPageDao;


    @Autowired
    private Solution5gPageDao solution5gPageDao;

    @Autowired
    private SolutionLetPageDao solutionLetPageDao;
    @Autowired
    private SupportDownloadDao supportDownloadDao;
    @Autowired
    private ProductPageContentDao productPageContentDao;

    @Override
    public void updateUrl(String url) {
        List<HomeBanner> homeBanners = homeBannerDao.selectAll();
        homeBanners.forEach(homeBanner -> {
            homeBanner.setImgUrl(convertUrl(url, homeBanner.getImgUrl()));
            homeBannerDao.updateByPrimaryKey(homeBanner);
        });

        List<MediaBrand> mediaBrands = mediaBrandDao.selectAll();
        mediaBrands.forEach(mediaBrand -> {
            mediaBrand.setImgUrl(convertUrl(url, mediaBrand.getImgUrl()));
            mediaBrandDao.updateByPrimaryKey(mediaBrand);
        });

        List<MediaCustomer> mediaCustomers = mediaCustomerDao.selectAll();
        mediaCustomers.forEach(mediaCustomer -> {
            mediaCustomer.setFileUrl(convertUrl(url, mediaCustomer.getFileUrl()));
            mediaCustomer.setNavPicUrl(convertUrl(url, mediaCustomer.getNavPicUrl()));
            mediaCustomerDao.updateByPrimaryKey(mediaCustomer);

        });

        List<MediaNews> mediaNews = mediaNewsDao.selectAll();
        mediaNews.forEach(mediaNews1 -> {
            mediaNews1.setForwardUrl(convertUrl(url, mediaNews1.getForwardUrl()));
            mediaNews1.setNavPicUrl(convertUrl(url, mediaNews1.getNavPicUrl()));
            mediaNewsDao.updateByPrimaryKey(mediaNews1);

        });


        List<ProductPageDetail> productPageDetails = productPageDetailDao.selectAll();
        productPageDetails.forEach(productPageDetail -> {
            productPageDetail.setPicUrl(convertUrl(url, productPageDetail.getPicUrl()));
            productPageDetail.setFileUrl(convertUrl(url, productPageDetail.getFileUrl()));
            if (StringUtils.isNotBlank(productPageDetail.getDesc())){
                productPageDetail.setDesc(productPageDetail.getDesc().replaceAll("http://localhost/",url));
            }
            productPageDetailDao.updateByPrimaryKey(productPageDetail);

        });

        List<ProductPage> productPages = productPageDao.selectAll();
        productPages.forEach(productPage -> {
            productPage.setPortalForwardUrl(convertUrl(url, productPage.getPortalForwardUrl()));
            productPage.setPortalPicUrl(convertUrl(url, productPage.getPortalPicUrl()));
            productPage.setNavPicUrl(convertUrl(url, productPage.getNavPicUrl()));
            productPage.setBgPicUrl(convertUrl(url, productPage.getBgPicUrl()));
            productPageDao.updateByPrimaryKey(productPage);

        });

        List<ProductPageContent> productPageContents = productPageContentDao.selectAll();
        productPageContents.forEach(pageContent -> {
            pageContent.setContent(pageContent.getContent().replaceAll("http://localhost/",url));
            productPageContentDao.updateByPrimaryKey(pageContent);

        });


        List<Solution5gPage> solution5gPages = solution5gPageDao.selectAll();
        solution5gPages.forEach(solution5gPage -> {
            solution5gPage.setPortalForwardUrl(convertUrl(url, solution5gPage.getPortalForwardUrl()));
            solution5gPage.setNavPicUrl(convertUrl(url, solution5gPage.getNavPicUrl()));
            solution5gPage.setBgPicUrl(convertUrl(url, solution5gPage.getBgPicUrl()));
            solution5gPage.setMiddlePic1(convertUrl(url, solution5gPage.getMiddlePic1()));
            solution5gPage.setMiddleBgPic(convertUrl(url, solution5gPage.getMiddleBgPic()));
            solution5gPage.setMiddlePic2(convertUrl(url, solution5gPage.getMiddlePic2()));
            solution5gPage.setMiddlePic3(convertUrl(url, solution5gPage.getMiddlePic3()));

            solution5gPage.setContentTop(solution5gPage.getContentTop().replaceAll("http://localhost/",url));
            solution5gPage.setContentBottom(solution5gPage.getContentBottom().replaceAll("http://localhost/",url));
            solution5gPageDao.updateByPrimaryKey(solution5gPage);

        });

        List<SolutionLetPage> solutionLetPages = solutionLetPageDao.selectAll();
        solutionLetPages.forEach(solutionLetPage -> {
            solutionLetPage.setPortalForwardUrl(convertUrl(url, solutionLetPage.getPortalForwardUrl()));
            solutionLetPage.setNavPicUrl(convertUrl(url, solutionLetPage.getNavPicUrl()));
            solutionLetPage.setBgPicUrl(convertUrl(url, solutionLetPage.getBgPicUrl()));
            solutionLetPage.setPortalPicUrl(convertUrl(url, solutionLetPage.getPortalPicUrl()));
            solutionLetPage.setContent(solutionLetPage.getContent().replaceAll("http://localhost/",url));
            solutionLetPageDao.updateByPrimaryKey(solutionLetPage);

        });

        List<SupportDownload> supportDownloads = supportDownloadDao.selectAll();
        supportDownloads.forEach(supportDownload -> {
            supportDownload.setUrl(convertUrl(url, supportDownload.getUrl()));
            supportDownloadDao.updateByPrimaryKey(supportDownload);

        });


    }


    private String convertUrl(String url, String data) {
        if (StringUtils.isNotBlank(data)) {

            return data.replaceAll("http://[a-zA-Z0-9.@:]*/+", url);

        }
        return data;
    }


}
