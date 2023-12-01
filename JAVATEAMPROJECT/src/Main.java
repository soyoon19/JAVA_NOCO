import controller_db.Controller;
import controller_db.DBConnect;
import dao.DAO;
import dao.GoodsDAO;
import dto.GoodsDTO;
import dto.GoodsImageDTO;
import process.ImageProcess;
import views.CardInfoPopup;
import views.DefaultFrame;
import views.MusicUseView;
import views.ProductListCartView;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DefaultFrame win = new DefaultFrame();
        win.setSize(1920,1080);

        DBConnect connect = new DBConnect();
        Controller controller = new Controller(connect.getConn());

        //add는 setVisible보다 위에 있어야 됨!
        //win.add(new ProductListCartView(win));

        //win.add(new MusicUseView(win));
        //win.add(new ProductListCartView(win));
        //win.add(new ProductListCartView(win));

        /* Image to Byte[], Byte[] to Image 테스트
        try {
            win.add(new JLabel(ImageProcess.bytesToImageIcon(
                    ImageProcess.ImageToBytes(DefaultFrame.PATH + "/images/goods/s0101.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */



        /* DB 연결 확인
         DAO[] ds = {
                controller.getGoodsDAO(), controller.getMemberDAO(), controller.getOrderDAO(),
                controller.getRoomImfDAO(), controller.getMemberLogDAO(), controller.getRoomManageDAO(),
                controller.getRoomOptionDAO(), controller.getOrderHDAO(), controller.getWorkerDAO()
        };

         for(DAO d : ds){
            //전체조회
            System.out.println(d.findAll());
        }
         */


        /* 이미지 데이터 삽입 -- 주석해제 금지 --
        ArrayList<GoodsDTO> goodsArr = controller.getGoodsDAO().findAll();

        for(GoodsDTO g : goodsArr){
            try {
                controller.getGoodsImageDAO().insert( new GoodsImageDTO(g.getCode(),
                        ImageProcess.ImageToBytes(DefaultFrame.PATH + "/images/goods/" + g.getCode() + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
         */

        //(new CardInfoPopup(win)).setVisible(true);

        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}