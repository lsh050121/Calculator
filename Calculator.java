import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    JTextField textField;
    JPanel panel;
    JButton button;
    String currentText = " ";
    double firstNumber = 0;
    String operator = "";

    public Calculator() {
        setTitle("계산기");
        setSize(300, 370);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        textField = new JTextField();
        textField.setBounds(10, 10, 265, 70);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setFont(new Font(" ", Font.BOLD, 30));
        textField.setBackground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
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

            button.addActionListener(new ButtonListener());
            panel.add(button);
        }

        add(panel);

        setVisible(true);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();
            if (buttonText.equals("C")){
                currentText = " ";
                firstNumber = 0;
                operator = "";
                textField.setText("");
            }
            else if (buttonText.equals("x²")){
                if(!currentText.isEmpty()){
                    double num = Double.parseDouble(currentText);
                    currentText = String.valueOf(num * num);
                    textField.setText(currentText);
                }
            }
            else if (buttonText.equals("⌫")){
                if(!currentText.isEmpty()){
                    currentText = currentText.substring(0, currentText.length() - 1);
                    textField.setText(currentText);
                }
            }
            else if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("X") || buttonText.equals("÷"))  {
                if (!currentText.isEmpty()){
                    firstNumber = Double.parseDouble(currentText);
                    operator = buttonText;
                    currentText = "";
                }
            }
            else if (buttonText.equals("=")) {
                if (!currentText.isEmpty() && !operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentText);
                    double result = 0;

                    if (operator.equals("+")) {
                        result = firstNumber + secondNumber;
                    }
                    else if (operator.equals("-")) {
                        result = firstNumber - secondNumber;
                    }
                    else if (operator.equals("X")) {
                        result = firstNumber * secondNumber;
                    }
                    else if (operator.equals("÷")) {
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                    }

                    currentText = String.valueOf(result);
                    textField.setText(currentText);
                    operator = "";
                }
            }
            else {
                if (!buttonText.equals(" ")){
                    currentText = currentText + buttonText;
                    textField.setText(currentText);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}