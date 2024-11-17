package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import controller.EmpleadoDAO;
import model.Empleado;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	JTextField txtDni;
	JPasswordField txtPasswordd;
	Empleado empleado = new Empleado();
	EmpleadoDAO empleadoDAO = new EmpleadoDAO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel pnLogin = new JPanel();
		pnLogin.setBounds(0, 0, 400, 51);
		pnLogin.setBackground(new Color(94, 17, 90));
		getContentPane().add(pnLogin);
		
		JLabel imgLogo = new JLabel();
		imgLogo.setIcon( new ImageIcon( Login.class.getResource("/ui/img/logo.png") ) );
		imgLogo.setBounds(96, 4, 208, 43);
		pnLogin.add(imgLogo);
		
		JLabel imgSalir = new JLabel();
		imgSalir.setBounds(370, 12, 24, 24);
		imgSalir.setIcon( new ImageIcon( Login.class.getResource("/ui/img/salir.png") ) );
		pnLogin.add(imgSalir);
		
		JLabel lblDni = new JLabel("Dni :");
		lblDni.setBounds(100, 100 , 100, 30);
		getContentPane().add(lblDni);
		
		JLabel lblPasswordd = new JLabel ("Password:");
		lblPasswordd.setBounds(100, 150 , 100, 30);
		getContentPane().add(lblPasswordd);
		
		txtDni = new JTextField();
		txtDni.setBounds(200, 100, 100, 30);
		txtDni.setColumns(8);
		txtDni.setMargin(new Insets(5, 5, 5, 5));
		getContentPane().add(txtDni);
		
		txtPasswordd = new JPasswordField();
		txtPasswordd.setBounds(200, 150, 100, 30);
		txtPasswordd.setColumns(6);
		txtPasswordd.setMargin(new Insets(5, 5, 5, 5));
		getContentPane().add(txtPasswordd);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(76, 250, 100, 30);
		btnIniciar.setBackground(new Color(94, 17, 90) );
		btnIniciar.setBorderPainted(false);
		btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setMnemonic('i');
		btnIniciar.setFocusPainted(false);
		getContentPane().add(btnIniciar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(225, 250, 100, 30);
		btnCancelar.setBackground(new Color(94, 17, 90) );
		btnCancelar.setBorderPainted(false);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setMnemonic('c');
		btnCancelar.setFocusPainted(false);
		getContentPane().add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        btnIniciar_actionPerformed();
		    }
		});
		
		btnIniciar.addActionListener(e -> btnCancelar_actionPerformed());
		btnCancelar.addActionListener(e -> btnCancelar_actionPerformed());
		
		imgSalir.addMouseListener(new MouseAdapter() {
		    @Override public void mouseClicked(MouseEvent e) { imgSalir_mouseClicked(); } });
		
		txtDni.addKeyListener(new KeyAdapter() {
		    @Override public void keyTyped(KeyEvent e) {txt_keyTyped(e, txtDni); }});
		
		txtPasswordd.addKeyListener(new KeyAdapter() {
		    @Override public void keyTyped(KeyEvent e) {txt_keyTyped(e, txtPasswordd);}});

		txtDni.addFocusListener(new FocusAdapter() {
		    @Override public void focusGained(FocusEvent e) {txt_focusGained(txtDni);}
		    @Override public void focusLost(FocusEvent e) {txt_focusLost(txtDni);}});

		txtPasswordd.addFocusListener(new FocusAdapter() {
		    @Override public void focusGained(FocusEvent e) {txt_focusGained(txtPasswordd);}
		    @Override public void focusLost(FocusEvent e) {txt_focusLost(txtPasswordd);}});

		btnIniciar.addFocusListener(new FocusAdapter() {
		    @Override public void focusGained(FocusEvent e) {btn_focusGained(btnIniciar);}
		    @Override public void focusLost(FocusEvent e) {btn_focusLost(btnIniciar);}});

		btnCancelar.addFocusListener(new FocusAdapter() {
		    @Override public void focusGained(FocusEvent e) {btn_focusGained(btnCancelar);}
		    @Override public void focusLost(FocusEvent e) {btn_focusLost(btnCancelar);}});

		btnIniciar.addMouseListener(new MouseAdapter() {
		    @Override public void mouseEntered(MouseEvent e) {btn_focusGained(btnIniciar);}
		    @Override public void mouseExited(MouseEvent e) {btn_focusLost(btnIniciar);}});

		btnCancelar.addMouseListener(new MouseAdapter() {
		    @Override public void mouseEntered(MouseEvent e) {btn_focusGained(btnCancelar);}
		    @Override public void mouseExited(MouseEvent e) {btn_focusLost(btnCancelar);}});

	
	}

	protected void txt_keyTyped(KeyEvent e, JTextField txt) {
		if ( txt.getText().length() == txt.getColumns() || !Character.isDefined( e.getKeyChar() ) )
			e.consume();

	}

	protected void txt_focusGained(JTextField txt) {
	    txt.setBackground(new Color(225, 245, 254)); 
	}


	protected void txt_focusLost(JTextField txt) {
	    txt.setBackground(Color.WHITE); 
	}

	protected void btn_focusGained(JButton btn) {
	    btn.setBackground(Color.BLACK);
	}

	protected void btn_focusLost(JButton btn) {
	    btn.setBackground(new Color(94, 17, 90));
	}
	
	private void btnCancelar_actionPerformed() {
		txtDni.setText("");
		txtPasswordd.setText("");
		txtDni.requestFocus();
	}

	protected void imgSalir_mouseClicked() {
		if ( JOptionPane.showConfirmDialog(this, "Desea Salir?", "Salir", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_NO_OPTION )
			System.exit(0);
	}
	
	protected void btnIniciar_actionPerformed() {
		String sDni = txtDni.getText();
		String sPasswordd= String.valueOf( txtPasswordd.getPassword() );
		
		if ( sDni.length() < txtDni.getColumns() || sPasswordd.length() < txtPasswordd.getColumns() ) {
			btnCancelar_actionPerformed();
			JOptionPane.showConfirmDialog(this, "Dni y/o contraseña invalida");
			return;
		}

	}
	
}