package controller_db;

import dao.*;
import dto.GoodsDTO;
import dto.OrderHDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class Controller {
    Connection conn;
    private GoodsDAO goodsDAO;
    private MemberDAO memberDAO;
    private MemberLogDAO memberLogDAO;
    private OrderDAO orderDAO;
    private OrderHDAO orderHDAO;
    private RoomImfDAO roomImfDAO;
    private RoomManageDAO roomManageDAO;
    private RoomOptionDAO roomOptionDAO;
    private WorkerDAO workerDAO;


    public Controller(Connection conn){
        goodsDAO = new GoodsDAO(conn);
        //memberDAO = new MemberDAO();
        //memberLogDAO = new MemberLogDAO();
    }

    public GoodsDAO getGoodsDAO() {
        return goodsDAO;
    }
}
