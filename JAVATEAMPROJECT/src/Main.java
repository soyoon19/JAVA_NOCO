
import controller_db.Controller;
import controller_db.DBConnect;
import dao.DAO;
import dao.GoodsDAO;
import dao.GoodsImageDAO;
import dto.GoodsDTO;
import dto.GoodsImageDTO;
import dto.StockDTO;
import dto.StockDateDTO;
import process.ImageProcess;
import views.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DBConnect connect = new DBConnect();
        Controller controller = new Controller(connect.getConn());
        DefaultFrame win = new DefaultFrame(controller);
        win.setSize(1920,1080);

        /*
        SDE 창 test
            win.add(new DrinksManagementView(win));

            new DrinksStatusPopup(win);

            (new NoticeStockPopup(win)).popup();
         */

        /* Image to Byte[], Byte[] to Image 테스트
        try {
            win.add(new JLabel(ImageProcess.bytesToImageIcon(
                    ImageProcess.ImageToBytes(DefaultFrame.PATH + "/images/goods/s0101.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */


        // DB 연결 확인

         DAO[] ds = {
                controller.getGoodsDAO(), controller.getMemberDAO(), controller.getOrderDAO(),
                controller.getRoomImfDAO(), controller.getMemberLogDAO(), controller.getRoomManageDAO(),
                controller.getRoomOptionDAO(), controller.getOrderHDAO(), controller.getWorkerDAO(),
                 controller.getStockDAO(), controller.getStockDateDAO()
        };

         for(DAO d : ds){
            //전체조회
            System.out.println(d.findAll());
        }




        ///* 이미지 데이터 삽입 -- 주석해제 금지 --
        /*
        ArrayList<GoodsDTO> goodsArr = controller.getGoodsDAO().findAll();
        int i = 0;

        for(GoodsDTO g : goodsArr){
            try {
                if(goodsArr.size() -4 <= i) controller.getGoodsImageDAO().insert( new GoodsImageDTO(g.getCode(),
                        ImageProcess.ImageToBytes(DefaultFrame.PATH + "/images/goods/" + g.getCode() + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
         */


        new StockMgPopup(win);
        //win.add(new UserHomeView(win));
        //win.add(new PswdFindView(win));
        //win.add(new StockManagementView());
        //win.add(new StockManagementView(win));
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}