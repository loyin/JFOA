package net.loyin.jFinal.anatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限绑定标志<br>
 * 如果此标记未注解的方法则默认为需要验证。
 * <br>默认为不验证
 * 在controller上使用
 * @author 刘声凤
 *  2012-9-4 上午11:48:26
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface PowerBind {
	/**对应权限代码*/
	String code() default "";
	/**验证标记  true:需要验证*/
	boolean v() default false;
}
