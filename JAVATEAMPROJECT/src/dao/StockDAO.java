package dao;

import dto.StockDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO implements DAO<StockDTO, String>{
    private Connection conn;

    public StockDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(StockDTO stock) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Stock_T (s_code, s_name, s_amount, s_minAmount, s_cost, s_date, s_category) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stock.getCode());
            pstmt.setString(2, stock.getName());
            pstmt.setInt(3, stock.getAmount());
            pstmt.setInt(4, stock.getMinAmount());
            pstmt.setInt(5, stock.getCost());
            pstmt.setDate(6, stock.getDate());
            pstmt.setInt(7, stock.getCategory());

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

    public boolean delete(String code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM Stock_T WHERE s_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);

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

    public StockDTO findById(String code) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StockDTO stock = null;

        try {
            String sql = "SELECT * FROM Stock_T WHERE s_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                stock = createStockDTO(rs);
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

        return stock;
    }

    public ArrayList<StockDTO> findAll() {
        ArrayList<StockDTO> stockList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Stock_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                  StockDTO stock = createStockDTO(rs);
                stockList.add(stock);
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

        return stockList;
    }

    private StockDTO createStockDTO(ResultSet rs) throws SQLException {
        String code = rs.getString("s_code");
        String name = rs.getString("s_name");
        int amount = rs.getInt("s_amount");
        int minAmount = rs.getInt("s_minAmount");
        int cost = rs.getInt("s_cost");
        java.sql.Date date = rs.getDate("s_date");
        int category = rs.getInt("s_category");

        return new StockDTO(code, name, amount, minAmount, cost, date, category);
    }
}
