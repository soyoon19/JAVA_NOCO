package dao;

import dto.OrderHDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderHDAO implements NoPKDAO<OrderHDTO> {
    private Connection conn;

    public OrderHDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(OrderHDTO orderH) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO OrderH_T (o_code, g_code, oH_temp, g_count, o_date, oH_price, oH_discount, oH_cost) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderH.getOrderCode());
            pstmt.setString(2, orderH.getGoodsCode());
            pstmt.setString(3, orderH.getH_temp());
            pstmt.setInt(4, orderH.getCount());
            pstmt.setDate(5, orderH.getDate());
            pstmt.setInt(6, orderH.getPrice());
            pstmt.setInt(7, orderH.getDiscount());
            pstmt.setInt(8, orderH.getCost());

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

    public boolean deleteOrderCode(String orderCode) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM OrderH_T WHERE o_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);

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

    public ArrayList<OrderHDTO> findOrderCode(String orderCode) {
        ArrayList<OrderHDTO> orderHList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM OrderH_T Where o_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderHDTO orderH = createOrderHDTO(rs);
                orderHList.add(orderH);
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

        return orderHList;
    }


    public ArrayList<OrderHDTO> findAll() {
        ArrayList<OrderHDTO> orderHList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM OrderH_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderHDTO orderH = createOrderHDTO(rs);
                orderHList.add(orderH);
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

        return orderHList;
    }

    private OrderHDTO createOrderHDTO(ResultSet rs) throws SQLException {
        String o_code = rs.getString("o_code");
        String g_code = rs.getString("g_code");
        String H_temp = rs.getString("oH_temp");
        int g_count = rs.getInt("g_count");
        java.sql.Date o_date = rs.getDate("o_date");
        int oH_price = rs.getInt("oH_price");
        int oH_discount = rs.getInt("oH_discount");
        int oH_cost = rs.getInt("oH_cost");


        return new OrderHDTO(o_code, g_code, H_temp, g_count, oH_price, oH_discount, oH_cost, o_date);
    }

}