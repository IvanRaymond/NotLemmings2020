package l3.lemmings.controller;

import java.awt.Point;

import l3.lemmings.view.Views;

/**
 * Controller methods for UI interactions.
 * @author Ivan
 */
public class UIcontroller {

	private int singleButtonSize;
	private int numberOfButtons = Button.values().length;
	
	public enum Button {
		DECREASE,
		INCREASE,
		CLIMBER,
		FLOATER,
		BOMB,
		BLOCKER,
		BRIDGE_BUILDER,
		BASHER,
		MINER,
		DIGGER,
		PAUSE,
		NUKE;

		public Button getNext() {
			return this.ordinal() < Button.values().length - 1
			         ? Button.values()[this.ordinal() + 1]
			         : null;
		}
	}
	
	private int width, height;
	private int matrixLengthX, matrixLengthY;
	
	public UIcontroller(Views view) {
		System.out.println("w = "+view.getWidth() + " h = "+view.getHeight());
		width = view.getWidth();
		height = view.getHeight();
		matrixLengthX = view.getNumCaseX();
		matrixLengthY = view.getNumCaseY();
		singleButtonSize = view.getSingleButtonSize();
	}
	
	public Point pointToMatrix(Point p) {
		
		for (int y = 0; y < matrixLengthY; y++) {
			for (int x = 0; x < matrixLengthX; x++) {
				if (width * x/matrixLengthX  < p.x && p.x < width * (x+1)/matrixLengthX) {
					if (height * y/matrixLengthY < p.y && p.y < height * (y+1)/matrixLengthY) {
						return new Point(x,y);
					}
				}
			}
		}
		return new Point(0,0);
	}
	
	
	public boolean isCellPlayable(Point matrix) {
		if (matrix.x >= 0 && matrix.x <= matrixLengthX && matrix.y >= 0 && matrix.y < matrixLengthY-singleButtonSize) {
			return true;
		}
		else return false;
	}
	
	
	public boolean isButton(Point matrix) {
		
		if (matrix.x >= 0 && matrix.x <= singleButtonSize*numberOfButtons && matrix.y <= matrixLengthY && matrix.y >= matrixLengthY-singleButtonSize) {
			return true;
		}
		else return false;
	}
	
	
	public Button getButton(Point matrix) {
		
		Button button = Button.DECREASE;
		
		for(int x=0; x <= numberOfButtons*singleButtonSize; x=x+(numberOfButtons*singleButtonSize)/numberOfButtons) {
			if (matrix.x >= x && matrix.x <= x+1) {
				return button;
			}
			button = button.getNext();
		}
		return null;
	}

	public int getNumberOfButtons() {
		return numberOfButtons;
	}
}
