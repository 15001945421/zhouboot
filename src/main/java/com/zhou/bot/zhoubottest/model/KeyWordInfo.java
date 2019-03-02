package com.zhou.bot.zhoubottest.model;

import java.io.Serializable;

/**
 * @ClassName KeyWordInfo
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/2/25 13:55
 */
public class KeyWordInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String keyword;
    private Integer num;
    private Integer bussType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getBussType() {
        return bussType;
    }

    public void setBussType(Integer bussType) {
        this.bussType = bussType;
    }
}
