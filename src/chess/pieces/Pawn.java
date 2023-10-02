package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Pawn chess piece.
 */
public class Pawn extends ChessPiece {
  private Color pieceColor;

  public Pawn(Board board, Color color) {
    super(board, color);
    pieceColor = color;
  }

  @Override
  public String toString() {
    if (pieceColor == Color.WHITE) {
      return "♙";
    } else {
      return "♟︎";
    }

  }
}
