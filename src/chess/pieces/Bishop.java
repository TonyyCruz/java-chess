package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Bishop chess piece.
 */
public class Bishop extends ChessPiece {

  public Bishop(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position(0, 0);

    // NW
    p.setValue(position.getRow() - 1, position.getColumn() - 1);
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setValue(p.getRow() - 1, p.getColumn() - 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // NE
    p.setValue(position.getRow() - 1, position.getColumn() + 1);
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setValue(p.getRow() - 1, p.getColumn() + 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SW
    p.setValue(position.getRow() + 1, position.getColumn() - 1);
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setValue(p.getRow() + 1, p.getColumn() - 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SE
    p.setValue(position.getRow() - 1, position.getColumn() + 1);
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setValue(p.getRow() + 1, p.getColumn() + 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♗";
    } else {
      return "♝";
    }

  }
}
