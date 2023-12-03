package dao;

import dto.RoomRequestDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomRequestDAO implements DAO<RoomRequestDTO, String> {
    private Connection conn;
    public RoomRequestDAO(Connection coon){this.conn=coon;}

    @Override
    public ArrayList<RoomRequestDTO> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<RoomRequestDTO> roomRequestArr = new ArrayList();

        try{
            String sql = "select * from RoomRequest_T";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while (rst.next()){
                RoomRequestDTO roomRequest = new RoomRequestDTO();
                roomRequest.setRequest(rst.getString("r_request"));
                roomRequest.setNum(rst.getString("r_num"));
                roomRequest.setComplete(rst.getBoolean("r_complete"));
                roomRequest.setRecTime(rst.getTime("r_recTime"));

                roomRequestArr.add(roomRequest);
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
        return roomRequestArr;
    }

    @Override
    public boolean insert(RoomRequestDTO roomRequestDTO) {
        return false;
    }

    @Override
    public RoomRequestDTO findById(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
