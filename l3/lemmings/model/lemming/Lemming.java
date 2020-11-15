package l3.lemmings.model.lemming;

import l3.lemmings.model.Element;

public class Lemming implements Element {

	private boolean alive = true;
	private int fallCount = 0;
	private int digCount = 0;
	private State state;
	private boolean busy = false;

	private int directionAxisX;
	private int directionAxisY;
	private int oldDirectionAxisX=0;
	private int x;
	private int y;
	
	public Lemming(int x, int y)
	{
		this.x = x;
		this.y = y;
		directionAxisX = 1;
		directionAxisY = 0;
		state = new Normal();
	}

//	public void move() {
//		x += directionAxisX;
//		if(state == LemmingState.FLOATER && directionAxisY > 0)
//		{
//			y += 0.5;
//		}
//		else
//			y += directionAxisY;
//
//
//	}

	public void setState(State state){
		this.state=state;
	}

	public void incFallCount(){
		fallCount++;
	}

	public boolean doAction(){
		return state.doAction();
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

	public void resetFallCount(){
		fallCount=0;
	}

	public void kill(){
		alive = false;
	}

	public boolean alive(){
		return alive;
	}

	public int getX() {
		return x;
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
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

	public void saveDirectionX(int n){
		oldDirectionAxisX = n;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}
}
