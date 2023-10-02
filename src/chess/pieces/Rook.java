package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

/**
 * This creates the Rook chess piece.
 */
public class Rook extends ChessPiece {
  private Color pieceColor;

  public Rook(Board board, Color color) {
    super(board, color);
    pieceColor = color;
  }

  @Override
  public String toString() {
    if (pieceColor == Color.WHITE) {
      return "♖";
    } else {
      return "♜";
    }

  }
}
