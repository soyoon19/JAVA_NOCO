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
    public boolean insert(RoomRequestDTO roomRequest) {
        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO RoomRequest_T (r_request, r_num, r_complete, r_recTime) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, roomRequest.getRequest());
            pstmt.setString(2, roomRequest.getNum());
            pstmt.setBoolean(3, roomRequest.getComplete());
            pstmt.setTime(4, roomRequest.getRecTime());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return  false;
            }
        }

        return true;
    }

    @Override
    public RoomRequestDTO findById(String requestId) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomRequestDTO roomRequest = null;

        try {
            String sql = "SELECT * FROM RoomRequest_T WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, requestId);

            rst = pstmt.executeQuery();
            if (rst.next()) {
                roomRequest = new RoomRequestDTO();
                roomRequest.setRequest(rst.getString("r_request"));
                roomRequest.setNum(rst.getString("r_num"));
                roomRequest.setComplete(rst.getBoolean("r_complete"));
                roomRequest.setRecTime(rst.getTime("r_recTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rst != null) rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roomRequest;
    }
    @Override
    public boolean delete(String requestId) {
        PreparedStatement pstmt = null;

        try {
            String sql = "DELETE FROM RoomRequest_T WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, requestId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public boolean update(RoomRequestDTO roomRequest) {
        PreparedStatement pstmt = null;

        try {
            String sql = "UPDATE RoomRequest_T SET r_request=?, r_num=?, r_complete=?, r_recTime=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, roomRequest.getRequest());
            pstmt.setString(2, roomRequest.getNum());
            pstmt.setBoolean(3, roomRequest.getComplete());
            pstmt.setTime(4, roomRequest.getRecTime());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    public RoomRequestDTO findLastRow() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomRequestDTO roomRequest = null;

        try {
            // 일련번호를 기준으로 내림차순 정렬하여 마지막 행을 가져오는 쿼리
            String sql = "SELECT * FROM RoomRequest_T ORDER BY id DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();

            if (rst.next()) {
                roomRequest = new RoomRequestDTO();
                roomRequest.setRequest(rst.getString("r_request"));
                roomRequest.setNum(rst.getString("r_num"));
                roomRequest.setComplete(rst.getBoolean("r_complete"));
                roomRequest.setRecTime(rst.getTime("r_recTime"));
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

        return roomRequest;
    }
}
