package com.justin.search.model;

import lombok.Data;

/**
    * 文章
    */
@Data
public class Article {
    /**
    * 主键
    */
    private Long id;

    /**
    * 名称
    */
    private String title;

    /**
    * 摘要
    */
    private String summary;

    /**
    * 封面
    */
    private String coverUrl;

    /**
    * 状态（0：待审核，1：显示(审核通过)，2：隐藏(仅作者能看)，3：禁用(逻辑删除)）4：停用
    */
    private Integer status;

    /**
    * 原文章ID（在已有文章上产生的新文章）
    */
    private Long parentId;

    /**
    * 爬取的url
    */
    private String requestUrl;

    /**
    * 来源类别（0公众号爬取 1关键字爬取 2用户爬取 3创建）
    */
    private Integer sourcesType;

    /**
    * 文章来源的链接
    */
    private String sourcesUrl;

    /**
    * 文章来源的公众号
    */
    private Long sourcesGzhId;

    /**
    * 原作者
    */
    private String originalAuthor;

    /**
    * 原发布时间
    */
    private Long originalTime;

    /**
    * 创建时间
    */
    private Long createTime;

    /**
    * 更新时间
    */
    private Long updateTime;

    /**
    * 风险等级
    */
    private Integer riskLevel;

    /**
    * 来源渠道（只有一个渠道）
    */
    private Long channelId;

    /**
    * 用户类别  (0 用户创建 1 平台创建)
    */
    private Integer creatorType;

    /**
    * 用户ID (用户创建 user_id,平台创建 admin_id)
    */
    private Long creatorId;

    /**
    * 分享配文
    */
    private String shareTitle;

    /**
    * 是否平台文章（0：渠道，1：平台）
    */
    private Integer isPlatform;

    /**
    * 审核用户   admin_id
    */
    private Long auditorId;

    /**
    * 审核时间
    */
    private Long auditorTime;

    /**
    * 审核用户类型（0：渠道管理员，1：平台管理员）
    */
    private Integer auditorType;

    /**
    * 入库人(admin_id)
    */
    private Long publisherId;

    /**
    * 入库时间
    */
    private Long publisherTime;

    /**
    * 审核信息
    */
    private String auditorInfo;

    /**
    * 是否原创（0：否，1：是）
    */
    private Integer isOriginal;

    /**
    * md5用于去重判断
    */
    private String md5;

    /**
    * 是否入库 0未入库 1 已入库
    */
    private Integer isWarehousing;

    /**
    * 语音生成的状态  0未生成  1生成中   2生成已完成
    */
    private Integer speechStatus;

    /**
    * 语音生成的路径
    */
    private String speechUrl;

    /**
    * 是否开放语音 0不开放 1开放
    */
    private Integer isOpenSpeech;

    /**
    * 是否公众号原创文章  0不是 1是原创
    */
    private Integer isCopyright;

    /**
    * 优先显示开始时间
    */
    private Long showStartTime;

    /**
    * 优先显示结束时间
    */
    private Long showEndTime;

    /**
    * 是否头条 0 否 1 是
    */
    private Integer isHeadLine;
}