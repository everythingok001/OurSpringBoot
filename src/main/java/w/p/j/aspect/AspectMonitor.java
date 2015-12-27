package w.p.j.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统服务组件Aspect切面Bean
 * @author wangdong
 * @date 2015-12-12
 */
//声明这是一个组件

//声明这是一个切面Bean
@Component
@Aspect
public class AspectMonitor {

	private final static Logger log = LoggerFactory.getLogger(AspectMonitor.class);
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* w.p.j.controller..*(..))")
	public void aspect(){	}
	
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint){
		log.info("----------日志开始------------");    
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		log.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
		log.info("请求IP:" + request.getRemoteHost()); 
     	Map<String,Object> queryMap = new HashMap<String,Object>();
     	//封装request参数
		Enumeration<String> en=request.getParameterNames();
		while (en.hasMoreElements()) {
		    String paramName = (String) en.nextElement();
		    String paramValue = request.getParameter(paramName);
		    if(!paramValue.equals("")){
		    	//形成键值对应的map
			    queryMap.put(paramName, paramValue);
		    }
		}
		try {
			Method setEiInfo =  joinPoint.getTarget().getClass().getMethod("setEiInfo",Map.class);
			setEiInfo.invoke(joinPoint.getTarget(),queryMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	//配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		try {
			Method setEiInfo =  joinPoint.getTarget().getClass().getMethod("getEiInfo");
			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String,Object>) setEiInfo.invoke(joinPoint.getTarget());
			log.info(map.toString());
			request.setAttribute("EiInfo", map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(log.isInfoEnabled()){
			log.info("----------日志结束------------");    
		}
	}
	
	
	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut="aspect()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		if(log.isInfoEnabled()){
			log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
		}
	}
	
}