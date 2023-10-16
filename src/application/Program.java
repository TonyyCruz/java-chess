package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

/**
 * This class is the main class. It's responsible all the user interaction and program execution.
 */
public class Program {

  /**
   * The main class.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ChessMatch chessMatch = new ChessMatch();
    List<ChessPiece> captured = new ArrayList<>();

    while (!chessMatch.getCheckMate()) {
      try {
        Ui.clearScreen();

        Ui.printMatch(chessMatch, captured);
        System.out.println();
        System.out.print("Source: ");
        ChessPosition source = Ui.readChessPosition(sc);

        boolean[][] possibleMoves = chessMatch.possibleMoves(source);
        Ui.clearScreen();
        Ui.printBoard(chessMatch.getPieces(), possibleMoves);

        System.out.println();
        System.out.print("Target: ");
        ChessPosition target = Ui.readChessPosition(sc);

        ChessPiece capturedPiece = chessMatch.performChessMovie(source, target);

        if (capturedPiece != null) {
          captured.add(capturedPiece);
        }

        if (chessMatch.getPromoted() != null) {
          System.out.println("Choose the piece you want to promote:");
          System.out.println("Press: 'Q' to Queen, 'H' to Horse, 'B' to bishop or 'R' to rook:");
          sc.nextLine();
          String type = sc.nextLine().toUpperCase();

          while (type.length() != 1 || !"QHBR".contains(type)) {
            System.out.println("Invalid piece type!");
            System.out.println(
                "Please choose: 'Q' to Queen, 'H' to Horse, 'B' to bishop or 'R' to rook:");
            type = sc.nextLine().toUpperCase();
          }

          chessMatch.replacePromotedPiece(type);
        }

      } catch (ChessException e) {
        System.out.println(e.getMessage());
        sc.nextLine();
        sc.nextLine();

      } catch (InputMismatchException e) {
        System.out.println(e.getMessage());
        sc.nextLine();
        sc.nextLine();
      }

    }

    Ui.clearScreen();
    Ui.printMatch(chessMatch, captured);
  }
}
