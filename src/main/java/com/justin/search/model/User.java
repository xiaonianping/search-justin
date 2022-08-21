package com.justin.search.model;

import lombok.Data;

/**
 * 用户
 * @author Administrator
 */
@Data
public class User {

    private Long id;

    private String openId;

    /**
     * 渠道id
     */
    private Long channelId;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String head;

    /**
     * 性别 （1：男，2：女）
     */
    private Integer gender;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 上级用户
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 二维码
     */
    private String wxQrcode;

    /**
     * 关注状态 0 未关注 1 已关注 2 取消关注
     */
    private Integer isFollowOfficial;

    /**
     * 关注时间（第一次关注时间）
     */
    private Long subscriptionTime;

    /**
     * 发送次数
     */
    private Integer sentTimes;

    /**
     * 店主二维码
     */
    private String shopperQrcode;

    /**
     * 直播二维码
     */
    private String liveQrcode;

    /**
     * 简介
     */
    private String profile;

    /**
     * 是否会员（0：非会员，1：会员）
     */
    private Integer isMember;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 是否开启勿扰（0：消息立即提醒，1：勿扰模式23:00-6:00）
     */
    private Integer notDisturb;

    /**
     * 状态是否可用 0正常 1不可用，拉入黑名单
     */
    private Integer status;

    /**
     * 上次活跃时间
     */
    private Long lastActiveTime;

    /**
     * 广告地址
     */
    private String advertAddress;

    /**
     * 海报地址
     */
    private String posterAddress;

    /**
     * 邀请二维码
     */
    private String inviteQrCode;

    /**
     * 打卡二维码
     */
    private String clockQrCode;

    /**
     * 上次关注时间
     */
    private Long lastSubscriptionTime;

    /**
     * 取消关注时间
     */
    private Long unsubscribeTime;

    /**
     * 用户来源应用的appId
     */
    private String applyId;

    /**
     * 广告类别（1：邀请码，2：链接，3：商品，4：店铺，5：群二维码）
     */
    private Integer advertiseType;

    /**
     * 广告ID
     */
    private Long advertiseId;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 红包开始时间  0则是第一次或开始
     */
    private Long redPacketTime;
}