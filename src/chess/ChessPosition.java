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
   * This stance the class with values from 'a1' to 'h8'.s
   */
  public ChessPosition(char column, int row) {
    if (column < 'a' || column > 'h' || row < 1 || row > 8) {
      throw new BoardException("Chess position error: Values need to be from 'a1' tos 'h8'.");
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

  protected Position toPosition() {
    int matrixRow = 8 - row;
    int matrixColumn = column - 'a';

    return new Position(matrixRow, matrixColumn);
  }

  protected static ChessPosition fromPosition(Position position) {
    char chessColumn = (char) ('a' - position.getColumn());
    int chessRow = 8 - position.getRow();

    return new ChessPosition(chessColumn, chessRow);
  }

  @Override
  public String toString() {
    return String.format("%s%d", column, row);
  }
}
