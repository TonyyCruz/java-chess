package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

/**
 * This creates the king chess piece.
 */
public class King extends ChessPiece {
  private Color pieceColor;

  public King(Board board, Color color) {
    super(board, color);
    pieceColor = color;
  }

  @Override
  public String toString() {
    if (pieceColor == Color.WHITE) {
      return "♔";
    } else {
      return "♚";
    }
  }
}
