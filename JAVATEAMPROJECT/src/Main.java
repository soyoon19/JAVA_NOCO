
import controller_db.Controller;
import controller_db.DBConnect;
import dao.DAO;
import views.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        DBConnect connect = new DBConnect();
        Controller controller = new Controller(connect.getConn());
        DefaultFrame win = new DefaultFrame(controller);
        win.setSize(1920,1080);

        //(new MemberControlView(win)).setVisible(true);//!
        //(new MemberDeletePopup(win)).setVisible(true);
        //(new MemberDetailCorrectPopup(win)).setVisible(true);
        //(new MemberDetailPopup(win)).setVisible(true);

        //(new NoMemberControlView(win)).setVisible(true); //!

        //(new OrderControlView(win)).setVisible(true); //!
        //(new OrderDeleteCheckPopup(win)).setVisible(true);
        //(new OrderDeletePopup(win)).setVisible(true);
        //(new OrderDeleteRCPopup(win)).setVisible(true);
        //(new OrderDetailPopup(win)).setVisible(true);
        //(new OrderListPopup(win)).setVisible(true);

        //(new SalesAnalysisDetailPopup(win)).setVisible(true);
        //(new SalesAnalysisView(win)).setVisible(true);
        //(new SalesCheckPopup(win)).setVisible(true);
        //(new SalesDetailPopup(win)).setVisible(true);

        //(new Worker_regPopup(win)).setVisible(true);
        //(new WorkerControlView(win)).setVisible(true);
        //(new WorkerDeleteCheckPopup(win)).setVisible(true);
        //(new WorkerDeleteRCPopup(win)).setVisible(true);
        //(new WorkerCorrectPopup(win)).setVisible(true);


        //add는 setVisible보다 위에 있어야 됨!
        //win.add(new ProductListCartView(win));


        //win.add(new UserLoginView());


        //new NoticeStockPopup(win);
        //win.add(new MusicUseView(win));
        //win.add(new ProductListCartView(win));
        //win.add(new ProductListCartView(win));

        //win.add(new StockManagementView());
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

        // DAO[] ds = {

        /*
         DAO[] ds = {
>>>>>>> cc31c0c19ddfe3b1a054886a91b79fd7a4bc13b9
                controller.getGoodsDAO(), controller.getMemberDAO(), controller.getOrderDAO(),
                controller.getRoomImfDAO(), controller.getMemberLogDAO(), controller.getRoomManageDAO(),
                controller.getRoomOptionDAO(), controller.getOrderHDAO(), controller.getWorkerDAO(), controller.getStockDAO()
        //};

       //  for(DAO d : ds){
            //전체조회
            //System.out.println(d.findAll());
      //  }

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
        //win.add(new ManagerMainView(win));
        win.add(new UserHomeView(win));
        //win.add(new StockManagementView());
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}