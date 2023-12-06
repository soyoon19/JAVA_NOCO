package dao;

import dto.MemberDTO;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO implements DAO<MemberDTO, String>{
    private Connection conn;
    public MemberDAO(Connection coon){
        this.conn = coon;
    }


    public ArrayList<MemberDTO> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MemberDTO> members = new ArrayList<>();


        try {
            String sql = "SELECT * FROM Member_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String hp = rs.getString("m_hp");
                String passwd = rs.getString("m_passwd");
                Date birthDate = rs.getDate("m_birthDate");
                Date joinDate = rs.getDate("m_joinDate");

                MemberDTO member = new MemberDTO(hp, passwd, birthDate, joinDate);
                members.add(member);
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

        return members;
    }


    @Override
    public boolean insert(MemberDTO member) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Member_T (m_hp, m_passwd, m_birthDate, m_joinDate) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getHp());
            pstmt.setString(2, member.getPasswd());
            pstmt.setDate(3, member.getBirthDate());
            pstmt.setDate(4, member.getJoinDate());

            pstmt.executeUpdate();
        }catch (SQLException e) {
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


    // 회원 삭제
    public boolean delete(String m_ph) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM Member_T WHERE m_hp = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m_ph);
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

    // 회원 조회 by ID
    public MemberDTO findById(String ph) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO member = null;

        try {
            String sql = "SELECT * FROM Member_T WHERE m_hp = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ph);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String hp = rs.getString("m_hp");
                String passwd = rs.getString("m_passwd");
                Date birthDate = rs.getDate("m_birthDate");
                Date joinDate = rs.getDate("m_joinDate");

                member = new MemberDTO(hp, passwd, birthDate, joinDate);
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

        return member; // 해당 ID의 회원을 찾지 못하면 null 반환
    }

    public boolean update(MemberDTO member) {
        PreparedStatement pstmt = null;

        try {
            String sql = "UPDATE Member_T SET m_passwd=?, m_birthDate=?, m_joinDate=? WHERE m_hp=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getPasswd());
            pstmt.setDate(2, member.getBirthDate());
            pstmt.setDate(3, member.getJoinDate());
            pstmt.setString(4, member.getHp());
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
