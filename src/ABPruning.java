import java.util.Random;

public class ABPruning {
	public static int alphabeta(ChessState node, int depth, int a, int b, boolean maximizingWhite, Random rand) {
		if(depth == 0 || node.terminatingState) {
			return node.heuristic(rand);
		}
		
		if(maximizingWhite) {
			int value = -1000;
			ChessState.ChessMoveIterator it = node.iterator(true); //get next move for white
			while(it.hasNext()) {
				ChessState child = new ChessState(node);
				ChessState.ChessMove m = it.next();
				child.terminatingState = child.move(m.xSource, m.ySource, m.xDest, m.yDest);
				value = Math.max(value, ABPruning.alphabeta(child, depth - 1, a, b, false, rand));
				a = Math.max(a, value);
				if(a >= b) {
					break;
				}
			}
		    return value;
		} else { //minimizer
			int value = 1000;
			ChessState.ChessMoveIterator it = node.iterator(false); //get next move for black
			while(it.hasNext()) {
				ChessState child = new ChessState(node);
				ChessState.ChessMove m = it.next();
				child.terminatingState = child.move(m.xSource, m.ySource, m.xDest, m.yDest);
				value = Math.min(value, ABPruning.alphabeta(child, depth - 1, a, b, true, rand));
				b = Math.min(b, value);
				if(a >= b) {
					break;
				}
			}
			return value;
		}
	}
}
