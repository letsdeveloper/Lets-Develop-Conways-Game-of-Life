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

	public void update() {
		updateState(getNeighborCounts());
	}

	private int[][] getNeighborCounts() {
		int[][] numbersOfAliveNeighbors = new int[state.length][];
		for (int row = 0; row < state.length; row++) {
			numbersOfAliveNeighbors[row] = new int[state[row].length];
			for (int col = 0; col < state[row].length; col++) {
				numbersOfAliveNeighbors[row][col] = getNumberOfAliveNeighbors(row, col);
			}
		}
		return numbersOfAliveNeighbors;
	}

	private void updateState(int[][] numbersOfAliveNeighbors) {
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				state[row][col].update(numbersOfAliveNeighbors[row][col]);
			}
		}
	}

	private int getNumberOfAliveNeighbors(int row, int col) {
		int numberOfAliveNeighbor = 0;
		numberOfAliveNeighbor += getNumberOfAliveNeighborsInRow(row - 1, col);
		if (getCell(row, col - 1).isAlive()) numberOfAliveNeighbor++;
		if (getCell(row, col + 1).isAlive()) numberOfAliveNeighbor++;
		numberOfAliveNeighbor += getNumberOfAliveNeighborsInRow(row + 1, col);
		return numberOfAliveNeighbor;
	}

	private int getNumberOfAliveNeighborsInRow(int row, int col) {
		int numberOfAliveNeighbor = 0;
		if (row >= 0 && row < state.length) {
			if (getCell(row, col - 1).isAlive()) numberOfAliveNeighbor++;
			if (getCell(row, col).isAlive()) numberOfAliveNeighbor++;
			if (getCell(row, col + 1).isAlive()) numberOfAliveNeighbor++;
		}
		return numberOfAliveNeighbor;
	}
	
	private Cell getCell(int row, int col) {
		if (col >= 0 && col < state[row].length) {
			return state[row][col];
		} else {
			return new Cell(CellState.DEAD);
		}
	}
}
