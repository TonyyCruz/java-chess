package chess;

import boardgame.BoardException;

/**
 * This is the personal chess exception.
 */
public class ChessException extends BoardException {
  private static final long serialVersionUID = 1L;

  public ChessException(String msg) {
    super(msg);
  }
}
