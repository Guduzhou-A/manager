package com.baicells.manager.config;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

//@Component
@Configuration
@ConfigurationProperties( prefix = "transaction" )
public class TransactionConfig {
    private static Log logger = LogFactory.getLog(TransactionConfig.class);

    /**
     * 默认
     */
    private static final String TRANSACTION_INTERCEPTOR_NAME   = "txAdvice";
    /**
     * 默认传播方式
     */
    private static final String PROPAGATION_REQUIRED   = "PROPAGATION_REQUIRED";
    /**
     * 默认只对 "*Service" , "*ServiceImpl" Bean 进行事务处理,"*"表示模糊匹配, 比如 : userService,orderServiceImpl
     */
    private static final String[] DEFAULT_TRANSACTION_BEAN_NAMES   = { "*Service" , "*ServiceImpl" };
    /**
     * 可传播事务配置
     */
    private static final String[] DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES  = {
            "add*" ,
            "save*" ,
            "insert*" ,
            "delete*" ,
            "update*" ,
            "edit*" ,
            "batch*" ,
            "create*" ,
            "remove*" ,
            "exec*",
    };
    /**
     * 默认的只读事务
     */
    private static final String[] DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
            "get*" ,
            "count*" ,
            "find*" ,
            "query*" ,
            "select*" ,
            "list*" ,
            "*" ,
    };

    /**
     * 自定义事务 BeanName 拦截
     */
    private              String[] transactionBeanNames    = {};
    /**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法; 只读事务
     */
    private              String[] readOnlyMethodRuleTransactionAttributes     = {};
    /**
     * 自定义方法名的事务属性相关联,可以使用通配符(*)字符关联相同的事务属性的设置方法;
     *
     */
    private              String[] requiredMethodRuleTransactionAttributes     = {};


    @Resource
    private DataSource dataSource;

    @Bean
    public  PlatformTransactionManager txManager(){
        logger.info("正在初始化事务管理器");
        return new DataSourceTransactionManager(dataSource);
    }




    // 创建事务通知
    @Bean(name = TRANSACTION_INTERCEPTOR_NAME)
    public TransactionInterceptor txAdvice() throws Exception {
        logger.info("正在初始化事务拦截器,事务通知规则");
        Properties properties = new Properties();
        //只读
        String readOnlyTransactionRule = readOnlyTransactionRule();
        //传播
        String requiredTransactionRule = requiredTransactionRule();
        // 默认的只读事务配置
        for ( String methodName : DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES ) {
            properties.put(methodName,readOnlyTransactionRule);
        }
        // 默认的传播事务配置
        for ( String methodName : DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES ) {
            properties.put(methodName,requiredTransactionRule);
        }
        // 定制的只读事务配置
        for ( String methodName : readOnlyMethodRuleTransactionAttributes ) {
            properties.put(methodName,readOnlyTransactionRule);
        }
        // 定制的传播事务配置
        for ( String methodName : requiredMethodRuleTransactionAttributes ) {
            properties.put(methodName,requiredTransactionRule);
        }
        logger.info("获取事务通知规则 : "+JSON.toJSONString(properties));
        TransactionInterceptor tsi = new TransactionInterceptor(txManager(),properties);
        return tsi;

    }


    @Bean
    public BeanNameAutoProxyCreator txProxy () {
        logger.info("配置事务拦截器");
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        // 设置定制的事务拦截器
        beanNameAutoProxyCreator.setInterceptorNames( TRANSACTION_INTERCEPTOR_NAME );
        // 默认
        for ( String defaultTransactionBeanNameSuffix : DEFAULT_TRANSACTION_BEAN_NAMES ) {
            beanNameAutoProxyCreator.setBeanNames( defaultTransactionBeanNameSuffix );
        }
        // 定制
        for ( String customizeTransactionBeanName : transactionBeanNames ) {
            beanNameAutoProxyCreator.setBeanNames( customizeTransactionBeanName );
        }
        beanNameAutoProxyCreator.setProxyTargetClass( true );
        return beanNameAutoProxyCreator;
    }

    /**
     * 只读
     * PROPAGATION_REQUIRED
     * @return
     */
    private String readOnlyTransactionRule () {
       StringBuffer readOnlyStringBuffer = new StringBuffer();
        readOnlyStringBuffer.append(PROPAGATION_REQUIRED)
                .append(",")
                .append("-Exception")
                .append(",")
                .append("readOnly");

        return readOnlyStringBuffer.toString();
    }
    /**
     * PROPAGATION_REQUIRED
     * @return
     */
    private String requiredTransactionRule () {
        StringBuffer requiredStringBuffer = new StringBuffer();
        requiredStringBuffer.append(PROPAGATION_REQUIRED)
                .append(",")
                .append("-Exception");

        return requiredStringBuffer.toString();
    }

        public String[] getTransactionBeanNames() {
        return transactionBeanNames;
    }

    public void setTransactionBeanNames(String[] transactionBeanNames) {
        this.transactionBeanNames = transactionBeanNames;
    }

    public String[] getReadOnlyMethodRuleTransactionAttributes() {
        return readOnlyMethodRuleTransactionAttributes;
    }

    public void setReadOnlyMethodRuleTransactionAttributes(String[] readOnlyMethodRuleTransactionAttributes) {
        this.readOnlyMethodRuleTransactionAttributes = readOnlyMethodRuleTransactionAttributes;
    }

    public String[] getRequiredMethodRuleTransactionAttributes() {
        return requiredMethodRuleTransactionAttributes;
    }

    public void setRequiredMethodRuleTransactionAttributes(String[] requiredMethodRuleTransactionAttributes) {
        this.requiredMethodRuleTransactionAttributes = requiredMethodRuleTransactionAttributes;
    }



    public static String getTransactionInterceptorName() {
        return TRANSACTION_INTERCEPTOR_NAME;
    }



}

