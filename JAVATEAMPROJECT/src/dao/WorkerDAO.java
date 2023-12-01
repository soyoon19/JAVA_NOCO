package dao;

import dto.WorkerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO implements DAO<WorkerDTO, String>{
    private Connection conn;

    public WorkerDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(WorkerDTO worker) {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Worker_T (w_id, w_passwd, w_name, w_hp, w_position, w_admin, w_regDate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, worker.getId());
            pstmt.setString(2, worker.getPasswd());
            pstmt.setString(3, worker.getName());
            pstmt.setString(4, worker.getHp());
            pstmt.setString(5, worker.getPosition());
            pstmt.setInt(6, worker.isAdmin() ? 1 : 0);
            pstmt.setDate(7, worker.getRegDate());

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

    public boolean delete(String id) {
        PreparedStatement pstmt = null;
        try {
            String sql = "DELETE FROM Worker_T WHERE w_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

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

    public WorkerDTO findById(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WorkerDTO worker = null;

        try {
            String sql = "SELECT * FROM Worker_T WHERE w_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                worker = createWorkerDTO(rs);
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

        return worker;
    }

    public ArrayList<WorkerDTO> findAll() {
        ArrayList<WorkerDTO> workerList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Worker_T";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                WorkerDTO worker = createWorkerDTO(rs);
                workerList.add(worker);
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

        return workerList;
    }

    private WorkerDTO createWorkerDTO(ResultSet rs) throws SQLException {
        String id = rs.getString("w_id");
        String passwd = rs.getString("w_passwd");
        String name = rs.getString("w_name");
        String hp = rs.getString("w_hp");
        String position = rs.getString("w_position");
        boolean admin = rs.getInt("w_admin") == 1;
        Date regDate = rs.getDate("w_regDate");

        return new WorkerDTO(id, passwd, name, hp, position, admin, regDate);
    }
}