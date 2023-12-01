package dao;

import dto.GoodsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class GoodsDAO implements  DAO<GoodsDTO, String>{
    private Connection conn;

    public GoodsDAO(Connection coon){
        this.conn = coon;
    }

    public ArrayList<GoodsDTO> findAll(){
        PreparedStatement pstmt = null; //sql Query을 위해서 필요
        ResultSet rst = null;     //Query문 결과를 저장
        ArrayList<GoodsDTO> goodsArr = new ArrayList(); //받아온 데이터를 DTO형태로 변환


        try{
            String sql = "SELECT * FROM Goods_T";   //sql문
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()){
                //받아온 데이터를 DTO로 변환
                GoodsDTO goods = new GoodsDTO();
                goods.setCode(rst.getString("g_code"));
                goods.setCategory(rst.getString("g_class"));
                goods.setMainCategory(rst.getInt("g_category"));
                goods.setName(rst.getString("g_name"));
                goods.setStatus(rst.getString("g_status"));
                goods.setSaleCount(rst.getInt("g_saleCount"));
                goods.setDisStatus(rst.getInt("g_disStatus") == 1);
                goods.setPrice(rst.getInt("g_price"));
                goods.setCost(rst.getInt("g_cost"));
                goods.setIce(rst.getInt("g_ice") == 1);
                goods.setHot(rst.getInt("g_hot") == 1);

                goodsArr.add(goods);
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
        return goodsArr;
    }


    public GoodsDTO findById(String code) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        GoodsDTO goods = null;
        try {
            String sql = "SELECT * FROM Goods_T WHERE g_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rst = pstmt.executeQuery();
            if (rst.next()) {
                goods = new GoodsDTO();
                goods.setCode(rst.getString("g_code"));
                goods.setCategory(rst.getString("g_class"));
                goods.setMainCategory(rst.getInt("g_category"));
                goods.setName(rst.getString("g_name"));
                goods.setStatus(rst.getString("g_status"));
                goods.setSaleCount(rst.getInt("g_saleCount"));
                goods.setDisStatus(rst.getInt("g_disStatus") == 1);
                goods.setPrice(rst.getInt("g_price"));
                goods.setCost(rst.getInt("g_cost"));
                goods.setIce(rst.getInt("g_ice") == 1);
                goods.setHot(rst.getInt("g_hot") == 1);
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
        return goods;
    }

    public boolean delete(String code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM Goods_T WHERE g_code = ?";
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

    public boolean insert(GoodsDTO goods) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Goods_T (g_code, g_class, g_category, g_name, g_status, g_saleCount, g_disStatus, g_price, g_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods.getCode());
            pstmt.setString(2, goods.getCategory());
            pstmt.setInt(3, goods.getMainCategory());
            pstmt.setString(4, goods.getName());
            pstmt.setString(5, goods.getStatus());
            pstmt.setInt(6, goods.getSaleCount());
            pstmt.setInt(7, goods.getDisStatus() ? 1 : 0);
            pstmt.setInt(8, goods.getPrice());
            pstmt.setInt(9, goods.getCost());
            pstmt.setInt(10, goods.getIce() ? 1 : 0);
            pstmt.setInt(11, goods.getHot() ? 1 : 0);
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
