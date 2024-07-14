import javax.swing.*;
import java.awt.*;
public class About extends JFrame{

    About(){
        setTitle("About NotePad App.");
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("myLogo1.png"));
        setIconImage(icon.getImage());

        JLabel text = new JLabel("<html> 'NotePad by Rohit' <br> Version 1.0 <br> This Project is build by <span><i>Rohit Yadav</i></span><br></html>");
        text.setFont(new Font("Arial Bold", Font.BOLD ,25));
        text.setBounds(50, 150, 500, 100);
        add(text);

    }
    public static void main(String[] args) {
        new About().setVisible(true);
    }
}
