import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculadora extends JFrame {
    private JTextField txtDisp;
    private StringBuilder entradaActual = new StringBuilder();
    private double primerOperando = 0;
    private String operador = "";
    private boolean nuevaEntrada = true;

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculadora frame = new Calculadora();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculadora() {
        setTitle("Calculadora Básica");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 0, 0));

        JPanel pnlDisp = new JPanel();
        pnlDisp.setBackground(new Color(0, 0, 0));
        pnlDisp.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Calculadora");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        pnlDisp.add(lblTitulo, BorderLayout.NORTH);

        txtDisp = new JTextField();
        txtDisp.setEditable(false);
        txtDisp.setBackground(new Color(0, 0, 0));
        txtDisp.setForeground(Color.WHITE);
        txtDisp.setFont(new Font("Arial", Font.PLAIN, 36));
        txtDisp.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisp.setBorder(new LineBorder(Color.BLACK, 1, true));
        pnlDisp.add(txtDisp, BorderLayout.CENTER);

        add(pnlDisp, BorderLayout.NORTH);

        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(5, 4, 5, 5));
        pnlBotones.setBackground(new Color(0, 0, 0));

        pnlBotones.add(createOperatorButton("CE"));
        pnlBotones.add(createOperatorButton("C"));
        pnlBotones.add(createOperatorButton("/"));
        pnlBotones.add(createOperatorButton("*"));

        pnlBotones.add(createButton("7"));
        pnlBotones.add(createButton("8"));
        pnlBotones.add(createButton("9"));
        pnlBotones.add(createOperatorButton("-"));

        pnlBotones.add(createButton("4"));
        pnlBotones.add(createButton("5"));
        pnlBotones.add(createButton("6"));
        pnlBotones.add(createOperatorButton("+"));

        pnlBotones.add(createButton("1"));
        pnlBotones.add(createButton("2"));
        pnlBotones.add(createButton("3"));
        pnlBotones.add(createEqualButton("="));

        pnlBotones.add(createButton("0", 2));
        pnlBotones.add(createButton("."));

        add(pnlBotones, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createButton(String text) {
        return createButton(text, 1);
    }

    private JButton createButton(String text, int width) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 24));
        btn.setBackground(new Color(169, 169, 169));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btn.setPreferredSize(new Dimension(width * 100, 100));
        btn.addActionListener(e -> handleButtonClick(text));
        return btn;
    }

    private JButton createOperatorButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 24));
        btn.setBackground(new Color(169, 169, 169));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btn.setPreferredSize(new Dimension(100, 100));
        btn.addActionListener(e -> handleOperatorClick(text));
        return btn;
    }

    private JButton createEqualButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 24));
        btn.setBackground(new Color(255, 105, 180));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        btn.setPreferredSize(new Dimension(100, 100));
        btn.addActionListener(e -> handleButtonClick(text));
        return btn;
    }

    private void handleButtonClick(String text) {
        if ("C".equals(text)) {
            clearDisplay();
        } else if ("CE".equals(text)) {
            entradaActual.setLength(0);
            txtDisp.setText("");
        } else if ("=".equals(text)) {
            calculateResult();
        } else {
            appendToDisplay(text);
        }
    }

    private void handleOperatorClick(String operator) {
        if (this.operador.isEmpty()) {
            primerOperando = Double.parseDouble(entradaActual.toString());
            this.operador = operator;
            entradaActual.setLength(0);
        } else {
            calculateResult();
            this.operador = operator;
        }
    }

    private void appendToDisplay(String text) {
        if (nuevaEntrada) {
            entradaActual.setLength(0);
            nuevaEntrada = false;
        }
        if (".".equals(text) && entradaActual.toString().contains(".")) {
            return;
        }
        entradaActual.append(text);
        txtDisp.setText(entradaActual.toString());
    }

    private void clearDisplay() {
        entradaActual.setLength(0);
        txtDisp.setText("");
        primerOperando = 0;
        operador = "";
        nuevaEntrada = true;
    }

    private void calculateResult() {
        if (entradaActual.length() > 0) {
            double segundoOperando = Double.parseDouble(entradaActual.toString());
            double resultado = 0;
            switch (operador) {
                case "+" -> resultado = primerOperando + segundoOperando;
                case "-" -> resultado = primerOperando - segundoOperando;
                case "*" -> resultado = primerOperando * segundoOperando;
                case "/" -> resultado = primerOperando / segundoOperando;
            }
            txtDisp.setText(String.valueOf(resultado));
            entradaActual.setLength(0);
            entradaActual.append(resultado);
            operador = "";
            nuevaEntrada = true;
        }
    }
}
