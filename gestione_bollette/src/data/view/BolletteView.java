package data.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import controller.DAO;
import data.model.Bolletta;
import data.model.Tipologia;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class BolletteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private static ArrayList<Bolletta> bolletteList;
	private static ArrayList<Tipologia> tipologieList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BolletteView frame = new BolletteView();
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
	public BolletteView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\dpasc\\OneDrive\\Desktop\\Programming\\JAVA\\Daniela\\gestione_bollette\\lib\\icon.png"));
		setTitle("Gestione Bollette by DomPascal");
		aggiornaListeDatabase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnBolletta = new JMenu("Bolletta");
		mnFile.add(mnBolletta);

		JMenuItem menuItem_inserimento = new JMenuItem("Inserimento");

		mnBolletta.add(menuItem_inserimento);

		JMenuItem menuItem_visualizzazione = new JMenuItem("Visualizzazione");
		mnBolletta.add(menuItem_visualizzazione);

		JMenu mnTipologia = new JMenu("Tipologia");
		mnFile.add(mnTipologia);

		JMenuItem menuItemInserimentoTipologia = new JMenuItem("Inserimento");

		mnTipologia.add(menuItemInserimentoTipologia);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BorderLayout(0, 0));

		menuItemInserimentoTipologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDesktop(new InsTipologia());
			}
		});
		menuItem_inserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDesktop(new InsBolletta(tipologieList));
			}
		});
		menuItem_visualizzazione.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addDesktop(new VisBolletta(bolletteList, tipologieList));
			}
		});

	}

	public static void aggiornaListeDatabase() {
		bolletteList = new ArrayList<Bolletta>(DAO.getBollette());
		tipologieList = new ArrayList<Tipologia>(DAO.getTipologie());
	}

	public static void aggiungiBolletta(Bolletta newBolletta) {
		DAO.addBolletta(newBolletta);
		aggiornaListeDatabase();
	}

	public static void aggiungiTipologia(Tipologia t) {
		DAO.addTipologia(t);
		aggiornaListeDatabase();
	}

	public static void pagaBolletta(Bolletta bolletta, LocalDate data) {
		DAO.addPagamentoBolletta(bolletta, data);
		aggiornaListeDatabase();
	}

	private void addDesktop(JInternalFrame jInternalFrame) {
		if (desktopPane.getSelectedFrame() != null)
			desktopPane.getSelectedFrame().dispose();
		((BasicInternalFrameUI) jInternalFrame.getUI()).setNorthPane(null);
		try {
			jInternalFrame.setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		jInternalFrame.setBorder(null);
		jInternalFrame.setVisible(true);
		desktopPane.add(jInternalFrame);
	}

	public static void openPagamentoBolletta(JFrame jframe) {
		jframe.setVisible(true);
	}

}
