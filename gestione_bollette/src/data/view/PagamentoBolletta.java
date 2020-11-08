package data.view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import data.model.Bolletta;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PagamentoBolletta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtGGPagamento;
	private JTextField txtMMPagamento;
	private JTextField txtAApagamento;

	public PagamentoBolletta(Bolletta bolletta, VisBolletta visBolletta) {
		setBounds(250, 250, 700, 300);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		setTitle("Pagamento Bolletta");
		JPanel panelInfoBolletta = new JPanel();
		getContentPane().add(panelInfoBolletta);
		panelInfoBolletta.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel infoBollettaLbl = new JLabel("");
		infoBollettaLbl.setFont(new Font("Tahoma", Font.BOLD, 10));
		infoBollettaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfoBolletta.add(infoBollettaLbl);
		infoBollettaLbl.setText(bolletta.toString());

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Inserisci Data Pagamento");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);

		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("GIORNO");
		panel_9.add(lblNewLabel_2);

		txtGGPagamento = new JTextField();
		panel_9.add(txtGGPagamento);
		txtGGPagamento.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("MESE");
		panel_9.add(lblNewLabel_3);

		txtMMPagamento = new JTextField();
		panel_9.add(txtMMPagamento);
		txtMMPagamento.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("ANNO");
		panel_9.add(lblNewLabel_4);

		txtAApagamento = new JTextField();
		panel_9.add(txtAApagamento);
		txtAApagamento.setColumns(10);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);

		JButton btnSalva = new JButton("PAGA");
		panel_2.add(btnSalva);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);

		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);

		JPanel panel_5 = new JPanel();
		getContentPane().add(panel_5);

		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6);

		JPanel panel_7 = new JPanel();
		getContentPane().add(panel_7);

		JPanel panel_8 = new JPanel();
		getContentPane().add(panel_8);

		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAApagamento.getText().isBlank() || txtMMPagamento.getText().isBlank()
						|| txtGGPagamento.getText().isBlank())
					JOptionPane.showMessageDialog(PagamentoBolletta.this, "Inserisci una data di pagamento");

				else {
					LocalDate data = LocalDate.of(Integer.parseInt(txtAApagamento.getText()),
							Integer.parseInt(txtMMPagamento.getText()), Integer.parseInt(txtGGPagamento.getText()));
					if (data.isBefore(bolletta.getDataEmissione()))
						JOptionPane.showMessageDialog(PagamentoBolletta.this,
								"La data inserita è anteriore alla data di emissione");
					else {
						BolletteView.pagaBolletta(bolletta, data);
						JOptionPane.showMessageDialog(PagamentoBolletta.this,
								"E' stata inserita una data di pagamento per la bolletta selezionata");
						visBolletta.refresh();
						PagamentoBolletta.this.dispose();
					}
				}
			}
		});
	}

}
