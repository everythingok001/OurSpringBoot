package w.p.j.db;

import com.alibaba.druid.pool.DruidDataSource;

import w.p.j.config.dataSourceSwitch.DataSources;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "w.p.j.dao")
public class MybatisConfig {
    private Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private OracleDataSourceEntity oracleDataSourceEntity;
    @Autowired
    private MysqlDataSourceEntity mysqlDataSourceEntity;

    //数据源1
    public DataSource oracleDataSource() {
        //加载配置文件属性
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(oracleDataSourceEntity.getDriverClassName());
        ds.setUsername(oracleDataSourceEntity.getUsername());
        ds.setPassword(oracleDataSourceEntity.getPassword());
        ds.setUrl(oracleDataSourceEntity.getUrl());
        ds.setMaxActive(oracleDataSourceEntity.getMaxActive());
        ds.setValidationQuery(oracleDataSourceEntity.getValidationQuery());
        ds.setTestOnBorrow(oracleDataSourceEntity.isTestOnBorrow());
        ds.setTestOnReturn(oracleDataSourceEntity.isTestOnReturn());
        ds.setTestWhileIdle(oracleDataSourceEntity.isTestWhileIdle());
        ds.setTimeBetweenEvictionRunsMillis(oracleDataSourceEntity.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(oracleDataSourceEntity.getMinEictableIdleTimeMillis());
        ds.setPoolPreparedStatements(oracleDataSourceEntity.isPoolPreparedStatements());
        ds.setMaxOpenPreparedStatements(oracleDataSourceEntity.getMaxOpenPreparedStatements());
        try {
            ds.setFilters(oracleDataSourceEntity.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    //数据源2
    public DataSource mysqlDataSource() {
        //加载配置文件属性
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(mysqlDataSourceEntity.getDriverClassName());
        ds.setUsername(mysqlDataSourceEntity.getUsername());
        ds.setPassword(mysqlDataSourceEntity.getPassword());
        ds.setUrl(mysqlDataSourceEntity.getUrl());
        ds.setMaxActive(mysqlDataSourceEntity.getMaxActive());
        ds.setValidationQuery(mysqlDataSourceEntity.getValidationQuery());
        ds.setTestOnBorrow(mysqlDataSourceEntity.isTestOnBorrow());
        ds.setTestOnReturn(mysqlDataSourceEntity.isTestOnReturn());
        ds.setTestWhileIdle(mysqlDataSourceEntity.isTestWhileIdle());
        ds.setTimeBetweenEvictionRunsMillis(mysqlDataSourceEntity.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(mysqlDataSourceEntity.getMinEictableIdleTimeMillis());
        ds.setPoolPreparedStatements(mysqlDataSourceEntity.isPoolPreparedStatements());
        ds.setMaxOpenPreparedStatements(mysqlDataSourceEntity.getMaxOpenPreparedStatements());
        try {
            ds.setFilters(mysqlDataSourceEntity.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    @Bean
    public DataSource dataSource(){
    	DataSource oracleDataSource = oracleDataSource();
    	DataSource mySqlDataSource = mysqlDataSource();
    	DataSources source = new DataSources();
    	Map<Object,Object> sourcesMap = new HashMap<Object,Object>();
    	sourcesMap.put("MYSQL", mySqlDataSource);
        sourcesMap.put("ORACLE", oracleDataSource);
    	source.setTargetDataSources(sourcesMap);
    	source.setDefaultTargetDataSource(oracleDataSource);
    	return source;
    }
    
    public Resource[] getResource( String basePackage, String pattern ) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + ClassUtils.convertClassNameToResourcePath(new StandardEnvironment()
                .resolveRequiredPlaceholders(basePackage)) + "/" + pattern;
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        return resources;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        logger.debug("--> sqlSessionFactory");
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setFailFast(true);
        sqlSessionFactory.setMapperLocations(getResource("mapper", "**/*.xml"));
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        logger.debug("> transactionManager");
        return new DataSourceTransactionManager(dataSource());
    }
    
    @Bean
    @Scope("prototype")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
    	return new SqlSessionTemplate(sqlSessionFactory());
    }
    
}
