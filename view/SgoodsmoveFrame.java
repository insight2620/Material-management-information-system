package store_ma.view;

import store_ma.dao.*;
import store_ma.model.ex_warehouse;
import store_ma.model.goods;
import store_ma.model.in_warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class SgoodsmoveFrame extends JFrame {

    private JTabbedPane Tab;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SgoodsmoveFrame frame = new SgoodsmoveFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SgoodsmoveFrame(){
        setTitle("仓库物资调度");
        setResizable(false);
        setBounds(140, 80, 1213, 750);

        /*界面布局*/
        this.getContentPane().add(this.Tab = new JTabbedPane());
        this.setVisible(true);
        Tab.setFont(new Font("隶书", Font.BOLD, 24));
        Tab.addTab("出库管理",new ex_JPanel());
        Tab.addTab("入库管理",new in_JPanel());
        Tab.addTab("内部管理",new internal_JPanel());
    }


    private class ex_JPanel extends JPanel{
        /*出库*/
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;
        private JLabel exgoodsnameLabel;
        private JLabel exgoodssortLabel;
        private JLabel exgoodsnumberLabel;
        private JLabel exgoodswarehouseLabel;
        private JLabel exrecordLabel;
        private JComboBox exgoodsnameBox;
        private JComboBox exgoodssortBox;
        private JTextField exgoodsnumberField;
        private JComboBox ex_warehouseBox;
        private JScrollPane scrollPane;
        private JTable ex_warehouseTable;
        private JButton exButton;
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


            exTitleLabel = new JLabel("销售单");
            exTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            exgoodsnameLabel = new JLabel("物资名称：");
            exgoodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodssortLabel = new JLabel("物资类型：");
            exgoodssortLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodsnumberLabel = new JLabel("物资数量：");
            exgoodsnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exgoodswarehouseLabel = new JLabel("出库仓库：");
            exgoodswarehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            exrecordLabel = new JLabel("出库记录");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));


            exgoodsnameBox = new JComboBox();
            exgoodssortBox = new JComboBox();
            exgoodsnumberField = new JTextField();
            ex_warehouseBox = new JComboBox();

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
                            "名称", "分类","数量","出价","所在仓库","出库日期"
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
            setex_warehouseTable(new ex_warehouse());


            exButton = new JButton("出库");
            exButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            exButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GoodsDao goodsdao = new GoodsDao();
                    int goodsID = goodsdao.getID(String.valueOf(exgoodsnameBox.getSelectedItem()));
                    //JOptionPane.showMessageDialog(null, String.valueOf(exgoodsnameBox.getSelectedItem()));
                    goodsdao.closeDao();
                    warehouseDao warehousedao = new warehouseDao();
                    int warehouseID = warehousedao.getID(String.valueOf(ex_warehouseBox.getSelectedItem()));
                    warehousedao.closeDao();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

                    ex_warehouseDao ex_wareDao = new ex_warehouseDao();
                    if(ex_wareDao.addex_warerecord(goodsID,warehouseID,Integer.parseInt(exgoodsnumberField.getText()),""+df.format(new Date())))
                    {JOptionPane.showMessageDialog(null, "出库成功！");}
                    ex_wareDao.closeDao();
                }
            });
            cancelButton = new JButton("取消");
            cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exgoodsnumberField.setText("");
                }
            });
            refreshButton = new JButton("刷新");
            refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setex_warehouseTable(new ex_warehouse());
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
                                    .addComponent(exgoodssortLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodssortBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodsnumberLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exgoodsnumberField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exgoodswarehouseLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ex_warehouseBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(exButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(exgoodssortLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsnumberLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodswarehouseLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addComponent(exButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(46)
                                    .addComponent(exgoodsnameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodssortBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(exgoodsnumberField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ex_warehouseBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
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
            this.setGoodsSortCombox();
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

        private void setGoodsSortCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsSortList(new goods());
            for(goods g:goods) {
                exgoodssortBox.addItem(g.getSort());
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
            List<ex_warehouse> ex_wareList = ex_wareDao.getex_warehouseList(ex_wareh);
            for (ex_warehouse g : ex_wareList) {
                Vector v = new Vector();
                v.add(g.getGoodsName());
                v.add(g.getGoodsSort());
                v.add(g.getNumber());
                v.add(g.getGoodsoutprice());
                v.add(g.getWarehouseName());
                v.add(g.getEX_time());
                dft.addRow(v);
            }
        }

        protected void selectedTableRow(MouseEvent e){

        }
    }

    private class in_JPanel extends JPanel{
        /*入库*/
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel inTitleLabel;
        private JLabel ingoodsnameLabel;
        private JLabel ingoodssortLabel;
        private JLabel ingoodsnumberLabel;
        private JLabel ingoodswarehouseLabel;
        private JLabel inrecordLabel;
        private JLabel supplierLabel;
        private JComboBox ingoodsnameBox;
        private JComboBox ingoodssortBox;
        private JTextField ingoodsnumberField;
        private JComboBox in_warehouseBox;
        private JComboBox supplierBox;
        private JScrollPane scrollPane;
        private JTable in_warehouseTable;
        private JButton inButton;
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


            inTitleLabel = new JLabel("采购单");
            inTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            ingoodsnameLabel = new JLabel("物资名称：");
            ingoodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            ingoodssortLabel = new JLabel("物资类型：");
            ingoodssortLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            ingoodsnumberLabel = new JLabel("物资数量：");
            ingoodsnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            ingoodswarehouseLabel = new JLabel("入库仓库：");
            ingoodswarehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            inrecordLabel = new JLabel("入库记录");
            inrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
            supplierLabel = new JLabel("供  应  商:");
            supplierLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));


            ingoodsnameBox = new JComboBox();
            ingoodssortBox = new JComboBox();
            ingoodsnumberField = new JTextField();
            in_warehouseBox = new JComboBox();
            supplierBox = new JComboBox();

            in_warehouseTable = new JTable();
            in_warehouseTable.setBackground(Color.WHITE);
            in_warehouseTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    selectedTableRow(me);
                }
            });
            in_warehouseTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "名称", "分类","数量","进价","入库仓库","入库日期","供应商"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false, false, false,false,false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            in_warehouseTable.getColumnModel().getColumn(5).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(in_warehouseTable);
            setex_warehouseTable(new in_warehouse());


            inButton = new JButton("入库");
            inButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            inButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GoodsDao goodsdao = new GoodsDao();
                    int goodsID = goodsdao.getID(String.valueOf(ingoodsnameBox.getSelectedItem()));
                    //JOptionPane.showMessageDialog(null, String.valueOf(exgoodsnameBox.getSelectedItem()));
                    goodsdao.closeDao();
                    warehouseDao warehousedao = new warehouseDao();
                    int warehouseID = warehousedao.getID(String.valueOf(in_warehouseBox.getSelectedItem()));
                    warehousedao.closeDao();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    supplierDao supplierdao = new supplierDao();
                    int supplierID = supplierdao.getID(String.valueOf(supplierBox.getSelectedItem()));
                    supplierdao.closeDao();

                    in_warehouseDao in_wareDao = new in_warehouseDao();
                    if(in_wareDao.addin_warerecord(goodsID,warehouseID,Integer.parseInt(ingoodsnumberField.getText()),""+df.format(new Date()),supplierID))
                    {JOptionPane.showMessageDialog(null, "入库成功！");}
                    in_wareDao.closeDao();
                }
            });
            cancelButton = new JButton("取消");
            cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ingoodsnumberField.setText("");
                }
            });
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
                                    .addComponent(inTitleLabel)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(ingoodsnameLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ingoodsnameBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(ingoodssortLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ingoodssortBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(ingoodsnumberLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ingoodsnumberField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(ingoodswarehouseLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(in_warehouseBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(supplierLabel)
                                    .addGap(19)
                                    .addComponent(supplierBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(inButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(inTitleLabel,GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodsnameLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodssortLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodsnumberLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodswarehouseLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addComponent(inButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(46)
                                    .addComponent(ingoodsnameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodssortBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodsnumberField,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(in_warehouseBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(inrecordLabel)
                                    .addGap(150)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
                            )
            );
            rightPanelgroupLayout.setVerticalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(inrecordLabel)
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
            this.setGoodsSortCombox();
            this.setwareNameCombox();
            this.setsupplierCombox();
            setVisible(true);
        }

        private void setex_warehouseTable(in_warehouse in_wareh){
            DefaultTableModel dft = (DefaultTableModel)in_warehouseTable.getModel();
            dft.setRowCount(0);

            in_warehouseDao in_wareDao = new in_warehouseDao();
            List<in_warehouse> in_wareList = in_wareDao.getin_warehouseList(in_wareh);
            for (in_warehouse g : in_wareList) {
                Vector v = new Vector();
                v.add(g.getGoodsName());
                v.add(g.getGoodsSort());
                v.add(g.getNumber());
                v.add(g.getGoodsinprice());
                v.add(g.getWarehouseName());
                v.add(g.getIN_time());
                v.add(g.getSupplierName());
                dft.addRow(v);
            }
        }

        private void setGoodsNameCombox() {
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsList(new goods());
            for(goods g:goods) {

                ingoodsnameBox.addItem(g.getName());
            }
            goodsDao.closeDao();
        }

        private void setGoodsSortCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsSortList(new goods());
            for(goods g:goods) {
                ingoodssortBox.addItem(g.getSort());
            }
            goodsDao.closeDao();

        }

        private void setwareNameCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getwarehouselist(new goods());
            for(goods g:goods) {
                in_warehouseBox.addItem(g.getWarehouse_name());
            }
            goodsDao.closeDao();

        }

        private void setsupplierCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getsuppliernameList(new goods());
            for(goods g:goods) {
                supplierBox.addItem(g.getSupplier_name());
            }
            goodsDao.closeDao();
        }

        protected void selectedTableRow(MouseEvent e){

        }

    }

    private class internal_JPanel extends JPanel{
        /*入库*/
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel inTitleLabel;
        private JLabel ingoodsnameLabel;
        private JLabel ingoodsnumberLabel;
        private JLabel ingoodswarehouseLabel;
        private JLabel localwarehouseLabel;
        private JLabel inrecordLabel;
        private JComboBox ingoodsnameBox;
        private JComboBox ingoodssortBox;
        private JComboBox localwarehouseBox;
        private JTextField ingoodsnumberField;
        private JComboBox in_warehouseBox;
        private JScrollPane scrollPane;
        private JTable in_warehouseTable;
        private JButton inButton;
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


            inTitleLabel = new JLabel("调度单");
            inTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            ingoodsnameLabel = new JLabel("物资名称：");
            ingoodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            ingoodsnumberLabel = new JLabel("物资数量：");
            ingoodsnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

            ingoodswarehouseLabel = new JLabel("目标仓库：");
            ingoodswarehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
            inrecordLabel = new JLabel("库存情况");
            inrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
            localwarehouseLabel = new JLabel("所在仓库：");
            localwarehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

            ingoodsnameBox = new JComboBox();
            ingoodsnameBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    goodsChanfeAct(ie);
                }
            });
            ingoodssortBox = new JComboBox();

            ingoodsnumberField = new JTextField();
            in_warehouseBox = new JComboBox();
            in_warehouseBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    //wareChanfeAct(ie);
                }
            });
            localwarehouseBox = new JComboBox();
            localwarehouseBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    //wareChanfeAct(ie);
                }
            });

            in_warehouseTable = new JTable();
            in_warehouseTable.setBackground(Color.WHITE);
            in_warehouseTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    selectedTableRow(me);
                }
            });
            in_warehouseTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "名称", "分类", "数量", "进价", "出价", "进货日期","所在仓库","供应商"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false, false, false,false,false,false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            in_warehouseTable.getColumnModel().getColumn(5).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(in_warehouseTable);
            setTable(new goods());


            inButton = new JButton("调度");
            inButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            inButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GoodsDao goodsdao = new GoodsDao();
                    int goodsID = goodsdao.getID(String.valueOf(ingoodsnameBox.getSelectedItem()));
                    //JOptionPane.showMessageDialog(null, String.valueOf(exgoodsnameBox.getSelectedItem()));
                    goodsdao.closeDao();
                    warehouseDao warehousedao = new warehouseDao();
                    int warehouseID = warehousedao.getID(String.valueOf(in_warehouseBox.getSelectedItem()));
                    warehousedao.closeDao();
                    JOptionPane.showMessageDialog(null, String.valueOf(goodsID));
                    GoodsDao goodsDao = new GoodsDao();
                    if(goodsDao.updatewarehouse(warehouseID,goodsID))
                    {JOptionPane.showMessageDialog(null, "调度成功！");}
                    goodsDao.closeDao();
                }
            });
            cancelButton = new JButton("取消");
            cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            refreshButton = new JButton("刷新");
            refreshButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTable(new goods());
                }
            });

            LeftPanel = new JPanel();/*左页面板*/
            GroupLayout titlegroupLayout = new GroupLayout(LeftPanel);
            titlegroupLayout.setHorizontalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(100)
                                    .addComponent(inTitleLabel)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(ingoodsnameLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ingoodsnameBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(localwarehouseLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(localwarehouseBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(ingoodswarehouseLabel)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(in_warehouseBox,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(inButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(inTitleLabel,GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodsnameLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(localwarehouseLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(ingoodswarehouseLabel,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addComponent(inButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(46)
                                    .addComponent(ingoodsnameBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(localwarehouseBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(in_warehouseBox,GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addComponent(cancelButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(inrecordLabel)
                                    .addGap(150)
                                    .addComponent(refreshButton,GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
                            )
            );
            rightPanelgroupLayout.setVerticalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addComponent(inrecordLabel)
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
            //this.initText();
            setVisible(true);
        }

        private void setTable(goods goods){
            DefaultTableModel dft = (DefaultTableModel)in_warehouseTable.getModel();
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

        protected void goodsChanfeAct(ItemEvent ie) {
            //会进行两次变化，第一次是取消选择的时候，第二次是重新选中的时候
            //而我们要的是选中时候
            if(ie.getStateChange() == ItemEvent.SELECTED) {
                this.initText();
            }
        }

        private void initText() {
            goods g = (goods)ingoodsnameBox.getSelectedItem();
            GoodsDao goodsDao = new GoodsDao();
            //inputPriceTextField.setText(new String(""+g.getInputPrice())); ;
            //outputGoodsPriceTextField.setText(new String(""+g.getOutputPrice()));
            goodsDao.closeDao();
        }

        private void setGoodsNameCombox() {
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getGoodsList(new goods());
            for(goods g:goods) {

                ingoodsnameBox.addItem(g);
            }
            goodsDao.closeDao();
        }

        private void setwareNameCombox(){
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goods = goodsDao.getwarehouselist(new goods());
            for(goods g:goods) {
                localwarehouseBox.addItem(g.getWarehouse_name());
                in_warehouseBox.addItem(g.getWarehouse_name());
            }
            goodsDao.closeDao();

        }

        protected void selectedTableRow(MouseEvent e){

        }

    }

}
