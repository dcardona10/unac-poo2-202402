import javax.swing.*;

public class UserModule {

    private JPanel pnlMain;
    private JLabel lblWelcomeMessage;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JPanel getPnlMain() {
        return this.pnlMain;
    }

    public UserModule() {
        this.lblWelcomeMessage.setText("Welcome, " + username);
    }
}
