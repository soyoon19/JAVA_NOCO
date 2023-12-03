package dao;

import dto.RoomIfmDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomIfmDAO implements DAO<RoomIfmDTO, String>{
    private Connection conn;

    public RoomIfmDAO(Connection coon){
        this.conn=coon;
    }

    public boolean insert(RoomIfmDTO imf){

        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO RoomIfm_T (r_num, r_userHP, r_enter, r_useSong, r_leftSong, r_paySong, r_using) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,imf.getNum());
            pstmt.setString(2, imf.getUserHp());
            pstmt.setTime(3,imf.getEnterTime());
            pstmt.setInt(4,imf.getUseSong());
            pstmt.setInt(5,imf.getLeftSong());
            pstmt.setInt(6,imf.getPaySong());
            pstmt.setBoolean(7,imf.isUsing());
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
    public RoomIfmDTO findById(String code) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomIfmDTO rimf = null;
        try {
            String sql = "select * from RoomIfm_T WHERE r_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rst = pstmt.executeQuery();

            if(rst.next()){
                rimf = new RoomIfmDTO();
                rimf.setUserHp(rst.getString("r_userHP"));
                rimf.setEnterTime(rst.getTime("r_enter"));
                rimf.setNum(rst.getString("r_num"));
                rimf.setUseSong(rst.getInt("r_useSong"));
                rimf.setLeftSong(rst.getInt("r_leftSong"));
                rimf.setPaySong(rst.getInt("r_paySong"));
                rimf.setUsing(rst.getBoolean("r_using"));
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
        return rimf;
    }

    @Override
    public ArrayList<RoomIfmDTO> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<RoomIfmDTO> roomimfarr = new ArrayList();

        try {
            String sql = "select * from RoomIfm_T";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()){
                RoomIfmDTO rimf = new RoomIfmDTO();
                rimf.setUserHp(rst.getString("r_userHP"));
                rimf.setEnterTime(rst.getTime("r_enter"));
                rimf.setNum(rst.getString("r_num"));
                rimf.setUseSong(rst.getInt("r_useSong"));
                rimf.setLeftSong(rst.getInt("r_leftSong"));
                rimf.setPaySong(rst.getInt("r_paySong"));
                rimf.setUsing(rst.getBoolean("r_using"));

                roomimfarr.add(rimf);
            }
        } catch (Exception e) {
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
        return roomimfarr;
    }

    @Override
    public boolean delete(String code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM RoomIfm_T WHERE r_num = ?";
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
}
