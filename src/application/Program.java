package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessExeption;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(),possibleMoves);
	
				System.out.println();
				System.out.print("Target: ");
				ChessPosition Target = UI.readChessPosition(sc);
	
				ChessPiece capturedPiece = chessMatch.performChessMove(source, Target);

				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				if(chessMatch.getPromoted()!=null) {
					System.out.println("Entre piece for promotion (B/C/Q/T):");
					String type = sc.nextLine().toUpperCase();
					while(!type.equals("B") && !type.equals("C") && !type.equals("Q")  & !type.equals("T")) {
						System.out.println(" Invalid! Entre piece for promotion (B/C/Q/T):");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch(ChessExeption e) {
			    System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				}
			
			}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

		
	}

}
