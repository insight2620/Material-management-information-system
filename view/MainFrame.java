package store_ma.view;


import store_ma.dao.GoodsDao;
import store_ma.model.goods;
import store_ma.model.Users;

import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class MainFrame  extends JFrame {
    //static Icon icon=new ImageIcon("ai.jpg");//要显示的图标
    ImageIcon background;
    //Image background;
    JLabel labelbackground;
    JPanel myBackgroundPanel;



    /*按钮*/
    private JButton SgoodsmoveButton;/*仓库物资管理*/
    private JButton SbasicinfomationButton;/*基础信息管理*/
    private JButton SqueryStatisticsButton;/*查询统计*/
    private JButton SusermanageButton;/*用户管理*/
    private JScrollPane scrollPane;
    private JPanel LeftPanel;
    private JTable table;
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }*/



    public MainFrame(Users user) {
        setTitle("物资管理系统");
        setResizable(false);
        setBounds(140, 80, 1213, 750);

        int authorityNumber;
        authorityNumber = user.getAuthority();//获取登录系统权限的用户权限值

        /*背景图的获取*/
        //this.getContentPane().add(new   MPanel());
        //this.getContentPane().add(new IPanel());
        //background = new ImageIcon("F://Code_document//store_m//Backgroundpic.jpg");
        background = new ImageIcon("C://Users//25//Desktop//快捷方式//数据库课设//最终3//store_m//Backgroundpic.jpg");
        labelbackground = new JLabel(background);
        labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());//background.getIconWidth(),background.getIconHeight());
        myBackgroundPanel = new JPanel();//命名为我自己的	//把我的面板设置为内容面板,myPanel与this.getContentPane()相同
        myBackgroundPanel.setBackground(Color.WHITE);
        myBackgroundPanel.add(labelbackground);

        /*背景放置方法*/
        //myBackgroundPanel.setOpaque(false);					//把我的面板设置为不可视
        //myBackgroundPanel.setLayout(new FlowLayout());		//把我的面板设置为流动布局
        //this.getLayeredPane().setLayout(null);		//把分层面板的布局置空
        //this.getLayeredPane().add(labelbackground, new Integer(Integer.MIN_VALUE));		//把标签添加到分层面板的最底层
        //this.getContentPane().add(myBackgroundPanel);


        SgoodsmoveButton = new JButton("仓库物资调度");
        SgoodsmoveButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        SgoodsmoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SgoodsmoveFrame();
            }
        });


        SbasicinfomationButton = new JButton("基础信息管理");
        SbasicinfomationButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        SbasicinfomationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SbasicinfomationFrame();
            }
        });

        SqueryStatisticsButton = new JButton("查询统计");
        SqueryStatisticsButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        SqueryStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SqueryStatisticsFrame();
            }
        });

        SusermanageButton =new JButton("用户管理");
        SusermanageButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        SusermanageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SusermanageFrame();
            }
        });

        LeftPanel = new JPanel();
        LeftPanel.setBackground(Color.WHITE);

        /*表*/
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                selectedTableRow(me);
            }
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "名称", "分类", "数量", "进价", "出价", "进货日期","所在仓库","供应商"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(6).setPreferredWidth(98);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        setTable(new goods());



        /*界面设计*/
        switch(authorityNumber)
        {
            case 1:
                ManagerLayout();
                break;
            case 2:
                BuyerLayout();
                break;
            case 3:
                SalemanLayout();
                break;
            case 4:
                CheckLayout();
        }



        /*整个界面的布局*/
        GroupLayout MaingroupLayout = new GroupLayout(getContentPane());
        MaingroupLayout.setHorizontalGroup(
                MaingroupLayout.createParallelGroup()
                .addGroup(MaingroupLayout.createSequentialGroup()
                    .addComponent(LeftPanel,GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(myBackgroundPanel)//,GroupLayout.PREFERRED_SIZE, 647, GroupLayout.PREFERRED_SIZE
                ).addGroup(MaingroupLayout.createSequentialGroup()
                        .addGap(200)
                        .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 950, GroupLayout.PREFERRED_SIZE)
                )
        );
        MaingroupLayout.setVerticalGroup(
                MaingroupLayout.createParallelGroup()
                        .addGroup(MaingroupLayout.createSequentialGroup()
                                .addGap(200)
                        .addComponent(LeftPanel)
                        )
                .addGroup(MaingroupLayout.createSequentialGroup()
                .addComponent(myBackgroundPanel,GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                )
        );

        getContentPane().setLayout(MaingroupLayout);
        getContentPane().setBackground(Color.WHITE);
    }


    protected void selectedTableRow(MouseEvent me) {
        //DefaultTableModel dft = (DefaultTableModel) table.getModel();
        //goodsNameTextField.setText(dft.getValueAt(table.getSelectedRow(), 1).toString());
    }

    protected void ManagerLayout(){
        GroupLayout groupLayout = new GroupLayout(LeftPanel);
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(32)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(SgoodsmoveButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addComponent(SbasicinfomationButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addComponent(SqueryStatisticsButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addComponent(SusermanageButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(12)
                                .addComponent(SgoodsmoveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(SbasicinfomationButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(SqueryStatisticsButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(SusermanageButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        );
        LeftPanel.setLayout(groupLayout);
    }

    protected void BuyerLayout(){
        GroupLayout groupLayout = new GroupLayout(LeftPanel);
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(32)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(SgoodsmoveButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(12)
                                .addComponent(SgoodsmoveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        );
        LeftPanel.setLayout(groupLayout);
    }

    protected void SalemanLayout(){
        GroupLayout groupLayout = new GroupLayout(LeftPanel);
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(32)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(SgoodsmoveButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(12)
                                .addComponent(SgoodsmoveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        );
        LeftPanel.setLayout(groupLayout);
    }

    protected void CheckLayout(){
        GroupLayout groupLayout = new GroupLayout(LeftPanel);
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(32)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(SqueryStatisticsButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(12)
                                .addComponent(SqueryStatisticsButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
        );
        LeftPanel.setLayout(groupLayout);
    }

    //将数据库里的数据放到表里
    private void setTable(goods goods){
        DefaultTableModel dft = (DefaultTableModel)table.getModel();
        dft.setRowCount(0);

        GoodsDao goodsDao = new GoodsDao();
        List<goods> goodList = goodsDao.getMainFrameGoodsList(goods);
        for (goods g : goodList) {
            Vector v = new Vector();
            v.add(g.getName());
            v.add(g.getSort());
            v.add(g.getNumber());
            v.add(g.getInputPrice());
            v.add(g.getOutputPrice());
            v.add(g.getDateTime());
            v.add(g.getWarehouse_name());
            v.add(g.getSupplier_name());

            dft.addRow(v);
        }
    }
}
