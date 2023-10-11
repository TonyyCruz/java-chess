package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.Horse;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * This class is responsible for the chess game.
 */
public class ChessMatch {
  private Board board;
  private int turn;
  private Color currentPlayer;
  private boolean check;
  private boolean checkMate;
  private ChessPiece enPassantVunerable;
  private ChessPiece promoted;

  private List<Piece> piecesOnTheBoard = new ArrayList<>();
  private List<Piece> capturedPieces = new ArrayList<>();

  /**
   * The class will be initialized with the default settings.
   */
  public ChessMatch() {
    this.board = new Board(8, 8);
    this.turn = 1;
    this.currentPlayer = Color.WHITE;
    initialSetup();
  }

  public int getTurn() {
    return turn;
  }

  /**
   * Inform if the King is in check.
   * 
   * @return boolean
   */
  public boolean getCheck() {
    return check;
  }

  /**
   * Inform if the King got a check mate.
   * 
   * @return boolean
   */
  public boolean getCheckMate() {
    return checkMate;
  }

  public Color getCurrentPlayer() {
    return currentPlayer;
  }

  public ChessPiece getEnPassantVunerable() {
    return enPassantVunerable;
  }

  public ChessPiece getPromoted() {
    return promoted;
  }

  /**
   * This returns an array with the current game progress.
   */
  public ChessPiece[][] getPieces() {
    ChessPiece[][] mat = new ChessPiece[board.getColumns()][board.getRows()];

    for (int r = 0; r < board.getRows(); r += 1) {
      for (int c = 0; c < board.getColumns(); c += 1) {
        mat[r][c] = (ChessPiece) board.piece(r, c);
      }
    }

    return mat;
  }

  /**
   * This method receive a piece, a column and a row. The received piece is added to the board in
   * the received position and is added on the piecesOnTheTable.
   * 
   * @param column
   *
   * @param row
   * 
   * @param piece
   * 
   */
  private void placeNewPiece(char column, int row, ChessPiece piece) {
    board.placePieece(piece, new ChessPosition(column, row).toPosition());

    piecesOnTheBoard.add(piece);
  }

  /**
   * This shows possible movements for the piece in the source position.
   */
  public boolean[][] possibleMoves(ChessPosition sourcePosition) {
    Position position = sourcePosition.toPosition();
    validateSourcePosition(position);

    return board.piece(position).possibleMoves();
  }

  /**
   * This performs the act of moving the pieces according to the chess positions. Return the
   * captured piece.
   */
  public ChessPiece performChessMovie(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();

    validateSourcePosition(source);
    validateTargetPosition(source, target);

    Piece capturedPiece = makeMove(source, target);

    // If the move put the player in check, it will be reversed
    if (testCheck(currentPlayer)) {
      undoMove(source, target, capturedPiece);
      throw new ChessException("You can´t put yourself in check.");
    }

    ChessPiece movedPiece = (ChessPiece) board.piece(target);

    // *SPECIAL MOVE* PROMOTION
    this.promoted = null;
    if (movedPiece instanceof Pawn && (target.getRow() == 0 || target.getRow() == 7)) {
      promoted = (ChessPiece) board.piece(target);

    }

    check = testCheck(opponentColor(currentPlayer));

    if (testCheckMate(opponentColor(currentPlayer))) {
      this.checkMate = true;

    } else {
      nextTurn();
    }

    if (movedPiece instanceof Pawn
        && (target.getRow() == source.getRow() + 2 || target.getRow() == source.getRow() - 2)) {
      enPassantVunerable = movedPiece;
    } else {
      enPassantVunerable = null;
    }

    return (ChessPiece) capturedPiece;
  }

  public ChessPiece replacePromotedPiece(String type) {
    if (this.promoted == null) {
      throw new IllegalStateException("There is no piece to be promoted.");
    }

    Position pawnPosition = this.promoted.getChessPosition().toPosition();
    Piece pawnPiece = board.removePiece(pawnPosition);
    this.piecesOnTheBoard.remove(pawnPiece);

    ChessPiece newPiece = newPiece(type, this.promoted.getColor());
    board.placePieece(newPiece, pawnPosition);
    piecesOnTheBoard.add(newPiece);

    return newPiece;
  }

  private ChessPiece newPiece(String type, Color color) {
    switch (type) {
      case "Q":
        return new Queen(board, color);

      case "B":
        return new Bishop(board, color);

      case "H":
        return new Horse(board, color);

      case "R":
        return new Rook(board, color);

      default:
        throw new ChessException("Please put a valid piece type.");
    }
  }

  /**
   * Move the pieces according to the matrix position. The source piece is placed in the target
   * position and If any piece is captured it is removed from the board.
   */
  private Piece makeMove(Position source, Position target) {
    ChessPiece movedPiece = (ChessPiece) board.removePiece(source);
    movedPiece.increseMoveCount();

    Piece capturedPiece = board.removePiece(target);
    board.placePieece(movedPiece, target);

    if (capturedPiece != null) {
      this.piecesOnTheBoard.remove(capturedPiece);
      this.capturedPieces.add(capturedPiece);
    }

    // *SPECIAL MOVE CASTLING* to RIGHT
    if (movedPiece instanceof King && target.getColumn() == source.getColumn() + 2) {
      Position rightRookSource = new Position(source.getRow(), source.getColumn() + 3);
      Position rightRookTarget = new Position(source.getRow(), source.getColumn() + 1);

      ChessPiece rook = (ChessPiece) board.removePiece(rightRookSource);
      board.placePieece(rook, rightRookTarget);
      rook.increseMoveCount();
    }

    // *SPECIAL MOVE CASTLING* to LEFT
    if (movedPiece instanceof King && target.getColumn() == source.getColumn() - 2) {
      Position leftRookSource = new Position(source.getRow(), source.getColumn() - 4);
      Position leftRookTarget = new Position(source.getRow(), source.getColumn() - 1);

      ChessPiece rook = (ChessPiece) board.removePiece(leftRookSource);
      board.placePieece(rook, leftRookTarget);
      rook.increseMoveCount();
    }

    // *SPECIAL MOVE EN PASSANT*
    if (movedPiece instanceof Pawn) {
      if (source.getColumn() != target.getColumn() && capturedPiece == null) {
        Position pawnPosition = new Position();

        if (movedPiece.getColor() == Color.WHITE) {
          pawnPosition.setValue(target.getRow() + 1, target.getColumn());
        } else {
          pawnPosition.setValue(target.getRow() - 1, target.getColumn());
        }

        capturedPiece = board.removePiece(pawnPosition);
        this.capturedPieces.add(capturedPiece);
        this.piecesOnTheBoard.remove(capturedPiece);
      }
    }

    return capturedPiece;
  }

  /**
   * This method undo the last piece movement.
   */
  private void undoMove(Position source, Position target, Piece capturedPiece) {
    ChessPiece undoThisPieceMovie = (ChessPiece) board.removePiece(target);
    undoThisPieceMovie.decreaseMoveCount();

    board.placePieece(undoThisPieceMovie, source);

    if (capturedPiece != null) {
      board.placePieece(capturedPiece, target);
      piecesOnTheBoard.add(capturedPiece);
      this.capturedPieces.remove(capturedPiece);
    }

    // *SPECIAL MOVE CASTLING* to RIGHT
    if (undoThisPieceMovie instanceof King && target.getColumn() == source.getColumn() + 2) {
      Position rightRookSource = new Position(source.getRow(), source.getColumn() + 3);
      Position rightRookTarget = new Position(source.getRow(), source.getColumn() + 1);

      ChessPiece rook = (ChessPiece) board.removePiece(rightRookTarget);
      board.placePieece(rook, rightRookSource);
      rook.decreaseMoveCount();
    }

    // *SPECIAL MOVE CASTLING* to LEFT
    if (undoThisPieceMovie instanceof King && target.getColumn() == source.getColumn() - 2) {
      Position leftRookSource = new Position(source.getRow(), source.getColumn() - 4);
      Position leftRookTarget = new Position(source.getRow(), source.getColumn() - 1);

      ChessPiece rook = (ChessPiece) board.removePiece(leftRookTarget);
      board.placePieece(rook, leftRookSource);
      rook.decreaseMoveCount();
    }

    // *SPECIAL MOVE EN PASSANT*
    if (undoThisPieceMovie instanceof Pawn) {
      if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVunerable) {
        Position pawnPosition = new Position();
        ChessPiece pawn = (ChessPiece) board.removePiece(target);

        if (undoThisPieceMovie.getColor() == Color.WHITE) {
          pawnPosition.setValue(3, target.getColumn());
        } else {
          pawnPosition.setValue(4, target.getColumn());
        }
        board.placePieece(pawn, pawnPosition);
      }
    }
  }

  public Color opponentColor(Color color) {
    return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  /**
   * This filter the pieces in the game and returns the pieces with received color.
   * 
   * @param color
   * 
   * @return The pieces that are in the game and have the color received by parameter.
   */
  private List<Piece> getPiecesWithTheColor(Color color) {
    return piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
        .collect(Collectors.toList());
  }

  /**
   * This method returns the king with the received color.
   */
  private ChessPiece king(Color color) {
    List<Piece> playerPieces = getPiecesWithTheColor(color);

    for (Piece p : playerPieces) {
      if (p instanceof King) {
        return (ChessPiece) p;
      }
    }

    throw new IllegalStateException("The " + color + " king is not on the board.");
  }

  /**
   * Verify if the player with received color is in check.
   * 
   * @param color
   * 
   * @return boolean - If the king with received color is in check or not.
   */
  private boolean testCheck(Color color) {
    Position kingPosition = king(color).getChessPosition().toPosition();
    List<Piece> opponentPieces = getPiecesWithTheColor(opponentColor(color));

    for (Piece p : opponentPieces) {
      if (p.possibleMove(kingPosition)) {
        return true;
      }
    }
    return false;
  }

  private boolean testCheckMate(Color color) {
    if (!testCheck(color)) {
      return false;
    }

    List<Piece> piecesWithReceivedColor = getPiecesWithTheColor(color);
    for (Piece p : piecesWithReceivedColor) {
      boolean[][] possibleMovements = p.possibleMoves();

      for (int i = 0; i < board.getRows(); i += 1) {
        for (int j = 0; j < board.getColumns(); j += 1) {

          if (possibleMovements[i][j]) {
            Position source = ((ChessPiece) p).getChessPosition().toPosition();
            Position target = new Position(i, j);
            Piece capturedPiece = makeMove(source, target);
            boolean isInCheck = testCheck(color);
            undoMove(source, target, capturedPiece);

            if (!isInCheck) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  /**
   * verify if the piece in the received position exists, if can move and in the piece belong to the
   * player.
   * 
   * @param position
   */
  private void validateSourcePosition(Position position) {
    if (!board.thereIsApiece(position)) {
      throw new ChessException("There is no piece on source position.");
    }

    ChessPiece currentPiece = (ChessPiece) board.piece(position);
    if (currentPlayer != currentPiece.getColor()) {
      throw new ChessException("That piece is not your.");
    }

    if (!board.piece(position).isThereAnyPossibleMove(position)) {
      throw new ChessException("This piece cannot make any movement.");
    }
  }

  private void validateTargetPosition(Position source, Position target) {
    if (!board.piece(source).possibleMove(target)) {
      throw new ChessException("The chosen piece cannot move to target position.");
    }
  }

  private void nextTurn() {
    turn += 1;
    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  /**
   * This places the pieces in their starting position.
   */
  public void initialSetup() {
    placeNewPiece('a', 8, new Rook(board, Color.BLACK));
    placeNewPiece('b', 8, new Horse(board, Color.BLACK));
    placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('d', 8, new Queen(board, Color.BLACK));
    placeNewPiece('e', 8, new King(board, Color.BLACK, this));
    placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('g', 8, new Horse(board, Color.BLACK));
    placeNewPiece('h', 8, new Rook(board, Color.BLACK));

    placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
    placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));


    placeNewPiece('a', 1, new Rook(board, Color.WHITE));
    placeNewPiece('b', 1, new Horse(board, Color.WHITE));
    placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('d', 1, new Queen(board, Color.WHITE));
    placeNewPiece('e', 1, new King(board, Color.WHITE, this));
    placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('g', 1, new Horse(board, Color.WHITE));
    placeNewPiece('h', 1, new Rook(board, Color.WHITE));

    placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
    placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));
  }
}
