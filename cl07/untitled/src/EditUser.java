import dao.SQLConstants;
import dao.User;
import dao.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUser {
    private JPanel pnlMain;
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JLabel lblLastName;
    private JLabel lblEmail;
    private JLabel lblType;
    private JLabel lblHeader;
    private JRadioButton rbtnProfessor;
    private JRadioButton rbtnStudent;
    private JCheckBox chkChangePassword;
    private JButton btnSave;

    private User user;
    private int index;

    private String type;

    public EditUser(User user, int index) {

        this.user = user;
        this.index = index;

        lblHeader.setText(lblHeader.getText() + "[" + user.getUsername() + "]");
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
        txtEmail.setText(user.getEmail());
        if (user.getType().equals("Professor")) {
            rbtnProfessor.setSelected(true);
            type = rbtnProfessor.getText();
        } else {
            rbtnStudent.setSelected(true);
            type = rbtnStudent.getText();
        }

        ButtonGroup group = new ButtonGroup();
        group.add(rbtnProfessor);
        group.add(rbtnStudent);

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

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDAO userDAO = new UserDAO();
                user.setFirstName(txtFirstName.getText());
                user.setLastName(txtLastName.getText());
                user.setEmail(txtEmail.getText());
                user.setType(type);
                if (chkChangePassword.isSelected()) {
                    user.setPassword(SQLConstants.createPassword());
                }
                userDAO.editUser(user);
                JOptionPane.showMessageDialog(pnlMain, "User edited successfully.");
                pnlMain.setVisible(false);
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
