package com.wutos.AAAABBBB.web.config.datasource;

import com.github.pagehelper.PageInterceptor;
import com.wutos.AAAABBBB.web.config.constant.DataSourceConstant;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源2 >  配置
 *
 * @author zc
 * @date 2019-06-25
 * 说明一下两个注解的作用
 * -- @Primary：在众多相同的bean中，优先选择用@Primary注解的bean（该注解加在各个bean上）
 * -- @Qualifier：在众多相同的bean中，@Qualifier指定需要注入的bean（该注解跟随在@Autowired后）
 */
@Configuration
@MapperScan(basePackages = DataSourceConstant.DB2_BASE_PACKAGES, sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class DataSource2Config {

    /**
     * 数据源配置
     * 使用的连接池是 HikariDataSource
     * <p>
     * 注解ConfigurationProperties
     * 作用就是将全局配置文件中的属性值注入到DruidDataSource 的同名参数
     */
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = DataSourceConstant.DB2_DATA_SOURCE_PREFIX)
    public DataSource testDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }


    /**
     * 创建 SqlSessionFactory 工厂
     */
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //数据源
        bean.setDataSource(dataSource);
        //mapper 地址
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConstant.DB2_MAPPER_LOCATION));

        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);
        bean.setPlugins(new Interceptor[] {interceptor});
        return bean.getObject();
    }

    /**
     * 事务管理
     */
    @Bean(name = "asecondTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * MyBatis提供的持久层访问模板化的工具
     * 线程安全，可通过构造参数或依赖注入SqlSessionFactory实例。
     */
    @Bean(name = "alarmSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
