package test;

import controller_db.Controller;
import controller_db.DBConnect;
import dto.RoomIfmDTO;

import java.sql.SQLException;

public class Tester extends Thread{
    public long USE_TIME = 10000;
    private Controller controller;
    private RoomIfmDTO roomIfm;
    private DBConnect db;
    public Tester(RoomIfmDTO roomIfm){
        this.roomIfm = roomIfm;
        db = new DBConnect();
        controller = new Controller(db.getConn()); //새로운 커넥션을 연결해 준다.
    }

    public void run(){
        while((roomIfm = controller.getRoomImfDAO().findById(roomIfm.getNum())) != null){
            roomIfm.setLeftSong(roomIfm.getLeftSong() - 1);
            roomIfm.setUseSong(roomIfm.getUseSong() + 1);
            if(roomIfm.getLeftSong() == 0){
                controller.getRoomImfDAO().delete(roomIfm.getNum());
            }
        }
        try {
            sleep(USE_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            db.getConn().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
