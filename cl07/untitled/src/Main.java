import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Login().getPnlMain());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}