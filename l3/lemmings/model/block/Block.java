package l3.lemmings.model.block;

import l3.lemmings.model.Element;

public class Block implements Element {
	
	private int x;
	private int y;
	private boolean destroyed = false;
	
	public Block(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean move() {
		return false;
	}

	@Override
	public boolean interfact(Element element) {
		return false;
	}

	public void setY(int y) {
		this.y = y;
	}

}
