package l3.lemmings.controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import l3.lemmings.controller.UIcontroller.Button;
import l3.lemmings.model.Lemming.LemmingState;
import l3.lemmings.model.Models;
import l3.lemmings.view.Views;

/**
 * Treats mouse inputs and decide on the action to make. 
 * @author Ivan
 *
 */
public class Listener extends MouseAdapter {

	private Views view;
	private UIcontroller uiController;
	private boolean actionFlag = false;
	Action action;
			
	
	public Listener(Views view) {
		this.view = view;
		uiController = new UIcontroller(view);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		if(e.getButton()==MouseEvent.BUTTON1) {
			
			Point cell = uiController.pointToMatrix(e.getPoint());
			
			if(uiController.isButton(cell)) {
				Button clicked = uiController.getButton(cell);
				action = new Action(clicked, this);
				action.doAction();
			}
			if(uiController.isCellPlayable(cell)) {
				if (view.lemmingPresent(cell) && actionFlag) {
					actionFlag = action.setAction(view.getLemming(cell));
				}
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		super.mouseMoved(e);
	}

	public void setActionFlag(){
		actionFlag = true;
	}
	
}
