import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class login extends JFrame implements ActionListener {
    private final JPasswordField pwdFld;
    private final StringBuilder inputPwd;
    private final String correctPwd = "123456";
    private JLabel lblPlace;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                login frame = new login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public login() {
        setTitle("login-Yape");
        setSize(385, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        inputPwd = new StringBuilder();

        JPanel pnlTop = new JPanel(new GridBagLayout());
        pnlTop.setBackground(new Color(128, 0, 128));
        pnlTop.setPreferredSize(new Dimension(385, 365));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);

        lblPlace = new JLabel("Ingresa tu clave");
        lblPlace.setFont(new Font("Arial", Font.BOLD, 24));
        lblPlace.setForeground(Color.white);
        lblPlace.setHorizontalAlignment(SwingConstants.CENTER);
        pnlTop.add(lblPlace, gbc);

        gbc.gridy = 1;
        JLabel lblImg = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("yape.png"));
        Image img = icon.getImage().getScaledInstance(165, 165, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        lblImg.setIcon(icon);
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        pnlTop.add(lblImg, gbc);

        add(pnlTop, BorderLayout.NORTH);

        JPanel pnlKeyboard = new JPanel(new BorderLayout());
        pnlKeyboard.setBackground(Color.WHITE);

        pwdFld = new JPasswordField(10);
        pwdFld.setFont(new Font("Arial", Font.BOLD, 24));
        pwdFld.setHorizontalAlignment(JPasswordField.CENTER);
        pwdFld.setEchoChar('•');
        pwdFld.setEditable(false);
        pwdFld.setOpaque(false);
        pwdFld.setBorder(BorderFactory.createEmptyBorder());
        pnlKeyboard.add(pwdFld, BorderLayout.NORTH);

        JPanel pnlBtns = new JPanel(new GridLayout(4, 3, 10, 10));
        pnlBtns.setBackground(Color.WHITE);
        pnlBtns.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ArrayList<String> nums = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            nums.add(String.valueOf(i));
        }
        Collections.shuffle(nums);

        int idx = 0;
        for (int i = 0; i < 12; i++) {
            JButton btn;
            if (i == 9) { // Botón "Enter"
                btn = new JButton("Enter");
                btn.setFont(new Font("Arial", Font.BOLD, 14));
                btn.setBackground(Color.green);
                btn.setFocusPainted(false);
                btn.setPreferredSize(new Dimension(50, 50));
                btn.setBorder(null);
                btn.addActionListener(e -> verifyPwd());
            } else if (i == 11) { // Botón "Borrar"
                btn = new JButton();
                btn.setBackground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setPreferredSize(new Dimension(50, 50));
                btn.setBorder(null);

                ImageIcon deleteIcon = new ImageIcon(getClass().getResource("borrar.jpeg"));
                Image deleteImg = deleteIcon.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH);
                deleteIcon = new ImageIcon(deleteImg);
                btn.setIcon(deleteIcon);

                btn.addActionListener(e -> {
                    if (inputPwd.length() > 0) {
                        inputPwd.deleteCharAt(inputPwd.length() - 1);
                        pwdFld.setText(inputPwd.toString());
                    }
                    if (inputPwd.length() == 0) {
                        lblPlace.setVisible(true);
                    }
                });
            } else { // Botones de números
                btn = new JButton(nums.get(idx++));
                btn.setFont(new Font("Arial", Font.BOLD, 14));
                btn.setBackground(new Color(240, 240, 240));
                btn.setFocusPainted(false);
                btn.setPreferredSize(new Dimension(50, 50));
                btn.setBorder(null);
                btn.addActionListener(this);
            }
            pnlBtns.add(btn);
        }

        pnlKeyboard.add(pnlBtns, BorderLayout.CENTER);
        add(pnlKeyboard, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (inputPwd.length() < 6) {
            inputPwd.append(comando);
            pwdFld.setText(inputPwd.toString());
        }

        if (inputPwd.length() == 1) {
            lblPlace.setVisible(false);
        }
    }

    private void verifyPwd() {
        if (inputPwd.toString().equals(correctPwd)) {
            JOptionPane.showMessageDialog(this, "¡Ha ingresado satisfactoriamente!");
        } else {
            JOptionPane.showMessageDialog(this, "Clave incorrecta, intente nuevamente.");
        }

        inputPwd.setLength(0);
        pwdFld.setText("");
        lblPlace.setVisible(true);
    }
}
