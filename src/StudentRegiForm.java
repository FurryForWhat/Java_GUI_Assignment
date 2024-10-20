import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegiForm extends JFrame implements ActionListener {

    private JTextField nameField, emailField, phoneField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> courseComboBox;
    private JCheckBox termsCheckBox;
    private JButton submitButton;

    public StudentRegiForm() {
        initializeForm();
    }

    // Method to initialize and set up the form components
    private void initializeForm() {
        // Set up the frame properties
        setTitle("Student Registration Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 5, 10, 10));


        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        JLabel courseLabel = new JLabel("Course:");
        String[] courses = {"Java", "Python", "C++"};
        courseComboBox = new JComboBox<>(courses);

        termsCheckBox = new JCheckBox("Accept Terms and Conditions");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);


        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(genderLabel);


        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel);

        add(courseLabel);
        add(courseComboBox);
        add(termsCheckBox);
        add(new JLabel());
        add(submitButton);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");
            String course = (String) courseComboBox.getSelectedItem();
            boolean termsAccepted = termsCheckBox.isSelected();


            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || gender.isEmpty() || !termsAccepted) {
                JOptionPane.showMessageDialog(this, "Please fill all fields and accept the terms and conditions.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                String message = String.format(
                        "Name: %s\nEmail: %s\nPhone: %s\nGender: %s\nCourse: %s\n",
                        name, email, phone, gender, course);
                JOptionPane.showMessageDialog(this, message, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new StudentRegiForm();
    }
}
