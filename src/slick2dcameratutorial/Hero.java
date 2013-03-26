package slick2dcameratutorial;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Hero {

    protected Vector2f pos; // Vector contains a value with components x &amp; y
    protected Rectangle box;
    protected Image sprite;

    public Hero(float x, float y, int width, int height, Image sprite) {
        pos = new Vector2f(x, y);
        box = new Rectangle(x, y, width, height);
        this.sprite = sprite;
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta) {

        Vector2f trans = new Vector2f(0, 0);

        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            trans.y = -0.5f * delta;
        }

        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            trans.y = 0.5f * delta;
        }

        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            trans.x = 0.5f * delta;
        }

        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            trans.x = -0.5f * delta;
        }       
        
        if (trans.x != 0 && trans.y != 0) { // If both components aren't null, we reduce them to have constant speed on all directions
            trans.set(trans.x / 1.5f, trans.y / 1.5f);
        }

        if (pos.x + trans.x > 32 && pos.x + trans.x < (mapWidth - 64)) {
            pos.x += trans.x;
        }

        if (pos.y + trans.y > 32 && pos.y + trans.y < (mapHeight - 64)) {
            pos.y += trans.y;
        }
    }

    public void render() {
        sprite.draw(pos.x, pos.y);
    }

    // Getters and Setters
    public Vector2f getPos() {
        return pos;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public Rectangle getBox() {
        return box;
    }

    public void setBox(Rectangle box) {
        this.box = box;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}