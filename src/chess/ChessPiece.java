package chess;

import boardgame.Board;
import boardgame.Piece;


/**
 * This class is responsible for instantiating chess pieces.
 */
public abstract class ChessPiece extends Piece {
  private Color color;

  public ChessPiece(Board board, Color color) {
    super(board);
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

}
