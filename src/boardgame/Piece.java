package boardgame;

/**
 * This class is the chess pieces base.
 */
public abstract class Piece {
  protected Position position;
  private Board board;

  public Piece(Board board) {
    this.board = board;
  }

  protected Board getBoard() {
    return board;
  }

  public abstract boolean[][] possibleMoves();

  public boolean possibleMove(Position position) {
    return possibleMoves()[position.getRow()][position.getColumn()];
  }

  /**
   * This checks if the piece has some possible move.
   */
  public boolean isThereAnyPossibleMove(Position position) {
    boolean[][] mat = possibleMoves();

    for (int i = 0; i < mat.length; i += 1) {
      for (int j = 0; j < mat[i].length; j += 1) {
        if (mat[i][j]) {
          return true;
        }
      }
    }
    return false;
  }
}
