package com.wjn.controller;

import cn.hutool.system.*;
import com.wjn.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Welcome controller.
 *
 * @auther WJN
 * @date 2019 /10/12 13:43
 * @describetion
 */
@RestController
@RequestMapping("welcome")
public class WelcomeController {

    /**
     * 通过hutool自带的工具方法，获取当前系统等信息
     *
     * @return json result
     */
    @GetMapping("version")
    @ApiOperation(value = "获取系统信息")
    public JsonResult getVersion(){
        Map<String,String> map = new HashMap<>();
        JvmSpecInfo jvmSpecInfo = SystemUtil.getJvmSpecInfo();
        JavaInfo javaInfo = SystemUtil.getJavaInfo();
        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        UserInfo userInfo = SystemUtil.getUserInfo();
        OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        map.put("JavaVM Spec. Name",jvmSpecInfo.getName());
        map.put("Java Version",javaInfo.getVersion());
        map.put("Java Vendor",javaInfo.getVendor());
        map.put("Java Vendor URL",javaInfo.getVendorURL());
        map.put("Java Runtime Name",javaRuntimeInfo.getName());
        map.put("Java Runtime Version:",javaRuntimeInfo.getVersion());
        map.put("Java Home Dir",javaRuntimeInfo.getHomeDir());
        map.put("Java Class Version",javaRuntimeInfo.getClassVersion());
        map.put("OS Arch",osInfo.getArch());
        map.put("OS Name",osInfo.getName());
        map.put("OS Version",osInfo.getVersion());
        map.put("User Name",userInfo.getName());
        map.put("User Language",userInfo.getLanguage());
        map.put("User Country",userInfo.getCountry());
        map.put("Host Name",hostInfo.getName());
        map.put("Host Address",hostInfo.getAddress());
        map.put("Max Memory",runtimeInfo.getMaxMemory()+"");
        map.put("Total Memory",runtimeInfo.getTotalMemory()+"");
        map.put("Free Memory",runtimeInfo.getFreeMemory()+"");
        map.put("Usable Memory",runtimeInfo.getUsableMemory()+"");
        return JsonResult.success(map);
    }
}
