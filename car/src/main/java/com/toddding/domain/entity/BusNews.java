package com.toddding.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * bus_news
 * @author 
 */
@ApiModel(value="com.toddding.domain.entity.BusNews")
@Data
public class BusNews implements Serializable {
    /**
     * 公告id
     */
    @ApiModelProperty(value="公告id")
    private Integer id;

    /**
     * 公告标题
     */
    @ApiModelProperty(value="公告标题")
    private String title;

    /**
     * 公告内容
     */
    @ApiModelProperty(value="公告内容")
    private String content;

    /**
     * 业务员id
     */
    @ApiModelProperty(value="业务员id")
    private String userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}