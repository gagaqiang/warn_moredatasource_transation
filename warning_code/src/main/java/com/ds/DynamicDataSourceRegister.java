package com.ds;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(DataSourceProperties.class)
@MapperScan(value = "com.dao")
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    public final static Logger _logger = Logger.getLogger(DynamicDataSourceRegister.class);

    //如配置文件中未指定数据源类型，使用该默认值

    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;
    // 默认数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();


    //对接数据库的实体层
    @Value("${mybatis.type-aliases-package}")
    private  String ALIASES_PACKAGE;
    @Value("${mybatis.mapper-locations}")
    private  String MAPPER_LOCATION;


    @Override
    public void setEnvironment(Environment environment) {
        //_logger.error("DynamicDataSourceRegister.setEnvironment()");
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }


    /**
     * 加载主数据源配置.
     * @param env
     */
    private void initDefaultDataSource(Environment env){
        // 读取主数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", propertyResolver.getProperty("type"));
        dsMap.put("driverClassName", propertyResolver.getProperty("driverClassName"));
        dsMap.put("url", propertyResolver.getProperty("url"));
        dsMap.put("username", propertyResolver.getProperty("username"));
        dsMap.put("password", propertyResolver.getProperty("password"));
        dsMap.put("resourceName", propertyResolver.getProperty("resourceName"));

        defaultDataSource = buildDataSource(dsMap);
        dataBinder(defaultDataSource, env);

    }


    /**
     * 初始化更多数据源
     * @param env
     */
    private void initCustomDataSources(Environment env) {

        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, env);
        }
    }


    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        Object type = dsMap.get("type");
        if (type == null){
            type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
        }
        Class<? extends DataSource> dataSourceType;
        try {
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
            String driverClassName = dsMap.get("driverClassName").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            String resourceName = dsMap.get("resourceName").toString();

            /*DataSourceBuilder factory =   DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
            return factory.build();*/


            MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
            mysqlXaDataSource.setUrl(url);
            mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
            mysqlXaDataSource.setPassword(password);
            mysqlXaDataSource.setUser(username);
            mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

            AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
            xaDataSource.setXaDataSource(mysqlXaDataSource);
            xaDataSource.setUniqueResourceName(resourceName);

            xaDataSource.setMinPoolSize(20);
            xaDataSource.setMaxPoolSize(100);
            xaDataSource.setMaxLifetime(20000);
            xaDataSource.setBorrowConnectionTimeout(30);
            xaDataSource.setLoginTimeout(30);
            xaDataSource.setMaintenanceInterval(60);
            xaDataSource.setMaxIdleTime(60);
            xaDataSource.setTestQuery("SELECT 1");

            return xaDataSource;



        } catch (ClassNotFoundException e) {
            _logger.error("error"+e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 为DataSource绑定更多数据
     * @param dataSource
     * @param env
     */
    private void dataBinder(DataSource dataSource, Environment env){
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if(dataSourcePropertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<>(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driverClassName");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //_logger.error("DynamicDataSourceRegister.registerBeanDefinitions()");
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
           // _logger.error("----从数据源---" + key);
            DataSourceContextHolder.dataSourceIds.add(key);
        }
        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        //添加属性：AbstractRoutingDataSource.defaultTargetDataSource
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        _logger.error("动态数据源注册成功,从数据源个数 == " + customDataSources.size());
    }


    @Bean(name = "sqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate() throws Exception {
        Map<Object,SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        for(String key: customDataSources.keySet()){
            sqlSessionFactoryMap.put(key,createSqlSessionFactory(customDataSources.get(key)));
        }

        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(createSqlSessionFactory(defaultDataSource));
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return customSqlSessionTemplate;
    }


    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setTypeAliasesPackage(ALIASES_PACKAGE);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }
}
