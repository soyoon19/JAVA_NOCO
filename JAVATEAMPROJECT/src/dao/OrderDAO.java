package dao;

import dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<OrderDTO, String>{
    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // 주문 추가
    public boolean insert(OrderDTO order) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Order_T (o_code, m_hp, w_id, r_code, o_pay, o_discount, o_date, o_time, o_comptime, o_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getO_code());
            pstmt.setString(2, order.getHp());
            pstmt.setString(3, order.getId());
            pstmt.setString(4, order.getCode());
            pstmt.setInt(5, order.getPay());
            pstmt.setInt(6, order.getDiscount());
            pstmt.setDate(7, order.getDate());
            pstmt.setTime(8, order.getTime());
            pstmt.setTime(9, order.getComptime());
            pstmt.setInt(10, order.getStatus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 추가에 실패하면 false를 반환
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


    public boolean delete(String o_code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM Order_T WHERE o_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, o_code);
            int rowsAffected = pstmt.executeUpdate();

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


    public OrderDTO findById(String o_code) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderDTO order = null;

        try {
            String sql = "SELECT * FROM Order_T WHERE o_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, o_code);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String hp = rs.getString("m_hp");
                String id = rs.getString("w_id");
                String code = rs.getString("r_code");
                int pay = rs.getInt("o_pay");
                int discount = rs.getInt("o_discount");
                java.sql.Date date = rs.getDate("o_date");
                java.sql.Time time = rs.getTime("o_time");
                java.sql.Time comptime = rs.getTime("o_comptime");
                int status = rs.getInt("o_status");

                order = new OrderDTO(o_code, hp, id, code, date, time, comptime, pay, discount, status);
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

        return order; // 해당 주문 ID의 주문을 찾지 못하면 null 반환
    }

    // 모든 주문 조회
    public ArrayList<OrderDTO> findAll() {
        ArrayList<OrderDTO> orders = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Order_T";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String o_code = rs.getString("o_code");
                String hp = rs.getString("m_hp");
                String id = rs.getString("w_id");
                String code = rs.getString("r_code");
                int pay = rs.getInt("o_pay");
                int discount = rs.getInt("o_discount");
                java.sql.Date date = rs.getDate("o_date");
                java.sql.Time time = rs.getTime("o_time");
                java.sql.Time comptime = rs.getTime("o_comptime");
                int status = rs.getInt("o_status");

                OrderDTO order = new OrderDTO(o_code, hp, id, code, date, time, comptime, pay, discount, status);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }
}