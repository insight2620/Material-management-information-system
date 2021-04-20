package store_ma.view;

import store_ma.dao.*;
import store_ma.model.*;
import store_ma.util.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class SqueryStatisticsFrame extends JFrame{
    private JTabbedPane Tab;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SqueryStatisticsFrame frame = new SqueryStatisticsFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SqueryStatisticsFrame(){
        setTitle("仓库查询统计");
        setResizable(false);
        setBounds(140, 80, 1213, 750);

        /*界面布局*/
        this.getContentPane().add(this.Tab = new JTabbedPane());
        this.setVisible(true);
        Tab.setFont(new Font("隶书", Font.BOLD, 24));
        Tab.addTab("出库查询",new SqueryStatisticsFrame.ex_JPanel());
        Tab.addTab("入库查询",new SqueryStatisticsFrame.in_JPanel());
        Tab.addTab("采购查询",new SqueryStatisticsFrame.internal_JPanel());
    }


    private class ex_JPanel extends JPanel{
        /*出库*/
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;

        private JLabel exgoodsnameLabel;
        private JLabel exgoodswarehouseLabel;
        private JLabel exgoodsdateStartLabel;
        private JLabel exgoodsdateEndLabel;
        private JLabel exgoodstotalnumberLabel;
        private JLabel exgoodstotalpriceLabel;
        private JLabel exrecordLabel;

        private JComboBox exgoodsnameBox;
        private JComboBox ex_warehouseBox;
        private JTextField exgoodsdateStartField;
        private JTextField exgoodsdateEndField;
        private JTextField exgoodstotalnumberField;
        private JTextField exgoodstotalpriceField;

        private JScrollPane scrollPane;
        private JTable ex_warehouseTable;

        private JButton checkButton;
        private JButton statisticsButton;
        private JButton cancelButton;
        private JButton refreshButton;

        private JPanel LeftPanel;
        private JPanel RightPanel;

        public ex_JPanel(){
            setBackground(Color.white);
            background = new ImageIcon("C://Users//25//Desktop//快捷方式//数据库课设//最终3//store_m//Backgroundpic.jpg");
            labelbackground = new JLabel(background);
            labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
            myBackgroundPanel = new JPanel();
            myBackgroundPanel.setBackground(Color.WHITE);
            myBackgroundPanel.add(labelbackground);


            exTitleLabel = new JLabel("操作面板");
            exTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            exgoodsnameLabel = new JLabel("物资名称：");
            exgoodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodswarehouseLabel = new JLabel("出库仓库：");
            exgoodswarehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodsdateStartLabel = new JLabel("时间起点：");
            exgoodsdateStartLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodsdateEndLabel = new JLabel("时间终点：");
            exgoodsdateEndLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodstotalnumberLabel = new JLabel("总数量：      ");
            exgoodstotalnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodstotalpriceLabel = new JLabel("总销售金额：");
            exgoodstotalpriceLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exrecordLabel = new JLabel("出库记录");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));


            exgoodsnameBox = new JComboBox();
            ex_warehouseBox = new JComboBox();
            exgoodsdateStartField = new JTextField();
            exgoodsdateStartField.setText("2021-01-20");
            exgoodsdateEndField = new JTextField();
            exgoodsdateEndField.setText("2021-01-31");
            exgoodstotalnumberField = new JTextField();
            exgoodstotalnumberField.setEditable(false);
            exgoodstotalpriceField = new JTextField();
            exgoodstotalpriceField.setEditable(false);

            ex_warehouseTable = new JTable();
            ex_warehouseTable.setBackground(Color.WHITE);
            ex_warehouseTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    selectedTableRow(me);
                }
            });
            ex_warehouseTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "名称", "分类","数量","出价","出库时所在仓库","出库日期"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false, false, false,false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            ex_warehouseTable.getColumnModel().getColumn(5).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(ex_warehouseTable);
            setex_warehouseTable(new ex_warehouse() );


            checkButton = new JButton("查询");
            checkButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            checkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    warehouseDao warehousedao = new warehouseDao();
                    int warehouseID = warehousedao.getID(String.valueOf(ex_warehouseBox.getSelectedItem()));
                    warehousedao.closeDao();


                    String sql = "SELECT goods.name1,goods.sort,ex_warehouse.number,goods.outputPrice,warehouse.name,ex_warehouse.Ex_time " +
                                 " From goods,ex_warehouse,warehouse"+
                            " where goods.id = ex_warehouse.goodsID and ex_warehouse.warehouseID = warehouse.id " +
                            " and goods.name1 = '" + String.valueOf(exgoodsnameBox.getSelectedItem()) + "' and goods.warehouse_id = '"
                            + String.valueOf(warehouseID)+"'";

                    if(!StringUtil.isEmpty(exgoodsdateStartField.getText()))
                    {
                            sql += "and ex_warehouse.EX_time >= '" + exgoodsdateStartField.getText()+"'";
                            if(!StringUtil.isEmpty(exgoodsdateEndField.getText()))
                            {
                                sql += "and ex_warehouse.EX_time <= '" + exgoodsdateEndField.getText()+"'";
                            }
                    }

                    DefaultTableModel dft = (DefaultTableModel)ex_warehouseTable.getModel();
                    dft.setRowCount(0);

                    ex_warehouseDao ex_wareDao = new ex_warehouseDao();
                    java.util.List<ex_warehouse> ex_wareList = ex_wareDao.checkex_warehouseList(sql);
                    for (ex_warehouse g : ex_wareList) {
                        Vector v = new Vector();
                        v.add(g.getGoodsName());
                        v.add(g.getGoodsSort());
                        v.add(g.getNumber());
                        v.add(g.getGoodsoutprice());
                        v.add(g.getWarehouseName());
                        v.add(g.getEX_time());
                        //JOptionPane.showMessageDialog(null, String.valueOf(g.getEX_time()));
                        dft.addRow(v);
                    }
                }
            });
            statisticsButton = new JButton("统计");
            statisticsButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            statisticsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    GoodsDao goodsdao = new GoodsDao();
                    int goodsID = goodsdao.getID(String.valueOf(exgoodsnameBox.getSelectedItem()));
                    //JOptionPane.showMessageDialog(null, String.valueOf(exgoodsnameBox.getSelectedItem()));
                    goodsdao.closeDao();

                    String sql = "call check_ex_warehouse ( '" + String.valueOf(goodsID) + "','"
                            + String.valueOf(exgoodsdateStartField.getText())+"','"+String.valueOf(exgoodsdateEndField.getText())+"' );";


                    ex_warehouseDao exwarehousedao = new ex_warehouseDao();
                    java.util.List<NP> ex_wareList = exwarehousedao.getexNP(sql);
                    for (NP g : ex_wareList) {
                        exgoodstotalnumberField.setText(String.valueOf(g.getNumber()));
                        exgoodstotalpriceField.setText(String.valueOf(g.getPrice()));
                    }
                }
            });
            cancelButton = new JButton("取消");
            cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton = new JButton("刷新");
            refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setex_warehouseTable(new ex_warehouse() );
                }
            });

            LeftPanel = new JPanel();/*左页面板*/
            GroupLayout titlegroupLayout = new GroupLayout(LeftPanel);
            titlegroupLayout.setHorizontalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(100)
                                    .addComponent(exTitleLabel)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsnameLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodswarehouseLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ex_warehouseBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsdateStartLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsdateStartField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsdateEndLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsdateEndField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodstotalnumberLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodstotalnumberField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodstotalpriceLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodstotalpriceField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(checkButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statisticsButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(exTitleLabel,GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsnameLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodswarehouseLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateStartLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateEndLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalnumberLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalpriceLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(45)
                                    .addGroup(titlegroupLayout.createParallelGroup()
                                            .addComponent(checkButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(statisticsButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)


                                    )
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(46)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ex_warehouseBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateStartField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateEndField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalnumberField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalpriceField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            )
            );
            LeftPanel.setLayout(titlegroupLayout);
            LeftPanel.setBackground(Color.WHITE);

            RightPanel = new JPanel();
            GroupLayout rightPanelgroupLayout = new GroupLayout(RightPanel);
            rightPanelgroupLayout.setHorizontalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(350)
                                    .addComponent(exrecordLabel)
                                    .addGap(150)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                            )
            );
            rightPanelgroupLayout.setVerticalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(exrecordLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(23)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            )
            );
            RightPanel.setLayout(rightPanelgroupLayout);
            RightPanel.setBackground(Color.white);

            GroupLayout groupLayout = new GroupLayout(this);
            groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(myBackgroundPanel)
                            )
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(24)
                                    .addComponent(LeftPanel)
                                    .addGap(32)
                                    .addComponent(RightPanel)
                            )
            );
            groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(myBackgroundPanel,GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LeftPanel)
                            )
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(188)
                                    .addComponent(RightPanel)
                            )
            );
            this.setLayout(groupLayout);
            this.setGoodsNameCombox();
            this.setwareNameCombox();
            setVisible(true);
        }

        private void setGoodsNameCombox() {
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsList(new goods());
            for(goods g:goods) {

                exgoodsnameBox.addItem(g.getName());
            }
            goodsDao.closeDao();
        }


        private void setwareNameCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getwarehouselist(new goods());
            for(goods g:goods) {
                ex_warehouseBox.addItem(g.getWarehouse_name());
            }
            goodsDao.closeDao();

        }
        private void setex_warehouseTable(ex_warehouse ex_wareh){
            DefaultTableModel dft = (DefaultTableModel)ex_warehouseTable.getModel();
            dft.setRowCount(0);

            ex_warehouseDao ex_wareDao = new ex_warehouseDao();
            java.util.List<ex_warehouse> ex_wareList = ex_wareDao.getex_warehouseList(ex_wareh);
            for (ex_warehouse g : ex_wareList) {
                Vector v = new Vector();
                v.add(g.getGoodsName());
                v.add(g.getGoodsSort());
                v.add(g.getNumber());
                v.add(g.getGoodsoutprice());
                v.add(g.getWarehouseName());
                v.add(g.getEX_time());
                //JOptionPane.showMessageDialog(null, String.valueOf(g.getEX_time()));
                dft.addRow(v);
            }
        }

        protected void selectedTableRow(MouseEvent e){

        }
    }

    private class in_JPanel extends JPanel{
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;

        private JLabel exgoodsnameLabel;
        private JLabel exgoodswarehouseLabel;
        private JLabel exgoodsdateStartLabel;
        private JLabel exgoodsdateEndLabel;
        private JLabel exgoodstotalnumberLabel;
        private JLabel exgoodstotalpriceLabel;
        private JLabel exrecordLabel;

        private JComboBox exgoodsnameBox;
        private JComboBox ex_warehouseBox;
        private JTextField exgoodsdateStartField;
        private JTextField exgoodsdateEndField;
        private JTextField exgoodstotalnumberField;
        private JTextField exgoodstotalpriceField;

        private JScrollPane scrollPane;
        private JTable ex_warehouseTable;

        private JButton checkButton;
        private JButton statisticsButton;
        private JButton cancelButton;
        private JButton refreshButton;

        private JPanel LeftPanel;
        private JPanel RightPanel;

        public in_JPanel(){
            setBackground(Color.white);
            background = new ImageIcon("F://Code_document//store_m//Backgroundpic.jpg");
            labelbackground = new JLabel(background);
            labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
            myBackgroundPanel = new JPanel();
            myBackgroundPanel.setBackground(Color.WHITE);
            myBackgroundPanel.add(labelbackground);


            exTitleLabel = new JLabel("操作面板");
            exTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            exgoodsnameLabel = new JLabel("物资名称：");
            exgoodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodswarehouseLabel = new JLabel("入库仓库：");
            exgoodswarehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodsdateStartLabel = new JLabel("时间起点：");
            exgoodsdateStartLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodsdateEndLabel = new JLabel("时间终点：");
            exgoodsdateEndLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodstotalnumberLabel = new JLabel("总数量：      ");
            exgoodstotalnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodstotalpriceLabel = new JLabel("总销售金额：");
            exgoodstotalpriceLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exrecordLabel = new JLabel("入库记录");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));


            exgoodsnameBox = new JComboBox();
            ex_warehouseBox = new JComboBox();
            exgoodsdateStartField = new JTextField();
            exgoodsdateStartField.setText("2021-01-01");
            exgoodsdateEndField = new JTextField();
            exgoodsdateEndField.setText("2021-01-31");
            exgoodstotalnumberField = new JTextField();
            exgoodstotalnumberField.setEditable(false);
            exgoodstotalpriceField = new JTextField();
            exgoodstotalpriceField.setEditable(false);

            ex_warehouseTable = new JTable();
            ex_warehouseTable.setBackground(Color.WHITE);
            ex_warehouseTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    selectedTableRow(me);
                }
            });
            ex_warehouseTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "名称", "分类","数量","出价","所在仓库","入库日期"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false, false, false,false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            ex_warehouseTable.getColumnModel().getColumn(5).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(ex_warehouseTable);
            setex_warehouseTable(new in_warehouse());


            checkButton = new JButton("查询");
            checkButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            checkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    warehouseDao warehousedao = new warehouseDao();
                    int warehouseID = warehousedao.getID(String.valueOf(ex_warehouseBox.getSelectedItem()));
                    warehousedao.closeDao();


                    String sql = "SELECT goods.name1,goods.sort,in_warehouse.number,goods.inputPrice,warehouse.name,in_warehouse.IN_time " +
                            " From goods,in_warehouse,warehouse"+
                            " where goods.id = in_warehouse.goodsID and in_warehouse.warehouseID = warehouse.id " +
                            " and goods.name1 = '" + String.valueOf(exgoodsnameBox.getSelectedItem()) + "' and goods.warehouse_id = '"
                            + String.valueOf(warehouseID)+"'";

                    if(!StringUtil.isEmpty(exgoodsdateStartField.getText()))
                    {
                        sql += "and in_warehouse.IN_time >= '" + exgoodsdateStartField.getText()+"'";
                        if(!StringUtil.isEmpty(exgoodsdateEndField.getText()))
                        {
                            sql += "and in_warehouse.IN_time <= '" + exgoodsdateEndField.getText()+"'";
                        }
                    }

                    DefaultTableModel dft = (DefaultTableModel)ex_warehouseTable.getModel();
                    dft.setRowCount(0);

                    in_warehouseDao in_wareDao = new in_warehouseDao();
                    java.util.List<in_warehouse> ex_wareList = in_wareDao.checkin_warehouseList(sql);
                    for (in_warehouse g : ex_wareList) {
                        Vector v = new Vector();
                        v.add(g.getGoodsName());
                        v.add(g.getGoodsSort());
                        v.add(g.getNumber());
                        v.add(g.getGoodsinprice());
                        v.add(g.getWarehouseName());
                        v.add(g.getIN_time());
                        //JOptionPane.showMessageDialog(null, String.valueOf(g.getEX_time()));
                        dft.addRow(v);
                }}
            });
            statisticsButton = new JButton("统计");
            statisticsButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            statisticsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GoodsDao goodsdao = new GoodsDao();
                    int goodsID = goodsdao.getID(String.valueOf(exgoodsnameBox.getSelectedItem()));
                    //JOptionPane.showMessageDialog(null, String.valueOf(exgoodsnameBox.getSelectedItem()));
                    goodsdao.closeDao();

                    String sql = "call check_in_warehouse ( '" + String.valueOf(goodsID) + "','"
                            + String.valueOf(exgoodsdateStartField.getText()) + "','" + String.valueOf(exgoodsdateEndField.getText()) + "' );";


                    in_warehouseDao inwarehousedao = new in_warehouseDao();
                    java.util.List<NP> ex_wareList = inwarehousedao.getinNP(sql);
                    for (NP g : ex_wareList) {
                        exgoodstotalnumberField.setText(String.valueOf(g.getNumber()));
                        exgoodstotalpriceField.setText(String.valueOf(g.getPrice()));
                    }
                }
            });
            cancelButton = new JButton("取消");
            cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton = new JButton("刷新");
            refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setex_warehouseTable(new in_warehouse());
                }
            });

            LeftPanel = new JPanel();/*左页面板*/
            GroupLayout titlegroupLayout = new GroupLayout(LeftPanel);
            titlegroupLayout.setHorizontalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(100)
                                    .addComponent(exTitleLabel)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsnameLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodswarehouseLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ex_warehouseBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsdateStartLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsdateStartField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsdateEndLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsdateEndField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodstotalnumberLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodstotalnumberField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodstotalpriceLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodstotalpriceField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(checkButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statisticsButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(exTitleLabel,GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsnameLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodswarehouseLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateStartLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateEndLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalnumberLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalpriceLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(45)
                                    .addGroup(titlegroupLayout.createParallelGroup()
                                            .addComponent(checkButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(statisticsButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)


                                    )
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(46)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ex_warehouseBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateStartField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsdateEndField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalnumberField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalpriceField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            )
            );
            LeftPanel.setLayout(titlegroupLayout);
            LeftPanel.setBackground(Color.WHITE);

            RightPanel = new JPanel();
            GroupLayout rightPanelgroupLayout = new GroupLayout(RightPanel);
            rightPanelgroupLayout.setHorizontalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(350)
                                    .addComponent(exrecordLabel)
                                    .addGap(150)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                            )
            );
            rightPanelgroupLayout.setVerticalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(exrecordLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(23)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            )
            );
            RightPanel.setLayout(rightPanelgroupLayout);
            RightPanel.setBackground(Color.white);

            GroupLayout groupLayout = new GroupLayout(this);
            groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(myBackgroundPanel)
                            )
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(24)
                                    .addComponent(LeftPanel)
                                    .addGap(32)
                                    .addComponent(RightPanel)
                            )
            );
            groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(myBackgroundPanel,GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LeftPanel)
                            )
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(188)
                                    .addComponent(RightPanel)
                            )
            );
            this.setLayout(groupLayout);
            this.setGoodsNameCombox();
            this.setwareNameCombox();
            setVisible(true);
        }

        private void setGoodsNameCombox() {
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsList(new goods());
            for(goods g:goods) {

                exgoodsnameBox.addItem(g.getName());
            }
            goodsDao.closeDao();
        }


        private void setwareNameCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getwarehouselist(new goods());
            for(goods g:goods) {
                ex_warehouseBox.addItem(g.getWarehouse_name());
            }
            goodsDao.closeDao();

        }

        private void setex_warehouseTable(in_warehouse in_wareh){
            DefaultTableModel dft = (DefaultTableModel)ex_warehouseTable.getModel();
            dft.setRowCount(0);

            in_warehouseDao in_wareDao = new in_warehouseDao();
            java.util.List<in_warehouse> in_wareList = in_wareDao.getin_warehouseList(in_wareh);
            for (in_warehouse g : in_wareList) {
                Vector v = new Vector();
                v.add(g.getGoodsName());
                v.add(g.getGoodsSort());
                v.add(g.getNumber());
                v.add(g.getGoodsinprice());
                v.add(g.getWarehouseName());
                v.add(g.getIN_time());
                dft.addRow(v);
            }
        }

        protected void selectedTableRow(MouseEvent e){

        }

    }

    private class internal_JPanel extends JPanel{
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;

        private JLabel exgoodsnameLabel;
        private JLabel exgoodstotalnumberLabel;
        private JLabel exrecordLabel;
        private JLabel suppliernameLabel;

        private JComboBox exgoodsnameBox;
        private JComboBox suppliernameBox;
        private JTextField exgoodstotalnumberField;

        private JScrollPane scrollPane;
        private JTable ex_warehouseTable;

        private JButton checkButton;
        private JButton statisticsButton;
        private JButton cancelButton;
        private JButton refreshButton;

        private JPanel LeftPanel;
        private JPanel RightPanel;

        public internal_JPanel(){
            setBackground(Color.white);
            background = new ImageIcon("F://Code_document//store_m//Backgroundpic.jpg");
            labelbackground = new JLabel(background);
            labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
            myBackgroundPanel = new JPanel();
            myBackgroundPanel.setBackground(Color.WHITE);
            myBackgroundPanel.add(labelbackground);


            exTitleLabel = new JLabel("操作面板");
            exTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            exgoodsnameLabel = new JLabel("物资名称：");
            exgoodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            suppliernameLabel = new JLabel("供应商：");
            suppliernameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodstotalnumberLabel = new JLabel("总数量：      ");
            exgoodstotalnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exrecordLabel = new JLabel("采购记录");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));


            exgoodsnameBox = new JComboBox();
            exgoodstotalnumberField = new JTextField();
            exgoodstotalnumberField.setEditable(true);
            suppliernameBox = new JComboBox();

            ex_warehouseTable = new JTable();
            ex_warehouseTable.setBackground(Color.WHITE);
            ex_warehouseTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    selectedTableRow(me);
                }
            });
            ex_warehouseTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "物资名称", "供应商名称","数量","是否确认"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false, false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            ex_warehouseTable.getColumnModel().getColumn(3).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(ex_warehouseTable);
            setTable(new supply());


            checkButton = new JButton("查询");
            checkButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            statisticsButton = new JButton("统计");
            statisticsButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            cancelButton = new JButton("取消");
            cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton = new JButton("刷新");
            refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));

            LeftPanel = new JPanel();/*左页面板*/
            GroupLayout titlegroupLayout = new GroupLayout(LeftPanel);
            titlegroupLayout.setHorizontalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(100)
                                    .addComponent(exTitleLabel)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsnameLabel,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )

                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(suppliernameLabel,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(suppliernameBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodstotalnumberLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodstotalnumberField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(checkButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(statisticsButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(exTitleLabel,GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsnameLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(suppliernameLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalnumberLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(45)
                                    .addGroup(titlegroupLayout.createParallelGroup()
                                            .addComponent(checkButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(statisticsButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    )
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(46)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(suppliernameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodstotalnumberField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    )
            );
            LeftPanel.setLayout(titlegroupLayout);
            LeftPanel.setBackground(Color.WHITE);

            RightPanel = new JPanel();
            GroupLayout rightPanelgroupLayout = new GroupLayout(RightPanel);
            rightPanelgroupLayout.setHorizontalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(350)
                                    .addComponent(exrecordLabel)
                                    .addGap(150)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
                            )
            );
            rightPanelgroupLayout.setVerticalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(exrecordLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(23)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            )
            );
            RightPanel.setLayout(rightPanelgroupLayout);
            RightPanel.setBackground(Color.white);

            GroupLayout groupLayout = new GroupLayout(this);
            groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(myBackgroundPanel)
                            )
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(24)
                                    .addComponent(LeftPanel)
                                    .addGap(32)
                                    .addComponent(RightPanel)
                            )
            );
            groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup()
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(myBackgroundPanel,GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LeftPanel)
                            )
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(188)
                                    .addComponent(RightPanel)
                            )
            );
            this.setLayout(groupLayout);
            this.setGoodsNameCombox();
            this.setsupplierCombox();
            setVisible(true);
        }

        private void setGoodsNameCombox() {
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsList(new goods());
            for(goods g:goods) {

                exgoodsnameBox.addItem(g.getName());
            }
            goodsDao.closeDao();
        }

        private void setsupplierCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getsuppliernameList(new goods());
            for(goods g:goods) {
                suppliernameBox.addItem(g.getSupplier_name());
            }
            goodsDao.closeDao();
        }

        private void setTable(supply supply){
            DefaultTableModel dft = (DefaultTableModel)ex_warehouseTable.getModel();
            dft.setRowCount(0);

            supplyDao supplyDao = new supplyDao();
            List<supply> supplyList = supplyDao.getStatisticssupplyList(supply);
            for (supply g : supplyList) {
                Vector v = new Vector();
                v.add(g.getGoodsname());
                v.add(g.getsupplierName());
                v.add(g.getNumber());
                if(g.getconfirm())
                {
                    v.add("是");
                }else{
                    v.add("否");
                }

                dft.addRow(v);
            }
        }

        protected void selectedTableRow(MouseEvent e){

        }
    }

}
