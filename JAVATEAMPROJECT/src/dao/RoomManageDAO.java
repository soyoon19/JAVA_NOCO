package dao;

import dto.RoomManageDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManageDAO implements DAO<RoomManageDTO, String> {
    private Connection conn;
    public RoomManageDAO(Connection coon){this.conn=coon;}


    @Override
    public boolean insert(RoomManageDTO manage) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO RoomManage_T (r_code, r_num, r_X, r_Y, r_option, r_check) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manage.getCode());
            pstmt.setString(2, manage.getNum());
            pstmt.setInt(3, manage.getX());
            pstmt.setInt(4,manage.getY());
            pstmt.setInt(5,manage.getOption());
            pstmt.setBoolean(6,manage.isCheck());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
    }


    @Override
    public RoomManageDTO findById(String code) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomManageDTO manage = null;
        try {
            String sql = "SELECT * FROM RoomManage_T WHERE r_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rst = pstmt.executeQuery();
            if (rst.next()) {
                manage = new RoomManageDTO();
                manage.setCode(rst.getString("r_code"));
                manage.setNum(rst.getString("r_num"));
                manage.setX(rst.getInt("r_X"));
                manage.setY(rst.getInt("r_Y"));
                manage.setOption(rst.getInt("r_option"));
                manage.setCheck(rst.getBoolean("r_check"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (rst != null)
                    rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return manage;
    }

    @Override
    public ArrayList<RoomManageDTO> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<RoomManageDTO> roomManageArr = new ArrayList();

        try{
            String sql = "select * from RoomManage_T";

            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()){
                RoomManageDTO roomMange = new RoomManageDTO();
                roomMange.setCode(rst.getString("r_code"));
                roomMange.setNum(rst.getString("r_num"));
                roomMange.setX(rst.getInt("r_X"));
                roomMange.setY(rst.getInt("r_Y"));
                roomMange.setOption(rst.getInt("r_option"));
                roomMange.setCheck(rst.getBoolean("r_check"));

                roomManageArr.add(roomMange);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null)
                    pstmt.close();
                if(rst != null)
                    rst.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return roomManageArr;
    }

    @Override
    public boolean delete(String code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM RoomManage_T WHERE r_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public boolean update(RoomManageDTO manage) {
        PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE RoomManage_T SET r_num=?, r_X=?, r_Y=?, r_option=?, r_check=? WHERE r_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manage.getNum());
            pstmt.setInt(2, manage.getX());
            pstmt.setInt(3, manage.getY());
            pstmt.setInt(4, manage.getOption());
            pstmt.setBoolean(5, manage.isCheck());
            pstmt.setString(6, manage.getCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public RoomManageDTO findLatest() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomManageDTO manage = null;

        try {
            String sql = "SELECT * FROM RoomManage_T ORDER BY r_code DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();

            if (rst.next()) {
                manage = new RoomManageDTO();
                manage.setCode(rst.getString("r_code"));
                manage.setNum(rst.getString("r_num"));
                manage.setX(rst.getInt("r_X"));
                manage.setY(rst.getInt("r_Y"));
                manage.setOption(rst.getInt("r_option"));
                manage.setCheck(rst.getBoolean("r_check"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (rst != null)
                    rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return manage;
    }

}
