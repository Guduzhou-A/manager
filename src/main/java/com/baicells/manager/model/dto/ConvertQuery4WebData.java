package com.baicells.manager.model.dto;

import com.baicells.manager.utils.DateUtils;
import com.baicells.manager.utils.FastJsonUtil;
import com.github.pagehelper.Page;
import freemarker.template.utility.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ConvertQuery4WebData {
    public static ProductPageQuery4WebDto convertCommentQuery(String data) {
        ProductPageQuery4WebDto dto = new ProductPageQuery4WebDto();
        if (StringUtils.isNotEmpty(data)) {
            List<DataDto> dataList = FastJsonUtil.toList(data, DataDto.class);

            for (DataDto dataDto : dataList) {
                if (dataDto.getName().equals("title")) {
                    dto.setTitle(dataDto.getValue());
                }
                if (dataDto.getName().equals("status")) {
                    dto.setStatus(Integer.valueOf(dataDto.getValue()));
                }
                if (dataDto.getName().equals("beginTime")) {
                    dto.setBeginTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("endTime")) {
                    dto.setEndTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("iDisplayStart")) {
                    dto.setIndex(Integer.valueOf(dataDto.getValue()) + 1);
                }
                if (dataDto.getName().equals("iDisplayLength")) {
                    dto.setSize(Integer.valueOf(dataDto.getValue()));
                }
            }
        }
        return dto;
    }

    public static SolutionQuery4WebDto convertSolutionQuery(String data) {
        SolutionQuery4WebDto dto = new SolutionQuery4WebDto();
        if (StringUtils.isNotEmpty(data)) {
            List<DataDto> dataList = FastJsonUtil.toList(data, DataDto.class);

            for (DataDto dataDto : dataList) {
                if (dataDto.getName().equals("title")) {
                    dto.setTitle(dataDto.getValue());
                }
                if (dataDto.getName().equals("status")) {
                    dto.setStatus(Integer.valueOf(dataDto.getValue()));
                }
                if (dataDto.getName().equals("beginTime")) {
                    dto.setBeginTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("endTime")) {
                    dto.setEndTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("iDisplayStart")) {
                    dto.setIndex(Integer.valueOf(dataDto.getValue()) + 1);
                }
                if (dataDto.getName().equals("iDisplayLength")) {
                    dto.setSize(Integer.valueOf(dataDto.getValue()));
                }
            }
        }
        return dto;
    }

    public static MediaQuery4WebDto convertMediaQuery(String data) {
        MediaQuery4WebDto dto = new MediaQuery4WebDto();
        if (StringUtils.isNotEmpty(data)) {
            List<DataDto> dataList = FastJsonUtil.toList(data, DataDto.class);

            for (DataDto dataDto : dataList) {
                if (dataDto.getName().equals("title")) {
                    dto.setTitle(dataDto.getValue());
                }
                if (dataDto.getName().equals("status")) {
                    dto.setStatus(Integer.valueOf(dataDto.getValue()));
                }
                if (dataDto.getName().equals("beginTime")) {
                    dto.setBeginTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("endTime")) {
                    dto.setEndTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("iDisplayStart")) {
                    dto.setIndex(Integer.valueOf(dataDto.getValue()) + 1);
                }
                if (dataDto.getName().equals("iDisplayLength")) {
                    dto.setSize(Integer.valueOf(dataDto.getValue()));
                }
            }
        }
        return dto;
    }


    public static SupportQuery4WebDto convertSupportQuery(String data) {
        SupportQuery4WebDto dto = new SupportQuery4WebDto();
        if (StringUtils.isNotEmpty(data)) {
            List<DataDto> dataList = FastJsonUtil.toList(data, DataDto.class);

            for (DataDto dataDto : dataList) {
                if (dataDto.getName().equals("title")) {
                    dto.setTitle(dataDto.getValue());
                }
                if (dataDto.getName().equals("type")) {
                    dto.setType(Integer.valueOf(dataDto.getValue()));
                }
                if (dataDto.getName().equals("beginTime")) {
                    dto.setBeginTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("endTime")) {
                    dto.setEndTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("iDisplayStart")) {
                    dto.setIndex(Integer.valueOf(dataDto.getValue()) + 1);
                }
                if (dataDto.getName().equals("iDisplayLength")) {
                    dto.setSize(Integer.valueOf(dataDto.getValue()));
                }
            }
        }
        return dto;
    }


    public static HomeQuery4WebDto convertHomeQuery(String data) {
        HomeQuery4WebDto dto = new HomeQuery4WebDto();
        if (StringUtils.isNotEmpty(data)) {
            List<DataDto> dataList = FastJsonUtil.toList(data, DataDto.class);

            for (DataDto dataDto : dataList) {
                if (dataDto.getName().equals("title")) {
                    dto.setTitle(dataDto.getValue());
                }
                if (dataDto.getName().equals("status")) {
                    dto.setStatus(Integer.valueOf(dataDto.getValue()));
                }
                if (dataDto.getName().equals("beginTime")) {
                    dto.setBeginTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("endTime")) {
                    dto.setEndTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("iDisplayStart")) {
                    dto.setIndex(Integer.valueOf(dataDto.getValue()) + 1);
                }
                if (dataDto.getName().equals("iDisplayLength")) {
                    dto.setSize(Integer.valueOf(dataDto.getValue()));
                }
            }
        }
        return dto;
    }


    public static AboutPageQuery4WebDto convertAboutQuery(String data) {

        AboutPageQuery4WebDto dto = new AboutPageQuery4WebDto();
        if (StringUtils.isNotEmpty(data)) {
            List<DataDto> dataList = FastJsonUtil.toList(data, DataDto.class);

            for (DataDto dataDto : dataList) {
                if (dataDto.getName().equals("nickName")) {
                    dto.setNickName(dataDto.getValue());
                }
                if (dataDto.getName().equals("country")) {
                    dto.setCountry(dataDto.getValue());
                }
                if (dataDto.getName().equals("beginTime")) {
                    dto.setBeginTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("endTime")) {
                    dto.setEndTime(dataDto.getValue());
                }
                if (dataDto.getName().equals("iDisplayStart")) {
                    dto.setIndex(Integer.valueOf(dataDto.getValue()) + 1);
                }
                if (dataDto.getName().equals("iDisplayLength")) {
                    dto.setSize(Integer.valueOf(dataDto.getValue()));
                }
            }
        }
        return dto;
    }
}
