package Project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class omok extends JFrame implements MouseListener {
	// 바둑돌의 상태를 나타내는 상수
	final int NONE = 0;
	final int BLACK = 1;
	final int WHITE = 2;

	final int LINE_NUM = 18; // 오목판 줄 수
	final int LINE_WIDTH = 30; // 오목판 줄 간격
	final int BOARD_SIZE = LINE_WIDTH * (LINE_NUM - 1); // 오목판의 크기 522
	final int STONE_SIZE = (int) (LINE_WIDTH * 0.9); // 돌의 크기 27
	final int X0; // 오목판 시작위치 x좌표
	final int Y0; // 오목판 시작위치 y좌표
	final int FRAME_WIDTH; // Frame의 폭
	final int FRAME_HEIGHT; // Frame의 높이

	// 흑백 중 어느 쪽의 차례인지를 저장할 변수
	int turn;
	// 바둑판에 놓인 돌의 상태(흑인지 백인지 비어있는지)를 저장할 배열
	int stones[][] = new int[LINE_NUM][LINE_NUM];

	Image img = null;
	Graphics gImg = null;

	omok() {
		// Event Handler를 등록한다.
		addMouseListener(this);

		setVisible(true); 
		Insets insets = getInsets(); // 여백값을 얻는다.
		System.out.println(insets);
		// 오목판 그려질 위치(LEFT, TOP)의 좌표 X0, Y0를 지정한다.
		X0 = insets.left + LINE_WIDTH;
		Y0 = insets.top + LINE_WIDTH;
		//left => 8
		//top  => 31
		//X0   => 38
		//Y0   => 61
		
		// Frame의 크기를 계산한다.
		FRAME_WIDTH = BOARD_SIZE + LINE_WIDTH * 2 + insets.left + insets.right;
		FRAME_HEIGHT = BOARD_SIZE + LINE_WIDTH * 2 + insets.top + insets.bottom;

		// Frame을 화면의 (100,100)의 위치에 계산된 크기로 보이게 한다.
		setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
		img = createImage(FRAME_WIDTH, FRAME_HEIGHT);
		gImg = img.getGraphics();
		setResizable(false); // Frame의 크기를 변경하지 못하게 한다.

		// 바둑판을 그리고 줄을 긋는다.
		drow();

	} // Exercise13_7(String title)

	public void drow() {
		// 흑이 선수이다.
		turn = BLACK;
		// 전체 바둑판의 귀에 비어있는 것으로 입력한다.
		for (int i = 0; i < LINE_NUM; i++) {
			for (int j = 0; j < LINE_NUM; j++) {
				stones[i][j] = NONE;
			}
		}
		// 바둑판을 그린다.
		drawBoard(gImg);

	}

	public void drawBoard(Graphics g) {
		// 바둑판을 오렌지색으로 칠한다.
		g.setColor(Color.white);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		// 검은색으로 선을 그린다.
		g.setColor(Color.black);
		// X0 => 38
		// Y0 => 61
		// BOARD_SIZE => 522
		// LINE_WIDTH => 30
		for (int i = 0; i < LINE_NUM; i++) {
			//i=0 => 38,61,550,30
			//       38,61,38,583
			g.drawLine(X0, Y0 + i * LINE_WIDTH, X0 + BOARD_SIZE, Y0 + i * LINE_WIDTH);
			g.drawLine(X0 + i * LINE_WIDTH, Y0, X0 + i * LINE_WIDTH, Y0 + BOARD_SIZE);
		}
	}

	public void paint(Graphics g) {
		if (img == null)
			return;
		g.drawImage(img, 0, 0, this); // 가상화면에 그려진 그림을 Frame에 복사
	}

	public void mousePressed(MouseEvent e) { // MouseListener
		int x = e.getX(); // 마우스 포인터의 x좌표
		int y = e.getY(); // 마우스 포인터의 y좌표
		int stoneX, stoneY;

		System.out.println(x);
		System.out.println(y);
		
		// 1. x 또는 y의 값이 오목판의 밖을 벗어난 곳이면 메서드를 종료한다.
		//=>x 23 or 563 마우스 포인터
		//=>y 46 or 586
		if (x < X0 - LINE_WIDTH / 2 || x > X0 + (LINE_NUM - 1) * LINE_WIDTH + LINE_WIDTH / 2)
			return;
		if (y < Y0 - LINE_WIDTH / 2 || y > Y0 + (LINE_NUM - 1) * LINE_WIDTH + LINE_WIDTH / 2)
			return;

		// 2. x와 y의 값을 클릭한 곳에서 가장 가까운 교차점으로 변경한다.(반올림)
		x = (x - X0 + LINE_WIDTH / 2) / LINE_WIDTH * LINE_WIDTH + X0;
		y = (y - Y0 + LINE_WIDTH / 2) / LINE_WIDTH * LINE_WIDTH + Y0;

		Insets insets = getInsets();
		stoneX = (x - insets.left) / LINE_WIDTH - 1;
		stoneY = (y - insets.top) / LINE_WIDTH - 1;
		// System.out.println(arrI + ":" + arrJ);

		// 3. 돌의 크기의 절반을 뺀다 그리기위해
		x -= STONE_SIZE / 2;
		y -= STONE_SIZE / 2;

		// 4. 눌러진 버튼이 마우스 왼쪽 버튼고 돌의 자리가 NONE이라면 돌을 그림
		if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK && stones[stoneX][stoneY] == NONE) {
			// 흑의 차례이면 검은 돌을 그리고
			if (turn == BLACK) {
				gImg.setColor(Color.black);
				gImg.fillOval(x, y, STONE_SIZE, STONE_SIZE);
				stones[stoneX][stoneY] = BLACK;
				// 백의 차례이면 흰 돌을 그린다.
			} else {
				gImg.setColor(Color.white);
				gImg.fillOval(x, y, STONE_SIZE, STONE_SIZE);
				// 흰색 돌을 위해 검은색 테두리를 그린다.
				gImg.setColor(Color.black);
				gImg.drawOval(x, y, STONE_SIZE, STONE_SIZE);
				stones[stoneX][stoneY] = WHITE;
			}
		}

		// 5. repaint()를 호출한다. 현재 페인트되고 있는 목록들을 다시 페인팅 하는것!!
		repaint();

		// 6. 오목 판정
		// 오목이면...
		if (isOmok(turn, stoneX, stoneY)) {
			if (turn == BLACK) {
				JOptionPane.showMessageDialog(this, "흑이 승리하였습니다.", "오목", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "백이 승리하였습니다.", "오목", JOptionPane.INFORMATION_MESSAGE);
			}
			// 초기화한다.
			drow();
			repaint();
		} else {
			if (turn == BLACK) {
				turn = WHITE;
			} else {
				turn = BLACK;
			}
		}
	}

	// 오목이면 '참'을 돌려주는 메소드이다.
	private boolean isOmok(int turn, int x, int y) {
		// 마지막 놓은 돌에서 오른쪽으로, 그리고 왼쪽으로 같은 색 돌이 몇 개인지 센다.
		// 그래서 5보다 크면(마지막 놓은 돌은 2번 카운트되므로) 참을 돌려준다.
		if (checkStones(turn, x, y, 1, 0) + checkStones(turn, x, y, -1, 0) > 5)
			return true;
		// 세로방향
		if (checkStones(turn, x, y, 0, 1) + checkStones(turn, x, y, 0, -1) > 5)
			return true;
		// 대각선 방향
		if (checkStones(turn, x, y, 1, 1) + checkStones(turn, x, y, 1, -1) > 5)
			return true;
		if (checkStones(turn, x, y, -1, -1) + checkStones(turn, x, y, -1, 1) > 5)
			return true;
		// 위에 해당되지 않으면 거짓을 돌려준다.
		return false;
	}

	private int checkStones(int turn, int x, int y, int dX, int dY) {
		// 변수 dX, dY가 어느쪽 방향으로 셀 것인지를 나타낸다.
		int i = 0;
		for (; stones[x][y] == turn; i++) {
			x += dX;
			y += dY;
			// 바둑판의 범위를 벗어나면 break를 사용하여 for 문을 벗어난다.
			if (x < 0 || x >= LINE_NUM || y < 0 || y >= LINE_NUM)
				break;
		}
		// 같은 돌이 몇개 연속으로 놓여있는지 돌려준다. 단 마지막 돌은 2번 카운트된다.
		return i;
	}

	public void mouseClicked(MouseEvent e) {
	} // MouseListener

	public void mouseEntered(MouseEvent e) {
	} // MouseListener

	public void mouseExited(MouseEvent e) {
	} // MouseListener

	public void mouseReleased(MouseEvent e) {
	} // MouseListener
}

