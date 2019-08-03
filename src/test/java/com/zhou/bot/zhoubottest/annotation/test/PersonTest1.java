package com.zhou.bot.zhoubottest.annotation.test;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName PersonTest1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/7/24 13:27
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PersonTest1 {

    String p() default "mi";

}
