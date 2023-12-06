package views;

import controller_db.Controller;
import custom_component.DefaultFont;
import custom_component.FreeImageIcon;
import dto.GoodsDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

//left
class DrinksCategoryPanel extends JPanel implements ActionListener{
    public static Dimension BUTTON_SIZE = new Dimension(80,80);
    public static final String[] drinksCategorys = {
            "전체 메뉴 보기", "커피", "논-커피", "티", "스무디", "상품"
    };
    private JPanel cCenter;
    private DefaultFrame parent;

    //
    private HashMap<String, GoodsDTO> goodsMap;
    DrinksDetailPanel drinksDetailPanel;

    DrinksCategoryPanel(DefaultFrame prt, DrinksDetailPanel drinksDetailPanel) {
        goodsMap = new HashMap<>();
        parent = prt;
        this.drinksDetailPanel = drinksDetailPanel;
        this.setLayout(new BorderLayout());

        //top : 음료 카테고리 보기 (JComboBox 사용)
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
        
        box layout 정리
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
        String [] drinkMangement = {"추가", "편집", "삭제", "<HTML><body style='text-align:center;'>일괄<br>설정</body></HTML>", "비우기"};
        String [] dMButtonColor = {"green", "yellow", "RED", "orange", ""};
        Color[] btnColors = {Color.green, Color.YELLOW, new Color(255, 0, 0) , Color.orange, Color.WHITE};

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
            dMButton[i].addActionListener(this);

        }

        add(cTop, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);
        add(cBottom, BorderLayout.SOUTH);
    }


    public void addDrink(GoodsDTO goods){
        //만약 리스트에 있다면 받지 않는다.
        if(goodsMap.get(goods.getCode()) != null ) return;
        //만약 리스트에 없는 정보라면 해쉬맵에 추가한다.
        goodsMap.put(goods.getCode(), goods);

        cCenter.add(new SelectDrinkPanel(goods));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String s = e.getActionCommand();
       if(s.contains("삭제")){

       }else if(s.contains("추가")){
           //(new DrinkMgPopup(parent, newArr))

       }else if(s.contains("편집")){


       } else if(s.contains("일괄")) {
           System.out.println(goodsMap.values());
           (new DrinksStatusPopup(parent, new ArrayList<GoodsDTO>(goodsMap.values()))).setVisible(true);
           drinksDetailPanel.tableUpdate();

       }else if(s.contains("비우기")) {

       }
    }

}

//table 선택시 담기는 정보 패널
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

        //전체 삭제 클릭 이벤트 처리 1 - 삭제 : X 버튼 클릭시 담겼던 음료 삭제
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
    private Vector<Vector<Object>> productData = new Vector<>(new Vector<>());
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
        Vector<String> columVector = new Vector<>();
        for(int i = 0; i < columnType.length; i++)
            columVector.add(columnType[i]);

        //columnType.setFont(new DefaultFont(50));

        /* table 기본 설정 */
        //2. JTable 수정 방지 하는 메서드
        DefaultTableModel tableModel = new DefaultTableModel(productData, columVector){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        table = new JTable(tableModel);


        //새로운 정보를 받아서 UPDATE
        tableUpdate();

        table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);

        /* table 기본 설정 */
        //1. header 움직이기 방지
        table.getTableHeader().setReorderingAllowed(false);

        //scrollPane 올리기
        this.add(scrollPane);
        this.setVisible(true);
    }

    //JTable 마우스 이벤트
    @Override
    public void mouseClicked(MouseEvent e) {
        //테이블 클릭시 CategoryPanel에 담아줌 < >
        //2번 클릭시 이벤트 구현 : DrinkMgPopup
        if(e.getClickCount() == 1){

        }

        if(e.getClickCount() == 2) {
            //더블 클릭 -
            int row = table.getSelectedRow();
            drinksManagementView.getDrinksCategoryPanel().addDrink(drinkList.get(row));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public JTable getJtable() {
        return table;
    }

    //ArrayList --> Object[][] 형으로 변환
    public void tableUpdate() {
        //테이블 정보 모두 삭제
        productData.removeAllElements();

        //테이블 정보 가져오기
        for (GoodsDTO g : drinkList) {
            productData.add(new Vector<Object>());
            productData.get(productData.size() - 1).add(g.getCode());
            productData.get(productData.size() - 1).add(g.getName());
            productData.get(productData.size() - 1).add(g.getCategory());
            productData.get(productData.size() - 1).add(g.getStatus());
            productData.get(productData.size() - 1).add(g.getSaleCount());
            productData.get(productData.size() - 1).add(g.getDisStatus());
            productData.get(productData.size() - 1).add(g.getPrice());
            productData.get(productData.size() - 1).add(g.getCost());
        }
        //새롭게 되도록 업데이트
        table.updateUI();
    }
}

//mainView
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

        drinksDetailPanel = new DrinksDetailPanel(parent, this);
        drinksCategoryPanel = new DrinksCategoryPanel(parent, drinksDetailPanel);


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