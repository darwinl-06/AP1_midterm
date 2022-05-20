package model;

import java.util.Collections;

/**
 * @author avillota
 * @since may 2022
 */
public class CrosswordController {
	
	/**
	 * Matrix of cells representing the crossword puzzle
	 */
	private Cell [][] crossword;
	
	/**
	 * method for initializing a crossword puzzle
	 * @param puzzle is a matrix of Strings containing 
	 * the initial state of a crossword puzzle
	 */
	public void initCrossword(String[][] puzzle) {

		int rows= puzzle.length;
		int columns= puzzle[0].length;

		crossword = new Cell[rows][columns];

		String letter = "";
		int number = 0;
		int cont = 0;

		CellType state = null;

		for(int i=0 ;i<rows ; i++) {
			
			for(int j=0 ;j<columns ; j++) {

				if(puzzle[i][j] != " ") {

					cont++;

					state = CellType.CLOSED;

					letter = puzzle[i][j];
					number = cont;

					Cell cell = new Cell(state,letter,number);
					crossword[i][j] = cell;


				} else {

					state = CellType.BLACK;

					Cell cell = new Cell(state,letter,number);

					crossword[i][j] = cell;

				}
			}
		}


	
	}
	/**
	 * Method to verify if a crossword puzzle is initialized
	 * @return boolean, true if it is initialized, else false
	 */
	public boolean isInitialized(){
		return crossword!=null;
	}
	
	/**
	 * Method to provide the dimensions of the crossword puzzle
	 * @return
	 */
	public int[] getGameDimensions() {
		int[] dims = new int[2];
		dims[0]= crossword.length;
		dims[1]= crossword[0].length;
		
		return dims;
	}
	
	public Cell[][] getCells(){
		return crossword;
	}
	/**
	 * 
	 * @param letter
	 * @return
	 */
	public String getHint(String letter) {

		int rows= crossword.length;
		int columns = crossword[0].length;

		String out = "";
		int cont = 0;

		boolean flag = false;

		for(int i=0 ;i<rows ; i++) {
			
			for(int j=0 ;j<columns ; j++) {
				
				if(flag != true){

					if(crossword[i][j].getLetter().equals(letter) && crossword[i][j].getState().equals(CellType.CLOSED) ) {	

					cont = crossword[i][j].getNumber();
					crossword[i][j].setState(CellType.OPEN);
					out = "Hay una palabra con esa letra " + letter + "  en el crucigrama en la casilla " + cont;

					flag = true;

					} else {

						out = "Lo siento, no hay palabras con la letra " + letter;
					}
				}
			}
		}
		
		return out;
	}
	
	/**
	 * 
	 * @param letter
	 * @param num
	 * @return
	 */
	public String evaluateCell(String letter, int num) {
		
		return null;
	}
	
	public String showCrossword() {
		int rows= crossword.length;
		int columns= crossword[0].length;
	
		String out="";
		String separator = "+---+ ";
		String line = "" + String.join("", Collections.nCopies(columns, separator));
		
		
		String numbers ="";
		String letters = "";
		int count =0;
		for(int i=0 ;i<rows ; i++) {
			numbers ="";
			letters ="";
			for(int j=0 ;j<columns ; j++) {
				count++;
				Cell actual = crossword[i][j];
				
				// numeros de dos cifras
				if (count>10) {
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"   ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}else //una cifra
				{
					//empty cell
					if (actual.getState()==CellType.BLACK) {
						numbers += " ---  ";
						letters += " ---  ";
						
					}else {
						numbers += " "+actual.getNumber() +"    ";
						letters += "    "+ actual.getLetter() + " ";
					}
				}
			}
			//por cada fila se imprimen las lineas
			out+= line + "\n";
			out+= numbers + "\n";
			out+= letters + "\n";
			
			
		}
		out+= line + "\n";
		return out;
	}


}
