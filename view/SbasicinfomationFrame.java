package store_ma.view;

import store_ma.dao.GoodsDao;
import store_ma.dao.supplierDao;
import store_ma.dao.warehouseDao;
import store_ma.model.goods;
import store_ma.model.supplier;
import store_ma.model.warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class SbasicinfomationFrame extends JFrame {
    private JTabbedPane Tab;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SbasicinfomationFrame frame = new SbasicinfomationFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public SbasicinfomationFrame(){
        setTitle("基础信息管理");
        setResizable(false);
        setBounds(140, 80, 1213, 750);

        /*界面布局*/
        this.getContentPane().add(this.Tab = new JTabbedPane());
        this.setVisible(true);
        Tab.setFont(new Font("隶书", Font.BOLD, 24));
        Tab.addTab("仓库信息",new SbasicinfomationFrame.ex_JPanel());
        Tab.addTab("供应商信息",new SbasicinfomationFrame.in_JPanel());
        Tab.addTab("物资信息",new SbasicinfomationFrame.internal_JPanel());
    }

    //仓库信息管理
    private class ex_JPanel extends JPanel{
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;
        private JLabel exrecordLabel;
        private JLabel warehouseLabel;
        private JLabel warehouseManagerLabel;
        private JLabel areaLabel;
        private JTextField warehouseId;
        private JTextField warehouseName;
        private JTextField warehouseArea;
        private JScrollPane scrollPane;
        private JTable warehouseTable;
        private JButton addButton;
        private JButton correctButton;
        private JButton deleteButton;
        private JPanel LeftPanel;
        private JPanel RightPanel;
        int row=-1;//用于确定点击注销按钮时是否选中了某一行
        int id2=-1;//用于确定要注销的用户的id

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
            exrecordLabel = new JLabel("仓库信息");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));

            warehouseLabel = new JLabel("仓库ID：");
            warehouseLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            warehouseManagerLabel = new JLabel("仓库名称：");
            warehouseManagerLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            areaLabel = new JLabel("仓库地址：");
            areaLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );

            warehouseId = new JTextField();
            warehouseName = new JTextField();
            warehouseArea = new JTextField();

            warehouseTable = new JTable();
            warehouseTable.setBackground(Color.WHITE);
            warehouseTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    row = warehouseTable.getSelectedRow();
                    String id=warehouseTable.getValueAt(row, 0).toString();
                    String name=warehouseTable.getValueAt(row, 1).toString();
                    String area=warehouseTable.getValueAt(row, 2).toString();
                    id2=Integer.parseInt(id);//id2用于注销
                    warehouseId.setText(id);
                    warehouseName.setText(name);
                    warehouseArea.setText(area);
                }
            });
            warehouseTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "仓库ID","仓库名称","仓库地址"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false
                };
                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            warehouseTable.getColumnModel().getColumn(2).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(warehouseTable);
            setwarehouseTable(new warehouse());

            addButton = new JButton("添加");
            addButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        warehouseDao warehouseDao = new warehouseDao();
                        String id = warehouseId.getText();
                        int id1 = Integer.parseInt(id);
                        String name = warehouseName.getText();
                        String area = warehouseArea.getText();
                        warehouseDao.insert(id1, name,area);
                        JOptionPane.showMessageDialog(null, "添加成功！");
                        setwarehouseTable(new warehouse());
                    }catch (NumberFormatException n) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "请输入要添加的仓库信息！");
                    }
                }
            });
            correctButton = new JButton("修改");
            correctButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            correctButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    warehouseDao warehouseDao = new warehouseDao();
                    warehouseDao.delete1(id2);
                    String id = warehouseId.getText();
                    int id1 = Integer.parseInt(id);
                    String name = warehouseName.getText();
                    String area = warehouseArea.getText();
                    warehouseDao.insert(id1, name,area);
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    setwarehouseTable(new warehouse());
                }
            });
            deleteButton = new JButton("删除");
            deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    warehouseDao warehouseDao = new warehouseDao();
                    if(row==-1){ JOptionPane.showMessageDialog(null, "请在表中选择要删除的项");}
                    else{
                        warehouseDao.delete(id2);
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        setwarehouseTable(new warehouse());
                        row=-1;
                    }
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
                                    .addComponent(warehouseLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(warehouseId,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(warehouseManagerLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(warehouseName,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(areaLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(warehouseArea,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(addButton,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE,75,GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(exTitleLabel,GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(warehouseLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(warehouseManagerLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(areaLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addGroup(titlegroupLayout.createParallelGroup()
                                            .addComponent(addButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    )
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(59)
                                    .addComponent(warehouseId, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(warehouseName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(warehouseArea, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                            )
            );
            LeftPanel.setLayout(titlegroupLayout);
            LeftPanel.setBackground(Color.WHITE);

            RightPanel = new JPanel();/*右页面板*/
            GroupLayout rightPanelgroupLayout = new GroupLayout(RightPanel);
            rightPanelgroupLayout.setHorizontalGroup(
                    rightPanelgroupLayout.createParallelGroup()
                            .addGroup(rightPanelgroupLayout.createSequentialGroup()
                                    .addGap(350)
                                    .addComponent(exrecordLabel)
                                    .addGap(150)
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
            setVisible(true);
        }

        private void setwarehouseTable(warehouse wareh){
            DefaultTableModel dft = (DefaultTableModel) warehouseTable.getModel();
            dft.setRowCount(0);
            warehouseDao warehouseDao = new warehouseDao();
            List<warehouse> warehList = warehouseDao.getwarehouseList(wareh);
            for (warehouse e : warehList) {
                Vector v = new Vector();
                v.add(e.getId());
                v.add(e.getName());
                v.add(e.getArea());
                dft.addRow(v);
            }
        }
    }
    //供应商信息管理
    private class in_JPanel extends JPanel{
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;
        private JLabel exrecordLabel;
        private JLabel supplierIDLabel;
        private JLabel suppliernameLabel;
        private JLabel supplierAddressLabel;
        private JLabel supplierPhoneLabel;
        private JTextField supplierIDField;
        private JTextField suppliernameField;
        private JTextField supplierAddressField;
        private JTextField supplierPhoneField;
        private JScrollPane scrollPane;
        private JTable supplierTable;
        private JButton addButton;
        private JButton correctButton;
        private JButton deleteButton;
        private JPanel LeftPanel;
        private JPanel RightPanel;
        int row=-1;//用于确定点击注销按钮时是否选中了某一行
        int id2=-1;//用于确定要注销的用户的id

        public in_JPanel(){
            setBackground(Color.white);
            background = new ImageIcon("C://Users//25//Desktop//快捷方式//数据库课设//最终3//store_m//Backgroundpic.jpg");
            labelbackground = new JLabel(background);
            labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
            myBackgroundPanel = new JPanel();
            myBackgroundPanel.setBackground(Color.WHITE);
            myBackgroundPanel.add(labelbackground);

            exTitleLabel = new JLabel("操作面板");
            exTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            exrecordLabel = new JLabel("供应商信息");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));

            supplierIDLabel = new JLabel("供应商ID：");
            supplierIDLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            suppliernameLabel = new JLabel("供应商名称：");
            suppliernameLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            supplierAddressLabel = new JLabel("供应商地址：");
            supplierAddressLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            supplierPhoneLabel = new JLabel("供应商电话：");
            supplierPhoneLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );

            supplierIDField = new JTextField();
            suppliernameField = new JTextField();
            supplierAddressField = new JTextField();
            supplierPhoneField = new JTextField();

            supplierTable = new JTable();
            supplierTable.setBackground(Color.WHITE);
            supplierTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    row = supplierTable.getSelectedRow();
                    String id=supplierTable.getValueAt(row, 0).toString();
                    String name=supplierTable.getValueAt(row, 1).toString();
                    String address=supplierTable.getValueAt(row, 2).toString();
                    String phone=supplierTable.getValueAt(row, 3).toString();
                    id2=Integer.parseInt(id);//id2用于注销

                    supplierIDField.setText(id);
                    suppliernameField.setText(name);
                    supplierAddressField.setText(address);
                    supplierPhoneField.setText(phone);
                }
            });
            supplierTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "供应商ID","供应商名称","供应商地址","供应商电话"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            supplierTable.getColumnModel().getColumn(2).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(supplierTable);
            setsupplierTable(new supplier());

            addButton = new JButton("添加");
            addButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        supplierDao supplierDao = new supplierDao();
                        String id = supplierIDField.getText();
                        String name = suppliernameField.getText();
                        String address = supplierAddressField.getText();
                        String phone = supplierPhoneField.getText();
                        int id1 = Integer.parseInt(id);
                        int phone1 = Integer.parseInt(phone);
                        supplierDao.insert(id1, name,address,phone1);

                        JOptionPane.showMessageDialog(null, "添加成功！");
                        setsupplierTable(new supplier());
                    }catch (NumberFormatException n) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "请输入要添加的供应商信息！");
                    }
                }
            });
            correctButton = new JButton("修改");
            correctButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            correctButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    supplierDao supplierDao = new supplierDao();
                    supplierDao.delete1(id2);
                    String id = supplierIDField.getText();
                    String name = suppliernameField.getText();
                    String address = supplierAddressField.getText();
                    String phone = supplierPhoneField.getText();
                    int id1 = Integer.parseInt(id);
                    int phone1 = Integer.parseInt(phone);

                    supplierDao.insert(id1, name,address,phone1);
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    setsupplierTable(new supplier());
                }
            });
            deleteButton = new JButton("删除");
            deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    supplierDao supplierDao = new supplierDao();
                    if(row==-1){ JOptionPane.showMessageDialog(null, "请在表中选择要删除的项");}
                    else{
                        supplierDao.delete(id2);
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        setsupplierTable(new supplier());
                        row=-1;
                    }
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
                                    .addComponent(suppliernameLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(suppliernameField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(supplierIDLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(supplierIDField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(supplierAddressLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(supplierAddressField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(supplierPhoneLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(supplierPhoneField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(addButton,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE,75,GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(//纵向
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(exTitleLabel,GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierIDLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(suppliernameLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierAddressLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierPhoneLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)
                                    .addGroup(titlegroupLayout.createParallelGroup()
                                            .addComponent(addButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    )

                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(59)
                                    .addComponent(supplierIDField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(suppliernameField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierAddressField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(23)
                                    .addComponent(supplierPhoneField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                    .addGap(85)

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
            setVisible(true);
        }
        private void setsupplierTable(supplier sup){
            DefaultTableModel dft = (DefaultTableModel) supplierTable.getModel();
            dft.setRowCount(0);
            supplierDao supplierDao = new supplierDao();
            java.util.List<supplier> supplierList = supplierDao.getsupplierList(sup);
            for (supplier s : supplierList) {
                Vector v = new Vector();
                v.add(s.getSupplierId());
                v.add(s.getSupplierName());
                v.add(s.getSupplierAddress());
                v.add(s.getSupplierPhone());
                dft.addRow(v);
            }
        }
    }
    //物资信息管理
    private class internal_JPanel extends JPanel{
        private ImageIcon background;
        private JLabel labelbackground;
        private JPanel myBackgroundPanel;
        private JLabel exTitleLabel;
        private JLabel exrecordLabel;
        private JLabel goodsIDLabel;
        private JLabel goodsnameLabel;
        private JLabel goodssortLabel;
        private JLabel goodsnumberLabel;
        private JLabel goodsinputPriceLabel;
        private JLabel goodsoutputPriceLabel;
        private JLabel goodsdateLabel;
        private JLabel warehousenameLabel;
        private JLabel suppliernameLabel;
        private JTextField goodsIDField;
        private JTextField goodsnameField;
        private JTextField goodssortField;
        private JTextField goodsnumberField;
        private JTextField goodsinputPriceField;
        private JTextField goodsoutputPriceField;
        private JTextField goodsdateField;
        private JTextField warehousenameField;
        private JTextField suppliernameField;
        private JScrollPane scrollPane;
        private JTable goodsTable;
        private JButton addButton;
        private JButton correctButton;
        private JButton deleteButton;
        private JPanel LeftPanel;
        private JPanel RightPanel;
        int row=-1;//用于确定点击注销按钮时是否选中了某一行
        int id2=-1;//用于确定要注销的用户的id

        public internal_JPanel(){
            setBackground(Color.white);
            background = new ImageIcon("C://Users//25//Desktop//快捷方式//数据库课设//最终3//store_m//Backgroundpic.jpg");
            labelbackground = new JLabel(background);
            labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
            myBackgroundPanel = new JPanel();
            myBackgroundPanel.setBackground(Color.WHITE);
            myBackgroundPanel.add(labelbackground);

            exTitleLabel = new JLabel("操作面板");
            exTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            exrecordLabel = new JLabel("物资信息");
            exrecordLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));

            goodsIDLabel = new JLabel("物资ID:");
            goodsIDLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            goodsnameLabel = new JLabel("物资名称：");
            goodsnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 21));
            goodssortLabel = new JLabel("物资类型：");
            goodssortLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            goodsnumberLabel = new JLabel("物资数量：");
            goodsnumberLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            goodsinputPriceLabel  = new JLabel("物资进价");
            goodsinputPriceLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            goodsoutputPriceLabel = new JLabel("物资售价");
            goodsoutputPriceLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            goodsdateLabel= new JLabel("进货日期");
            goodsdateLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            warehousenameLabel= new JLabel("所在仓库");
            warehousenameLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );
            suppliernameLabel= new JLabel("供应商");
            suppliernameLabel.setFont(new Font("微软雅黑", Font.BOLD, 21) );

            goodsIDField = new JTextField();
            goodsnameField = new JTextField();
            goodssortField = new JTextField();
            goodsnumberField = new JTextField();
            goodsinputPriceField = new JTextField();
            goodsoutputPriceField = new JTextField();
            goodsdateField = new JTextField();
            warehousenameField = new JTextField();
            suppliernameField = new JTextField();

            goodsTable = new JTable();
            goodsTable.setBackground(Color.WHITE);
            goodsTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    row = goodsTable.getSelectedRow();
                    String id=goodsTable.getValueAt(row, 0).toString();
                    String name1=goodsTable.getValueAt(row, 1).toString();
                    String sort=goodsTable.getValueAt(row, 2).toString();
                    String number=goodsTable.getValueAt(row, 3).toString();
                    String inputPrice=goodsTable.getValueAt(row, 4).toString();
                    String outputPrice=goodsTable.getValueAt(row, 5).toString();
                    String date_of_entry=goodsTable.getValueAt(row, 6).toString();
                    String warehouse_id=goodsTable.getValueAt(row, 7).toString();
                    String supplier_id=goodsTable.getValueAt(row, 8).toString();
                    id2=Integer.parseInt(id);//id2用于注销

                    goodsIDField.setText(id);
                    goodsnameField.setText(name1);
                    goodssortField.setText(sort);
                    goodsnumberField.setText(number);
                    goodsinputPriceField.setText(inputPrice);
                    goodsoutputPriceField.setText(outputPrice);
                    goodsdateField.setText(date_of_entry);
                    warehousenameField.setText(warehouse_id);
                    suppliernameField.setText(supplier_id);
                }
            });
            goodsTable.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                            "编号","名称", "分类", "数量", "进价", "出价", "进货日期","所在仓库","供应商"
                    }
            ) {
                boolean[] columnEditables = new boolean[]{
                        false, true, false, false, false, false, false
                };

                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            goodsTable.getColumnModel().getColumn(2).setPreferredWidth(98);
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(goodsTable);
            setTable(new goods());

            addButton = new JButton("添加");
            addButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        GoodsDao GoodsDao = new GoodsDao();
                        goods goods =new goods();
                        goods.setId(Integer.parseInt(goodsIDField.getText()));
                        goods.setName(goodsnameField.getText());
                        goods.setSort(goodssortField.getText());
                        goods.setNumber(Integer.parseInt(goodsnumberField.getText()));
                        goods.setInputPrice(Double.parseDouble(goodsinputPriceField.getText()));
                        goods.setOutputPrice(Double.parseDouble(goodsoutputPriceField.getText()));
                        //SimpleDateFormat ft= new SimpleDateFormat("yyyy-mm-dd");
                        //String time=goodsdateField.getText();
                        //Date f=ft.parse(time);
                        //goods.setDateTime((java.sql.Date) f);
                        goods.setDateTime(null);
                        goods.setWarehouse_id(Integer.parseInt( warehousenameField.getText()));
                        goods.setSupplier_id(Integer.parseInt(suppliernameField.getText()));

                        GoodsDao.addGoods(goods);
                        JOptionPane.showMessageDialog(null, "添加成功！");
                        setTable(new goods());
                    }catch (NumberFormatException n) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "供应商和仓库请使用代号！");
                    }
                }
            });
            correctButton = new JButton("修改");
            correctButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            correctButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                    GoodsDao GoodsDao = new GoodsDao();
                    goods goods =new goods();
                    goods.setId(Integer.parseInt(goodsIDField.getText()));
                    goods.setName(goodsnameField.getText());
                    goods.setSort(goodssortField.getText());
                    goods.setNumber(Integer.parseInt(goodsnumberField.getText()));
                    goods.setInputPrice(Double.parseDouble(goodsinputPriceField.getText()));
                    goods.setOutputPrice(Double.parseDouble(goodsoutputPriceField.getText()));
                    //SimpleDateFormat ft= new SimpleDateFormat("yyyy-mm-dd");
                    //String time=goodsdateField.getText();
                    //Date f=ft.parse(time);
                    //goods.setDateTime((java.sql.Date) f);
                    goods.setDateTime(null);
                    goods.setWarehouse_id(Integer.parseInt(warehousenameField.getText()));
                    goods.setSupplier_id(Integer.parseInt(suppliernameField.getText()));

                    GoodsDao.delete(Integer.parseInt(goodsIDField.getText()));
                    GoodsDao.addGoods(goods);
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    setTable(new goods());
                    }catch (NumberFormatException n) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(null, "供应商和仓库请使用代号！");
                     }
                }
            });
            deleteButton = new JButton("删除");
            deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GoodsDao GoodsDao = new GoodsDao();
                    if(row==-1){ JOptionPane.showMessageDialog(null, "请在表中选择要删除的项");}
                    else{
                        GoodsDao.delete(id2);
                        JOptionPane.showMessageDialog(null, "删除成功！");
                        setTable(new goods());
                        row=-1;
                    }
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
                                    .addComponent(goodsIDLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodsIDField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(goodsnameLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodsnameField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(goodssortLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodssortField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(goodsnumberLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodsnumberField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(goodsinputPriceLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodsinputPriceField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(goodsoutputPriceLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodsoutputPriceField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(goodsdateLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(goodsdateField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(warehousenameLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(warehousenameField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(suppliernameLabel,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(suppliernameField,GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(32)
                                    .addComponent(addButton,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE,75,GroupLayout.PREFERRED_SIZE)
                            )
            );
            titlegroupLayout.setVerticalGroup(//纵向
                    titlegroupLayout.createParallelGroup()
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addComponent(exTitleLabel,GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(15)
                                    .addComponent(goodsIDLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsnameLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodssortLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsnumberLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsinputPriceLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsoutputPriceLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsdateLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(warehousenameLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(suppliernameLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)

                                    .addGap(10)
                                    .addGroup(titlegroupLayout.createParallelGroup()
                                            .addComponent(addButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                    )

                            )
                            .addGroup(titlegroupLayout.createSequentialGroup()
                                    .addGap(47)
                                    .addComponent(goodsIDField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsnameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodssortField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsnumberField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsinputPriceField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(15)
                                    .addComponent(goodsoutputPriceField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(goodsdateField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(warehousenameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(suppliernameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20)
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
            setVisible(true);
        }

        //将数据库里的数据放到表里
        private void setTable(goods goods){
            DefaultTableModel dft = (DefaultTableModel) goodsTable.getModel();
            dft.setRowCount(0);
            GoodsDao goodsDao = new GoodsDao();
            List<goods> goodsList = goodsDao.getMainFrameGoodsList(goods);
            for (goods g : goodsList) {
                Vector v = new Vector();
                v.add(g.getId());
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
}
