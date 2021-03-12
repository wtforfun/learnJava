package org.channel.common.constant;

/**
 * 普通常量类
 * @author ChenQiang
 * @author 陈超
 * @date 2014-08-07
 */
public class CommonConst
{
    //网别:联通
    public static final String MOBILE_OPERATE_CU = "CU";
    //网别:电信
    public static final String MOBILE_OPERATE_CN = "CN";
    //网别:移动
    public static final String MOBILE_OPERATE_CM = "CM";
    
    /**
     * 
     * 系统管理模块
     * @author  longlong
     * @version  [版本号, 2018年11月28日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class system
    {
        /**
         * 状态值  0  _Integer类型
         */
        public static final Integer STATUS_ZERO = 0;
        
        /**
         * 状态值  1  _Integer类型
         */
        public static final Integer STATUS_ONE = 1;
        
        /**
         * 状态值  2  _Integer类型
         */
        public static final Integer STATUS_TWO = 2;
        
        /**
         * 状态值 3  _Integer类型
         */
        public static final Integer STATUS_THREE = 3;
        
        /**
         * 状态值 6  _Integer类型
         */
        public static final Integer STATUS_SIX = 6;
        
    }
    
    /**
     * 业务管理模块
     * @author  longlong
     * @version  [版本号, 2018年11月28日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class business
    {
        /**
         * 状态值  0  _String类型
         */
        public static final String STATUS_ZERO = "0";
        
        /**
         * 状态值  1  _String类型
         */
        public static final String STATUS_ONE = "1";
        
        /**
         * 状态值  0  _Integer类型
         */
        public static final Integer STATUS_ZERO_Integer = 0;
        
        /**
         * 状态值  1  _Integer类型
         */
        public static final Integer STATUS_ONE_Integer = 1;
        
        /**
         * 状态值  2  _Integer类型
         */
        public static final Integer STATUS_TWO_Integer = 2;
        
        /**
         * 状态值 3  _Integer类型
         */
        public static final Integer STATUS_THREE_Integer = 3;
        
    }
    
    /**
     * 供应商管理模块
     * @author  longlong
     * @version  [版本号, 2018年11月28日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class supplier
    {
        /**
         * 状态值  0  _Integer类型
         */
        public static final Integer STATUS_ZERO_Integer = 0;
        
        /**
         * 状态值  1  _Integer类型
         */
        public static final Integer STATUS_ONE_Integer = 1;
        
        /**
         * 状态值  2  _Integer类型
         */
        public static final Integer STATUS_TWO_Integer = 2;
        
        /**
         * 状态值 3  _Integer类型
         */
        public static final Integer STATUS_THREE_Integer = 3;
        
        /**
         * 状态值  0   _String类型
         */
        public static final String STATUS_ZERO_String = "0";
        
        /**
         * 状态值  1   _String类型
         */
        public static final String STATUS_ONE_String = "1";
        
        /**
         * 状态值  2   _String类型
         */
        public static final String STATUS_TWO_String = "2";
        
        /**
         * 状态值  3   _String类型
         */
        public static final String STATUS_THREE_String = "3";
        
    }
    
    /**
     * 
     * 代理商管理模块
     * @author  Administrator
     * @version  [版本号, 2018年12月6日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class customer
    {
        /**
         * 状态值  0  _Integer类型
         */
        public static final Integer STATUS_ZERO_Integer = 0;
        
        /**
         * 状态值  1  _Integer类型
         */
        public static final Integer STATUS_ONE_INTEGER = 1;
        
        /**
         * 状态值  2  _Integer类型
         */
        public static final Integer STATUS_TWO_Integer = 2;
        
        /**
         * 状态值 3  _Integer类型
         */
        public static final Integer STATUS_THREE_Integer = 3;
        
        /**
         * 状态值 6  _Integer类型
         */
        public static final Integer STATUS_SIX_INTEGER = 6;
        
        /**
         * 状态值  0   _String类型
         */
        public static final String STATUS_ZERO_String = "0";
        
        /**
         * 状态值  1   _String类型
         */
        public static final String STATUS_ONE_String = "1";
        
        /**
         * 状态值  2   _String类型
         */
        public static final String STATUS_TWO_String = "2";
        
        /**
         * 状态值  3   _String类型
         */
        public static final String STATUS_THREE_String = "3";
        
    }
    
    /**
     * 
     * 模板默认值
     * <功能详细描述>
     * 
     * @author  chuyh
     * @version  [版本号, 2019年2月14日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class modelDefault
    {
        /**
         * 状态值  0  _Integer类型 非默认模板
         */
        public static final Integer STATUS_ZERO = 0;
        
        /**
         * 状态值  1  _Integer类型  默认模板
         */
        public static final Integer STATUS_ONE = 1;
        
    }
    
    
    /**
     * 订购能力相关系统信息
     * @author  Administrator
     * @version  [版本号, 2014-12-22]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static class ORDER_WAY_INFO
    {
        /**
         * API订购能力
         */
        public static String ORDER_WAY_TYPE_API = "11";
        /**
         * 批量订购能力
         */
        public static String ORDER_WAY_TYPE_BATCH = "12";
        /**
         * code订购能力
         */
        public static String ORDER_WAY_TYPE_CODE = "13";
        
        /**
         * 业务类型  流量
         */
        public static String BATCH_ORDER_SERVICE_ID= "23";
        
        
        /**
         *  订购能力状态
         *  0 什么都没有
         */
        public static Integer ORDER_WAY_STATUS_NOTHING = 0;
        /**
         *  订购能力状态
         *  1 正常
         */
        public static Integer ORDER_WAY_STATUS_NORMAL = 1;
        /**
         *  订购能力状态
         *  2 暂停
         */
        public static Integer ORDER_WAY_STATUS_PAUSE = 2;
        /**
         *  订购能力状态
         *  3 下架
         */
        public static Integer ORDER_WAY_STATUS_STOP = 3;
    }
    
}