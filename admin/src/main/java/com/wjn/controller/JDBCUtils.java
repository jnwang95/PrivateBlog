package com.wjn.controller;


import cn.hutool.core.util.StrUtil;
import com.mysql.jdbc.PreparedStatement;
import com.wjn.bean.mysql.Columns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


/**
 * @auther WJN
 * @date 2019/9/27 10:44
 * @describetion
 */
public class JDBCUtils {
    //链接数据库
    private static Connection conn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");//加载Driver类
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "root");//链接数据库
    }

    /**
     * 查询所有数据库名
     *
     * @return 数据库名集合
     * @throws Exception
     */
    public static List<String> getDatabases() throws Exception {
        Connection connection;
        connection = conn();
        //SQL语句
        String sql = "SELECT `SCHEMA_NAME` FROM `information_schema`.`SCHEMATA`";
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String schema_name = resultSet.getString("SCHEMA_NAME");
            list.add(schema_name);
        }
        statement.close();
        connection.close();
        return list;
    }

    /**
     * 查询某个数据库的所有表名
     *
     * @param database
     * @return
     * @throws Exception
     */
    public static List<String> getTables(String database) throws Exception {
        Connection connection;
        connection = conn();
        //SQL语句
        String sql = "SELECT `TABLE_NAME` FROM `information_schema`.`TABLES` WHERE `TABLE_SCHEMA` = ? ";
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        statement.setString(1, database);
        ResultSet resultSet = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String TABLE_NAME = resultSet.getString("TABLE_NAME");
            list.add(TABLE_NAME);
        }
        statement.close();
        connection.close();
        return list;
    }

    /**
     * 获取某个数据库某个表的所有列的属性
     * @param database
     * @param table
     * @return
     * @throws Exception
     */
    public static List<Columns> getTableAttributes(String database, String table) throws Exception {
        Connection connection;
        connection = conn();

        String sql = "SELECT COLUMN_NAME,IS_NULLABLE,DATA_TYPE,COLUMN_TYPE,COLUMN_KEY,EXTRA,COLUMN_COMMENT,COLUMN_DEFAULT FROM " +
                "`information_schema`.`COLUMNS` WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        statement.setString(1,database);
        statement.setString(2,table);
        ResultSet resultSet = statement.executeQuery();
        List<Columns> list = new ArrayList<>();
        while (resultSet.next()) {
            //列名
            String COLUMN_NAME = resultSet.getString("COLUMN_NAME");
            //数据结构
            String DATA_TYPE = resultSet.getString("DATA_TYPE");
            //列属性
            String COLUMN_TYPE = resultSet.getString("COLUMN_TYPE");
            //默认值
            String COLUMN_DEFAULT = resultSet.getString("COLUMN_DEFAULT");
            //是否为空
            String IS_NULLABLE = resultSet.getString("IS_NULLABLE");
            boolean nul;
            nul = "YES".equalsIgnoreCase(IS_NULLABLE);
            //是否是主键
            String COLUMN_KEY = resultSet.getString("COLUMN_KEY");
            boolean pri;
            pri = "PRI".equalsIgnoreCase(COLUMN_KEY);
            //是否自增
            String EXTRA = resultSet.getString("EXTRA");
            boolean inc;
            inc = "auto_increment".equalsIgnoreCase(EXTRA);
            //注释
            String COLUMN_COMMENT = resultSet.getString("COLUMN_COMMENT");
            Columns columns = Columns.of();
            columns.setCOLUMN_NAME(COLUMN_NAME)
                    .setDATA_TYPE(DATA_TYPE)
                    .setCOLUMN_TYPE(COLUMN_TYPE)
                    .setCOLUMN_DEFAULT(COLUMN_DEFAULT)
                    .setIS_NULLABLE(nul)
                    .setCOLUMN_KEY(pri)
                    .setEXTRA(inc)
                    .setCOLUMN_COMMENT(COLUMN_COMMENT);
            list.add(columns);
        }
        statement.close();
        connection.close();
        return list;
    }


    public static List<String> getTableName(String database, String table) throws Exception {
        Connection connection;
        connection = conn();

        String sql = "SELECT COLUMN_NAME  FROM `information_schema`.`COLUMNS` WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        statement.setString(1, database);
        statement.setString(2, table);
        ResultSet resultSet = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            //列名
            String COLUMN_NAME = resultSet.getString("COLUMN_NAME");
            list.add(COLUMN_NAME);
        }
        statement.close();
        connection.close();
        return list;
    }


    /**
     * 获取某个表的所有数据
     * @param database
     * @param table
     * @return
     * @throws Exception
     */
    public static List<List<String>> getTableData(String database, String table) throws Exception {
        Connection connection;
        connection = conn();
        String sql = "select * from "+database+"."+table;
        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
//        List<Map<String,String>> list = new ArrayList<>();
//
//        while (resultSet.next()) {
//            Map<String,String> map = new HashMap<>();
//            for (int i = 1; i <= columnCount; i++){
//                String column = metaData.getColumnLabel(i);
//                String data= resultSet.getString(column);
//                map.put(column,data);
//            }
//            list.add(map);
//        }
        List<List<String>> list = new ArrayList<>();
        while (resultSet.next()) {
            List<String> stringList = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++){
                String column = metaData.getColumnLabel(i);
                String data= resultSet.getString(column);
                if(StrUtil.isNotEmpty(data)){
                    if(data.length() > 20){
                        data = data.substring(0, 19);
                    }
                }
                stringList.add(data);
            }
            list.add(stringList);
        }
        statement.close();
        connection.close();
        return list;
    }
}