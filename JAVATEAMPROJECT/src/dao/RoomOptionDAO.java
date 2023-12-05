package dao;

import dto.GoodsDTO;
import dto.RoomOptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomOptionDAO implements DAO<RoomOptionDTO, Integer>{
    private Connection conn;
    public RoomOptionDAO(Connection coon){ this.conn=coon;}


    @Override
    public boolean insert(RoomOptionDTO option) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO RoomOption_T (r_option,r_width,r_height) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,option.getOption());
            pstmt.setInt(2,option.getWidth());
            pstmt.setInt(3,option.getHeight());
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
    public RoomOptionDTO findById(Integer code) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomOptionDTO option = null;
        try {
            String sql = "SELECT * FROM RoomOption_T WHERE r_option = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, code);
            rst = pstmt.executeQuery();
            if (rst.next()) {
                option = new RoomOptionDTO();
                option.setOption(rst.getInt("r_option"));
                option.setWidth(rst.getInt("r_width"));
                option.setHeight(rst.getInt("r_height"));
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
        return option;
    }

    @Override
    public ArrayList<RoomOptionDTO> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<RoomOptionDTO> roomOptionArr = new ArrayList();

        try {
            String sql = "select * from RoomOption_T";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()){
                RoomOptionDTO roomOption = new RoomOptionDTO();
                roomOption.setOption(rst.getInt("r_option"));
                roomOption.setWidth(rst.getInt("r_width"));
                roomOption.setHeight(rst.getInt("r_height"));

                roomOptionArr.add(roomOption);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (rst != null)
                    rst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return roomOptionArr;
    }

    @Override
    public boolean delete(Integer code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM RoomOption_T WHERE r_option = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, code);
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


    public boolean update(RoomOptionDTO option) {
        PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE RoomOption_T SET r_width=?, r_height=? WHERE r_option=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, option.getWidth());
            pstmt.setInt(2, option.getHeight());
            pstmt.setInt(3, option.getOption());

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

}
