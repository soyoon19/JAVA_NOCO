package dao;

import dto.GoodsImageDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsImageDAO implements DAO<GoodsImageDTO, String>{
    private Connection conn;

    public GoodsImageDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(GoodsImageDTO goodsImage) {
        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO GoodsImage_T (g_code, g_image) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goodsImage.getCode());
            pstmt.setBytes(2, goodsImage.getImage());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(pstmt != null)
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }


    public boolean delete(String code) {
        PreparedStatement pstmt = null;

        try {
            String sql = "DELETE FROM GoodsImage_T WHERE g_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (pstmt != null) pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public GoodsImageDTO findById(String code) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM GoodsImage_T WHERE g_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                byte[] image = rs.getBytes("g_image");
                return new GoodsImageDTO(code, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<GoodsImageDTO> findAll() {
        ArrayList<GoodsImageDTO> goodsImages = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM GoodsImage_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("g_code");
                byte[] image = rs.getBytes("g_image");
                goodsImages.add(new GoodsImageDTO(code, image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(rs != null)
                    rs.close();
                if(pstmt != null)
                    pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return goodsImages;
    }
}
