package store_ma.dao;

import store_ma.model.Users;
import store_ma.util.DbUtils;
import store_ma.util.StringUtil;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao{

    /*
    * 用户登录
    * */
    public Users login(Users user){
        PreparedStatement pstmt = null;
        Users USER = null;
        ResultSet re = null;

        String sql = "select * from users where name = ? and password = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());
            //执行sql
            re = pstmt.executeQuery();
            if(re.next()){
                USER = new Users();
                USER.setId(re.getInt("id"));
                USER.setName(re.getString("name"));
                USER.setPassword(re.getString("password"));
                USER.setAuthority(re.getInt("authority"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            //释放资源
            DbUtils.close(pstmt,conn,re);
        }
        return USER;
    }

    //显示用户表
    public List<Users> getUsersList(Users user) {
        List<Users> list = new ArrayList<>();
        String sql = "select* from Users";
        PreparedStatement pstmt = null;
        ResultSet re = null;
        if(!StringUtil.isEmpty(user.getName()))
        {
            sql += "where name like '%"+user.getName()+"%'";
        }
        try {
            pstmt = conn.prepareStatement(sql);
            re = pstmt.executeQuery();
            while (re.next()) {
                Users u = new Users();
                u.setId(re.getInt("id"));
                u.setName(re.getString("name"));
                u.setPassword(re.getString("password"));
                u.setAuthority(re.getInt("authority"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(pstmt,conn,re);
        }
        return list;
    }

    //删除
    public boolean delete(int id){
        String sql = "delete from users where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(pstmt,conn);
        }
        return false;
    }
    public boolean delete1(int id){
        String sql = "delete from users where id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally { }
        return false;
    }

    //修改密码
    public Boolean correctPw(Users user,String newPassword) {
        String sql = "UPDATE users SET password = ? where id = ?" ;
        PreparedStatement pstmt =null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newPassword);
            pstmt.setInt(2,user.getId());
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(pstmt,conn);
        }
        return false;
    }

    //注册用户
    public Boolean register(int id,String name,int password,int authority) {
        String sql = "insert into users values(?,?,?,?)" ;
        PreparedStatement pstmt =null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setInt(3,password);
            pstmt.setInt(4,authority);
            if(pstmt.executeUpdate()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.close(pstmt,conn);
        }
        return false;
    }

}
