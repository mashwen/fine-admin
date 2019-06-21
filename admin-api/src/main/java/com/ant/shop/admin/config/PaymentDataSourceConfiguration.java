package com.ant.shop.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ant.shop.asorm.mapper.payment", sqlSessionTemplateRef  = "paymentSqlSessionTemplate")
public class PaymentDataSourceConfiguration {

    @Bean(name = "paymentDataSource")
    //下面这个注解控制哪个实例优先被注入，我们放在第一个数据源上面

    @ConfigurationProperties(prefix = "spring.datasource.payment")
    public DataSource dataSource() {
        return  new DruidDataSource();
    }

    @Bean(name = "paymentSqlSessionFactory")

    public SqlSessionFactory sqlSessionFactory(@Qualifier("paymentDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "paymentTransactionManager")

    public DataSourceTransactionManager transactionManager(@Qualifier("paymentDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "paymentSqlSessionTemplate")

    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("paymentSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
