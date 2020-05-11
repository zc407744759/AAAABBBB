package com.wutos.AAAABBBB.web.config.constant;

/**
 * 数据源常量
 *
 * @author zc
 * @date 2019-06-25
 */
public class DataSourceConstant {

    //************** 数据源1 配置 **************
    /**
     * mapper 接口包地址
     */
    public static final String DB1_BASE_PACKAGES = "com.wutos.*.domain.mapper.primary";
    /**
     * 数据源配置 前缀
     */
    public static final String DB1_DATA_SOURCE_PREFIX = "spring.datasource.primary";
    /**
     * mapper xml文件地址
     */
    public static final String DB1_MAPPER_LOCATION = "classpath:com/wutos/*/domain/mapper/primary/*.xml";


    //************** 数据源 2 配置 **************
    /**
     * mapper 接口包地址
     */
    public static final String DB2_BASE_PACKAGES = "com.wutos.*.domain.mapper.second";
    /**
     * 数据源配置 前缀
     */
    public static final String DB2_DATA_SOURCE_PREFIX = "spring.datasource.second";
    /**
     * mapper xml文件地址
     */
    public static final String DB2_MAPPER_LOCATION = "classpath:com/wutos/*/domain/mapper/second/*.xml";
}