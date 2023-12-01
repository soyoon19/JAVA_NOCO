package dao;

import dto.StocksDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StocksDAO implements DAO<StocksDTO, String> {

    private Connection conn;

    public StocksDAO(Connection coon){
        this.conn = coon;
    }

    public ArrayList<StocksDAO> findAll() {
        PreparedStatement pstmt = null; // sql Query를 위해 필요
        ResultSet rst = null;  //Query문 결과를 저장
        ArrayList<StocksDAO> stocksArr = new ArrayList();

        //int flag = 0;

        try {
            String sql = "select * from Stock_T;"; //필요 sql문
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()) {
                //DTO로 변환
                StocksDTO stocks = new StocksDTO();
                stocks.setCode(rst.getString("s_code"));
                stocks.setName(rst.getString("s_name"));
                stocks.setAmount(rst.getInt("s_amount"));
                stocks.setMinAmount(rst.getInt("s_minAmount"));
                stocks.setcost(rst.getInt("s_cost"));
                //stocks.(rst.get("s_date"));

                stocksArr.add(stocks);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(pstmt != null)
                    pstmt.close();
                if(rst != null)
                    rst.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return stocksArr;
    }

    @Override
    public StocksDTO save(StocksDTO stocksDTO) {
        return null;
    }

    @Override
    public ArrayList<StocksDAO> findById(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
