import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JPanel pnlMain;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JButton btnLogin;

    public Login() {

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(pwdPassword.getPassword());
                try {
                    Connection connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
                    PreparedStatement statement = (PreparedStatement) connection.prepareStatement(SQLConstants.SQL_GET_USER_BY_CREDENTIALS);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        JFrame frame = new JFrame();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        if (resultSet.getString("type").equals("Admin")) {
                            frame.setTitle("Admin Module");
                            frame.setContentPane(new AdminModule().getPnlMain());
                        } else {
                            frame.setTitle("User Module");
                            UserModule userModule = new UserModule();
                            frame.setContentPane(userModule.getPnlMain());
                            userModule.setUsername(resultSet.getString("first_name"));
                        }
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(pnlMain, "Wrong Username and/or Password", "Login Failed", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(pnlMain, ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getPnlMain() {
        return this.pnlMain;
    }
}
