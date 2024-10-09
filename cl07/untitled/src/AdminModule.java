import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminModule {
    private JPanel pnlMain;
    private JTabbedPane tabpUsers;
    private JPanel pnlCreateUser;
    private JPanel pnlEditUser;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblEmail;
    private JLabel lblType;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JButton btnCreateUser;
    private JRadioButton rbtnProfessor;
    private JRadioButton rbtnStudent;
    private JTable tblUsers;

    private String type;

    public JPanel getPnlMain() {
        return this.pnlMain;
    }

    public AdminModule() throws SQLException {
        ButtonGroup group = new ButtonGroup();
        group.add(rbtnProfessor);
        group.add(rbtnStudent);

        loadData();

        rbtnProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = rbtnProfessor.getText();
            }
        });

        rbtnStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type = rbtnStudent.getText();
            }
        });

        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_CREATE_USER);
                    statement.setString(1, txtFirstName.getText());
                    statement.setString(2, txtLastName.getText());
                    statement.setString(3, txtEmail.getText());
                    statement.setString(4, createUsername(txtFirstName.getText(), txtLastName.getText()));
                    statement.setString(5, SQLConstants.createPassword());
                    statement.setString(6, type);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(pnlMain, "User created successfully") ;
                    loadData();
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(pnlMain, ex.getMessage());
                }
            }
        });
    }

    private String createUsername(String firstName, String lastName) throws SQLException {
        String username = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        try {
            Connection connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
            PreparedStatement queryUsernameStatement = connection.prepareStatement(SQLConstants.SQL_GET_USER_BY_USERNAME);
            queryUsernameStatement.setString(1, username);
            ResultSet resultSet = queryUsernameStatement.executeQuery();
            if (resultSet.next()) {
                username += resultSet.getInt("usercount");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(pnlMain, e.getMessage());
        }

        return username;
    }

    private void loadData() {
        try {
            Connection connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SQLConstants.SQL_GET_USERS);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("First Name");
            model.addColumn("Last Name");
            model.addColumn("Email");
            model.addColumn("Username");
            model.addColumn("Type");
            while (resultSet.next()) {
                Object[] row = new Object[5];
                for (int i = 0; i < row.length; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                model.addRow(row);
            }
            tblUsers.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(pnlMain, e.getMessage());
        }
    }

    private void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        rbtnProfessor.setSelected(false);
        rbtnStudent.setSelected(false);
    }
}
