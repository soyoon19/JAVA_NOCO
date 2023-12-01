package dao;

import dto.MemberLogDTO;

import java.sql.*;
import java.util.ArrayList;

public class MemberLogDAO implements DAO<MemberLogDTO, String> {
    private Connection conn;

    public MemberLogDAO(Connection conn) {
        this.conn = conn;
    }

    // 회원 로그 추가
    public boolean insert(MemberLogDTO memberLog) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO MemberLog_T (m_hp, m_holdSong, m_totalPay, m_rate, m_lastLogin) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberLog.getPhone());
            pstmt.setInt(2, memberLog.getHoldSong());
            pstmt.setFloat(3, memberLog.getTotalPay());
            pstmt.setString(4,String.valueOf(memberLog.getM_rate()));
            pstmt.setDate(5, memberLog.getLastLogin());

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
        return true; // 추가가 성공하면 true를 반환
    }

    // 회원 로그 삭제
    public boolean delete(String m_hp) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM MemberLog_T WHERE m_hp = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m_hp);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0; // 삭제된 행이 하나 이상이면 true 반환

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 삭제에 실패하면 false를 반환
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    // 회원 로그 조회
    public MemberLogDTO findById(String m_hp) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberLogDTO memberLogDTO = null;

        try {
            String sql = "SELECT * FROM MemberLog_T WHERE m_hp = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m_hp);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int holdSong = rs.getInt("m_holdSong");
                float totalPay = rs.getFloat("m_totalPay");
                char m_rate = rs.getString("m_rate").charAt(0);
                Date lastLogin = rs.getDate("m_lastLogin");

                memberLogDTO =  new MemberLogDTO(m_hp, m_rate, holdSong, lastLogin, totalPay);
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

        return memberLogDTO; // 해당 회원의 로그를 찾지 못하면 null 반환
    }

    // 모든 회원 로그 조회
    public ArrayList<MemberLogDTO> findAll() {
        ArrayList<MemberLogDTO> memberLogs = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM MemberLog_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String hp = rs.getString("m_hp");
                int holdSong = rs.getInt("m_holdSong");
                float totalPay = rs.getFloat("m_totalPay");
                char m_rate = rs.getString("m_rate").charAt(0);
                Date lastLogin = rs.getDate("m_lastLogin");

                MemberLogDTO memberLog = new MemberLogDTO(hp, m_rate, holdSong, lastLogin, totalPay);
                memberLogs.add(memberLog);
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

        return memberLogs;
    }

}
