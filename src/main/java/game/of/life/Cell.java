package game.of.life;

public class Cell {

	private CellState state;

	public Cell(CellState state) {
		this.state = state;
	}

	public enum CellState {
		ALIVE, DEAD
	}

	public CellState GetNextState(int i) {
		if (state == CellState.ALIVE) {
			return i > 1 && i < 4 ? CellState.ALIVE : CellState.DEAD;
		} else {
			return i == 3 ? CellState.ALIVE : CellState.DEAD;
		}
	}

	public CellState getState() {
		return state;
	}
}
