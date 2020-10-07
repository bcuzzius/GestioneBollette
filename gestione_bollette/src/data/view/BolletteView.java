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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\dpasc\\OneDrive\\Desktop\\Programming\\JAVA\\Daniela\\gestione_bollette\\lib\\icon.png"));
		setTitle("Gestione Bollete by DomPascal");
		aggiornaListeDatabase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem menuItem_inserimento = new JMenuItem("Inserimento");

		mnFile.add(menuItem_inserimento);

		JMenuItem menuItem_visualizzazione = new JMenuItem("Visualizzazione");

		mnFile.add(menuItem_visualizzazione);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BorderLayout(0, 0));

		menuItem_inserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDesktop(new InsBolletta(tipologieList));
			}
		});

		menuItem_visualizzazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDesktop(new VisBolletta(bolletteList, tipologieList));
			}
		});
	}

	public static void aggiornaListeDatabase() {
		bolletteList = new ArrayList<Bolletta>(DAO.getBollette());
		tipologieList = new ArrayList<Tipologia>(DAO.getTipologie());
	}

	public void addDesktop(JInternalFrame jframe) {
		desktopPane.removeAll();
		((BasicInternalFrameUI) jframe.getUI()).setNorthPane(null);
		jframe.setBorder(null);
		jframe.setVisible(true);
		try {
			jframe.setMaximum(true);
		} catch (PropertyVetoException e) {

		}
		desktopPane.add(jframe);
	}

	public static void aggiungiBolletta(Bolletta newBolletta) {
		DAO.addBolletta(newBolletta);
		aggiornaListeDatabase();
	}

}
