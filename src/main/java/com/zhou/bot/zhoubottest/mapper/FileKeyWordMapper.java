package com.zhou.bot.zhoubottest.mapper;

import com.zhou.bot.zhoubottest.model.KeyWordInfo;

import java.util.Map;

public interface FileKeyWordMapper {

    public String getKeyWordNum(KeyWordInfo keyWordInfo);

    public Integer updateKeyWordNum(KeyWordInfo keyWordInfo);

    public Long insertKeyWord(KeyWordInfo keyWordInfo);

    public Integer countKeyWordNum(KeyWordInfo keyWordInfo);
}
