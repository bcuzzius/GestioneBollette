package data.view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.model.Bolletta;
import data.model.Tipologia;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class InsBolletta extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCommento;
	private JTextField txtIndirizzo;
	private JTextField txtCifra;
	private JTextField ggEmissione;
	private JTextField mmEmissione;
	private JTextField aaEmissione;
	private JTextField ggScadenza;
	private JTextField mmScadenza;
	private JTextField aaScadenza;
	private JTextField ggPagamento;
	private JTextField mmPagamento;
	private JTextField aaPagamento;

	public InsBolletta(ArrayList<Tipologia> tipologieList) {
		setResizable(true);
		setTitle("Inserimento bolletta");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(9, 1, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("COMMENTO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		txtCommento = new JTextField();
		txtCommento.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(txtCommento);
		txtCommento.setColumns(10);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel Indirizzo = new JLabel("INDIRIZZO");
		Indirizzo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(Indirizzo);

		txtIndirizzo = new JTextField();
		panel_1.add(txtIndirizzo);
		txtIndirizzo.setColumns(10);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel Tipologia = new JLabel("TIPOLOGIA");
		Tipologia.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(Tipologia);

		JComboBox<Tipologia> comboTipologia = new JComboBox<Tipologia>();
		tipologieList.forEach(t -> comboTipologia.addItem(t));
		panel_2.add(comboTipologia);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel Cifra = new JLabel("CIFRA");
		Cifra.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(Cifra);

		txtCifra = new JTextField();
		panel_3.add(txtCifra);
		txtCifra.setColumns(10);

		JPanel panel9 = new JPanel();
		getContentPane().add(panel9);
		panel9.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel DataEmissione = new JLabel("DATA EMISSIONE");
		DataEmissione.setHorizontalAlignment(SwingConstants.CENTER);
		panel9.add(DataEmissione);

		JPanel panel_4 = new JPanel();
		panel9.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel giorno = new JLabel("GIORNO");
		panel_4.add(giorno);

		ggEmissione = new JTextField();
		panel_4.add(ggEmissione);
		ggEmissione.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("MESE");
		panel_4.add(lblNewLabel_2);

		mmEmissione = new JTextField();
		panel_4.add(mmEmissione);
		mmEmissione.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("ANNO");
		panel_4.add(lblNewLabel_3);

		aaEmissione = new JTextField();
		panel_4.add(aaEmissione);
		aaEmissione.setColumns(10);

		JPanel panel9_1 = new JPanel();
		getContentPane().add(panel9_1);
		panel9_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblDataScadenza = new JLabel("DATA SCADENZA");
		lblDataScadenza.setHorizontalAlignment(SwingConstants.CENTER);
		panel9_1.add(lblDataScadenza);

		JPanel panel_4_1 = new JPanel();
		panel9_1.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel giorno_1 = new JLabel("GIORNO");
		panel_4_1.add(giorno_1);

		ggScadenza = new JTextField();
		ggScadenza.setColumns(10);
		panel_4_1.add(ggScadenza);

		JLabel lblNewLabel_2_1 = new JLabel("MESE");
		panel_4_1.add(lblNewLabel_2_1);

		mmScadenza = new JTextField();
		mmScadenza.setColumns(10);
		panel_4_1.add(mmScadenza);

		JLabel lblNewLabel_3_1 = new JLabel("ANNO");
		panel_4_1.add(lblNewLabel_3_1);

		aaScadenza = new JTextField();
		aaScadenza.setColumns(10);
		panel_4_1.add(aaScadenza);

		JPanel panel9_2 = new JPanel();
		getContentPane().add(panel9_2);
		panel9_2.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblDataPagamento = new JLabel("DATA PAGAMENTO");
		lblDataPagamento.setHorizontalAlignment(SwingConstants.CENTER);
		panel9_2.add(lblDataPagamento);

		JPanel panel_4_2 = new JPanel();
		panel9_2.add(panel_4_2);
		panel_4_2.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel giorno_2 = new JLabel("GIORNO");
		panel_4_2.add(giorno_2);

		ggPagamento = new JTextField();
		ggPagamento.setColumns(10);
		panel_4_2.add(ggPagamento);

		JLabel lblNewLabel_2_2 = new JLabel("MESE");
		panel_4_2.add(lblNewLabel_2_2);

		mmPagamento = new JTextField();
		mmPagamento.setColumns(10);
		panel_4_2.add(mmPagamento);

		JLabel lblNewLabel_3_2 = new JLabel("ANNO");
		panel_4_2.add(lblNewLabel_3_2);

		aaPagamento = new JTextField();
		aaPagamento.setColumns(10);
		panel_4_2.add(aaPagamento);

		JPanel panel9_3 = new JPanel();
		getContentPane().add(panel9_3);
		panel9_3.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_4_3 = new JPanel();
		panel9_3.add(panel_4_3);
		panel_4_3.setLayout(new GridLayout(0, 6, 0, 0));

		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		JButton btn_reset = new JButton("RESET");
		btn_reset.setBackground(new Color(204, 102, 102));
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCommento.setText("");
				txtIndirizzo.setText("");
				txtCifra.setText("");
				ggEmissione.setText("");
				mmEmissione.setText("");
				aaEmissione.setText("");
				ggPagamento.setText("");
				mmPagamento.setText("");
				aaPagamento.setText("");
				ggScadenza.setText("");
				mmScadenza.setText("");
				aaScadenza.setText("");
				txtCommento.requestFocus();
			}
		});
		panel_5.add(btn_reset);

		JButton btn_salva = new JButton("SALVA");
		btn_salva.setBackground(new Color(102, 204, 102));
		btn_salva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bolletta newBolletta = new Bolletta();
				if (txtCommento.getText().isBlank()) {
					JOptionPane.showMessageDialog(InsBolletta.this, "Inserisci commento");
					return;
				} else {
					newBolletta.setCommento(txtCommento.getText());
				}
				if (txtIndirizzo.getText().isBlank()) {
					JOptionPane.showMessageDialog(InsBolletta.this, "Inserisci indirizzo");
					return;
				} else {
					newBolletta.setIndirizzo(txtIndirizzo.getText());
				}
				newBolletta.setTipologia((Tipologia) comboTipologia.getSelectedItem());
				try {
					newBolletta.setCifra(Double.parseDouble(txtCifra.getText()));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(InsBolletta.this, "Inserisci una cifra valida");
					return;
				}
				try {
					int giorno = Integer.parseInt(ggEmissione.getText());
					int mese = Integer.parseInt(mmEmissione.getText());
					int anno = Integer.parseInt(aaEmissione.getText());
					if (anno < 1900)
						throw new Exception();
					LocalDate emissione = LocalDate.of(anno, mese, giorno);
					newBolletta.setDataEmissione(emissione);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(InsBolletta.this, "Inserisci una data di emissione valida");
					return;
				}
				try {
					int giorno = Integer.parseInt(ggScadenza.getText());
					int mese = Integer.parseInt(mmScadenza.getText());
					int anno = Integer.parseInt(aaScadenza.getText());
					LocalDate scadenza = LocalDate.of(anno, mese, giorno);
					if (newBolletta.getDataEmissione().isAfter(scadenza))
						throw new Exception();
					newBolletta.setDataScadenza(scadenza);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(InsBolletta.this, "Inserisci una data di scadenza valida");
					return;
				}
				try {
					if (!ggPagamento.getText().isBlank() || !mmPagamento.getText().isBlank()
							|| !aaPagamento.getText().isBlank()) {
						int giorno = Integer.parseInt(ggPagamento.getText());
						int mese = Integer.parseInt(mmPagamento.getText());
						int anno = Integer.parseInt(aaPagamento.getText());
						LocalDate pagamento = LocalDate.of(anno, mese, giorno);
						if (newBolletta.getDataEmissione().isAfter(pagamento))
							throw new Exception();
						newBolletta.setDataPagamento(pagamento);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(InsBolletta.this, "Inserisci una data di pagamento valida");
					return;
				}
				BolletteView.aggiungiBolletta(newBolletta);
				JOptionPane.showMessageDialog(InsBolletta.this,
						"La bolletta è stata aggiunta con successo al database");
			}
		});
		panel_5.add(btn_salva);
	}

}
