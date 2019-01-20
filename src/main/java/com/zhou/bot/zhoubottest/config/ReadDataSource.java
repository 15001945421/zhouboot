package com.zhou.bot.zhoubottest.config;

import java.lang.annotation.*;

/**
 * @ClassName ReadDataSource
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/1/15 19:16
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReadDataSource {
}
