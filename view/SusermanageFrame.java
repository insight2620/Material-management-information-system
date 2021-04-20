package store_ma.view;


import store_ma.dao.UserDao;
import store_ma.model.Users;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

public class SusermanageFrame extends JFrame {
    //static Icon icon=new ImageIcon("ai.jpg");//要显示的图标
    ImageIcon background;
    //Image background;
    JLabel labelbackground;
    JPanel myBackgroundPanel;

    /*按钮*/
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JLabel authorityLabel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField passwordField;
    private JComboBox authorityBox;
    private JButton registerButton;
    private JButton deleteButton;
    private JButton correctButton;
    private JScrollPane scrollPane;
    private JPanel LeftPanel;
    private JTable table;
    int row=-1;//用于确定点击注销按钮时是否选中了某一行
    int id2=-1;//用于确定要注销的用户的id

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SusermanageFrame frame = new SusermanageFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public SusermanageFrame() {
        setTitle("用户管理");
        setResizable(false);
        setBounds(140, 80, 1213, 750);

        /*背景图的获取*/
        //this.getContentPane().add(new   MPanel());
        //this.getContentPane().add(new IPanel());
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

        idLabel        = new JLabel("ID:");
        idLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        nameLabel      = new JLabel("name:");
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        passwordLabel  = new JLabel("password:");
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        authorityLabel = new JLabel("authority:");
        authorityLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));

        idField = new JTextField();
        nameField = new JTextField();
        passwordField = new JTextField();
        authorityBox = new JComboBox();
        authorityBox.setModel(new DefaultComboBoxModel(new String[] {
                "1(系统管理员)","2(采购权限)","3(销售权限)","4(查看统计信息权限)"
        }));

        registerButton = new JButton("注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UserDao userDao = new UserDao();
                    String check;
                    String id = idField.getText();
                    int id1 = Integer.parseInt(id);
                    String name = nameField.getText();
                    String password = passwordField.getText();
                    int password1 = Integer.parseInt(password);//取整数
                    int m = authorityBox.getSelectedIndex();//获取组合框的选择
                    int authority = m + 1;
                    userDao.register(id1, name, password1, authority);
                    JOptionPane.showMessageDialog(null, "注册成功！");
                    setTable_user(new Users());
                }catch (NumberFormatException n) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "请输入要注册的用户信息！");
                }
            }
        });

        deleteButton = new JButton("注销");
        deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDao userDao = new UserDao();
                if(row==-1){ JOptionPane.showMessageDialog(null, "请在表中选择要注销的用户");}
                else{
                    userDao.delete(id2);
                    JOptionPane.showMessageDialog(null, "注销成功！");
                    setTable_user(new Users());
                    row=-1;
                }
            }
        });

        correctButton = new JButton("修改");
        correctButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        correctButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    UserDao userDao = new UserDao();
                    userDao.delete1(id2);
                    String id = idField.getText();
                    int id1 = Integer.parseInt(id);
                    String name = nameField.getText();
                    String password = passwordField.getText();
                    int password1 = Integer.parseInt(password);//取整数
                    int m = authorityBox.getSelectedIndex();//获取组合框的选择
                    int authority = m + 1;
                    userDao.register(id1, name, password1, authority);
                    JOptionPane.showMessageDialog(null, "修改成功！");
                    setTable_user(new Users());
            }
        });
        LeftPanel = new JPanel();
        LeftPanel.setBackground(Color.WHITE);

        /*表*/
        table = new JTable();
        table.setBackground(Color.WHITE);
        //表的鼠标事件监听
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                row = table.getSelectedRow();
                String id=table.getValueAt(row, 0).toString();
                String name=table.getValueAt(row, 1).toString();
                String password=table.getValueAt(row, 2).toString();
                String authority=table.getValueAt(row, 3).toString();
                id2=Integer.parseInt(id);//id2用于注销
                idField.setText(id);
                nameField.setText(name);
                passwordField.setText(password);
                int authority1=Integer.parseInt(authority);//权限转换成整数
                authorityBox.setSelectedIndex(authority1-1);
            }
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "id", "名字", "密码", "权限"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false, true, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(3).setPreferredWidth(98);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        setTable_user(new Users());


        /*界面设计*/
        GroupLayout groupLayout = new GroupLayout(LeftPanel);
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(32)
                        .addGroup(groupLayout.createParallelGroup()
                                .addComponent(idLabel,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addComponent(passwordLabel,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addComponent(authorityLabel,GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                .addComponent(registerButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addComponent(deleteButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addComponent(correctButton,GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        )
                .addGap(10)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(idField,GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameField,GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField,GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                        .addComponent(authorityBox,GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(12)
                                .addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(authorityLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addComponent(registerButton)
                                .addGap(12)
                                .addComponent(deleteButton)
                                .addGap(12)
                                .addComponent(correctButton)
                        )

                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(12)
                                .addComponent(idField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addGap(12)
                                .addComponent(authorityBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        )
        );
        LeftPanel.setLayout(groupLayout);

        /*整个界面的布局*/
        GroupLayout MaingroupLayout = new GroupLayout(getContentPane());
        MaingroupLayout.setHorizontalGroup(
                MaingroupLayout.createParallelGroup()
                        .addGroup(MaingroupLayout.createSequentialGroup()
                                .addComponent(myBackgroundPanel,GroupLayout.PREFERRED_SIZE, 840, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LeftPanel,GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                //,GroupLayout.PREFERRED_SIZE, 647, GroupLayout.PREFERRED_SIZE
                        ).addGroup(MaingroupLayout.createSequentialGroup()
                        .addGap(64)
                        .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
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
        setVisible(true);
    }


    //将数据库里的数据放到表里
    private void setTable_user(Users user){
        DefaultTableModel dft = (DefaultTableModel)table.getModel();
        dft.setRowCount(0);
        UserDao userDao = new UserDao();
        List<Users> UserList = userDao.getUsersList(user);
        for (Users u : UserList) {
            Vector v = new Vector();
            v.add(u.getId());
            v.add(u.getName());
            v.add(u.getPassword());
            v.add(u.getAuthority());
            dft.addRow(v);
        }
    }
}
