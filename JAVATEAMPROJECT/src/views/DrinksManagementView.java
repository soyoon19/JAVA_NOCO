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
            "전체 메뉴 보기", "커피", "논커피", "티", "스무디", "상품"
    };
    private JPanel cCenter;
    private DefaultFrame parent;

    //
    private HashMap<String, GoodsDTO> goodsMap;
    DrinksDetailPanel drinksDetailPanel;
    private ArrayList<GoodsDTO> goodsArr;

    private int nowSelect = 0;

    //BoxLayout 초기화 : 담아준 음료를 초기화 시키기
    private void clearBoxLayout() {
        cCenter.removeAll(); //all 컴포넌트 제거
        cCenter.revalidate(); //다시 유효하게
        cCenter.repaint(); //패널을 다시 그림
        goodsMap.clear();
    }

    DrinksCategoryPanel(DefaultFrame prt, DrinksDetailPanel drinksDetailPanel) {
        goodsMap = new HashMap<>();
        parent = prt;
        goodsArr = parent.getController().getGoodsDAO().findAll();
        parent = prt;
        this.drinksDetailPanel = drinksDetailPanel;
        this.setLayout(new BorderLayout());

        //top : 음료 카테고리 보기 (JComboBox 사용)
        JPanel cTop = new JPanel();
        //setBackground(Color.WHITE);

        JComboBox categoryComboBox = new JComboBox(drinksCategorys);
        categoryComboBox.setFont(new DefaultFont(50));
        //콤보 이벤트 처리
        categoryComboBox.addActionListener(this);
        cTop.setLayout(new BorderLayout());
        cTop.add(categoryComboBox);

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

        //실제 내용
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
        String [] dMButtonColor = {"green", "yellow", "RED", "orange", "white"};
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

        categoryComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int s = categoryComboBox.getSelectedIndex();
                if(s == nowSelect) return;;
                drinksDetailPanel.tableUpdate(s);
                nowSelect = s;
            }
        });
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
       int row = drinksDetailPanel.getTable().getSelectedRow();
       //추가
        if(s.contains("추가")){
            (new GoodsAddPopup(parent)).setVisible(true);
            //(new DrinkMgPopup(parent, newArr))
            //(new DrinkMgPopup(parent, )).setVisible(true);

       if(s.contains("삭제")){
           if(!(row < 0)){//미선택 예외처리
               JOptionPane.showMessageDialog(null, "선택된 음료가 없어 삭제할 수 없습니다.");
               return;
           }else {
               int r = JOptionPane.showConfirmDialog(null, "해당 음료를 정말 삭제하겠습니까?",
                       "음료 종류 삭제 확인창", JOptionPane.YES_NO_OPTION);

               if(r == JOptionPane.YES_NO_OPTION) {
                   //DB 삭제 방식
                   parent.getController().getGoodsDAO().delete(drinksDetailPanel.getProductData().get(row).get(0).toString());
                   drinksDetailPanel.tableUpdate(nowSelect);
           }
       }

       //todo : goodEditPopup에 DB에 저장된 정보 불러오고, 수정 후 DB에 반영되는 이벤트 처리
       }else if(s.contains("편집")){
           if(row < 0) {//미선택시 에외처리
               JOptionPane.showMessageDialog(null, "선택된 음료가 없어 편집할 수 없습니다.");
               return;
           }else {
               //parent.getController().getGoodsDAO().update(drinksDetailPanel.getProductData().get(row).get(0).toString());
               drinksDetailPanel.tableUpdate(nowSelect);
               (new GoodEditPopup(parent)).setVisible(true);
           }

       } else if(s.contains("일괄")) {
           if(row < 0) {//미선택시 에외처리
               JOptionPane.showMessageDialog(null, "선택된 음료가 없어 일괄설정할 수 없습니다.");
               return;
           }
           System.out.println(goodsMap.values());
           DrinksStatusPopup drinksStatusPopup = new DrinksStatusPopup(parent, new ArrayList<GoodsDTO>(goodsMap.values()));
           drinksStatusPopup.setVisible(true);

           //확인이 눌렸다면
           if (drinksStatusPopup.isChecked() == true) {
               clearBoxLayout(); //메서드 실행
           }
           drinksDetailPanel.tableUpdate(nowSelect);

       }else if(s.contains("비우기")) {
           clearBoxLayout();
           drinksDetailPanel.tableUpdate(nowSelect);// 선택 되어있던 카테고리로 보여줌
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

        //판매 가능 개수
        JLabel saleCount = new JLabel("판매 가능 개수: "+ String.valueOf(goods.getSaleCount()));
        saleCount.setFont(new DefaultFont(15));
        drinkSelect.add(saleCount);

        //음료 상태
        JLabel status = new JLabel("상태: "+ goods.getStatus());
        status.setFont(new DefaultFont(15));
        drinkSelect.add(status);


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
    private int nowCategoryIndex = 0;

    DrinksDetailPanel(DefaultFrame parent, DrinksManagementView drinksManagementView) {
        this.setLayout(new BorderLayout());
        this.parent = parent;
        controller = parent.getController(); //getController 하기 귀찮아서 변수에 저장

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
        //2. JTable 사용자 수정 방지 하는 메서드
        DefaultTableModel tableModel = new DefaultTableModel(productData, columVector){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        table = new JTable(tableModel);

        //새로운 정보를 받아서 UPDATE
        tableUpdate(nowCategoryIndex);

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
            drinksManagementView.getDrinksCategoryPanel().addDrink(
                    parent.getController().getGoodsDAO().findById(
                            productData.get(row).get(0).toString()));
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
        tableUpdate(0);
    }

    public void tableUpdate(int s){
        nowCategoryIndex = s;
        drinkList = parent.getController().getGoodsDAO().findAll();
        String category = DrinksCategoryPanel.drinksCategorys[nowCategoryIndex];
        productData.removeAllElements();
        System.out.println(s);

        if(s == 0){
            for(GoodsDTO goods : drinkList) {
                productData.add(GoodsDTOToVector(goods));
            }
        }else{
            for(GoodsDTO goods : drinkList){
                if(DrinksCategoryPanel.drinksCategorys[s].equals(goods.getCategory()))
                    productData.add(GoodsDTOToVector(goods));

            }
        }
        table.updateUI();
    }

    public JTable getTable() {
        return table;
    }

    public Vector<Vector<Object>> getProductData() {
        return productData;
    }

    public Vector<Object> GoodsDTOToVector(GoodsDTO g){
        Vector<Object> productData = new Vector<>();
        productData.add(g.getCode());
        productData.add(g.getName());
        productData.add(g.getCategory());
        productData.add(g.getStatus());
        productData.add(g.getSaleCount());
        productData.add(g.getDisStatus());
        productData.add(g.getPrice());
        productData.add(g.getCost());

        return productData;
    }

    public void setNowCategoryIndex(int nowCategoryIndex){
        this.nowCategoryIndex = nowCategoryIndex;
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
}