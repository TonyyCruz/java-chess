package boardgame;

/**
 * This is responsible for managing the board and showing the position of the this.pieces.
 */
public class Board {
  private int rows;
  private int columns;
  private Piece[][] pieces;

  /**
   * This start the board with the rows and columns received.
   */
  public Board(int rows, int columns) {
    if (rows < 1 || columns < 1) {
      throw new BoardException("Board error: The board must have at least 1 row and 1 column.");
    }
    this.rows = rows;
    this.columns = columns;
    this.pieces = new Piece[rows][columns];
  }

  public int getRows() {
    return this.rows;
  }

  public int getColumns() {
    return this.columns;
  }

  /**
   * This returns the piece on the position received. If the position is not on the board, it will
   * raise an exception.
   */
  public Piece piece(int row, int column) {
    if (!positionExists(row, column)) {
      throw new BoardException("This position does not exist on the board.");
    }
    return this.pieces[row][column];
  }

  /**
   * This returns the piece on the position received. If the position is not on the board, it will
   * raise an exception.
   */
  public Piece piece(Position position) {
    if (!positionExists(position)) {
      throw new BoardException("Board error: This position does not exist on the board.");
    }
    return this.pieces[position.getRow()][position.getColumn()];
  }

  /**
   * This places the received piece in the received position on the board. if the position is not
   * free, it will raise an exception.
   */
  public void placePieece(Piece piece, Position position) {
    if (thereIsApiece(position)) {
      throw new BoardException("Board error: Already have one piece on the position" + position);
    }
    this.pieces[position.getRow()][position.getColumn()] = piece;
    piece.position = position;
  }

  /**
   * This remove the piece on the received position.
   */
  public Piece removePiece(Position position) {
    if (!positionExists(position)) {
      throw new BoardException("Board error: The position" + position + "does not exists.");
    }
    if (piece(position) == null) {
      return null;
    }
    Piece pieceToBeRemoved = piece(position);
    pieceToBeRemoved.position = null;
    this.pieces[position.getRow()][position.getColumn()] = null;
    return pieceToBeRemoved;
  }

  public boolean positionExists(int row, int column) {
    return row >= 0 && row < rows && column >= 0 && column < columns;
  }

  public boolean positionExists(Position position) {
    return positionExists(position.getRow(), position.getColumn());
  }

  /**
   * Returns true if there is a piece in the position and false if there is not.
   */
  public boolean thereIsApiece(Position position) {
    if (!positionExists(position)) {
      throw new BoardException("Board error: This position does not exist on the board.");
    }
    return piece(position) != null;
  }

}
