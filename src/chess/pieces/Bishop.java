package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Bishop chess piece.
 */
public class Bishop extends ChessPiece {
  private Color pieceColor;

  public Bishop(Board board, Color color) {
    super(board, color);
    pieceColor = color;
  }

  @Override
  public String toString() {
    if (pieceColor == Color.WHITE) {
      return "♗";
    } else {
      return "♝";
    }

  }
}
