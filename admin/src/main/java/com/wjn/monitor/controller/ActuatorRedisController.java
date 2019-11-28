package com.wjn.monitor.controller;

import com.wjn.monitor.domain.RedisInfo;
import com.wjn.monitor.service.RedisService;
import com.wjn.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/actuator/redis")
public class ActuatorRedisController {

    @Autowired
    private RedisService redisService;

    /**
     * Redis详细信息
     * @return
     * @throws Exception
     */
    @GetMapping("/info")
    public JsonResult getRedisInfo() throws Exception {
		List<RedisInfo> infoList = this.redisService.getRedisInfo();
        log.info(infoList.toString());
        return JsonResult.success(infoList);
    }

    @GetMapping("/keysSize")
    public Map<String, Object> getKeysSize() throws Exception {
        return redisService.getKeysSize();
    }

    @GetMapping("/memoryInfo")
    public Map<String, Object> getMemoryInfo() throws Exception {
        return redisService.getMemoryInfo();
    }
    
  	/**
  	 * @功能：获取磁盘信息
  	 * @return
  	 */
  	@GetMapping("/queryDiskInfo")
  	public JsonResult<List<Map<String,Object>>> queryDiskInfo(){
		List<Map<String,Object>> list = new ArrayList<>();
  		try {
  			// 当前文件系统类
  	        FileSystemView fsv = FileSystemView.getFileSystemView();
  	        // 列出所有windows 磁盘
  	        File[] fs = File.listRoots();
  	        log.info("查询磁盘信息:"+fs.length+"个");
			for (File f : fs) {
				if (f.getTotalSpace() == 0) {
					continue;
				}
				Map<String, Object> map = new HashMap<>();
				map.put("name", fsv.getSystemDisplayName(f));
				map.put("max", f.getTotalSpace());
				map.put("rest", f.getFreeSpace());
				map.put("restPPT", (f.getTotalSpace() - f.getFreeSpace()) * 100 / f.getTotalSpace());
				list.add(map);
				log.info(map.toString());
			}
  		} catch (Exception e) {
			JsonResult.failMessage(e.getMessage());
  		}
  		return JsonResult.success(list);
  	}
}
