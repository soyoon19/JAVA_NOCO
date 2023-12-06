package dao;

import dto.MemberDTO;
import dto.RoomManageDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomManageDAO implements DAO<RoomManageDTO, String> {
    private Connection conn;
    public RoomManageDAO(Connection coon){this.conn=coon;}

    public String getNextCode(){
        //일련번호 갱신
        RoomManageDTO lastOrder = findLastRow(); //마지막 행을 가져온다.

        String code;
        if(lastOrder == null){
            code = RoomManageDTO.PREFIX + "0001";
        }else{
            String lastCode = lastOrder.getCode();
            System.out.println("x : " + lastCode);

            lastCode = lastCode.substring(1); //r0002 --> 0002
            System.out.println("y : " + lastCode);


            int intCode = Integer.parseInt(lastCode); //0002 --> 2
            intCode++; //3

            String needZero = ""; //빈 공간을 0으로 채울 변수

            lastCode = String.valueOf(intCode); //(int)3 --> (String)3 --> 03 --> 003 --> 0003
                                                //(int)10 --> (String)10 --> 010 --> 0010
            while(lastCode.length() + needZero.length() < 4)
                needZero += "0";

            code = RoomManageDTO.PREFIX + needZero + lastCode;
        }

        return code;
    }

    @Override
    public boolean insert(RoomManageDTO manage) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO RoomManage_T (r_code, r_num, r_X, r_Y, r_option, r_check) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manage.getCode());
            pstmt.setString(2, manage.getNum());
            pstmt.setInt(3, manage.getX());
            pstmt.setInt(4,manage.getY());
            pstmt.setInt(5,manage.getOption());
            pstmt.setBoolean(6,manage.isCheck());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
    }


    @Override
    public RoomManageDTO findById(String code) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomManageDTO manage = null;
        try {
            String sql = "SELECT * FROM RoomManage_T WHERE r_code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            rst = pstmt.executeQuery();
            if (rst.next()) {
                manage = new RoomManageDTO();
                manage.setCode(rst.getString("r_code"));
                manage.setNum(rst.getString("r_num"));
                manage.setX(rst.getInt("r_X"));
                manage.setY(rst.getInt("r_Y"));
                manage.setOption(rst.getInt("r_option"));
                manage.setCheck(rst.getBoolean("r_check"));
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
        return manage;
    }

    @Override
    public ArrayList<RoomManageDTO> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<RoomManageDTO> roomManageArr = new ArrayList();

        try{
            String sql = "select * from RoomManage_T";

            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();
            while(rst.next()){
                RoomManageDTO roomMange = new RoomManageDTO();
                roomMange.setCode(rst.getString("r_code"));
                roomMange.setNum(rst.getString("r_num"));
                roomMange.setX(rst.getInt("r_X"));
                roomMange.setY(rst.getInt("r_Y"));
                roomMange.setOption(rst.getInt("r_option"));
                roomMange.setCheck(rst.getBoolean("r_check"));

                roomManageArr.add(roomMange);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null)
                    pstmt.close();
                if(rst != null)
                    rst.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return roomManageArr;
    }

    @Override
    public boolean delete(String code) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM RoomManage_T WHERE r_code = ?";
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


    public boolean update(RoomManageDTO manage) {
        PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE RoomManage_T SET r_num=?, r_X=?, r_Y=?, r_option=?, r_check=? WHERE r_code=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, manage.getNum());
            pstmt.setInt(2, manage.getX());
            pstmt.setInt(3, manage.getY());
            pstmt.setInt(4, manage.getOption());
            pstmt.setBoolean(5, manage.isCheck());
            pstmt.setString(6, manage.getCode());

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


    public RoomManageDTO findLastRow() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomManageDTO manage = null;

        try {
            String sql = "SELECT * FROM RoomManage_T ORDER BY r_code DESC LIMIT 1";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();

            if (rst.next()) {
                manage = new RoomManageDTO();
                manage.setCode(rst.getString("r_code"));
                manage.setNum(rst.getString("r_num"));
                manage.setX(rst.getInt("r_X"));
                manage.setY(rst.getInt("r_Y"));
                manage.setOption(rst.getInt("r_option"));
                manage.setCheck(rst.getBoolean("r_check"));
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

        return manage;
    }

    public ArrayList<RoomManageDTO> findNotNullRNum() {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        ArrayList<RoomManageDTO> roomManageArr = new ArrayList();

        try {
            String sql = "SELECT * FROM RoomManage_T WHERE r_num IS NOT NULL";
            pstmt = conn.prepareStatement(sql);
            rst = pstmt.executeQuery();

            while (rst.next()) {
                RoomManageDTO roomMange = new RoomManageDTO();
                roomMange.setCode(rst.getString("r_code"));
                roomMange.setNum(rst.getString("r_num"));
                roomMange.setX(rst.getInt("r_X"));
                roomMange.setY(rst.getInt("r_Y"));
                roomMange.setOption(rst.getInt("r_option"));
                roomMange.setCheck(rst.getBoolean("r_check"));

                roomManageArr.add(roomMange);
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
        return roomManageArr;
    }


    public RoomManageDTO findByRNum(String rNum) {
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        RoomManageDTO roomManage = null;

        try {
            String sql = "SELECT * FROM RoomManage_T WHERE r_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rNum);
            rst = pstmt.executeQuery();

            if (rst.next()) {
                roomManage = new RoomManageDTO();
                roomManage.setCode(rst.getString("r_code"));
                roomManage.setNum(rst.getString("r_num"));
                roomManage.setX(rst.getInt("r_X"));
                roomManage.setY(rst.getInt("r_Y"));
                roomManage.setOption(rst.getInt("r_option"));
                roomManage.setCheck(rst.getBoolean("r_check"));

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
        return roomManage;
    }


}
