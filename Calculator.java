import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *  JAVA GUI - 계산기 프로그램
 *
 * @author 이상혁
 * @version 1.1
 *
 * 절대 배치 참고
 * 이스케이프화 참고
 * @see "https://blog.naver.com/sks6624/150165603219"
 * @see "https://moonsiri.tistory.com/28"
 */
public class Calculator extends JFrame {
    /**
     *  필드값 변수 생성
     */
    JTextField textField;
    JPanel panel;
    JButton button;
    String currentText = "";
    double firstNumber = 0;
    String operator = "";

    /**
     *  Calculator 생성자
     *  size 300, 370
     *  인터페이스 @version 1.4
     */
    public Calculator() {
        setTitle("계산기");
        setSize(300, 370);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //프레임을 화면 중앙에 나오게 설정
        setResizable(false);

        textField = new JTextField();
        textField.setBounds(10, 10, 265, 70);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createEmptyBorder()); // 텍스트필드의 테두리 없음으로 설정
        textField.setFont(new Font(" ", Font.BOLD, 30));
        textField.setBackground(Color.WHITE);
        textField.setHorizontalAlignment(SwingConstants.RIGHT); // 텍스트필드값 우측배치
        add(textField);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 6, 6));
        panel.setBounds(10, 90, 265, 230);

        String[] buttons = {
                "C", "x²", "÷", "⌫",
                "7", "8", "9", "X",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
        };

        for (int i = 0; i < buttons.length; i++) { //buttons에 있는 값들을 하나씩 버튼으로 만듬, 숫자와 연산 버튼의 색상 다르게 설정

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

    /**
     *  currentText 변수를 이용해 내가 누른 버튼의 값이 currentText에 올라가게 설정
     *  이후 currentText를 calculate 메소드를 이용해 값 연산 실행
     *  ActionListener 클래스
     *  각 연산에 대한 이벤트 설정
     */
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();
            if (buttonText.equals("C")) { // Clear 처음 세팅으로 돌아가게 설정
                currentText = "";
                firstNumber = 0;
                operator = "";
                textField.setText("");
            }
            else if (buttonText.equals("x²")) { // 텍스트필드의 값을 제곱 시켜 보여줌
                if (!currentText.isEmpty()) {
                    double num = Double.parseDouble(currentText);
                    currentText = String.valueOf(num * num);
                    textField.setText(currentText);
                }
            }
            else if (buttonText.equals("⌫")){ // 텍스트필드의 값 1개 삭제
                if(!currentText.isEmpty()){
                    currentText = currentText.substring(0, currentText.length() - 1);
                    textField.setText(currentText);
                }
            }
            else if (buttonText.equals("=")) { // calculate 메소드 실행
                if (!currentText.isEmpty()) {
                    try {
                        double result = calculate(currentText);
                        currentText = String.valueOf(result);
                        textField.setText(currentText);
                    } catch (Exception ex) {
                        textField.setText("Error");
                    }
                }
            }
            else if (buttonText.equals("+/-")) { // 양수 음수 변환
                if (!currentText.isEmpty()) {
                    double num = Double.parseDouble(currentText);
                    num = -num;
                    currentText = String.valueOf(num);
                    textField.setText(currentText);
                }
            }
            else {
                if (!buttonText.equals(" ")) { // 누른 버튼 텍스트필드에 표시
                    currentText = currentText + buttonText;
                    textField.setText(currentText);
                }
            }
        }
    }

    /**
     * currentText를 받아와 .charAt()을 이용해 문자열 하나 단위로 잘라서 연산함
     * @param input String 타입의 currentText 를 받아옴 (textField에 표시된 값)
     * @return  각 연산의 결과값을 리턴하여 currentText에 저장
     *
     * @see "chatgpt를 참고하여 코드 작성"
     */
    private double calculate(String input) {
        List<Double> numbers = new ArrayList<>();
        List<Character> operations = new ArrayList<>();
        StringBuilder numberBuffer = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {//input으로 받은 currentText를 문자배열로 변환하여 각 문자(ch)를 순차적으로 검사
            char ch = input.charAt(i);

            if (ch == '-' && (i == 0 || !Character.isDigit(input.charAt(i - 1)))) { // 음수조건
                numberBuffer.append(ch);
            } else if (Character.isDigit(ch) || ch == '.') { // 문자가 숫자이거나 소수점인 경우 numberBuffer에 추가
                numberBuffer.append(ch);
            } else {
                if (numberBuffer.length() > 0) { // numberBuffer에 값이 있으면 double로 변환해 numbers에 저장후 numberBuffer 초기화
                    numbers.add(Double.parseDouble(numberBuffer.toString()));
                    numberBuffer = new StringBuilder();
                }
                operations.add(ch); // 연산자일경우 opearations 에 추가
            }
        }
        numbers.add(Double.parseDouble(numberBuffer.toString())); //numbersBuffer에 있는 숫자 문자열을 double로 변환하여 numbers 에 추가

        for (int i = 0; i < operations.size(); i++) { // X 연산과 ÷ 연산 우선순위 설정
            char op = operations.get(i);
            if (op == 'X' || op == '÷') {
                double result = (op == 'X') ? numbers.get(i) * numbers.get(i + 1)
                        : numbers.get(i) / numbers.get(i + 1);
                numbers.set(i, result);
                numbers.remove(i + 1);
                operations.remove(i);
                i--;
            }
        }

        double result = numbers.get(0);
        for (int i = 0; i < operations.size(); i++) { // + 와 - 연산 과정 설정
            char op = operations.get(i);
            if (op == '+') {
                result += numbers.get(i + 1);
            } else if (op == '-') {
                result -= numbers.get(i + 1);
            }
        }

        return result; // 결과값 리턴
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
