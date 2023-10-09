package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This creates the king chess piece.
 */
public class King extends ChessPiece {

  public King(Board board, Color color) {
    super(board, color);
  }

  private boolean canMove(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p == null || p.getColor() != getColor();
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position();

    // Above
    p.setValue(position.getRow() - 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // Below
    p.setValue(position.getRow() + 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // Left
    p.setValue(position.getRow(), position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // Right
    p.setValue(position.getRow(), position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // NW
    p.setValue(position.getRow() - 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // NE
    p.setValue(position.getRow() - 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SW
    p.setValue(position.getRow() + 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SE
    p.setValue(position.getRow() + 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♔";
    } else {
      return "♚";
    }
  }
}
