package com.baicells.manager.web.controller;

import com.baicells.manager.model.entity.*;
import com.baicells.manager.service.*;
import com.baicells.manager.utils.DateUtils;
import com.baicells.manager.utils.Result;
import com.baicells.manager.utils.ResultCode;
import com.baicells.manager.utils.WebUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class APIController {

    @Autowired
    private Solution5gService solution5gService;
    @Autowired
    private SolutionLETService solutionLETService;
    @Autowired
    private ProductPageService productPageService;
    @Autowired
    private HomeBannerService homeBannerServiceImpl;
    @Autowired
    private MediaNewsService mediaNewsServiceImpl;
    @Autowired
    private MediaBrandService mediaBrandServiceImpl;
    @Autowired
    private MediaCustomerService mediaCustomerServiceImpl;
    @Autowired
    private SupportDownloadService supportDownloadServiceImpl;

    @Autowired
    private SystemService systemServiceImpl;
    @Autowired
    private HomerBrandService homerBrandServiceImpl;
    @Autowired
    private AboutInfoService aboutInfoServiceImpl;

    @Autowired
    private DownloadService downloadServiceImpl;


    @RequestMapping("getHeaderNavMsg")
    public Result getHeaderNavMsg(HttpServletResponse response) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> fiveG = new ArrayList<>();
        List<Map<String, Object>> lte = new ArrayList<>();
        List<Map<String, Object>> product = new ArrayList<>();
        try {

            List<Solution5gPage> solution5gPages = solution5gService.listByEnable();

            solution5gPages.forEach(solution5gPage -> {
                Map<String, Object> map = new HashMap<>();
                map.put("title", solution5gPage.getTitle());
                map.put("url", solution5gPage.getPortalForwardUrl());
                fiveG.add(map);
            });
            List<SolutionLetPage> solutionLetPages = solutionLETService.listByEnable();

            solutionLetPages.forEach(solutionLetPage -> {
                Map<String, Object> map = new HashMap<>();
                map.put("title", solutionLetPage.getTitle());
                map.put("url", solutionLetPage.getPortalForwardUrl());
                lte.add(map);
            });
            List<ProductPage> productPages = productPageService.listByEnable();

            productPages.forEach(productPage -> {
                Map<String, Object> map = new HashMap<>();
                map.put("title", productPage.getTitle());
                map.put("url", productPage.getPortalForwardUrl());
                product.add(map);
            });
        } catch (Exception e) {
            result.setCode(ResultCode.FAIL);
        }

        resultMap.put("fiveG", fiveG);
        resultMap.put("lte", lte);
        resultMap.put("product", product);
        result.setData(resultMap);
        return result;
    }


    @RequestMapping("getHomeBanner")
    public Result getHomeBanner(HttpServletResponse response) {
        Result result = new Result();
        List<Map<String, Object>> results = new ArrayList<>();

        result.setCode(ResultCode.SUCCESS);
        List<HomeBanner> homeBanners = homeBannerServiceImpl.list();
        homeBanners.forEach(homeBanner -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url", homeBanner.getImgUrl());
            resultMap.put("name", homeBanner.getTitle());
            results.add(resultMap);
        });
        result.setData(results);
        return result;
    }

    @RequestMapping("getHomeBrand")
    public Result getHomeBrand(HttpServletResponse response) {
        Result result = new Result();

        result.setCode(ResultCode.SUCCESS);
        HomeBrand homeBrand = homerBrandServiceImpl.getByEnable();
        Map<String, Object> resultMap = new HashMap<>();
        if (homeBrand !=null){
            resultMap.put("pic", homeBrand.getImgUrl());
            resultMap.put("title", homeBrand.getTitle());
        }
        result.setData(resultMap);
        return result;
    }

    @RequestMapping("getHomeProduct")
    public Result getHomeProduct(HttpServletResponse response) {
        Result result = new Result();
        List<Map<String, Object>> results = new ArrayList<>();

        result.setCode(ResultCode.SUCCESS);
        List<ProductPage> productPages = productPageService.listByEnable();
        productPages.forEach(productPage -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("pic", productPage.getPortalPicUrl());
            resultMap.put("url", productPage.getPortalForwardUrl());
            resultMap.put("name", productPage.getTitle());
            results.add(resultMap);
        });
        result.setData(results);
        return result;
    }

    @RequestMapping("getHomeSolution")
    public Result getHomeSolution(HttpServletResponse response) {
        Result result = new Result();
        List<Map<String, Object>> results = new ArrayList<>();

        result.setCode(ResultCode.SUCCESS);
        List<SolutionLetPage> solutionLetPages = solutionLETService.listByEnable();
        solutionLetPages.forEach(solutionLetPage -> {
            if (StringUtils.isNotBlank(solutionLetPage.getPortalPicUrl())) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("pic", solutionLetPage.getPortalPicUrl());
                resultMap.put("name", solutionLetPage.getTitle());
                resultMap.put("url", solutionLetPage.getPortalForwardUrl());
                results.add(resultMap);
            }

        });
        result.setData(results);
        return result;
    }


    @RequestMapping("getNav5G")
    public Result getNav5G(HttpServletResponse response) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> results = new ArrayList<>();
        SysPageConfig sysPageConfig = systemServiceImpl.execGetOne();
        result.setCode(ResultCode.SUCCESS);
        List<Solution5gPage> solution5gPages = solution5gService.listByEnable();
        solution5gPages.forEach(solution5gPage -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url", solution5gPage.getPortalForwardUrl());
            resultMap.put("pic", solution5gPage.getNavPicUrl());
            resultMap.put("desc", solution5gPage.getNavDesc());
            resultMap.put("title", solution5gPage.getTitle());
            results.add(resultMap);

        });
        map.put("list",results);
        map.put("desc",sysPageConfig.getSolution5gNavDesc());
        result.setData(map);
        return result;
    }


    @RequestMapping("getNavLte")
    public Result getNavLte(HttpServletResponse response) {
        Result result = new Result();
        List<Map<String, Object>> results = new ArrayList<>();
        result.setCode(ResultCode.SUCCESS);
        List<SolutionLetPage> solutionLetPages = solutionLETService.listByEnable();
        solutionLetPages.forEach(solutionLetPage -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url", solutionLetPage.getPortalForwardUrl());
            resultMap.put("pic", solutionLetPage.getNavPicUrl());
            resultMap.put("title", solutionLetPage.getTitle());
            results.add(resultMap);

        });
        result.setData(results);
        return result;
    }


    @RequestMapping("getNavProduct")
    public Result getNavProduct(HttpServletResponse response) {
        Result result = new Result();
        List<Map<String, Object>> results = new ArrayList<>();
        result.setCode(ResultCode.SUCCESS);
        List<ProductPage> productPages = productPageService.listByEnable();
        productPages.forEach(productPage -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url", productPage.getPortalForwardUrl());
            resultMap.put("pic", productPage.getNavPicUrl());
            resultMap.put("title", productPage.getTitle());
            results.add(resultMap);

        });
        result.setData(results);
        return result;
    }



    @RequestMapping("getMediaShow")
    public Result getMediaShow(HttpServletResponse response) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> brands = new ArrayList<>();
        List<Map<String, Object>> newss = new ArrayList<>();
        List<Map<String, Object>> customers = new ArrayList<>();
        result.setCode(ResultCode.SUCCESS);
        //
        int first = 0;
        int maxSize = 3;
        List<MediaBrand> mediaBrands = mediaBrandServiceImpl.listByIndexAndSort(first,maxSize);
        mediaBrands.forEach(mediaBrand -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("pic",mediaBrand.getImgUrl());
            resultMap.put("title",mediaBrand.getTitle());
            brands.add(resultMap);
        });

        PageInfo<MediaNews> mediaNewsPageInfo = mediaNewsServiceImpl.listByIndexAndSort(first,maxSize);
        mediaNewsPageInfo.getList().forEach(mediaNews -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url",mediaNews.getForwardUrl());
            resultMap.put("pic",mediaNews.getNavPicUrl());
            resultMap.put("title",mediaNews.getNavTitle());
            resultMap.put("date",DateUtils.toDateText(mediaNews.getCreateTime(),DateUtils.DATE_FORMAT));
            newss.add(resultMap);
        });

        PageInfo<MediaCustomer> mediaCustomerPageInfo = mediaCustomerServiceImpl.listByIndexAndSort(first,maxSize);
        mediaCustomerPageInfo.getList().forEach(mediaCustomer -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url",mediaCustomer.getFileUrl());
            resultMap.put("title",mediaCustomer.getNavTitle());
            resultMap.put("pic",mediaCustomer.getNavPicUrl());
            customers.add(resultMap);
        });

        map.put("brand",brands);
        map.put("news",newss);
        map.put("customer",customers);
        result.setData(map);
        return result;
    }




    @RequestMapping("getCustomerList")
    public Result getCustomerList(int index,int size) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        //

        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> customers = new ArrayList<>();

        PageInfo<MediaCustomer> mediaCustomerPageInfo = mediaCustomerServiceImpl.listByIndexAndSort(index,size);
        mediaCustomerPageInfo.getList().forEach(mediaCustomer -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url",mediaCustomer.getFileUrl());
            resultMap.put("title",mediaCustomer.getNavTitle());
            resultMap.put("pic",mediaCustomer.getNavPicUrl());
            resultMap.put("desc",mediaCustomer.getNavDesc());
            resultMap.put("date",DateUtils.toDateText(mediaCustomer.getCreateTime(),DateUtils.DATE_FORMAT));
            customers.add(resultMap);
        });

        map.put("list",customers);
        map.put("pageInfo",mediaCustomerPageInfo);
        result.setData(map);
        return result;
    }

    @RequestMapping("getNewsList")
    public Result getNewsList(int index,int size) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        //

        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> customers = new ArrayList<>();

        PageInfo<MediaNews> mediaNewsPageInfo = mediaNewsServiceImpl.listByIndexAndSort(index,size);
        mediaNewsPageInfo.getList().forEach(mediaNews -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url",mediaNews.getForwardUrl());
            resultMap.put("title",mediaNews.getNavTitle());
            resultMap.put("pic",mediaNews.getNavPicUrl());
            resultMap.put("desc",mediaNews.getNavDesc());
            resultMap.put("date",DateUtils.toDateText(mediaNews.getCreateTime(),DateUtils.DATE_FORMAT));
            customers.add(resultMap);
        });

        map.put("list",customers);
        map.put("pageInfo",mediaNewsPageInfo);
        result.setData(map);
        return result;
    }



    @RequestMapping("getDownList")
    public Result getDownList(int index,int size,int type) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        //

        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> downloads = new ArrayList<>();

        PageInfo<SupportDownload> supportDownloadPageInfo = supportDownloadServiceImpl.listByIndexAndSort(index,size,type);
        supportDownloadPageInfo.getList().forEach(supportDownload -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("url",supportDownload.getUrl());
            resultMap.put("title",supportDownload.getTitle());
            resultMap.put("date",DateUtils.toDateText(supportDownload.getCreateTime(),DateUtils.DATE_FORMAT));
            downloads.add(resultMap);
        });

        map.put("list",downloads);
        map.put("pageInfo",supportDownloadPageInfo);
        result.setData(map);
        return result;
    }



    @RequestMapping("about/sub")
    public Result updateAbout(@RequestBody  AboutInfo aboutInfo) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        //
        aboutInfoServiceImpl.updateByEntity(aboutInfo);
        return result;
    }

    @RequestMapping("about/dUrl")
    public Result downloadFile() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS);
        //
        String url = downloadServiceImpl.getOne();
        result.setData(url);
        return result;
    }


}
