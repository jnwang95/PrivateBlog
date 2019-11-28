package com.wjn.workbook.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.wjn.workbook.bean.WjnLoveXyq;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @description:
 * @author: jnWang
 * @create: 2019-11-25 15:23
 */
@RestController
public class WorkbookController {

    @GetMapping("workbook")
    public void workbook(HttpServletResponse res) throws IOException {

        // 默认的为application/json,而谷歌浏览器所期望的值应该是text/html
        res.setHeader("Content-type", "text/html");
        // 设置响应头 设置 导出的表的名称
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("庆庆", "utf-8") + ".xlsx");
        // 设置响应数据编码格式
        res.setCharacterEncoding("utf-8");
        List<WjnLoveXyq> loveXyqs = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            WjnLoveXyq wjnLoveXyq = new WjnLoveXyq();
            wjnLoveXyq.setWjn("汪靖宁");
            wjnLoveXyq.setXyq("徐迎庆");
            wjnLoveXyq.setLove("汪靖宁love徐迎庆");
            loveXyqs.add(wjnLoveXyq);
        }


        ExportParams deptExportParams = new ExportParams();
        deptExportParams.setSheetName("好玩吧");
        short height = 15;
        deptExportParams.setHeight(height);
        // 创建sheet1使用得map
        Map<String, Object> deptExportMap = new HashMap<>();
        deptExportMap.put("title", deptExportParams);
        deptExportMap.put("entity", WjnLoveXyq.class);
        deptExportMap.put("data", loveXyqs);
        List<Map<String, Object>> sheetsList = new ArrayList<>();
        sheetsList.add(deptExportMap);
        // 执行方法
        Workbook workBook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
        workBook.write(res.getOutputStream());
    }
}
