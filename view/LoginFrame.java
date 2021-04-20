package store_ma.view;


import store_ma.util.StringUtil;
import store_ma.view.MainFrame;
import store_ma.dao.UserDao;
import store_ma.model.Users;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField userNameField;
    private JPasswordField passwordField;
    ImageIcon background;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginFrame() {
        setResizable(false);
        setTitle("仓库信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 565, 402);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        background = new ImageIcon("F://Code_document//store_m//littleback.jpg");

        JLabel lblNewLabel = new JLabel(background);
        lblNewLabel.setBounds(134, 23,296, 107);

        lblNewLabel.setFont(new Font("隶书", Font.BOLD, 32));

        JLabel lblNewLabel_1 = new JLabel("用户名：");
        lblNewLabel_1.setBounds(131, 131, 58, 16);
        lblNewLabel_1.setFont(new Font("隶书", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("密码：");
        lblNewLabel_2.setBounds(131, 168, 58, 16);
        lblNewLabel_2.setFont(new Font("隶书", Font.PLAIN, 14));

        userNameField = new JTextField();
        userNameField.setBounds(199, 129, 189, 21);
        userNameField.setBackground(Color.LIGHT_GRAY);
        userNameField.setColumns(10);
        userNameField.setText("段鑫绘");

        passwordField = new JPasswordField();
        passwordField.setBounds(199, 168, 189, 21);
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setText("123");


        JButton loginButton = new JButton("确定");
        loginButton.setBounds(100, 271, 97, 25);
        loginButton.setFont(new Font("隶书", Font.PLAIN, 14));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction(e);
            }

        });

        JButton resetButton = new JButton("取消");
        resetButton.setBounds(400, 271, 97, 25);
        resetButton.setFont(new Font("隶书", Font.PLAIN, 14));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                resetValue(ae);
            }
        });

        JButton chgButton = new JButton("修改密码");
        chgButton.setBounds(250, 271, 97, 25);
        chgButton.setFont(new Font("隶书", Font.PLAIN, 14));
        chgButton.addActionListener(e -> {
            new changePw();
        });


        contentPane.setLayout(null);
        contentPane.add(lblNewLabel);
        contentPane.add(lblNewLabel_1);
        contentPane.add(lblNewLabel_2);
        contentPane.add(userNameField);
        contentPane.add(passwordField);
        //  contentPane.add(userTypeComboBox);
        contentPane.add(loginButton);
        contentPane.add(resetButton);
        contentPane.add(chgButton);
        contentPane.setBackground(Color.WHITE);
    }

    protected void loginAction(ActionEvent ae) {
        //从界面获取用户名和密码
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());
        String authorname = null;
        //进行向上转型
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(this,"用户名不能为空！");
            return;
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(this,"密码不能为空！");
            return;
        }



        Users UserTmp = null;
        //将获取到的密码和用户名设置到UserTmp中
        UserTmp =new Users();
        UserTmp.setName(userName);
        UserTmp.setPassword(password);

        UserDao Userdao = new UserDao();
        Users Userresult = Userdao.login(UserTmp);

        //一查询完就释放
        Userdao.closeDao();
        if(Userresult == null){
                JOptionPane.showMessageDialog(this,"用户名或密码错误！");
                this.resetValue(ae);
                return;
        }
        switch(Userresult.getAuthority())
        {
            case 1:
                authorname = "仓库管理员";
                break;
            case 2:
                authorname = "采购员";
                break;
            case 3:
                authorname = "销售员";
                break;
            case 4:
                authorname = "报表查询员";
                break;

        }
        JOptionPane.showMessageDialog(this,"欢迎"+authorname+Userresult.getName()+"登录系统");
        //销毁此窗口
        this.dispose();
        //跳转到主界面,并设置为可视
        store_ma.view.MainFrame mainFrame = new MainFrame(Userresult);
        mainFrame.setVisible(true);
    }


    protected void resetValue(ActionEvent ae) {
        userNameField.setText("");
        passwordField.setText("");
    }

}
