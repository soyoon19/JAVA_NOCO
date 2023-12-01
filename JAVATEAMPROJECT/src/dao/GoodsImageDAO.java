package dao;

import dto.GoodsImageDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsImageDAO {
    private Connection conn;

    public GoodsImageDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(GoodsImageDTO goodsImage) {
        try {
            String sql = "INSERT INTO GoodsImage_T (g_code, g_image) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, goodsImage.getCode());
                pstmt.setBytes(2, goodsImage.getImage());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String code) {
        try {
            String sql = "DELETE FROM GoodsImage_T WHERE g_code=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, code);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GoodsImageDTO findById(String code) {
        try {
            String sql = "SELECT * FROM GoodsImage_T WHERE g_code=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, code);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        byte[] image = rs.getBytes("g_image");
                        return new GoodsImageDTO(code, image);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GoodsImageDTO> findAll() {
        List<GoodsImageDTO> goodsImages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM GoodsImage_T";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String code = rs.getString("g_code");
                    byte[] image = rs.getBytes("g_image");
                    goodsImages.add(new GoodsImageDTO(code, image));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsImages;
    }
}
