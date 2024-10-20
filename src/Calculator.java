import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame myFrame;
    private JTextField myText;
    private JPanel myPanel;
    private JButton myButton;
    private char operator;
    private double num1,num2;
    private StringBuilder currentInput;

    public Calculator(){
        initializor();

    }
    public  void initializor(){
        this.myFrame = new JFrame("Swing Calculator");
        this.myFrame.setSize(300,400);
        this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myFrame.setLayout(new BorderLayout(10,10));

        this.myText = new JTextField();
        this.myText.setEditable(false);
        this.myText.setHorizontalAlignment(JTextField.RIGHT);
        this.myFrame.add(this.myText,BorderLayout.NORTH);

        this.myPanel = new JPanel();
        this.myPanel.setLayout(new GridLayout(4,4,5,5));
        this.myFrame.add(this.myPanel,BorderLayout.CENTER);

        currentInput = new StringBuilder();
        num1=num2 = 0;
        operator = ' ';

        String[] buttonLbl = {
                "7","8","9","+",
                "4","5","6","-",
                "3,","2","1","*",
                "0","C","=","/" };

        for (String label : buttonLbl){
            myButton = new JButton(label);
            myButton.addActionListener(new CalculatorActionListener());
            myPanel.add(myButton);
        }

        this.myFrame.setVisible(true);
    }

    private class CalculatorActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if(cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9'){
                currentInput.append(cmd);
                myText.setText(currentInput.toString());
            }else if (cmd.equals("C")){
                currentInput.setLength(0);
                myText.setText("");
                num1=num2=0;
                operator = ' ';
            }else if(cmd.equals("="))
            {
                if(currentInput.length() > 0 && operator !=  ' '){
                    num2 = Double.parseDouble(currentInput.toString());
                    double result = 0;
                    switch (operator){
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '/':
                            if(num2 != 0){
                                result = num1 / num2;
                            }else{
                                myText.setText("Error: Divided by zero");
                                return;
                            }
                            break;
                    }
                    myText.setText(String.valueOf(result));
                    currentInput.setLength(0);
                    num1 = num2 = 0;
                    operator = ' ';
                }
            }else{
                if(currentInput.length() > 0){
                    num1 = Double.parseDouble(currentInput.toString());
                    operator = cmd.charAt(0);
                    currentInput.setLength(0);
                }
            }
        }
    }

    public static void main(String[] args){
        new Calculator();
    }
}
