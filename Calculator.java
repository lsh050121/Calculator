import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    JTextField textField;
    JPanel panel;
    JButton button;

    public Calculator() {
        setTitle("계산기");
        setSize(300, 370);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        textField = new JTextField(" 0 ");
        textField.setBounds(10, 10, 265, 70);
        textField.setEditable(false);
        add(textField);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 6, 6));
        panel.setBounds(10, 90, 265, 230);

        String[] buttons = {
                "C", "x²", "÷", "⌫",
                "7", "8", "9", "X",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                " ", "0", ".", "="
        };

        for (int i = 0; i < buttons.length; i++) {

            String text = buttons[i];
            button = new JButton(text);
            if (i < 4 || i % 4 == 3){
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }
            else{
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
            }
            button.setFont(new Font(" ", Font.BOLD, 20));
            button.setBorderPainted(false);


            panel.add(button);
        }

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}