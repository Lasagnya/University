
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.function.Predicate;
import javax.swing.*;

public class SimGUI extends JFrame {
    private JButton button = new JButton("Output >300");
    private JButton button2 = new JButton("Cipher");
    private JButton button3 = new JButton("Decipher");
    private JTextField input = new JTextField("",5);
    private JLabel label = new JLabel("Input:");

    public SimGUI() {
        super("Programm");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 2, 2, 2));
        container.add(label);
        container.add(input);

        button.addActionListener(new ButtonEventListener());
        container.add(button);
        button2.addActionListener(new Button2EventListener());
        container.add(button2);
        button3.addActionListener(new Button3EventListener());
        container.add(button3);
    }

    class Button3EventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ElList elList = new ElList();
            try {
                String[] data;
                data = elList.decipher("input.txt");
                JOptionPane.showMessageDialog(null,data,"Output",JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchProviderException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (InvalidKeySpecException ex) {
                ex.printStackTrace();
            } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            } catch (SignatureException ex) {
                ex.printStackTrace();
            }
        }
    }
    class Button2EventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ElList elList = new ElList();
            try {
                elList.input();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                elList.cipher("input.txt");
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            } catch (NoSuchProviderException ex) {
                ex.printStackTrace();
            } catch (SignatureException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    class ButtonEventListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String message ="";
            message += "Text is " + input.getText() +"\n";
            ElList elList = new ElList();
            try {
                elList.input();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for(Travel i: elList.elems)
            {
                Predicate<Travel> condition = x -> Integer.parseInt(x.getLength().replaceAll("\\s+",""))>300;
                if(condition.test(i)) {
                    message += i.toString() +"\n";
                }
            }

            JOptionPane.showMessageDialog(null,message,"Output",JOptionPane.PLAIN_MESSAGE);
        }
    }
}
