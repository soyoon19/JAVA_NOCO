package views;

import controller_db.Controller;
import custom_component.DefaultFont;
import custom_component.FreeImageIcon;
import dto.GoodsDTO;
import dto.WorkerDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//left
class DrinksCategoryPanel extends JPanel  {
    public static Dimension BUTTON_SIZE = new Dimension(80,80);
    public static final String[] drinksCategorys = {
            "전체 메뉴 보기", "커피", "논-커피", "티", "스무디", "상품"
    };
    private JPanel cCenter;
    DrinksCategoryPanel() {
        this.setLayout(new BorderLayout());

        //top : 음료 카테고리 보기 (JComboBox)
        JPanel cTop = new JPanel();
        //setBackground(Color.WHITE);

        JComboBox CategoryComboBox = new JComboBox(drinksCategorys);
        CategoryComboBox.setFont(new DefaultFont(50));
        cTop.setLayout(new BorderLayout());
        cTop.add(CategoryComboBox);



        //center : 변경할 상품 담는
        cCenter = new JPanel();
        
        /*
        DrinksCart가 Border에 의해서 고정적인 공간이 생기는 걸 피하기 위해서
        FlowLayout을 가지고 있는 패널(tmp)를 만들고 JScrollPanel 넣음.
        가변적인 패널 생성을 위해 팀장의 조언 -> Box layout 사용
        
        box layout 설명
        가변적으로 컴포너트가 추가, 삭제되는 되는 패널을 만들 때 유용
        BoxLayout을 설정하려하는 JPanel과 수평, 수직으로 배치할지 선택 필요
        add을 수행하면 정한 방향(수평 혹은 수직)으로 계속 추가 가능
         */

        //box 시작
        JPanel tmp = new JPanel();
        tmp.setLayout(new FlowLayout(FlowLayout.LEFT));

        //-실제 내용
        cCenter.setLayout(new BoxLayout(cCenter, BoxLayout.Y_AXIS)); //수직 방향으로 설정하였다.
        tmp.add(cCenter);

        //스크롤 팬
        JScrollPane jsp = new JScrollPane(tmp);
        //jsp.setBackground(Color.orange);


        //bottom : 추가/편집/삭제/일괄설정 버튼 넣기 (색깔 지정)
        JPanel cBottom = new JPanel();
        this.add(cBottom, BorderLayout.SOUTH);

        //버튼 넣기
        String [] drinkMangement = {"추가", "편집", "삭제", "<HTML><body style='text-align:center;'>일괄<br>설정</body></HTML>"};
        String [] dMButtonColor = {"green", "yellow", "RED", "orange"};
        Color[] btnColors = {Color.green, Color.YELLOW, new Color(255, 0, 0) , Color.orange};

        //1. 반복문으로 drinkMangement 개수만큼 버튼 생성
        JButton[] dMButton = new JButton[drinkMangement.length];

        for(int i=0; i<drinkMangement.length; i++) {

            //2. 버튼에 내용 넣기
            dMButton[i] =  new JButton(drinkMangement[i]);
            dMButton[i].setPreferredSize(BUTTON_SIZE);

            //4. 버튼에 색깔 지정
            dMButton[i].setBackground(btnColors[i]);
            //5. 버튼 생성
            cBottom.add(dMButton[i]);

        }

        add(cTop, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);
        add(cBottom, BorderLayout.SOUTH);
    }

    public void addDrink(GoodsDTO goods){
        cCenter.add(new SelectDrinkPanel(goods));
    }

}

//table 선택시 담길 정보 패널
class SelectDrinkPanel extends JPanel{

    public SelectDrinkPanel(GoodsDTO goods){
        this.setLayout(new GridBagLayout());

        //관리자가 선택해 담긴 음료 show : left (음료 사진)
        ImageIcon drinkImg = FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/goods/" + goods.getCode() + ".png", 150, 150);
        JLabel imageLb = new JLabel(drinkImg);
        imageLb.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.add(imageLb, DefaultFrame.easyGridBagConstraint(0,0,2,1));

        //right (음료명, 판매가능 개수, 상태, 선택된 음료 삭제 버튼 구현)
        JPanel drinkSelect = new JPanel();
        drinkSelect.setLayout(new GridLayout(4,1));
        this.add(drinkSelect, DefaultFrame.easyGridBagConstraint(1,0,2,1));

        //음료명
        JLabel name = new JLabel(goods.getName());
        name.setFont(new DefaultFont(30));
        drinkSelect.add(name);

        //판매가능 개수
        JLabel saleCount = new JLabel("판매 가능 개수: "+ String.valueOf(goods.getSaleCount()));
        saleCount.setFont(new DefaultFont(15));
        drinkSelect.add(saleCount);

        //음료 상태
        JLabel status = new JLabel("상태: "+ goods.getStatus());
        status.setFont(new DefaultFont(15));
        drinkSelect.add(status);

        //삭제 버튼 (개별)
        JButton cancelDrinkMg = new JButton("X");
        cancelDrinkMg.setForeground(Color.red);

        cancelDrinkMg.setBorderPainted(false);        //주변 테투리 이미지를 없게 한다.
        cancelDrinkMg.setContentAreaFilled(false);    //버튼 안에 기본이미지를 없게 한다.
        cancelDrinkMg.setFocusPainted(false);         //포커스 했을 때 이미지를 없게 한다.
        cancelDrinkMg.setFont(new DefaultFont(10));
        //cancelDrinkMg.setRolloverIcon();          //버튼 마우스 오버 되었을 경우 변경되는 이미지

        drinkSelect.add(cancelDrinkMg);

        //버튼 클릭 이벤트 처리 : (X 버튼 클릭시 담겼던 음료 삭제)
        cancelDrinkMg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}

//right
class DrinksDetailPanel extends  JPanel implements MouseListener {
    //상품 정보 출력 (JTable 이용)
    private JTable table;
    private  DefaultFrame parent;
    private  Controller controller;
    private  ArrayList<GoodsDTO> drinkList;
    private DrinksManagementView drinksManagementView;


    DrinksDetailPanel(DefaultFrame parent, DrinksManagementView drinksManagementView) {
        this.setLayout(new BorderLayout());
        this.parent = parent;
        controller = parent.getController(); //getController 하기 귀찮아서 변수에 저장

        //!!!!!!
        ArrayList<GoodsDTO> goodsList = controller.getGoodsDAO().findAll();
        drinkList = new ArrayList<>();
        for(int i = 0; i < goodsList.size(); i++)
            if(goodsList.get(i).getMainCategory() == GoodsDTO.MAIN_CATEGORY_DRINK)
                drinkList.add(goodsList.get(i));


        this.drinksManagementView = drinksManagementView;

        String[] columnType = {"음료코드", "음료명", "분류", "상태", "판매 가능 개수", "이벤트 여부", "판매가", "단가"};

        //columnType.setFont(new DefaultFont(50));

        Object[][] productData = new Object[drinkList.size()][];

        for(int i = 0; i <productData.length; i++){
            GoodsDTO goods = drinkList.get(i);

            productData[i] = new Object[]{
                    goods.getCode(), goods.getName(), goods.getCategory(), goods.getStatus(), goods.getSaleCount(),
                    goods.getDisStatus(), goods.getPrice(), goods.getCost()
            };
        }

        /*
        Object[][] drinksData = {
                //테스트를 위한 데이터
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
        }; */



        table = new JTable(productData, columnType);
        table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);

        //table 기본 설정
        table.getTableHeader().setReorderingAllowed(false); //header 움직이기 방지)

        //table.setEnabled(false); //아예 못 움직이게 (이벤트 처리 불가)
        /*table.setModel(new DefaultTableModel(new Object[][][] {}, new String[] { "셀1","셀2","셀3" })
        {public boolean isCellEditable(int row, int column) {return false;}})*/



        //scrollPane 올리기
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //테이블 클릭시 CategoryPanel에 담아줌 < >
        //2번 클릭시 이벤트 구현 : DrinkMgPopup
        if(e.getClickCount() == 2){
            int row = table.getSelectedRow();
            drinksManagementView.getDrinksCategoryPanel().addDrink(drinkList.get(row));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


public class DrinksManagementView extends JPanel{

    private DrinksDetailPanel drinksDetailPanel;
    private DrinksCategoryPanel drinksCategoryPanel;

    DefaultFrame parent;
    //음료 카테고리 정보

    //left: drinksCategoryPanel | Right: drinksDetailPanel
    public DrinksManagementView(DefaultFrame parent){
        this.parent = parent;


        this.setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        drinksCategoryPanel = new DrinksCategoryPanel();
        drinksDetailPanel = new DrinksDetailPanel(parent, this);


        //left : drinksCategoryPanel
        drinksCategoryPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,15));
        this.add(drinksCategoryPanel, DefaultFrame.easyGridBagConstraint(0, 0, 2, 1));
       // drinksCategoryPanel.setBackground(Color.orange);


        //right : drinksDetailPanel
        drinksDetailPanel.setBorder(BorderFactory.createEmptyBorder(30,15,30,30));
        this.add(drinksDetailPanel, DefaultFrame.easyGridBagConstraint(1,0,5,1));
        //drinksDetailPanel.setBackground(Color.red);
    }

    public DrinksDetailPanel getDrinksDetailPanel(){
        return drinksDetailPanel;
    }

    public DrinksCategoryPanel getDrinksCategoryPanel(){
        return drinksCategoryPanel;
    }


}