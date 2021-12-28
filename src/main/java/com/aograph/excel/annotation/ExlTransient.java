package com.aograph.excel.annotation;

import java.lang.annotation.*;

/**
 * 是否将字段写入到Excel中，默认不写入
 *
 * @author LiuJunGuang
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExlTransient {
	public boolean value() default true;
}
