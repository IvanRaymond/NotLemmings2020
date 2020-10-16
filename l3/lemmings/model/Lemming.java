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
	
	LemmingState state = LemmingState.NORMAL;

	int x;
	int y;
	
	public Lemming(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void move() {
		x++;
		//y++;
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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
