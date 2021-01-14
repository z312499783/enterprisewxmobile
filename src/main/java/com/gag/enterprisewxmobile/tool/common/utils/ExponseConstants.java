package com.gag.enterprisewxmobile.tool.common.utils;

import com.gag.enterprisewxmobile.tool.common.utils.DateUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;

//把map转换成list的公共方法
public class ExponseConstants {


    public LinkedList parse(LinkedList list, Class obj){
        //生成集合
        LinkedList ary = new LinkedList();
        //遍历集合中的所有数据
        for(int i = 0; i<list.size(); i++){
            try {
                ////生成对象实历 将MAP中的所有参数封装到对象中
                Object o = addProperty( (LinkedHashMap)list.get(i),obj.newInstance() );
                //把对象加入到集合中
                ary.add(o);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //返回封装好的集合
        return ary;
    }

    public Object addProperty(LinkedHashMap map,Object obj){
        //遍历所有名称
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            //取得名称
            String name = it.next().toString();
            //取得值
            String value = map.get(name).toString();
            try{
                //取得值的类形
                Class type = PropertyUtils.getPropertyType(obj, name);

                if(type!=null){
                    //设置参数
                    if (type.getName().equals("java.util.Date")){
                        PropertyUtils.setProperty(obj, name, ConvertUtils.convert(DateUtils.parseDate(value), type));

                    }else {
                        PropertyUtils.setProperty(obj, name, ConvertUtils.convert(value, type));
                    }
                }else {
                    PropertyUtils.setProperty(obj, name, ConvertUtils.convert(value, Long.TYPE));
                }

            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
        return obj;
    }
}
