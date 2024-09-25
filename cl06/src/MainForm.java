import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainForm extends Container {
    private JPanel pnlMain;
    private JLabel lblNumber;
    private JTextField txtNumber;
    private JButton btnCalculate;

    private int number;

    public MainForm() {
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    number = Integer.parseInt(txtNumber.getText());
                    if (isPrimeNumber(number)) {
                        JOptionPane.showMessageDialog(null, "Prime number");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not prime number");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please insert numeric values");
                }

                number = 0;
                txtNumber.setText("");
            }
        });
    }

    private boolean isPrimeNumber(int number) {
        List<Integer> divisors = new ArrayList<Integer>();
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                divisors.add(i);
            }
        }
        if (divisors.size() <= 2) return true;

        divisors.clear();
        return false;

    }

    public JPanel getPnlMain() {
        return this.pnlMain;
    }
}
