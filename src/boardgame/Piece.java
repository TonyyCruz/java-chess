package boardgame;

/**
 * Creating a generic piece.
 */
public abstract class Piece {
  protected Position position;
  private Board board;

  protected Piece(Board board) {
    this.board = board;
  }

  public Position getPosition() {
    return this.position;
  }

  protected Board getBoard() {
    return board;
  }

  public abstract boolean[][] possibleMoves();

  /**
   * This method receive a position and verify if the current piece can move for the position.
   */
  public boolean isMovementPossible(Position position) {
    return possibleMoves()[position.getRow()][position.getColumn()];
  }

  /**
   * This checks if the piece has some possible move.
   */
  public boolean isThereAnyPossibleMove(Position position) {
    boolean[][] matrix = possibleMoves();
    for (boolean[] row : matrix) {
      for (boolean column : row) {
        if (column) {
          return true;
        }
      }
    }
    return false;
  }
}
