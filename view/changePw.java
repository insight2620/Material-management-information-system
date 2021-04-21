package store_ma.view;

import store_ma.dao.UserDao;
import store_ma.model.Users;
import store_ma.util.StringUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class changePw extends JFrame {
    private JLabel label, label1, label2, label3;
    private JTextField text1;
    private JPasswordField text2, text3;
    private JButton button;

    public changePw() {
        super("修改密码");
        this.setSize(400, 290);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(8, 1));

        //定义组件
        label = new JLabel("修改密码");
        label.setFont(new Font("隶书", Font.BOLD, 14));
        label1 = new JLabel("原账号:");
        label1.setFont(new Font("隶书", Font.BOLD, 14));
        label2 = new JLabel("原密码:");
        label2.setFont(new Font("隶书", Font.BOLD, 14));
        label3 = new JLabel("新密码:");
        label3.setFont(new Font("隶书", Font.BOLD, 14));
        text1 = new JTextField(12);
        text2 = new JPasswordField(12);
        text3 = new JPasswordField(12);
        button = new JButton("确认");
        button.setFont(new Font("隶书", Font.PLAIN, 14));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });

        //button.setBackground(Color.gray);

        //定义五个面板,并给每个面板添加不同的组件
        JPanel panel1 = new JPanel();
        panel1.add(label);
        JPanel panel2 = new JPanel();
        panel2.add(label1);
        panel2.add(text1);
        JPanel panel3 = new JPanel();
        panel3.add(label2);
        panel3.add(text2);
        JPanel panel4 = new JPanel();
        panel4.add(label3);
        panel4.add(text3);
        JPanel panel5 = new JPanel();
        panel5.add(button);

        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.getContentPane().add(panel4);
        this.getContentPane().add(panel5);

        this.setVisible(true);
    }

    protected void changePassword(){
        //从界面获取用户名和密码
        String userName = text1.getText();
        String password = new String(text2.getPassword());
        String newpassword = new String(text3.getPassword());
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
            return;
        }
        UserDao Userpw = new UserDao();
        if(Userpw.correctPw(Userresult,newpassword)) {
            JOptionPane.showMessageDialog(this,"修改成功！");
        }
        Userpw.closeDao();


    }
}
