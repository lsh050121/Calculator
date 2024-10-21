import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    JTextField textField;
    JPanel panel;
    JButton button;

    public Calculator() {
        setTitle("계산기");
        setSize(300, 370);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField("0");
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 3, 3));

        String[] buttons = {
                "C", "x²", "÷", "⌫",
                "7", "8", "9", "X",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                " ", "0", ".", "="
        };

        for (int i = 0; i < buttons.length; i++) {

            String text = buttons[i];
            button = new JButton(text); //
            button.setBackground(Color.GRAY);
            button.setForeground(Color.WHITE);


            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}