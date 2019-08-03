package com.zhou.bot.zhoubottest.iotest;


import java.io.Serializable;

/**
 * @ClassName PersonIo
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/3/26 20:48
 */
public class PersonIo implements Serializable {
    private static final long serialVersionUID = 3392059630173353223L;

    public PersonIo(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonIo{" +
                "name='" + name + '\'' +
                '}';
    }

    public static String setSubHttps(String jump){
        /*if(StringUtils.isNotBlank(jump) && jump.length()>=5){
            String subMp = jump.substring(0,5);
            if(subMp.contains("//")){
                //包含协议头
                return "https:"+jump;
            }else if(subMp.contains("/")){
                return "https:/"+jump;
            }else if(subMp.equalsIgnoreCase("http:")){
                return  jump = "https:"+jump.substring(5,jump.length());
            }else if(!subMp.equalsIgnoreCase("https")){
                return "https://"+jump;
            }else{
                return jump;
            }
        }*/
        return jump;
    }

    public static String setSubHttp(String jump){
        //只有4中合法URL开头形式：http://xxx.xxx.xx  https://xxx.xxx.xx  //xxx.xxx.xx  xxx.xxx.xx
        /*if(StringUtils.isNotBlank(jump) && jump.length()>=5){
            String subMp = jump.substring(0,5);
            if(!subMp.equalsIgnoreCase("http:")){
                if(subMp.contains("//")){
                    //包含协议头
                    return "http:"+jump;
                }else if(subMp.contains("/")){
                    return "http:/"+jump;
                }else if(!subMp.equalsIgnoreCase("https")){
                    return "http://"+jump;
                }else{
                    return jump;
                }
            }
        }*/
        return jump;
    }

    public static void main(String[] args){
        String jump = "//thirdwx1.qlogo.cn/mmopen/vi_32/DYAIOgq83ervZTcn4xdDUcf07PCBpXuqq8KWZ8icT05ZWWjkjT8wZ9k7jibSA1jbe3aGFUicNxt6KHxJUk5paZ0xg/132";
        System.out.println("解析后： "+setSubHttps(jump));
        /*if(StringUtils.isNotBlank(jump) && jump.length()>=5){
            String subMp = jump.substring(0,5);
            System.out.println("subMp  "+subMp);
            if(subMp.contains("//")){
                //包含协议头
                System.out.println("https:"+jump);
            }else if(subMp.contains("/")){
                System.out.println("https:/"+jump);
            }else if(subMp.equals("http:")){
                jump = "https:"+jump.substring(5,jump.length());
                System.out.println(jump);
            }else if(!subMp.equals("https")){
                System.out.println("https://"+jump);
            }else{
                System.out.println(jump);
            }
        }*/
    }
}
