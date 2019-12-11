package fr.evonarx.connect4;
import java.awt.*;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.event.*;

public class GridGraphical extends JFrame implements MouseListener, ActionListener {
	
	private static final long serialVersionUID = -1322362820347666830L;
	
	//The main panel
	JPanel mainPanel = new JPanel();
	
	//The grid panel
	JPanel gridPanel = new JPanel();
	
	ImageIcon pionR = new ImageIcon("pionR.gif");
	ImageIcon pionV = new ImageIcon("pionV.gif");
	ImageIcon NoIcon = new ImageIcon("");
	
	//The status bar
	JLabel statusBar;
	
	
	public GridGraphical() {
	
		super("Connect 4");
		setSize(400, 300);
		setLocation(50, 50);
		
		mainPanel.setLayout(new BorderLayout());
		
		GridLayout gridLay;
		gridLay = new GridLayout(6, 7, 0, 0);
		//gridPanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		gridPanel.setLayout(gridLay);
		gridPanel.setName("the grid panel");
		
		makeCells();
		mainPanel.add(gridPanel, "Center");
		
		statusBar = new JLabel("It is player 1's turn", pionR, JLabel.LEFT);
		mainPanel.add(statusBar, "South");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setContentPane(mainPanel);
		setVisible(true);
	}
		
	
	/* Adds nbRow * nbCol cells in the GridGraphical object */	
	public void makeCells() {
		Cell c;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				c = new Cell(0, "name"+(5-i)+""+j); // create an  instance of Cell
				c.addMouseListener(this);
				gridPanel.add(c);
			}
		}	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("mouseClicked");
		
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		Cell src = (Cell)evt.getSource();
		//System.out.println("mousePressed : Cell name = "+src.name);
		if (!Game.endOfTheGame) src.process();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseReleased");
		
	}

	public void mouseEntered(MouseEvent evt) {
		//System.out.println("mouseEntered");
		Cell src = (Cell)evt.getSource();
		src.colorNextFreeCell(new Color(198, 198, 242));

	}

	
	@Override
	public void mouseExited(MouseEvent evt) {
		//System.out.println("mouseExited");
		Cell src = (Cell)evt.getSource();
		src.colorNextFreeCell(Color.white);
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
	public void actionPerformed(ActionEvent actionEvent) {
		JButton src = (JButton)actionEvent.getSource();
		if (src == undo) {
			Cell c = (Cell)this.pane.getComponent(0); // On prend par exemple la 1ère case pour récupérer le Jeu
			c.jeu.undo();
		}
		else if (src == saveas) {
			Cell c = (Cell)this.pane.getComponent(0);
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				c.jeu.enregistrer(fc.getSelectedFile());
		}
		else if (src == open) {
			Cell c = (Cell)this.pane.getComponent(0);
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				c.jeu.ouvrir(fc.getSelectedFile());
		}
		else if (src == nouv) {
			Jeu.nouveauJeu();
		}
		
		else if (src == comput) {
			Cell c = (Cell)this.pane.getComponent(0);
			c.jeu.ordiJoue();
		}
	}
	*/	
	
	
	
}
