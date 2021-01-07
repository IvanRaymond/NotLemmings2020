package l3.lemmings.observable.props;

import l3.lemmings.App;
import l3.lemmings.observable.IDrawable;

import java.awt.*;

public class TeleporterDrawable implements IDrawable {

    private Teleporter element;
    private Color color = Color.PINK;

    public TeleporterDrawable(Teleporter teleporter){
        element = teleporter;
    }

    private void drawAux(Graphics g, Point position){
        g.setColor(color.darker());
        g.fillRect( (int) position.getX() * App.blockWidth, (int) position.getY() * App.blockHeight, App.blockWidth, App.blockHeight);
        g.setColor(color);
        g.fillRect( (int) position.getX() * App.blockWidth + 4, (int) position.getY() * App.blockHeight + 4, App.blockWidth - 8, App.blockHeight - 8);
    }

    @Override
    public void draw(Graphics g) {
        drawAux(g, element.getPosition());
        drawAux(g, element.getSecondPosition());
    }
}
