package net.loyin.jFinal.anatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * model绑定数据库表注解
 * @author loyin
 *  2012-9-4 上午11:48:26
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableBind {
	/**表名*/
	String name() default "";
	/**主键名*/
	String pk() default "id";
}