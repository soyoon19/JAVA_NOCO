package dao;

import dto.GoodsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoodsDAO {
    private Connection conn;

    public GoodsDAO(Connection coon){
        this.conn = coon;
    }

    public ArrayList<GoodsDTO> all(){
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<GoodsDTO> goodsArr = new ArrayList();

        int flag = 0;
        try{
            String sql = "select * from Goods_T";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()){
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
}
