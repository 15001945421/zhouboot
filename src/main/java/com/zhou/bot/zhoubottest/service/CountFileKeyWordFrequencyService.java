package com.zhou.bot.zhoubottest.service;

import com.zhou.bot.zhoubottest.config.WriteDataSource;
import com.zhou.bot.zhoubottest.mapper.FileKeyWordMapper;
import com.zhou.bot.zhoubottest.model.KeyWordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CountFileKeyWordFrequencyService
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/2/25 13:15
 */
@Service
public class CountFileKeyWordFrequencyService {

    @Autowired
    private FileKeyWordMapper fileKeyWordMapper;


    @WriteDataSource
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,rollbackFor=Exception.class,readOnly=false)
    public String handleFeile (Integer bussType) throws Exception{
        BufferedReader br = null;
        try {
            //设置文件路径
            String path = "d:\\keyword.txt";
            File file=new File(path);
            if(!file.exists()){
                //判断文件是否存在
                return "文件目录或者文件不存在";
            }
            //获取缓冲流
            br = new BufferedReader(new FileReader(file));
            //一行一行读取
            String line = null;
            while((line=br.readLine())!=null){
                //去除大小写
                line = line.toLowerCase();
                String reg1 = "\\s+";
                //将读取的文本进行分割
                String str[] = line.split(reg1);
                for(String s: str){
                    KeyWordInfo param = new KeyWordInfo();
                    param.setBussType(bussType);
                    param.setKeyword(s);
                    //查询关键词是否存在
                    String keyWord = fileKeyWordMapper.getKeyWordNum(param);
                    if(keyWord!=null){
                        //该关键字已存在，则更新数量
                        fileKeyWordMapper.updateKeyWordNum(param);
                    }else{
                        //说明没有该关键词，那么插入数据库
                        KeyWordInfo keyWordInfo = new KeyWordInfo();
                        keyWordInfo.setKeyword(s);
                        keyWordInfo.setNum(1);
                        keyWordInfo.setBussType(bussType);
                        fileKeyWordMapper.insertKeyWord(keyWordInfo);
                        System.out.println(keyWordInfo.getId());
                    }
                }
            }
            br.close();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            try {
                if(br!=null){
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @WriteDataSource
    public String countKeyWordNum(KeyWordInfo param){
        Integer countKeyWord = null;
        try {
            countKeyWord = fileKeyWordMapper.countKeyWordNum(param);
            if(countKeyWord==null){
                countKeyWord = 0;
            }
            return countKeyWord+"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取关键词频率异常，请稍后再试！";
    }

}
