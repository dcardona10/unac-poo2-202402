import dao.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class AdminModule implements ActionListener {
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
    private JTextField txtSearch;
    private JButton btnSearch;
    private JLabel lblSearch;
    private JScrollPane spTable;

    private String type;

    private ArrayList<User> users;
    private ArrayList<JButton> btnEditList, btnDeleteList;

    public JPanel getPnlMain() {
        return this.pnlMain;
    }

    public AdminModule() throws SQLException {

        ButtonGroup group = new ButtonGroup();
        group.add(rbtnProfessor);
        group.add(rbtnStudent);

        users = new ArrayList<>();
        btnEditList = new ArrayList<>();
        btnDeleteList = new ArrayList<>();

        loadData("");

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
                    UserDAO userDAO = new UserDAO();
                    User user = new User();
                    user.setFirstName(txtFirstName.getText());
                    user.setLastName(txtLastName.getText());
                    user.setEmail(txtEmail.getText());
                    user.setUsername(createUsername(txtFirstName.getText(), txtLastName.getText()));
                    user.setPassword(SQLConstants.createPassword());
                    user.setType(type);
                    userDAO.createUser(user);
                    JOptionPane.showMessageDialog(pnlMain, "User created successfully") ;
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(pnlMain, ex.getMessage());
                }
            }
        });

        btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton btnClicked = (JButton) e.getSource();
        if (btnClicked == btnSearch) {
            loadData(txtSearch.getText());
            return;
        }
        for (int i = 0; i < btnEditList.size(); i++) {
            if (btnClicked.equals(btnEditList.get(i))) {
                btnEditClick();
                return;
            }
        }
        for (int i = 0; i < btnDeleteList.size(); i++) {
            if (btnClicked.equals(btnDeleteList.get(i))) {
                btnDeleteClick();
                return;
            }
        }
    }

    private String createUsername(String firstName, String lastName) throws SQLException {
        String username = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        try {
            Connection connection = DriverManager.getConnection(SQLConstants.SQL_CONNECTION, SQLConstants.SQL_USER, SQLConstants.SQL_PASSWORD);
            PreparedStatement queryUsernameStatement = connection.prepareStatement(SQLConstants.SQL_GET_USER_BY_USERNAME);
            queryUsernameStatement.setString(1, username);
            ResultSet resultSet = queryUsernameStatement.executeQuery();
            if (resultSet.getInt("usercount") >= 1) {
                username += (resultSet.getInt("usercount") + 1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(pnlMain, e.getMessage());
        }

        return username;
    }

    private void loadData(String keyword) {

        tblUsers.setModel(new UserTableModel());
        try {
            UserDAO userDAO = new UserDAO();
            users = userDAO.searchUser(keyword);
            btnEditList.clear();
            btnDeleteList.clear();
            for (int i = 0; i < users.size(); i++) {
                JButton button = new JButton("Edit");
                button.addActionListener(this);
                btnEditList.add(button);
                button = new JButton("Delete");
                button.addActionListener(this);
                btnDeleteList.add(button);
            }
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();
            tblUsers.getColumn("Edit").setCellRenderer(buttonRenderer);
            tblUsers.getColumn("Delete").setCellRenderer(buttonRenderer);
            tblUsers.addMouseListener(new JTableButtonListener(tblUsers));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlMain, e.getMessage());
        }

        tblUsers.getTableHeader().setVisible(true);
    }

    private void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        rbtnProfessor.setSelected(false);
        rbtnStudent.setSelected(false);
    }

    class JTableButtonRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;
            return button;
        }
    }

    class UserTableModel extends AbstractTableModel {

        private String[] columns = {"First Name", "Last Name", "Email", "Username", "Type", "Edit", "Delete"};
        private final Class<?>[] columnTypes = new Class<?>[] {String.class, String.class, String.class, String.class, String.class, JButton.class, JButton.class};

        public int getColumnCount() {
            return columns.length;
        }

        public int getRowCount() {
            return users.size();
        }

        public String getColumnName(int column) {
            return columns[column];
        }

        public Class<?> getColumnClass(int column) {
            return columnTypes[column];
        }

        public Object getValueAt(int row, int column) {
            switch (column) {
                case 0:
                    return users.get(row).getFirstName();
                case 1:
                    return users.get(row).getLastName();
                case 2:
                    return users.get(row).getEmail();
                case 3:
                    return users.get(row).getUsername();
                case 4:
                    return users.get(row).getType();
                case 5:
                    return btnEditList.get(row);
                case 6:
                    return btnDeleteList.get(row);
                default:
                    return "Error";
            }
        }
    }

    class JTableButtonListener extends MouseAdapter {

        private final JTable table;

        public JTableButtonListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    ((JButton) value).doClick();
                }
            }
        }
    }

    private void btnEditClick() {
        //
    }

    private void btnDeleteClick() {
        //
    }
}
