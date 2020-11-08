package data.view;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import data.model.Tipologia;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsTipologia extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_insTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsTipologia frame = new InsTipologia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsTipologia() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblTipologia = new JLabel("Inserisci nuova tipologia");
		panel.add(lblTipologia);

		txt_insTipo = new JTextField();
		panel.add(txt_insTipo);
		txt_insTipo.setColumns(10);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		JButton btnSalva = new JButton("Salva");
		panel_2.add(btnSalva);
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_insTipo.getText().isBlank())
					JOptionPane.showMessageDialog(InsTipologia.this, "Inserisci una tipologia");
				else {
					Tipologia t = new Tipologia(txt_insTipo.getText().toUpperCase());
					BolletteView.aggiungiTipologia(t);
					JOptionPane.showMessageDialog(InsTipologia.this,
							"La tipologia è stata aggiunta con successo al database");
					txt_insTipo.setText("");
				}
			}

		});

	}

}
