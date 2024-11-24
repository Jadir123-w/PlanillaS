import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {
    private JTextField txtId;
    private JPasswordField txtPwd;
    private JLabel lblPwd;
    private JLabel lblHora;

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginFrame() {
        setTitle("Maestro Home Center");
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(45, 45, 45));

        JPanel pnlIzq = new JPanel();
        pnlIzq.setBackground(new Color(139, 0, 0));
        pnlIzq.setLayout(new BoxLayout(pnlIzq, BoxLayout.Y_AXIS));
        pnlIzq.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel pnlImg = new JPanel();
        pnlImg.setBackground(new Color(139, 0, 0));
        JLabel lblImg = createScaledImageLabel("Maestro.jpg", 500, 500);
        pnlImg.add(lblImg);
        pnlIzq.add(pnlImg);

        pnlIzq.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel pnlId = new JPanel();
        pnlId.setBackground(new Color(139, 0, 0));
        pnlId.setLayout(new BoxLayout(pnlId, BoxLayout.Y_AXIS));

        JLabel lblId = new JLabel("ID de operador:");
        lblId.setForeground(Color.WHITE);
        lblId.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlId.add(lblId);

        txtId = new JTextField(5);
        txtId.setMaximumSize(new Dimension(150, 30));
        txtId.setBackground(Color.WHITE);
        txtId.setForeground(new Color(139, 0, 0));
        txtId.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlId.add(txtId);

        pnlIzq.add(pnlId);

        pnlIzq.add(Box.createRigidArea(new Dimension(0, 20)));

        lblPwd = new JLabel("Ingrese Contraseña:");
        lblPwd.setForeground(Color.WHITE);
        lblPwd.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPwd.setVisible(false);
        pnlIzq.add(lblPwd);

        txtPwd = new JPasswordField(5);
        txtPwd.setMaximumSize(new Dimension(150, 30));
        txtPwd.setBackground(Color.WHITE);
        txtPwd.setForeground(Color.BLACK);
        txtPwd.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPwd.setVisible(false);
        pnlIzq.add(txtPwd);

        add(pnlIzq, BorderLayout.CENTER);

        JPanel pnlNumPad = new JPanel(new GridLayout(4, 4));
        pnlNumPad.setBackground(Color.WHITE);

        pnlNumPad.add(createNumPadButton("7"));
        pnlNumPad.add(createNumPadButton("8"));
        pnlNumPad.add(createNumPadButton("9"));
        pnlNumPad.add(createSpecialButton("Cancelar"));

        pnlNumPad.add(createNumPadButton("4"));
        pnlNumPad.add(createNumPadButton("5"));
        pnlNumPad.add(createNumPadButton("6"));
        pnlNumPad.add(createSpecialButton("Borrar"));

        pnlNumPad.add(createNumPadButton("1"));
        pnlNumPad.add(createNumPadButton("2"));
        pnlNumPad.add(createNumPadButton("3"));
        pnlNumPad.add(createSpecialButton("Atrás"));

        pnlNumPad.add(createNumPadButton("0"));
        pnlNumPad.add(createNumPadButton("00"));
        pnlNumPad.add(createSpecialButton("Entrar"));

        JPanel pnlWrapper = new JPanel(new BorderLayout());
        pnlWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlWrapper.add(pnlNumPad, BorderLayout.CENTER);

        JPanel pnlImgAbajo = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlImgAbajo.setBackground(Color.WHITE);
        pnlImgAbajo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        pnlImgAbajo.add(createScaledImageLabel("ayuda.jpg", 10, 10));
        pnlImgAbajo.add(createScaledImageLabel("huella.jpg", 10, 10));

        pnlWrapper.add(pnlImgAbajo, BorderLayout.SOUTH);
        add(pnlWrapper, BorderLayout.EAST);

        JPanel pnlAbajo = new JPanel(new BorderLayout());
        pnlAbajo.setBackground(new Color(139, 0, 0));

        lblHora = new JLabel();
        lblHora.setForeground(Color.WHITE);
        pnlAbajo.add(lblHora, BorderLayout.WEST);

        JLabel lblRegistro = new JLabel("register01");
        lblRegistro.setForeground(Color.WHITE);
        pnlAbajo.add(lblRegistro, BorderLayout.EAST);

        add(pnlAbajo, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        setVisible(true);
    }

    private JLabel createScaledImageLabel(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }

    private JButton createNumPadButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(100, 100));
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setBorder(new LineBorder(Color.BLACK, 1));
        btn.addActionListener(e -> {
            if (txtPwd.isVisible()) {
                txtPwd.setText(new String(txtPwd.getPassword()) + text);
            } else {
                txtId.setText(txtId.getText() + text);
            }
        });
        return btn;
    }

    private JButton createSpecialButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(100, 100));
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setBorder(new LineBorder(Color.BLACK, 1));
        btn.addActionListener(e -> {
            switch (text) {
                case "Cancelar" -> {
                    txtId.setText("");
                    txtPwd.setText("");
                }
                case "Borrar" -> {
                    if (txtPwd.isVisible()) {
                        String txtActual = new String(txtPwd.getPassword());
                        if (!txtActual.isEmpty()) {
                            txtPwd.setText(txtActual.substring(0, txtActual.length() - 1));
                        }
                    } else {
                        String txtActual = txtId.getText();
                        if (!txtActual.isEmpty()) {
                            txtId.setText(txtActual.substring(0, txtActual.length() - 1));
                        }
                    }
                }
                case "Atrás" -> {
                    txtPwd.setText("");
                    lblPwd.setVisible(false);
                    txtPwd.setVisible(false);
                    txtId.setText("");
                }
                case "Entrar" -> checkID();
            }
        });
        return btn;
    }

    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String hora = sdf.format(new Date());
        lblHora.setText(hora);
    }

    private void checkID() {
        String id = txtId.getText();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese su ID de operador.");
        } else {
            lblPwd.setVisible(true);
            txtPwd.setVisible(true);
            txtPwd.requestFocusInWindow();
        }
    }
}
