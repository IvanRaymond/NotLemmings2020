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
	private int fallCount = 0;
	private int digCount = 0;
	private LemmingState state;
	private boolean busy = false;

	private int directionAxisX;
	private int directionAxisY;
	private int oldDirectionAxisX=0;
	private double x;
	private double y;
	
	public Lemming(int x, int y)
	{
		this.x = x;
		this.y = y;
		directionAxisX = 1;
		directionAxisY = 0;
		state = LemmingState.NORMAL;
	}

	public void move() {
		x += directionAxisX;
		if(state == LemmingState.FLOATER && directionAxisY > 0)
		{
			y += 0.5;
		}
		else
			y += directionAxisY;


	}

	public void incFallCount(){
		fallCount++;
	}

	public void fellToDeath(){
		int deathByFallHeight = 5;
		if(fallCount== deathByFallHeight && state != LemmingState.FLOATER){
			kill();
		}
	}

	public void incDigCount(){
		digCount++;
	}

	public int getDigCount(){
		return digCount;
	}

	public void resetDigCount(){
		digCount=0;
	}

	public void toggleBusy(){
		busy = !busy;
	}

	public boolean getIfBusy(){
		return busy;
	}

	public boolean isState(LemmingState state){
		return this.state==state;
	}

	public void resetFallCount(){
		fallCount=0;
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getDirectionAxisY() {
		return directionAxisY;
	}

	public void changeDirectionX(){
		directionAxisX = -directionAxisX;
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

	public void restoreDirectionAxisX() {
		directionAxisX = oldDirectionAxisX;
	}

	public void saveDirectionX() {
		oldDirectionAxisX = directionAxisX;
	}

}
