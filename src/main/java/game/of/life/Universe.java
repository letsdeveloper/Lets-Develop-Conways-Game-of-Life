package game.of.life;

import game.of.life.Cell.CellState;

public class Universe {

	private Cell[][] state;
	
	public Universe(CellState[][] cellStates) {
		state = new Cell[cellStates.length][];
		for (int row = 0; row < cellStates.length; row++) {
			state[row] = new Cell[cellStates[row].length];
			for (int col = 0; col < cellStates[row].length; col++) {
				state[row][col] = new Cell(cellStates[row][col]);
			}
		}
	}

	public CellState[][] getState() {
		CellState[][] cellStates = new CellState[state.length][];
		for (int row = 0; row < state.length; row++) {
			cellStates[row] = new CellState[state[row].length];
			for (int col = 0; col < state[row].length; col++) {
				cellStates[row][col] = state[row][col].getState();
			}
		}
		return cellStates;
	}

}
