package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This creates the Rook chess piece.
 */
public class Rook extends ChessPiece {

  public Rook(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position();

    // Above
    p.setValue(position.getRow() - 1, position.getColumn());
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setRow(p.getRow() - 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // below
    p.setValue(position.getRow() + 1, position.getColumn());
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setRow(p.getRow() + 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // // left
    p.setValue(position.getRow(), position.getColumn() - 1);
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setColumn(p.getColumn() - 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // right
    p.setValue(position.getRow(), position.getColumn() + 1);
    while (getBoard().positionExists(p) && !getBoard().thereIsApiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
      p.setColumn(p.getColumn() + 1);
    }

    if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♖";
    } else {
      return "♜";
    }
  }
}
