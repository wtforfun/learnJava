package org.channel.common.constant;

public class OrderMsgConst
{
    
    public static class OrderStatusCode
    {
        /*
         * 成功
         */
        public static final Integer ORDER_STATUS_EXECUTION = 1;
        

        public static final Integer ORDER_STATUS_COMPLETE = 2;
    }
    
    /**
     * order错误码
     * @author CQ
     *
     */
    public static class OrderResponseCode
    {
        
        /*
         * 成功
         */
        public static final String ORDER_RESPONSECODE_0000 = "0000";
        
        /*
         * 订购请求已接受
         */
        public static final String ORDER_RESPONSECODE_0001 = "0001";
        
        /*
         * 系统异常，请尽快联系相关运营人员
         */
        public static final String ORDER_RESPONSECODE_1000 = "1000";
        
        /*
         * 参数错误
         */
        public static final String ORDER_RESPONSECODE_1001 = "1001";
        
        /*
         * 数字签名验证失败
         */
        public static final String ORDER_RESPONSECODE_1002 = "1002";
        
        /*
         * 请求服务不存在
         */
        public static final String ORDER_RESPONSECODE_1003 = "1003";
        
        /*
         * 此地区产品未开通
         */
        public static final String ORDER_RESPONSECODE_1004 = "1004";
        
        /*
         * IP地址错误
         */
        public static final String ORDER_RESPONSECODE_1005 = "1005";
        
        /*
         * 请求时间错误
         */
        public static final String ORDER_RESPONSECODE_1006 = "1006";
        
        /*
         * 订购请求重复提交
         */
        public static final String ORDER_RESPONSECODE_1007 = "1007";
        /*
         * 没有该接口的访问权限
         */
        public static final String ORDER_RESPONSECODE_1008 = "1008";
        /*
         * 该接口当日累计访问次数达到上限
         */
        public static final String ORDER_RESPONSECODE_1009 = "1009";
        /*
         * 用户不存在
         */
        public static final String ORDER_RESPONSECODE_2001 = "2001";
        
        /*
         * 用户已停机
         */
        public static final String ORDER_RESPONSECODE_2002 = "2002";
        
        /*
         * 用户状态异常（欠费）
         */
        public static final String ORDER_RESPONSECODE_2003 = "2003";
        
        /*
         * 用户是黑名单
         */
        public static final String ORDER_RESPONSECODE_2004 = "2004";
        
        /*
         * 该笔订单已完成订购
         */
        public static final String ORDER_RESPONSECODE_2005 = "2005";
        
        /*
         * 订购关系不存在
         */
        public static final String ORDER_RESPONSECODE_2006 = "2006";
        
        /*
         * 与已订购的其他产品冲突
         */
        public static final String ORDER_RESPONSECODE_2007 = "2007";
        
        /*
         * 不允许变更的产品
         */
        public static final String ORDER_RESPONSECODE_2008 = "2008";
        
        /*
         * 订购接入通道参数错误
         */
        public static final String ORDER_RESPONSECODE_2009 = "2009";
        
        /*
         * 用户套餐不能订购该业务
         */
        public static final String ORDER_RESPONSECODE_2010 = "2010";
        
        /*
         * 第三方系统其他原因
         */
        public static final String ORDER_RESPONSECODE_2011 = "2011";
        
        /*
         * 订购接入通道订购失败
         */
        public static final String ORDER_RESPONSECODE_2012 = "2012";
        
        /*
         * 该地区暂不支持2G用户订购
         */
        public static final String ORDER_RESPONSECODE_2013 = "2013";
        
        /*
         * 无更多的产品订购（用户当月订购流量包已到达叠加上限）
         */
        public static final String ORDER_RESPONSECODE_2014 = "2014";
        
        /*
         * 暂时无产品订购，针对预付用户
         */
        public static final String ORDER_RESPONSECODE_2015 = "2015";
        
        /*
         * 2G/3G融合用户不允许订购
         */
        public static final String ORDER_RESPONSECODE_2016 = "2016";
        
        /*
         * 用户状态异常（资料不全）
         */
        public static final String ORDER_RESPONSECODE_2017 = "2017";
        
        /*
         * 用户状态异常（不在有效期）
         */
        public static final String ORDER_RESPONSECODE_2018 = "2018";
        
        /*
         * 用户主套餐变更，当月不能订购
         */
        public static final String ORDER_RESPONSECODE_2019 = "2019";
        
        /*
         * 用户服务密码为初始密码
         */
        public static final String ORDER_RESPONSECODE_2020 = "2020";
        
        /*
         * 用户有正在处理订单
         */
        public static final String ORDER_RESPONSECODE_2021 = "2021";
        
        /*
         * 该地区暂不支持4G用户订购
         */
        public static final String ORDER_RESPONSECODE_2022 = "2022";
        
        /*
         * 用户状态异常（未查到用户类型）
         */
        public static final String ORDER_RESPONSECODE_2023 = "2023";
        
        /*
         * 该地区暂不支持3G用户订购
         */
        public static final String ORDER_RESPONSECODE_2024 = "2024";
        
        /*
         * 手机号不正确
         */
        public static final String ORDER_RESPONSECODE_2025 = "2025";
        
        /*
         * channelId验证无效
         */
        public static final String ORDER_RESPONSECODE_3001 = "3001";
        
        /*
         * 账户余额不足，请尽快联系相关运营人员
         */
        public static final String ORDER_RESPONSECODE_3002 = "3002";
        
        /*
         * 账户余额不足，请尽快联系相关运营人员
         */
        public static final String ORDER_RESPONSECODE_3003 = "3003";
        
        /*
         * 产品编号无效
         */
        public static final String ORDER_RESPONSECODE_3004 = "3004";
        
        /*
         * 剩余的订购关系数目不足
         */
        public static final String ORDER_RESPONSECODE_3005 = "3005";
        
        /*
         * 此地区产品未开通
         */
        public static final String ORDER_RESPONSECODE_3006 = "3006";
        
        /*
         * 活动信息不存在
         */
        public static final String ORDER_RESPONSECODE_3007 = "3007";
        
        /*
         * 活动已过期
         */
        public static final String ORDER_RESPONSECODE_3008 = "3008";
        
        /*
         * 活动不包含此产品
         */
        public static final String ORDER_RESPONSECODE_3009 = "3009";
        
        /*
         * 没有pkgNo的订购权限
         */
        public static final String ORDER_RESPONSECODE_3010 = "3010";
        
        /*
         * 订单信息已存在，但未提交订购
         */
        public static final String ORDER_RESPONSECODE_3011 = "3011";
        
        /*
         * 客户端流水号请求重复订购
         */
        public static final String ORDER_RESPONSECODE_4001 = "4001";
        
        /*
         * 代理商状态冻结
         */
        public static final String ORDER_RESPONSECODE_3201 = "3201";
        /*
         * 代理商交付能力鉴权失败
         */
        public static final String ORDER_RESPONSECODE_3211 = "3211";
        /*
         * 代理商交付能力冻结
         */
        public static final String ORDER_RESPONSECODE_3212 = "3212";
        /*
         * 代理商交付密码无效
         */
        public static final String ORDER_RESPONSECODE_3213 = "3213";
        /*
         * 代理商业务鉴权失败
         */
        public static final String ORDER_RESPONSECODE_3221 = "3221";
        /*
         * 代理商业务冻结
         */
        public static final String ORDER_RESPONSECODE_3222 = "3222";
        /*
         * 代理商产品冻结
         */
        public static final String ORDER_RESPONSECODE_3223 = "3223";
        /*
         * 代理商产品适用手机号鉴权失败
         */
        public static final String ORDER_RESPONSECODE_3224 = "3224";
        /*
         * 订购能力鉴权失败
         */
        public static final String ORDER_RESPONSECODE_5201 = "5201";
        /*
         * 订购能力暂停使用
         */
        public static final String ORDER_RESPONSECODE_5202 = "5202";
        /*
         * 业务鉴权失败
         */
        public static final String ORDER_RESPONSECODE_5211 = "5211";
        /*
         * 业务暂停使用
         */
        public static final String ORDER_RESPONSECODE_5212 = "5212";
        /*
         * 产品鉴权失败
         */
        public static final String ORDER_RESPONSECODE_5221 = "5221";
        /*
         * 产品暂停使用
         */
        public static final String ORDER_RESPONSECODE_5222 = "5222";
        /*
         * 产品适用地区不匹配
         */
        public static final String ORDER_RESPONSECODE_5223 = "5223";
        /*
         * 供应商产品鉴权失败
         */
        public static final String ORDER_RESPONSECODE_5231 = "5231";
        /*
         * 供应商产品暂停使用
         */
        public static final String ORDER_RESPONSECODE_5232 = "5232";
        /*
         * 供应商产品地区不匹配
         */
        public static final String ORDER_RESPONSECODE_5233 = "5233";
        /*
         * 供应商产品所属运营商不匹配
         */
        public static final String ORDER_RESPONSECODE_5234 = "5234";
        /*
         * 供应商鉴权失败
         */
        public static final String ORDER_RESPONSECODE_5241 = "5241";
        /*
         * 供应商暂停服务
         */
        public static final String ORDER_RESPONSECODE_5242 = "5242";
        /*
         * 等待响应消息超时
         */
        public static final String ORDER_RESPONSECODE_9001 = "9001";
        /*
         * 网络异常
         */
        public static final String ORDER_RESPONSECODE_9002 = "9002";
        /*
         * 数据库连接异常
         */
        public static final String ORDER_RESPONSECODE_9003 = "9003";
        
        /*
         * 系统错误
         */
        public static final String ORDER_RESPONSECODE_9101 = "9101";
        
        /*
         * 网络异常
         */
        public static final String ORDER_RESPONSECODE_9102 = "9102";
        /*
         * 系统连接数据超过
         */
        public static final String ORDER_RESPONSECODE_9103 = "9103";
        /*
         * 其他原因
         */
        public static final String ORDER_RESPONSECODE_9999 = "9999";

    }
}
