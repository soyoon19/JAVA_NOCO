package views;

import custom_component.DefaultFont;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesAnalysisView extends JPanel {
    public SalesAnalysisView(DefaultFrame prt) {

        this.setLayout(new BorderLayout());


        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 2, 20, 20));

        //1. 금일 매출 현황
        //금일 매출 윗 부분
        JPanel today = new JPanel();
        today.setLayout(new BorderLayout());

        JPanel Daydate = new JPanel();
        Daydate.setLayout((new FlowLayout(FlowLayout.LEFT)));
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//날짜 자르기


        JLabel nowdate = new JLabel("금일 매출 현황" + format.format(d));

        Daydate.add(nowdate);
        today.add(Daydate, BorderLayout.NORTH);


        //금일 매출 아랫부분

        JPanel SaleList = new JPanel();

        JLabel nowSale = new JLabel("현재 매출:");
        JLabel nowPay = new JLabel("n원");//임시로 n원 배치 db 연결 시 바꿀 것
        JLabel orderCount = new JLabel("주문 건수");
        JLabel orderCountSu = new JLabel("n건"); //임시로 n원 배치 db 연결시 바꿀것


        JLabel[] jlb = new JLabel[]{nowSale, orderCount, nowPay, orderCountSu};

        SaleList.setLayout(new GridLayout(2, 1));

        for (int i = 0; i < 2; i++) {
            jlb[i].setFont(new DefaultFont(20));
            jlb[i + 2].setFont(new DefaultFont(20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5);
            SaleList.add(jlb[i]);

            gbc.gridx = 1;
            SaleList.add(jlb[i + 2], gbc);
        }
        today.add(SaleList, BorderLayout.CENTER);
        //-------------금일 매출 현황 레이 아웃 끝---------------

        //2.매출 조회
        JPanel SaleCheck = new JPanel(); // 매출 조회 가장 큰 레이아웃
        SaleCheck.setLayout(new GridLayout(4, 1));

        //매출 조회 상단 타이틀
        JPanel title2 = new JPanel();
        title2.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel T_title2 = new JLabel("매출 조회");
        title2.add(T_title2);

        //날짜 지정 레이아웃
        JPanel payDay = new JPanel();
        payDay.setLayout(new FlowLayout());

        JLabel T_payDay = new JLabel("마감일   yyyy-mm-dd  ~  yyyy-mm-dd");

        payDay.add(T_payDay);


        //즉시 조회 버튼 레이아웃
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 4));

        JButton B_today = new JButton("오늘");
        JButton B_week = new JButton("1주일");
        JButton B_month = new JButton("1개월");
        JButton B_3month = new JButton("3개월");

        gridBtn.add(B_today);
        gridBtn.add(B_week);
        gridBtn.add(B_month);
        gridBtn.add(B_3month);

        // 조회버튼 배치 레이아웃
        JPanel CheckBtm = new JPanel();
        CheckBtm.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton checking = new JButton("조회");
        CheckBtm.add(checking);

        SaleCheck.add(title2);
        SaleCheck.add(payDay);
        SaleCheck.add(gridBtn);
        SaleCheck.add(CheckBtm);

        //----------------매출 조회 레이아웃 끝------------

        //3.재고현황

        JPanel current = new JPanel();// 가장 큰 레이아웃
        current.setLayout(new BorderLayout());

        //재고 현황 타이틀
        JPanel title3 = new JPanel();
        title3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel T_title3 = new JLabel("재고현황");

        title3.add(T_title3);
        current.add(title3, BorderLayout.NORTH);

        // 재고현황 테이블 레이아웃

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                        {"커피", 2},
                        {"케이크", 1},
                        {"샌드위치", 3},
                        // ... (Add more rows as needed)
                },
                new Object[]{"상품명", "개수"}
        );

        // JTable 생성 및 모델 설정
        JTable table = new JTable(tableModel);

        // 스크롤 가능하도록 JScrollPane에 JTable 추가
        JScrollPane scrollPane = new JScrollPane(table);

        current.add(scrollPane, BorderLayout.CENTER);

        //---------------재고현황 레이아웃 끝---------------

        //매출 분석 Layout

        JPanel analysis = new JPanel(); // 가장 큰 Layout
        analysis.setLayout(new BorderLayout());

        //타이틀 배치
        JPanel title4 = new JPanel();
        title4.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel T_title4 = new JLabel("매출 분석");
        title4.add(T_title4);


        analysis.add(T_title4, BorderLayout.NORTH);
        JPanel analysisCtr = new JPanel();
        analysisCtr.setLayout(new GridLayout(4, 1));

        JPanel FL1 = new JPanel();
        FL1.setLayout(new FlowLayout());
        JCheckBox FL1box = new JCheckBox();

        JLabel date1 = new JLabel("YYYY-MM-DD");
        JLabel date2 = new JLabel("YYYY-MM-DD");
        JLabel sym = new JLabel(" ~ ");
        JButton btn1 = new JButton("icon1");
        JButton btn2 = new JButton("icon2");

        FL1.add(FL1box);
        FL1.add(date1);
        FL1.add(btn1);
        FL1.add(sym);
        FL1.add(date2);
        FL1.add(btn2);

        JPanel FL2 = new JPanel();
        FL2.setLayout(new FlowLayout());

        String[] year = {"2023", "2022", "2021", "2020", "2019"};
        String[] month = {"1    ", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        JComboBox strYear1 = new JComboBox(year);
        JComboBox strMonth1 = new JComboBox(month);
        JComboBox strYear2 = new JComboBox(year);
        JComboBox strMonth2 = new JComboBox(month);
        JCheckBox FL2box = new JCheckBox();
        JLabel sym1 = new JLabel("-");
        JLabel sym2 = new JLabel(" ~ ");
        JLabel sym3 = new JLabel("-");

        FL2.add(FL2box);
        FL2.add(strYear1);
        FL2.add(sym1);
        FL2.add(strMonth1);
        FL2.add(sym2);
        FL2.add(strYear2);
        FL2.add(sym3);
        FL2.add(strMonth2);


        JPanel FL3 = new JPanel();
        FL3.setLayout(new FlowLayout());

        String[] r_num={"1","2","3","4","5","6"};
        JLabel room= new JLabel("방");
        JCheckBox FL3box  = new JCheckBox();
        JCheckBox r1Box  = new JCheckBox();
        JCheckBox r2Box  = new JCheckBox();
        JComboBox strRoom1= new JComboBox(r_num);
        JComboBox strRoom2= new JComboBox(r_num);

        FL3.add(FL3box);
        FL3.add(room);
        FL3.add(r1Box);
        FL3.add(strRoom1);
        FL3.add(r2Box);
        FL3.add(strRoom2);
        JPanel FL4 = new JPanel();
        FL3.setLayout(new FlowLayout());

        String[] g_name={"아메리카노","딸기스무디","바닐라라뗴","과채주스","코카콜라","칠성사이다"};
        JLabel goods= new JLabel("상품");
        JCheckBox FL4box  = new JCheckBox();
        JCheckBox g1Box  = new JCheckBox();
        JCheckBox g2Box  = new JCheckBox();
        JComboBox strGoods1= new JComboBox(g_name);
        JComboBox strGoods2= new JComboBox(g_name);

        FL4.add(FL4box);
        FL4.add(goods);
        FL4.add(g1Box);
        FL4.add(strGoods1);
        FL4.add(g2Box);
        FL4.add(strGoods2);


        analysisCtr.add(FL1);
        analysisCtr.add(FL2);
        analysisCtr.add(FL3);
        analysisCtr.add(FL4);


        analysis.add(analysisCtr,BorderLayout.CENTER);
        main.add(today);
        main.add(SaleCheck);
        main.add(current);
        main.add(analysis);
        this.add(main, BorderLayout.CENTER);
    }
}
