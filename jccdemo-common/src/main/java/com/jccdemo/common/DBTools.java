package com.jccdemo.common;

import java.sql.*;

/**
 *
 */
public class DBTools {
    private static String url;
    private static String userName;
    private static String password;

    /**
     * 得到DB驱动
     * @createtime Feb 11, 2012 4:10:31 PM
     * @return DB驱动
     */
    public static String getUrl() {
        return url;
    }

    /**
     * 得到DB用户名
     * @createtime Feb 11, 2012 4:10:58 PM
     * @return DB用户名
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * 得到DB连接密码
     * @createtime Feb 11, 2012 4:11:14 PM
     * @return DB连接密码
     */
    public static String getPassword() {
        return password;
    }

    /**
     * 设置DB参数
     * @createtime Feb 11, 2012 4:11:29 PM
     * @param driver driver
     * @param url url
     * @param userName userName
     * @param password password
     */
    public static void setDBParameter(String driver, String url, String userName, String password) {
        try {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DBTools.url = url;
        DBTools.userName = userName;
        DBTools.password = password;
    }

    /**
     * 检查DB参数是否已经初始化
     * @createtime Feb 11, 2012 4:12:10 PM
     * @throws SQLException
     */
    private static void checkInitial() throws SQLException {
        if ( (Tools.isEmpty(url)) || (Tools.isEmpty(userName)) || (Tools.isEmpty(password)) )
            throw new SQLException("parameter is null.");
    }

    /**
     * 得到DB连接
     * @createtime Feb 11, 2012 4:12:30 PM
     * @return DB连接
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        checkInitial();
        return DriverManager.getConnection(url, userName, password);
    }

    /**
     * 执行存储过程
     * @createtime Feb 11, 2012 4:12:49 PM
     * @param sql 执行存储过程的SQL
     * @param args 参数，如果没有则填入null
     * @return 执行存储过程所影响的行数
     * @throws SQLException
     */
    public static int executeCallableUpdate(String sql, Object[] args) throws SQLException {
        Connection conn = null;
        CallableStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareCall(sql);
            if ( args != null ) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i, args[i]);
                }
            }
            int i = pstmt.executeUpdate();
            return i;
        }
        finally {
            closeConnection(conn, pstmt, null);
        }
    }

    /**
     * 批量执行SQL修改语句
     * @createtime Feb 11, 2012 4:16:57 PM
     * @param sqls SQL语句
     * @return 返回值
     * @throws SQLException
     */
    public static int[] batchUpdate(String[] sqls) throws SQLException {
        Connection conn = null;
        Statement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.createStatement();
            String[] arrayOfString = sqls;
            int j = sqls.length;
            for (int i = 0; i < j; i++) {
                String sqlStr = arrayOfString[i];
                pstmt.addBatch(sqlStr);
            }
            int[] arrayOfInt = pstmt.executeBatch();
            return arrayOfInt;
        }
        finally {
            closeConnection(conn, pstmt, null);
        }
    }

    /**
     * 执行SQL修改语句
     * @createtime Feb 11, 2012 4:16:57 PM
     * @param sql SQL语句
     * @return 返回值
     * @throws SQLException
     */
    public static int executeUpdate(String sql, Object[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if ( args != null ) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                }
            }
            int i = pstmt.executeUpdate();
            return i;
        }
        finally {
            closeConnection(conn, pstmt, null);
        }
    }

    /**
     * 关闭数据库资源
     * @createtime Feb 11, 2012 4:18:53 PM
     * @param conn conn
     * @param pstmt pstmt
     * @param rs 结果集
     * @throws SQLException
     */
    public static void closeConnection(Connection conn, Statement pstmt, ResultSet rs) throws SQLException {
        if ( rs != null ) {
            rs.close();
        }
        rs = null;
        if ( pstmt != null ) {
            pstmt.close();
        }
        pstmt = null;
        if ( conn != null ) {
            conn.close();
        }
        conn = null;
    }
}
