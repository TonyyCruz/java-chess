package boardgame;

/**
 * This is responsible for managing the board and showing the position of the pieces.
 */
public class Board {
  private int rows;
  private int columns;
  private Piece[][] pieces;

  /**
   * This start the board with the rows and columns received.
   */
  public Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    pieces = new Piece[rows][columns];
  }

  public int getRows() {
    return rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public int getColumns() {
    return columns;
  }

  public void setColumns(int columns) {
    this.columns = columns;
  }

  public Piece piece(int row, int column) {
    return pieces[row][column];
  }

  public Piece piece(Position position) {
    return pieces[position.getRow()][position.getColumn()];
  }
}
