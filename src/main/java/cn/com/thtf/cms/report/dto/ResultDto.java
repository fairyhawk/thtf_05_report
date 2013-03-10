package cn.com.thtf.cms.report.dto;

import java.util.List;

import cn.com.thtf.cms.report.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

public class ResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private List<String> list;

    @Setter
    @Getter
    private List<UserEntity> users;
}
