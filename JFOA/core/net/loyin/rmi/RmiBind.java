package net.loyin.rmi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RMI绑定注解
 * @author 刘声凤 
 * @date 2012-9-11 下午9:40:23
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface RmiBind {
	/** bind名称 */
	String name();
	/** 对应接口class **/
	Class<?> interfase();
}
