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

    //삽입 : INSERT
    public boolean insert(StockDTO stock) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Stock_T (s_code, s_name, s_amount, s_minAmount, s_cost, s_category) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stock.getCode());
            pstmt.setString(2, stock.getName());
            pstmt.setInt(3, stock.getAmount());
            pstmt.setInt(4, stock.getMinAmount());
            pstmt.setInt(5, stock.getCost());
            pstmt.setInt(6, stock.getCategory());

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

    //삭제
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

    //특정 하나 조회 : 재고코드가 ~ 일때 하나의 재고를 조회
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

    //조회 : SELECT *;
    public ArrayList<StockDTO> findAll() {
        ArrayList<StockDTO> stockList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //조회시
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

    //생성 : CREATE
    private StockDTO createStockDTO(ResultSet rs) throws SQLException {
        String code = rs.getString("s_code");
        String name = rs.getString("s_name");
        int amount = rs.getInt("s_amount");
        int minAmount = rs.getInt("s_minAmount");
        int cost = rs.getInt("s_cost");
        int category = rs.getInt("s_category");

        return new StockDTO(code, name, amount, minAmount, cost, category);
    }

    //편집(수정) : UPDATE
    public boolean update(StockDTO stock) {
        PreparedStatement pstmt = null;

        try {
            String sql = "UPDATE Stock_T SET s_name=?, s_amount=?, s_minAmount=?, s_cost=?, s_category=? WHERE s_code=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, stock.getName());
            pstmt.setInt(2, stock.getAmount());
            pstmt.setInt(3, stock.getMinAmount());
            pstmt.setInt(4, stock.getCost());
            pstmt.setInt(5, stock.getCategory());
            pstmt.setString(6, stock.getCode());

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
}
