package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Queen chess piece.
 */
public class Queen extends ChessPiece {
  private Color pieceColor;

  public Queen(Board board, Color color) {
    super(board, color);
    pieceColor = color;
  }

  @Override
  public String toString() {
    if (pieceColor == Color.WHITE) {
      return "♕";
    } else {
      return "♛";
    }

  }
}
