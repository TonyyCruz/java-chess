package chess;

import boardgame.BoardException;
import boardgame.Position;

/**
 * This manage the position of the pieces in the chess game.
 */
public class ChessPosition {
  private char column;
  private int row;

  /**
   * This receive a chess position from 'a1' to 'h8' and returns the correspondent position in a
   * stance of Position class.
   */
  public ChessPosition(char column, int row) {
    if (column < 'a' || column > 'h' || row < 1 || row > 8) {
      throw new BoardException("ChessPosition error: Values need to be from 'a1' tos 'h8'.");
    }
    this.column = column;
    this.row = row;
  }

  public char getColumn() {
    return column;
  }

  public int getRow() {
    return row;
  }

  /**
   * This receive a chess position "from a1 to h8" and returns the respective position in the
   * matrix.
   */
  protected Position toPosition() {
    int matrixRow = 8 - row;
    int matrixColumn = column - 'a';

    return new Position(matrixRow, matrixColumn);
  }

  /**
   * This receive a position and returns a chess position "a1 to h8".
   */
  protected static ChessPosition fromPosition(Position position) {
    int chessRow = 8 - position.getRow();
    char chessColumn = (char) ('a' + position.getColumn());

    return new ChessPosition(chessColumn, chessRow);
  }

  @Override
  public String toString() {
    return String.format("%s%d", column, row);
  }
}
