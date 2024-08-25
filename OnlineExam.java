//𝒐𝒊𝒃𝒔𝒊𝒑_taskno-04 Name:-Harivansh Chauhan
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
class Login extends JFrame implements ActionListener {
    JButton loginButton, registerButton, resetButton;
    JLabel userLabel, passLabel, messageLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    int loginAttempts = 0;
    HashMap<String, String> credentials = new HashMap<>();
    RegisterForm registerForm;
    Login() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(51, 153, 255));
        userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 30, 80, 25);
        panel.add(userLabel);
        usernameField = new JTextField(20);
        usernameField.setBounds(150, 30, 200, 25);
        panel.add(usernameField);
        passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 70, 80, 25);
        panel.add(passLabel);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 70, 200, 25);
        panel.add(passwordField);
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 80, 30);
        panel.add(loginButton);
        registerButton = new JButton("Register");
        registerButton.setBounds(190, 110, 100, 30);
        panel.add(registerButton);
        resetButton = new JButton("Reset Password");
        resetButton.setBounds(100, 160, 190, 30);
        panel.add(resetButton);
        messageLabel = new JLabel();
        messageLabel.setBounds(50, 200, 300, 25);
        panel.add(messageLabel);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        credentials.put("user1", "password1");
        credentials.put("user2", "password2");
        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new OnlineTestBegin(username);
            } else {
                loginAttempts++;
                if (loginAttempts >= 3) {
                    JOptionPane.showMessageDialog(this, "Login Attempts Exceeded. Please try again later.");
                    System.exit(0);
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Invalid username or password. Please try again.");
                }
            }
        } else if (ae.getSource() == registerButton) {
            registerForm = new RegisterForm(this);
            registerForm.setVisible(true);
        } else if (ae.getSource() == resetButton) {
        }
    }
    void addCredentials(String username, String password) {
        credentials.put(username, password);
    }
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Login();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}

class RegisterForm extends JFrame implements ActionListener {
    JLabel userLabel, passLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton registerButton;
    Login parent;
    RegisterForm(Login parent) {
        this.parent = parent;
        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        panel.add(userLabel);
        usernameField = new JTextField(20);
        usernameField.setBounds(120, 30, 150, 25);
        panel.add(usernameField);
        passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        panel.add(passLabel);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(120, 70, 150, 25);
        panel.add(passwordField);
        registerButton = new JButton("Register");
        registerButton.setBounds(100, 120, 100, 25);
        registerButton.addActionListener(this);
        panel.add(registerButton);
        add(panel);
    }
    public void actionPerformed(ActionEvent ae) {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        parent.addCredentials(username, password);
        JOptionPane.showMessageDialog(this, "Registration Successful!");
        dispose();
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel questionLabel;
    JRadioButton[] options;
    JButton nextButton, submitButton;
    ButtonGroup optionGroup;
    int count = 0, current = 0;
    OnlineTestBegin(String username) {
        setTitle("Online Test - Welcome " + username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);
        questionLabel = new JLabel();
        panel.add(questionLabel);
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            panel.add(options[i]);
        }
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");
        nextButton.addActionListener(this);
        submitButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        panel.add(buttonPanel);
        setQuestion(current);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (check()) count++;
            current++;
            setQuestion(current);
            if (current == 9) {
                nextButton.setEnabled(false);
                submitButton.setText("Submit");
            }
        } else if (e.getSource() == submitButton) {
            if (check()) count++;
            submitAnswers();
        }
    }
    void setQuestion(int index) {
        switch (index) {
            case 0:
                questionLabel.setText("Q1: Who was the first Prime Minister of India?");
                options[0].setText("Jawaharlal Nehru");
                options[1].setText("Indira Gandhi");
                options[2].setText("Mahatma Gandhi");
                options[3].setText("Rajendra Prasad");
                break;
            case 1:
                questionLabel.setText("Q2: Which river is considered the lifeline of India?");
                options[0].setText("Ganges");
                options[1].setText("Brahmaputra");
                options[2].setText("Yamuna");
                options[3].setText("Godavari");
                break;
            case 2:
                questionLabel.setText("Q3: What is the national flower of India?");
                options[0].setText("Rose");
                options[1].setText("Sunflower");
                options[2].setText("Lotus");
                options[3].setText("Jasmine");
                break;
            case 3:
                questionLabel.setText("Q4: Which festival is known as the “Festival of Lights” in India?");
                options[0].setText("Holi");
                options[1].setText("Navratri");
                options[2].setText("Durga Puja");
                options[3].setText("Diwali");
                break;
            case 4:
                questionLabel.setText("Q5: What is the national game of India?");
                options[0].setText("Cricket");
                options[1].setText("Field Hockey");
                options[2].setText("Football");
                options[3].setText("Badminton");
                break;
            case 5:
                questionLabel.setText("Q6: Who is known as the “Missile Man of India”?");
                options[0].setText("Dr. A. P. J. Abdul Kalam");
                options[1].setText("Dr. Homi J. Bhabha");
                options[2].setText("Dr. C. V. Raman");
                options[3].setText("Dr. Vikram Sarabhai");
                break;
            case 6:
                questionLabel.setText("Q7: What is the currency of India?");
                options[0].setText("Rupiah");
                options[1].setText("Yen");
                options[2].setText("Rupee");
                options[3].setText("Euro");
                break;
            case 7:
                questionLabel.setText("Q8: The Indian space agency is known as what?");
                options[0].setText("CNSA");
                options[1].setText("ESA");
                options[2].setText("NASA");
                options[3].setText("ISRO");
                break;
            case 8:
                questionLabel.setText("Q9: The Indian classical dance form “Bharatanatyam” originated in which state?");
                options[0].setText("Kerala");
                options[1].setText("Tamil Nadu");
                options[2].setText("Karnataka");
                options[3].setText("Andhra Pradesh");
                break;
            case 9:
                questionLabel.setText("Q10: Which famous Indian leader is known as the “Iron Man of India”?");
                options[0].setText("Jawaharlal Nehru");
                options[1].setText("Subhas Chandra Bose");
                options[2].setText("Sardar Vallabhbhai Patel");
                options[3].setText("Lal Bahadur Shastri");
                break;
        }
        for (JRadioButton option : options) {
            option.setSelected(false);
        }
    }
    boolean check() {
        if (current == 0)
            return options[0].isSelected();
        if (current == 1)
            return options[0].isSelected();
        if (current == 2)
            return options[2].isSelected();
        if (current == 3)
            return options[3].isSelected();
        if (current == 4)
            return options[1].isSelected();
        if (current == 5)
            return options[0].isSelected();
        if (current == 6)
            return options[2].isSelected();
        if (current == 7)
            return options[3].isSelected();
        if (current == 8)
            return options[1].isSelected();
        if (current == 9)
            return options[2].isSelected();
        return false;
    }
    void submitAnswers() {
        double percentage = (count / 10.0) * 100;
        String message = "You scored " + percentage + "%.\n\n" + count + " out of 10 answers were correct.";
        JOptionPane.showMessageDialog(this, message);
        JOptionPane.showMessageDialog(this, "Score = " + count + "\nPercentage = " + percentage + "%");

        if (percentage >= 75) {
            JOptionPane.showMessageDialog(this, "Congratulations! You passed the exam.");
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, you did not pass the exam. Please try again.");
        }

        System.exit(0);
    }
}

class OnlineExam {
    public static void main(String args[]) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Login();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}