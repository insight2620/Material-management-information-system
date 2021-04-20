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
        setTitle("�ֿ���Ϣ����ϵͳ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 565, 402);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        background = new ImageIcon("F://Code_document//store_m//littleback.jpg");

        JLabel lblNewLabel = new JLabel(background);
        lblNewLabel.setBounds(134, 23,296, 107);

        lblNewLabel.setFont(new Font("����", Font.BOLD, 32));

        JLabel lblNewLabel_1 = new JLabel("�û�����");
        lblNewLabel_1.setBounds(131, 131, 58, 16);
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("���룺");
        lblNewLabel_2.setBounds(131, 168, 58, 16);
        lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 14));

        userNameField = new JTextField();
        userNameField.setBounds(199, 129, 189, 21);
        userNameField.setBackground(Color.LIGHT_GRAY);
        userNameField.setColumns(10);
        userNameField.setText("���λ�");

        passwordField = new JPasswordField();
        passwordField.setBounds(199, 168, 189, 21);
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setText("123");


        JButton loginButton = new JButton("ȷ��");
        loginButton.setBounds(100, 271, 97, 25);
        loginButton.setFont(new Font("����", Font.PLAIN, 14));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction(e);
            }

        });

        JButton resetButton = new JButton("ȡ��");
        resetButton.setBounds(400, 271, 97, 25);
        resetButton.setFont(new Font("����", Font.PLAIN, 14));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                resetValue(ae);
            }
        });

        JButton chgButton = new JButton("�޸�����");
        chgButton.setBounds(250, 271, 97, 25);
        chgButton.setFont(new Font("����", Font.PLAIN, 14));
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
        //�ӽ����ȡ�û���������
        String userName = userNameField.getText();
        String password = new String(passwordField.getPassword());
        String authorname = null;
        //��������ת��
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(this,"�û�������Ϊ�գ�");
            return;
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(this,"���벻��Ϊ�գ�");
            return;
        }



        Users UserTmp = null;
        //����ȡ����������û������õ�UserTmp��
        UserTmp =new Users();
        UserTmp.setName(userName);
        UserTmp.setPassword(password);

        UserDao Userdao = new UserDao();
        Users Userresult = Userdao.login(UserTmp);

        //һ��ѯ����ͷ�
        Userdao.closeDao();
        if(Userresult == null){
                JOptionPane.showMessageDialog(this,"�û������������");
                this.resetValue(ae);
                return;
        }
        switch(Userresult.getAuthority())
        {
            case 1:
                authorname = "�ֿ����Ա";
                break;
            case 2:
                authorname = "�ɹ�Ա";
                break;
            case 3:
                authorname = "����Ա";
                break;
            case 4:
                authorname = "�����ѯԱ";
                break;

        }
        JOptionPane.showMessageDialog(this,"��ӭ"+authorname+Userresult.getName()+"��¼ϵͳ");
        //���ٴ˴���
        this.dispose();
        //��ת��������,������Ϊ����
        store_ma.view.MainFrame mainFrame = new MainFrame(Userresult);
        mainFrame.setVisible(true);
    }


    protected void resetValue(ActionEvent ae) {
        userNameField.setText("");
        passwordField.setText("");
    }

}
