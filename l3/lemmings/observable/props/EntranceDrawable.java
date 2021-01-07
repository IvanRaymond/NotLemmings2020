package l3.lemmings.observable.props;

import l3.lemmings.App;
import l3.lemmings.observable.IDrawable;

import java.awt.*;

public class EntranceDrawable implements IDrawable {

    private Entrance element;
    private Color color = Color.MAGENTA;

    public EntranceDrawable(Entrance entrance){
        element = entrance;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color.darker());
        g.fillRect( (int) element.getPosition().getX() * App.blockWidth, (int) element.getPosition().getY() * App.blockHeight, App.blockWidth, App.blockHeight);
        g.setColor(color);
        g.fillRect( (int) element.getPosition().getX() * App.blockWidth + 4, (int) element.getPosition().getY() * App.blockHeight + 4, App.blockWidth - 8, App.blockHeight - 8);

    }
}
