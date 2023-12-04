package controller_db;

import dao.*;
import dto.GoodsImageDTO;

import java.sql.Connection;

public class Controller {
    Connection conn;
    private GoodsDAO goodsDAO;
    private MemberDAO memberDAO;
    private MemberLogDAO memberLogDAO;
    private OrderDAO orderDAO;
    private OrderHDAO orderHDAO;
    private RoomIfmDAO roomImfDAO;
    private RoomManageDAO roomManageDAO;
    private RoomOptionDAO roomOptionDAO;
    private WorkerDAO workerDAO;
    private GoodsImageDAO goodsImageDAO;
    private StockDAO stockDAO;
    private StockDateDAO stockDateDAO;

    public Controller(Connection conn){
        goodsDAO = new GoodsDAO(conn);
        memberDAO = new MemberDAO(conn);
        memberLogDAO = new MemberLogDAO(conn);
        orderDAO = new OrderDAO(conn);
        orderHDAO = new OrderHDAO(conn);
        roomImfDAO = new RoomIfmDAO(conn);
        roomManageDAO = new RoomManageDAO(conn);
        roomOptionDAO = new RoomOptionDAO(conn);
        workerDAO = new WorkerDAO(conn);
        goodsImageDAO = new GoodsImageDAO(conn);
        stockDAO = new StockDAO(conn);
        stockDateDAO = new StockDateDAO(conn);
    }

    public GoodsDAO getGoodsDAO() {
        return goodsDAO;
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public MemberLogDAO getMemberLogDAO() {
        return memberLogDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public OrderHDAO getOrderHDAO() {
        return orderHDAO;
    }

    public RoomIfmDAO getRoomImfDAO() {
        return roomImfDAO;
    }

    public RoomManageDAO getRoomManageDAO() {
        return roomManageDAO;
    }

    public RoomOptionDAO getRoomOptionDAO() {
        return roomOptionDAO;
    }

    public WorkerDAO getWorkerDAO() {return workerDAO;}
    public GoodsImageDAO getGoodsImageDAO() {return goodsImageDAO; }

    public StockDAO getStockDAO(){ return stockDAO; }

    public StockDateDAO getStockDateDAO() {return stockDateDAO;}
}
