package com.justin.search.utils;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;

/**
 * @Auther:justin_xiao
 * @Date:2024/4/11
 */
public class DubboUtil {


    public static void main(String[] args) {
        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("sendEmailFromDispatchCenter");
        reference.setApplication(applicationConfig);


        //自定义报表数据统计

        reference.setProtocol("dubbo");

        reference.setInterface("com.sekorm.dubbo.ecm.declare.iedmservice.EmailSendDubbo");
        //dev
//   	 reference.setUrl("dubbo://172.16.20.212:21080");
        //newdev
        reference.setUrl("dubbo://172.16.2.57:21084");
        //local
//   	  reference.setUrl("dubbo://127.0.0.1:21080");
        //newtest
//            reference.setUrl("dubbo://172.16.2.56:21080");
        //zhuge
        //reference.setUrl("dubbo://172.16.1.190:21080");


        reference.setTimeout(10000);
        reference.setRetries(-1);

        // 声明为泛化接口
        reference.setGeneric(true);
        // GenericService可以接住所有的实现
        GenericService genericService = reference.get();


        String[] parameterTypes = {"java.lang.Long"};
        Object[] parameters = {1};
        Object result = genericService.$invoke("sendEmailFromDispatchCenter", parameterTypes, parameters);
        System.out.println(JSON.toJSONString(result));


    }


}
