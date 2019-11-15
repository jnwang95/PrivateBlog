package com.wjn.controller;

import com.wjn.bean.mysql.Columns;
import com.wjn.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther WJN
 * @date 2019/9/27 10:27
 * @describetion MySQL管理工具
 */
@RestController
public class MySQLController {


    /**
     * 获取所有的数据库名
     */
    @GetMapping("databases")
    public JsonResult getDatabase() throws Exception {
        List<String> databases = JDBCUtils.getDatabases();
        return JsonResult.success(databases);
    }

    /**
     * 获取该数据库的所有表名
     */
    @GetMapping("tables/{database}")
    public JsonResult getTables(@PathVariable("database") String database) throws Exception{
        List<String> tables = JDBCUtils.getTables(database);
        return JsonResult.success(tables);
    }

    /**
     * 获取某数据库的某个表的所有列及其属性
     */
    @GetMapping("getTableAttributes/{database}/{table}")
    public JsonResult getTableAttributes(@PathVariable("database") String database, @PathVariable("table") String table) throws Exception{
        List<Columns> columns = (List<Columns>) JDBCUtils.getTableAttributes(database, table);
        return JsonResult.success(columns);
    }

    /**
     * 获取某数据库的某个表的所有列名称
     */
    @GetMapping("getTableName/{database}/{table}")
    public JsonResult getTableName(@PathVariable("database") String database, @PathVariable("table") String table) throws Exception{
        List<String> tables = JDBCUtils.getTableName(database,table);
        return JsonResult.success(tables);
    }

    /**
     * 获取某数据库的某个表的所有数据
     */
    @GetMapping("getTableData/{database}/{table}")
    public JsonResult getTableData(@PathVariable("database") String database, @PathVariable("table") String table) throws Exception{
        List<List<String>> tables = JDBCUtils.getTableData(database,table);
        return JsonResult.success(tables);
    }
}
