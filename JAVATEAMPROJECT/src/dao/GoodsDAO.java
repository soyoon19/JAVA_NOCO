package dao;

import dto.GoodsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoodsDAO implements  DAO<GoodsDTO, String>{
    private Connection conn;

    public GoodsDAO(Connection coon){
        this.conn = coon;
    }

    public ArrayList<GoodsDTO> findAll(){
        PreparedStatement pstmt = null; //sql Query을 위해서 필요
        ResultSet rst = null;     //Query문 결과를 저장
        ArrayList<GoodsDTO> goodsArr = new ArrayList(); //받아온 데이터를 DTO형태로 변환

        int flag = 0;

        try{
            String sql = "select * from Goods_T";   //sql문
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

    @Override
    public GoodsDTO save(GoodsDTO goodsDTO) {
        return null;
    }

    @Override
    public ArrayList<GoodsDTO> findById(String s) {
        return null;
    }


    @Override
    public boolean delete(String s) {
        return false;
    }
}
