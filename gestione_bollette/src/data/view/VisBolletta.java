package data.view;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DAO;
import data.model.Bolletta;
import data.model.Tipologia;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VisBolletta extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txt_ricerca;
	private DefaultTableModel modello;
	private ArrayList<Bolletta> bolletteList;
	private ArrayList<Tipologia> tipologieList;
	private JComboBox<String> combo_stato;
	private JComboBox<Tipologia> comboTipologia;
	private JComboBox<String> combo_anno;

	public VisBolletta(ArrayList<Bolletta> bollette, ArrayList<Tipologia> tipologie) {
		bolletteList = new ArrayList<Bolletta>(bollette);
		tipologieList = new ArrayList<Tipologia>(tipologie);
		setResizable(true);
		setTitle("Visualizzazione bollette");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		modello = new DefaultTableModel();
		table = new JTable(modello);
		modello.addColumn("Commento");
		modello.addColumn("Indirizzo");
		modello.addColumn("Tipologia");
		modello.addColumn("Cifra");
		modello.addColumn("Data Emissione");
		modello.addColumn("Data Scadenza");
		modello.addColumn("Data Pagamento");
		modello.addColumn("Key");
		table.removeColumn(table.getColumn("Key"));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblRicerca = new JLabel("Ricerca");
		panel.add(lblRicerca);

		txt_ricerca = new JTextField();

		txt_ricerca.setColumns(8);
		panel.add(txt_ricerca);

		JLabel lbl_tipologia = new JLabel("Tipologia");
		panel.add(lbl_tipologia);

		comboTipologia = new JComboBox<Tipologia>();
		comboTipologia.addItem(new Tipologia("TUTTE"));
		tipologieList.forEach(t -> comboTipologia.addItem(t));

		panel.add(comboTipologia);

		JLabel lblData = new JLabel("Anno");
		panel.add(lblData);

		combo_anno = new JComboBox<String>();
		combo_anno.addItem("TUTTI");
		bolletteList.stream().map(bolletta -> bolletta.getDataEmissione().getYear() + "").distinct()
				.forEach(anno -> combo_anno.addItem(anno));
		panel.add(combo_anno);

		JLabel lbl_selezionePagate = new JLabel("Stato");
		panel.add(lbl_selezionePagate);

		combo_stato = new JComboBox<String>();
		combo_stato.addItem("TUTTE");
		combo_stato.addItem("PAGATE");
		combo_stato.addItem("NON PAGATE");
		panel.add(combo_stato);

		comboTipologia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cercaBolletta();
			}
		});

		combo_anno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cercaBolletta();
			}
		});

		combo_stato.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cercaBolletta();
			}
		});

		txt_ricerca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cercaBolletta();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (table.getValueAt(table.getSelectedRow(), 6).equals("DA PAGARE")) {
					int scelta = JOptionPane.showConfirmDialog(VisBolletta.this,
							"Vuoi pagare la bolletta selezionata?");
					if (scelta == JOptionPane.YES_OPTION) {
						Bolletta b = DAO.getBolletta(
								Long.parseLong((String) table.getModel().getValueAt(table.getSelectedRow(), 7)));
						BolletteView.openPagamentoBolletta(new PagamentoBolletta(b, VisBolletta.this));
					}
				}

			}

		});

		showAll();
	}

	public void refresh() {
		bolletteList = DAO.getBollette();
		showAll();
	}

	public void showAll() {
		azzeraTabella();
		bolletteList.stream().sorted((b1, b2) -> {
			if (b1.getDataPagamento() == null)
				return -1;
			else {
				return 1;
			}
		}).forEach(bolletta -> modello.addRow(bolletta.toTab()));
	}

	private void azzeraTabella() {
		modello.setRowCount(0);
	}

	private void cercaBolletta() {
		List<Bolletta> ricerca = new ArrayList<Bolletta>(bolletteList);
		String ricercaT = txt_ricerca.getText();
		if (!ricercaT.isBlank())
			ricerca = ricerca.stream().filter(b -> {
				if (b.getCommento().toUpperCase().contains(ricercaT.toUpperCase())
						|| b.getIndirizzo().toUpperCase().contains(ricercaT.toUpperCase())
						|| b.getTipologia().getNomeTipologia().toUpperCase().contains(ricercaT.toUpperCase())
						|| String.valueOf(b.getCifra()).toUpperCase().contains(ricercaT.toUpperCase()))
					return true;
				return false;
			}).collect(Collectors.toList());
		Tipologia tipoC = (Tipologia) comboTipologia.getSelectedItem();
		if (!tipoC.getNomeTipologia().equals("TUTTE"))
			ricerca = ricerca.stream()
					.filter(bolletta -> bolletta.getTipologia().getNomeTipologia().equals(tipoC.getNomeTipologia()))
					.collect(Collectors.toList());
		String statoC = (String) combo_stato.getSelectedItem();
		if (!statoC.equals("TUTTE"))
			if (statoC.equals("PAGATE"))
				ricerca = ricerca.stream().filter(bolletta -> bolletta.getDataPagamento() != null)
						.collect(Collectors.toList());
			else {
				ricerca = ricerca.stream().filter(bolletta -> bolletta.getDataPagamento() == null)
						.collect(Collectors.toList());
			}
		String annoC = (String) combo_anno.getSelectedItem();
		if (!annoC.equals("TUTTI"))
			ricerca = ricerca.stream()
					.filter(bolletta -> bolletta.getDataEmissione().getYear() == Integer.parseInt(annoC))
					.collect(Collectors.toList());

		azzeraTabella();
		ricerca.stream().sorted((b1, b2) -> {
			if (b1.getDataPagamento() == null)
				return -1;
			else {
				return 1;
			}
		}).forEach(bolletta -> modello.addRow(bolletta.toTab()));
	}
}
