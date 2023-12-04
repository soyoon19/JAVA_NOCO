package dao;

import dto.OrderDTO;
import dto.OrderHDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHDAO implements DAO<OrderHDTO, String> {
    private Connection conn;

    public OrderHDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(OrderHDTO orderH) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO OrderH_T (o_code, g_code, g_count, o_date, oH_price, oH_discount, oH_cost) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderH.getOrderCode());
            pstmt.setString(2, orderH.getGoodsCode());
            pstmt.setInt(3, orderH.getCount());
            pstmt.setDate(4, orderH.getDate());
            pstmt.setInt(5, orderH.getPrice());
            pstmt.setInt(6, orderH.getDiscount());
            pstmt.setInt(7, orderH.getCost());

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

    public boolean delete(String orderCode) {
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

    public OrderHDTO findById(String orderCode) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        OrderHDTO orderH = null;

        try {
            String sql = "SELECT * FROM OrderHD_T WHERE o_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderCode);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                orderH = createOrderHDTO(rs);
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

        return orderH;
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
        int g_count = rs.getInt("g_count");
        java.sql.Date o_date = rs.getDate("o_date");
        int oH_price = rs.getInt("oH_price");
        int oH_discount = rs.getInt("oH_discount");
        int oH_cost = rs.getInt("oH_cost");


        return new OrderHDTO(o_code, g_code, g_count, oH_price, oH_discount, oH_cost, o_date);
    }


    public boolean update(OrderHDTO orderH) {
        PreparedStatement pstmt = null;

        try {
            String sql = "UPDATE OrderH_T SET g_code=?, g_count=?, o_date=?, oH_price=?, oH_discount=?, oH_cost=? WHERE o_code=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, orderH.getGoodsCode());
            pstmt.setInt(2, orderH.getCount());
            pstmt.setDate(3, orderH.getDate());
            pstmt.setInt(4, orderH.getPrice());
            pstmt.setInt(5, orderH.getDiscount());
            pstmt.setInt(6, orderH.getCost());
            pstmt.setString(7, orderH.getOrderCode());

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