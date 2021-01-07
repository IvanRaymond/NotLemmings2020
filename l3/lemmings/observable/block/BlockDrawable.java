package l3.lemmings.observable.block;

import l3.lemmings.App;
import l3.lemmings.observable.IDrawable;

import java.awt.*;

public class BlockDrawable implements IDrawable {

    private Color color = Color.DARK_GRAY;
    private Block element;

    public BlockDrawable(Block element){
        this.element = element;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color.darker());
        g.fillRect( (int) element.getPosition().getX() * App.blockWidth, (int) element.getPosition().getY() * App.blockHeight, App.blockWidth, App.blockHeight);
        g.setColor(color);
        g.fillRect( (int) element.getPosition().getX() * App.blockWidth + 4, (int) element.getPosition().getY() * App.blockHeight + 4, App.blockWidth - 8, App.blockHeight - 8);
    }
}
