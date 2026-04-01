class Solution {

	static boolean isWin(String[] board, char player) {

		for (int i = 0; i < 3; i++) {
			if (board[i].charAt(0) == player &&
					board[i].charAt(1) == player &&
					board[i].charAt(2) == player) return true;
		}

		for (int j = 0; j < 3; j++) {
			if (board[0].charAt(j) == player &&
					board[1].charAt(j) == player &&
					board[2].charAt(j) == player) return true;
		}

		if (board[0].charAt(0) == player &&
				board[1].charAt(1) == player &&
				board[2].charAt(2) == player) return true;
		if (board[0].charAt(2) == player &&
				board[1].charAt(1) == player &&
				board[2].charAt(0) == player) return true;

		return false;
	}

	public int solution(String[] board) {
		int cntO = 0, cntX = 0;

		for (String row : board) {
			for (char c : row.toCharArray()) {
				if (c == 'O') cntO++;
				else if (c == 'X') cntX++;
			}
		}

		boolean oWin = isWin(board, 'O');
		boolean xWin = isWin(board, 'X');

		if (cntO != cntX && cntO != cntX + 1) return 0;

		if (oWin && xWin) return 0;

		if (oWin && cntO != cntX + 1) return 0;

		if (xWin && cntO != cntX) return 0;

		return 1;
	}
}