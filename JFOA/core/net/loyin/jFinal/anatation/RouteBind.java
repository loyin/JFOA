package net.loyin.jFinal.anatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Route 绑定Controller注解<br>
 * 在controller上使用
 * @author 刘声凤
 *  2012-9-4 上午11:48:26
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RouteBind {
	/**对应的路径名 已/开头*/
	String path() default "/";
	/**视图所在目录*/
	String viewPath() default "";
}
