package com.ds;

import com.utils.ReflectUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

@Aspect
@Order(-100)
@Component
public class DynamicDataSourceAspect {

    public final static Logger _logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    @Pointcut("execution(* com.dao.demo.*.*(..))")
    private void pointCutMethod() {

    }


    @Before("pointCutMethod()")
    public void changeDataSource1(JoinPoint point) throws Exception {

        //拿到anotation中配置的数据源
        String resultDS = determineDatasource(point);
        System.out.print("----------"+resultDS);
        //没有配置实用默认数据源
        if (resultDS == null) {
            DataSourceContextHolder.setDataSourceType(null);
            return;
        }
        //将数据源设置到数据源持有者
        DataSourceContextHolder.setDataSourceType(resultDS);
    }


/*

    */
/**
     * @Before： 在方法执行之前进行执行：
     * @annotation(ds)：会拦截注解ds的方法，否则不拦截;
     * @param point
     * @param ds
     * @throws Exception
     *//*

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DS ds) throws Exception {

        String dsId = ds.value();
        if (!DataSourceContextHolder.containsDataSource(dsId)) {
            DataSourceContextHolder.setDataSourceType(null);
            return;
        } else {
            DataSourceContextHolder.setDataSourceType(ds.value());
        }

    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DS ds) {
        DataSourceContextHolder.clearDataSourceType();
    }
*/


/*



    */
/**
     * <p>创建时间： 2013-8-20 上午9:48:44</p>
     * 如果需要修改获取数据源的逻辑，请重写此方法
     *
     * @param jp
     * @return
     */

    @SuppressWarnings("rawtypes")
    protected String determineDatasource(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class targetClass = jp.getSignature().getDeclaringType();
        String dataSourceForTargetClass = resolveDataSourceFromClass(targetClass);
        String dataSourceForTargetMethod = resolveDataSourceFromMethod(
                targetClass, methodName);
        String resultDS = determinateDataSource(dataSourceForTargetClass,
                dataSourceForTargetMethod);
        return resultDS;
    }


/**
     * 方法执行完毕以后，数据源切换回之前的数据源。
     * 比如foo()方法里面调用bar()，但是bar()另外一个数据源，
     * bar()执行时，切换到自己数据源，执行完以后，要切换到foo()所需要的数据源，以供
     * foo()继续执行。
     * <p>创建时间： 2013-8-16 下午4:27:06</p>
     */

    @After("pointCutMethod()")
    public void restoreDataSourceAfterMethodExecution() {
        DataSourceContextHolder.clearDataSourceType();
    }


/**
     * <li>创建时间： 2013-6-17 下午5:34:13</li> <li>创建人：amos.zhou</li> <li>方法描述 :</li>
     *
     * @param targetClass
     * @param methodName
     * @return
     */

    @SuppressWarnings("rawtypes")
    private String resolveDataSourceFromMethod(Class targetClass,
                                               String methodName) {

        Method m = ReflectUtil.findUniqueMethod(targetClass, methodName);
        if (m != null) {
            DS choDs = m.getAnnotation(DS.class);
            return resolveDataSourcename(choDs);
        }
        return null;
    }

/**
     * <li>创建时间： 2013-6-17 下午5:06:02</li>
     * <li>创建人：amos.zhou</li>
     * <li>方法描述 : 确定
     * 最终数据源，如果方法上设置有数据源，则以方法上的为准，如果方法上没有设置，则以类上的为准，如果类上没有设置，则使用默认数据源</li>
     *
     * @param classDS
     * @param methodDS
     * @return
     */

    private String determinateDataSource(String classDS, String methodDS) {
//        if (null == classDS && null == methodDS) {
//            return null;
//        }
        // 两者必有一个不为null,如果两者都为Null，也会返回Null
        return methodDS == null ? classDS : methodDS;
    }

/**
     * <li>创建时间： 2013-6-17 下午4:33:03</li> <li>创建人：amos.zhou</li> <li>方法描述 : 类级别的 @ChooseDataSource
     * 的解析</li>
     *
     * @param targetClass
     * @return
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    private String resolveDataSourceFromClass(Class targetClass) {
        DS classAnnotation = (DS) targetClass
                .getAnnotation(DS.class);
        // 直接为整个类进行设置
        return null != classAnnotation ? resolveDataSourcename(classAnnotation)
                : null;
    }

/**
     * <li>创建时间： 2013-6-17 下午4:31:42</li> <li>创建人：amos.zhou</li> <li>方法描述 :
     * 组装DataSource的名字</li>
     *
     * @param ds
     * @return
     */

    private String resolveDataSourcename(DS ds) {
        return ds == null ? null : ds.value();
    }


}
