package l3.lemmings.observable.lemming;

import l3.lemmings.observable.IDrawable;

import java.awt.*;

import l3.lemmings.App;

public class LemmingDrawable implements IDrawable {

    LemmingObservable lemming;

    public LemmingDrawable(LemmingObservable lemming){
        this.lemming = lemming;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(lemming.getColor().darker());
        g.fillRect( (int) lemming.getPosition().getX() * App.blockWidth, (int) lemming.getPosition().getY() * App.blockHeight, App.blockWidth, App.blockHeight);
        g.setColor(lemming.getColor());
        g.fillRect( (int) lemming.getPosition().getX() * App.blockWidth + 4, (int) lemming.getPosition().getY() * App.blockHeight + 4, App.blockWidth - 8, App.blockHeight - 8);
    }
}
