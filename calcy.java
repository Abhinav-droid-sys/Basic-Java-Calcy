import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calcy extends JFrame implements ActionListener {

    private JTextField textField;
    private double num1, num2, result;
    private char operator;

    public calcy() {

        setTitle("Calcy");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        textField = new JTextField();
        textField.setBounds(30, 40, 330, 50);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        add(textField);

        // Button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        // Buttons
        JButton[] buttons = new JButton[buttonLabels.length];
        int x = 30, y = 100;

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 18));
            buttons[i].setBounds(x, y, 70, 50);
            buttons[i].addActionListener(this);
            add(buttons[i]);
            x += 80;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        // Clear button (C)
        buttons[16].setBounds(30, y, 330, 50);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Clear
        if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            return;
        }

        // Equals
        if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            textField.setText("Error: Divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            } catch (Exception ex) {
                textField.setText("Error");
            }
            return;
        }

        // Operators
        if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = command.charAt(0);
                textField.setText("");
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else {
            // Numbers or decimal
            textField.setText(textField.getText() + command);
        }
    }

    public static void main(String[] args) {
        new calcy();
    }
}
