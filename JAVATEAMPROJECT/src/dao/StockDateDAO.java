package dao;

import dto.StockDateDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDateDAO implements DAO<StockDateDTO, Integer> {
    private Connection conn;

    public StockDateDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(StockDateDTO stockDate) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO StockDate_T (s_num, s_date, s_code, s_category) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stockDate.getNum());
            pstmt.setDate(2, stockDate.getDate());
            pstmt.setString(3, stockDate.getCode());
            pstmt.setInt(4, stockDate.getCategory());

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
            }
        }
        return true;
    }

    public boolean delete(Integer num) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM StockDate_T WHERE s_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);

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
            }
        }
        return true;
    }

    public StockDateDTO findById(Integer num) {
        return null;
    }

    public ArrayList<StockDateDTO> findAll() {
        ArrayList<StockDateDTO> stockDateList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM StockDate_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockDateDTO stockDate = createStockDateDTO(rs);
                stockDateList.add(stockDate);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return stockDateList;
    }

    private StockDateDTO createStockDateDTO(ResultSet rs) throws SQLException {
        int num = rs.getInt("s_num");
        java.sql.Date date = rs.getDate("s_date");
        String code = rs.getString("s_code");
        int category = rs.getInt("s_category");

        return new StockDateDTO(num, date, code, category);
    }
}