package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Horse chess piece.
 */
public class Horse extends ChessPiece {

  public Horse(Board board, Color color) {
    super(board, color);
  }

  /**
   * Check if the received position can be used.
   */
  private boolean canMove(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p == null || isThereOpponentPiece(position);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position();

    p.setValue(position.getRow() - 2, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() - 2, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() + 2, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() + 2, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() - 1, position.getColumn() - 2);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() - 1, position.getColumn() + 2);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() + 1, position.getColumn() - 2);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValue(position.getRow() + 1, position.getColumn() + 2);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♘";
    } else {
      return "♞";
    }

  }
}
