import javax.swing.*;

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
    private JComboBox cmbType;
    private JButton btnCreateUser;

    public JPanel getPnlMain() {
        return this.pnlMain;
    }
}
