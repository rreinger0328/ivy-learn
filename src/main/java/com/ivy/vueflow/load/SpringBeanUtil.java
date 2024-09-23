package com.ivy.vueflow.load;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author moon
 * @date 2023-08-31 11:18
 * @since 1.8
 */
@Component(value = "ivySpringBeanUtil")
public class SpringBeanUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    private static ConfigurableApplicationContext context ;

    /**
     * 获取 Bean 工厂
     */
    private static DefaultListableBeanFactory beanFactory ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
        SpringBeanUtil.context= (ConfigurableApplicationContext) applicationContext;
        SpringBeanUtil.beanFactory= (DefaultListableBeanFactory) context.getBeanFactory();
    }

    /**
     * 替换 bean 并获取新的
     * @param beanName
     * @param clazz
     * @return
     */
    public static Object replace(String beanName,Class clazz){
        //卸载
        unregister(beanName);
        //注册
        register(beanName,clazz);
        //获取
        return getBean(beanName);
    }

    /**
     * 注册 Bean
     * @param clazz
     */
    public static void register(String beanName,Class clazz){
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        BeanDefinition definition = builder.getBeanDefinition();
        //为 definition 设置额外属性
        definition.setScope("singleton");
        //注册
        beanFactory.registerBeanDefinition(beanName,definition);
    }

    /**
     * 卸载 Bean
     * @param beanName
     */
    public static void unregister(String beanName){
        if (beanFactory.containsBean(beanName)){
            beanFactory.removeBeanDefinition(beanName);
        }
    }

    /**
     * 获取所有 Bean
     * @return
     */
    public static List<String> getBeans(){
        String[] names = applicationContext.getBeanDefinitionNames();
        List<String> beans = new ArrayList<>(names.length);
        for (String name:names){
            beans.add(applicationContext.getBean(name).getClass().getName());
        }
        return beans;
    }

    /**
     * bean 是否存在
     * @param name
     * @return
     */
    public static boolean isBeanExist(String name){
        return applicationContext.containsBean(name);
    }

    /**
     * 通过名称获取 Bean
     * @param name
     * @return
     * @param <T>
     * @throws BeansException
     */
    public static <T> T getBean(String name) throws BeansException{
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过类型获取 Bean
     * @param clazz
     * @return
     * @param <T>
     * @throws BeansException
     */
    public static <T> T getBean(Class<?> clazz) throws BeansException{
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * 获取指定类型的 Bean 的名称
     * @param className
     * @return
     * @throws BeansException
     */
    public static List<String> getBeanName(String className) throws BeansException, ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return Arrays.asList(applicationContext.getBeanNamesForType(clazz));
    }
}
