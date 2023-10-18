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
    boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position nwPosition = new Position(position.getRow() - 1, position.getColumn() - 1);
    while (isEmptyPosition(nwPosition)) {
      matrix[nwPosition.getRow()][nwPosition.getColumn()] = true;
      nwPosition.setValue(nwPosition.getRow() - 1, nwPosition.getColumn() - 1);
    }
    if (getBoard().positionExists(nwPosition) && isThereOpponentPiece(nwPosition)) {
      matrix[nwPosition.getRow()][nwPosition.getColumn()] = true;
    }
    Position nePosition = new Position(position.getRow() - 1, position.getColumn() + 1);
    while (isEmptyPosition(nePosition)) {
      matrix[nePosition.getRow()][nePosition.getColumn()] = true;
      nePosition.setValue(nePosition.getRow() - 1, nePosition.getColumn() + 1);
    }
    if (getBoard().positionExists(nePosition) && isThereOpponentPiece(nePosition)) {
      matrix[nePosition.getRow()][nePosition.getColumn()] = true;
    }
    Position swPosition = new Position(position.getRow() + 1, position.getColumn() - 1);
    while (isEmptyPosition(swPosition)) {
      matrix[swPosition.getRow()][swPosition.getColumn()] = true;
      swPosition.setValue(swPosition.getRow() + 1, swPosition.getColumn() - 1);
    }
    if (getBoard().positionExists(swPosition) && isThereOpponentPiece(swPosition)) {
      matrix[swPosition.getRow()][swPosition.getColumn()] = true;
    }
    Position sePosition = new Position(position.getRow() + 1, position.getColumn() + 1);
    while (isEmptyPosition(sePosition)) {
      matrix[sePosition.getRow()][sePosition.getColumn()] = true;
      sePosition.setValue(sePosition.getRow() + 1, sePosition.getColumn() + 1);
    }
    if (getBoard().positionExists(sePosition) && isThereOpponentPiece(sePosition)) {
      matrix[sePosition.getRow()][sePosition.getColumn()] = true;
    }
    return matrix;
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
