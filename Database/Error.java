import javax.swing.*;
import java.awt.*;

public class Error extends JFrame {
    public Error() {
        super("Error");

        JPanel panel = new JPanel();
        JTextArea txtrUsernameOrPassword = new JTextArea("Username or password is incorrect. ");
        txtrUsernameOrPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtrUsernameOrPassword.setBackground(UIManager.getColor("Button.background"));
        txtrUsernameOrPassword.setLineWrap(true);
        txtrUsernameOrPassword.setWrapStyleWord(true);

        panel.setLayout(null);
        txtrUsernameOrPassword.setBounds(37, 56, 224, 24);
        panel.add(txtrUsernameOrPassword);

        setContentPane(panel);
        
        JTextPane txtpnPleaseTryAgain = new JTextPane();
        txtpnPleaseTryAgain.setBackground(UIManager.getColor("Button.background"));
        txtpnPleaseTryAgain.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtpnPleaseTryAgain.setText("Please try again.");
        txtpnPleaseTryAgain.setBounds(97, 77, 111, 33);
        panel.add(txtpnPleaseTryAgain);

        setSize(306, 218);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        setVisible(true);
        
    }
}

