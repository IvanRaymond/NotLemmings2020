package l3.lemmings.model;

public class Lemming {
	
	public enum LemmingState{
		NORMAL,
		CLIMBER,
		FLOATER,
		BOMB,
		BLOCKER,
		BRIDGE_BUILDER,
		BASHER,
		MINER,
		DIGGER
	}

	private boolean alive = true;
	
	private LemmingState state = LemmingState.NORMAL;

	private int directionAxisX;
	private int directionAxisY;
	private int x;
	private int y;
	
	public Lemming(int x, int y)
	{
		this.x = x;
		this.y = y;
		directionAxisX = 1;
		directionAxisY = 0;
	}

	public void move() {
		x += directionAxisX;
		y += directionAxisY;
	}

	public void kill(){
		alive = false;
	}

	public boolean alive(){
		return alive;
	}
	
	public LemmingState getState() {
		return state;
	}

	public void setState(LemmingState state) {
		this.state = state;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirectionAxisY() {
		return directionAxisY;
	}

	public void setDirectionAxisY(int directionAxisY) {
		this.directionAxisY = directionAxisY;
	}

	public int getDirectionAxisX() {
		return directionAxisX;
	}

	public void setDirectionAxisX(int directionAxisX) {
		this.directionAxisX = directionAxisX;
	}
}
