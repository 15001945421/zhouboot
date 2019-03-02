package com.zhou.bot.zhoubottest.controller;

import com.zhou.bot.zhoubottest.model.KeyWordInfo;
import com.zhou.bot.zhoubottest.service.CountFileKeyWordFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CountFileKeyWordFrequencyController
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/2/25 13:02
 */
@RestController
public class CountFileKeyWordFrequencyController {

    @Autowired
    private CountFileKeyWordFrequencyService countFileKeyWordFrequencyService;

    @RequestMapping("/getKeyWord/{key}/{bussType}")
    public String getKeyWord(@PathVariable("key") String key,@PathVariable("bussType") Integer bussType ) {
        String count = "获取关键词出现频率失败";
        try {
            KeyWordInfo keyWordInfo = new KeyWordInfo();
            keyWordInfo.setBussType(bussType);
            keyWordInfo.setKeyword(key);
            count = countFileKeyWordFrequencyService.countKeyWordNum(keyWordInfo);
            count = "关键词： "+key+" ,出现的频率次数为："+count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @RequestMapping("/handleFeile/{bussType}")
    public String handleFeile(@PathVariable("bussType") Integer bussType ) {
        String result = "文件处理失败，请再次尝试";
        try {
            result = countFileKeyWordFrequencyService.handleFeile(bussType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
