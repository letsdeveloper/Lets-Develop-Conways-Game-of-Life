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
		CellState[][] cellStates = getState();
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				int numberOfAliveNeighbors = getNumberOfAliveNeighbors(cellStates, row, col);
				state[row][col].update(numberOfAliveNeighbors);
			}
		}
	}

	private int getNumberOfAliveNeighbors(CellState[][] state, int row, int col) {
		int numberOfAliveNeighbor = 0;
		if (row > 0) {
			int rowAbove = row - 1;
			numberOfAliveNeighbor += getNumberOfAliveNeighborsInRow(state, rowAbove, col);
		}

		numberOfAliveNeighbor += getCountIfCellIsAlive(state, row, col - 1);
		numberOfAliveNeighbor += getCountIfCellIsAlive(state, row, col + 1);

		if (row < state.length - 1) {
			int rowBelow = row + 1;
			numberOfAliveNeighbor += getNumberOfAliveNeighborsInRow(state, rowBelow, col);
		}

		return numberOfAliveNeighbor;
	}

	private int getNumberOfAliveNeighborsInRow(CellState[][] state, int row, int col) {
		int numberOfAliveNeighbor = 0;
		numberOfAliveNeighbor += getCountIfCellIsAlive(state, row, col - 1);
		if (state[row][col] == CellState.ALIVE) {
			numberOfAliveNeighbor++;
		}
		numberOfAliveNeighbor += getCountIfCellIsAlive(state, row, col + 1);
		return numberOfAliveNeighbor;
	}

	private int getCountIfCellIsAlive(CellState[][] state, int row, int col) {
		if (col >= 0 && col < state[row].length) {
			if (state[row][col + 1] == CellState.ALIVE) {
				return 1;
			}
		}
		return 0;
	}

}
