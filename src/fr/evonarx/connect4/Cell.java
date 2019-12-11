package fr.evonarx.connect4;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Cell extends JPanel {
	
	private static final long serialVersionUID = 36172644784724253L;

	//a cell is defined by its name, its value and its background color;
	
	String name; //name + row number + column number
	//name50 name51 name52 name53 name54 name55 name56
	//name40 name41 name42 name43 name44 name45 name46
	//name30 name31 name32 name33 name34 name35 name36
	//name20 name21 name22 name23 name24 name25 name26
	//name10 name11 name12 name13 name14 name15 name16
	//name00 name01 name02 name03 name04 name05 name06
	
	int value; //1 = player 1 ; 2 = player 2
	Color bgCol;
	
	// A constructor
	public Cell() {
		super();
	}
	
	// Another constructor
	public Cell(int value, String name) {
		super();
	
		this.value = value;
		this.bgCol = Color.white;
		
		this.name = name;
		
	}
	
	public String getName() {
		return name;
	}

	
	public void changeVal(int value) {
		this.value = value;
		this.bgCol = Color.white; // Pour bien confirmer le fond blanc de la case
		repaint();
	}
	
	public void changeBg(Color color) {
		this.bgCol = color;
		repaint();
	}
	
	/* Only for the graphical grid 					*/
	/* Extract the column number from a cell name 	*/
	public int extractColumnNbFromACellName(String name) { //name(= 4 letters) + row number + column number => example : name00
		String s = name.substring(5, 6);
		return (Integer.parseInt(s));		
	}
	
	
	/* Only for the graphical grid 												*/
	/* Color the next free cell of a column with the color given in parameter 	*/
	public void colorNextFreeCell(Color color)
	{
		
		String cellName = this.getName();
		int column = extractColumnNbFromACellName(cellName);
		
		// Where is the next free cell in this column ?
		int result = Game.gameGrid.GetTheNextFreeRowInThisColumn(column);
			
		if (result != -1) {
			
			String nameToFind = "name"+result+""+column;
			JPanel jp = (JPanel)this.getParent();
			Cell resultCell = getCellByName(jp, nameToFind);
			
			resultCell.changeBg(color);
			repaint();
		}	
	}
	
	/* Only for the graphical grid 														*/
	/* Manage every possibilities of the graphical input until the end of the game 		*/
	public void process()
	{
		JLabel jl = GameGraphical.gridG.statusBar;
		jl.setHorizontalAlignment(JLabel.LEFT);
		
		String cellName = this.getName(); //get the name of the cell
		int column = extractColumnNbFromACellName(cellName); // extract the column number
		
		int row = Game.gameGrid.GetTheNextFreeRowInThisColumn(column); //get the next free row in this column
		if (row != -1) {
			
			Game.changePlayer = Game.gameGrid.putTokenInTheColum(column, Game.player.getCurrent_player());
			String nameToFind = "name"+(row)+""+column;	
			JPanel jp = (JPanel)this.getParent();
			Cell resultCell = this.getCellByName(jp, nameToFind);
					
			resultCell.changeBg(Color.white);
			char c = Game.player.getCurrent_player();
			int val = 0;
			if (c == '1') val = 1;
			else val = 2;
			
			resultCell.changeVal(val);
			repaint();
			//is there a winner ?
			//System.out.println("process - current player = "+Game.player.getCurrent_player());
			
			if (!Game.endOfTheGame) {
				if (!Game.gameGrid.isThereAWinner(Game.player.getCurrent_player())) {
					if (!Game.gameGrid.isGridFull()) {
						if (Game.changePlayer == true ) {
							Game.player.changePlayer(Game.player.getCurrent_player());
							
							//System.out.println("process - current player changed = "+Game.player.getCurrent_player() );
					
							if (Game.player.getCurrent_player() == '1')
							{
								jl.setText("It is player 1's turn");
								jl.setIcon(GameGraphical.gridG.pionR);
							}		
							else {
								jl.setText("It is player 2's turn");
								jl.setIcon(GameGraphical.gridG.pionV);
								
							}
							jl.updateUI();
							repaint();
						}
						
					}
					else {
							Game.endOfTheGame= true;
							jl.setIcon(GameGraphical.gridG.NoIcon);
							jl.setText("DRAW...");
					}
				}
					
				else {
					Game.endOfTheGame= true;
					jl.setIcon(GameGraphical.gridG.NoIcon);
					jl.setText("WINNER : PLAYER "+Game.player.getCurrent_player());
				}
			}
			
			
		} else {
			jl.setIcon(GameGraphical.gridG.NoIcon);
			jl.setText("COLUMN "+(column+1)+" IS FULL : PLAYER "+ Game.player.getCurrent_player()+ " HAS TO PLAY");
		}
			 
	}
	
	
	/* Only for the graphical grid 								*/
	/* Get the object cell by the name given in parameter 		*/
	public Cell getCellByName(JPanel jp, String name) {
		for(Component child: jp.getComponents()){
		    if(child instanceof Cell){
		    	Cell cellt = (Cell) child;
		    	if (cellt.name.equals(name)) return cellt;
		    }
		}
		return null;
		
	}
	
	/*
	 	Who calls paintComponent ?
		When you subclass JComponent or JPanel to draw graphics, override the paintComponent() method. This method is called because the user did something with the user interface that required redrawing, or your code has explicitly requested that it be redrawn.

	   	Called automatically when it becomes visible
		When a window becomes visible (uncovered or deminimized) or is resized, the "system" automatically calls the paintComponent() method for all areas of the screen that have to be redrawn.
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics comp) {
		Graphics2D comp2D = (Graphics2D)comp;
		
		 comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 RenderingHints.VALUE_ANTIALIAS_ON); // Pour l'antialias
		 
		
		//the background color
		comp2D.setColor(bgCol);
		
		int Width = getWidth();
		int Height = getHeight();
		//System.out.println("Width = "+Width+" Height = "+Height);
		comp2D.fillRect(0, 0, getWidth(), getHeight());
		
		//the borders of the rectangle
		comp2D.setColor(Color.black);
		comp2D.drawRect(0, 0, getWidth(), getHeight());
               
		if (value != 0) {
			if (value == 1)
				comp2D.setColor(Color.red);
			else
				comp2D.setColor(Color.green);
			
			//Draws the outline of a circular or elliptical arc covering the specified rectangle.
			comp2D.fillOval(3, 3, getWidth() - 4, getHeight() - 4);
		}
	}
	
}
