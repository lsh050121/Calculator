import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    JTextField textField;
    JPanel panel;
    JButton button;

    public Calculator() {
        setTitle("계산기");
        setSize(520, 250);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 3, 3));

        String[] buttons = {
                "Backspace", " ", " ", "CE", "C",
                "7", "8", "9", "/", "sqrt",
                "4", "5", "6", "x", "%",
                "1", "2", "3", "-", "1/x",
                "0", "+/-", ".", "+", "="
        };

        for (int i = 0; i < buttons.length; i++) {

            String text = buttons[i];
            button = new JButton(text); //

            if (i % 5 < 3) {
                button.setForeground(Color.BLUE);
            } else {
                button.setForeground(Color.RED);
            }

            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}