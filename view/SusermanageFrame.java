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
    //static Icon icon=new ImageIcon("ai.jpg");//Ҫ��ʾ��ͼ��
    ImageIcon background;
    //Image background;
    JLabel labelbackground;
    JPanel myBackgroundPanel;

    /*��ť*/
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
    int row=-1;//����ȷ�����ע����ťʱ�Ƿ�ѡ����ĳһ��
    int id2=-1;//����ȷ��Ҫע�����û���id

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
        setTitle("�û�����");
        setResizable(false);
        setBounds(140, 80, 1213, 750);

        /*����ͼ�Ļ�ȡ*/
        //this.getContentPane().add(new   MPanel());
        //this.getContentPane().add(new IPanel());
        background = new ImageIcon("C://Users//25//Desktop//��ݷ�ʽ//���ݿ����//����3//store_m//Backgroundpic.jpg");
        labelbackground = new JLabel(background);
        labelbackground.setBounds(0,0,background.getIconWidth(),background.getIconHeight());//background.getIconWidth(),background.getIconHeight());
        myBackgroundPanel = new JPanel();//����Ϊ���Լ���	//���ҵ��������Ϊ�������,myPanel��this.getContentPane()��ͬ
        myBackgroundPanel.setBackground(Color.WHITE);
        myBackgroundPanel.add(labelbackground);

        /*�������÷���*/
        //myBackgroundPanel.setOpaque(false);					//���ҵ��������Ϊ������
        //myBackgroundPanel.setLayout(new FlowLayout());		//���ҵ��������Ϊ��������
        //this.getLayeredPane().setLayout(null);		//�ѷֲ����Ĳ����ÿ�
        //this.getLayeredPane().add(labelbackground, new Integer(Integer.MIN_VALUE));		//�ѱ�ǩ��ӵ��ֲ�������ײ�
        //this.getContentPane().add(myBackgroundPanel);

        idLabel        = new JLabel("ID:");
        idLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
        nameLabel      = new JLabel("name:");
        nameLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
        passwordLabel  = new JLabel("password:");
        passwordLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));
        authorityLabel = new JLabel("authority:");
        authorityLabel.setFont(new Font("΢���ź�", Font.BOLD, 16));

        idField = new JTextField();
        nameField = new JTextField();
        passwordField = new JTextField();
        authorityBox = new JComboBox();
        authorityBox.setModel(new DefaultComboBoxModel(new String[] {
                "1(ϵͳ����Ա)","2(�ɹ�Ȩ��)","3(����Ȩ��)","4(�鿴ͳ����ϢȨ��)"
        }));

        registerButton = new JButton("ע��");
        registerButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
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
                    int password1 = Integer.parseInt(password);//ȡ����
                    int m = authorityBox.getSelectedIndex();//��ȡ��Ͽ��ѡ��
                    int authority = m + 1;
                    userDao.register(id1, name, password1, authority);
                    JOptionPane.showMessageDialog(null, "ע��ɹ���");
                    setTable_user(new Users());
                }catch (NumberFormatException n) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "������Ҫע����û���Ϣ��");
                }
            }
        });

        deleteButton = new JButton("ע��");
        deleteButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDao userDao = new UserDao();
                if(row==-1){ JOptionPane.showMessageDialog(null, "���ڱ���ѡ��Ҫע�����û�");}
                else{
                    userDao.delete(id2);
                    JOptionPane.showMessageDialog(null, "ע���ɹ���");
                    setTable_user(new Users());
                    row=-1;
                }
            }
        });

        correctButton = new JButton("�޸�");
        correctButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        correctButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    UserDao userDao = new UserDao();
                    userDao.delete1(id2);
                    String id = idField.getText();
                    int id1 = Integer.parseInt(id);
                    String name = nameField.getText();
                    String password = passwordField.getText();
                    int password1 = Integer.parseInt(password);//ȡ����
                    int m = authorityBox.getSelectedIndex();//��ȡ��Ͽ��ѡ��
                    int authority = m + 1;
                    userDao.register(id1, name, password1, authority);
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
                    setTable_user(new Users());
            }
        });
        LeftPanel = new JPanel();
        LeftPanel.setBackground(Color.WHITE);

        /*��*/
        table = new JTable();
        table.setBackground(Color.WHITE);
        //�������¼�����
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                row = table.getSelectedRow();
                String id=table.getValueAt(row, 0).toString();
                String name=table.getValueAt(row, 1).toString();
                String password=table.getValueAt(row, 2).toString();
                String authority=table.getValueAt(row, 3).toString();
                id2=Integer.parseInt(id);//id2����ע��
                idField.setText(id);
                nameField.setText(name);
                passwordField.setText(password);
                int authority1=Integer.parseInt(authority);//Ȩ��ת��������
                authorityBox.setSelectedIndex(authority1-1);
            }
        });
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "id", "����", "����", "Ȩ��"
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


        /*�������*/
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

        /*��������Ĳ���*/
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


    //�����ݿ�������ݷŵ�����
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
