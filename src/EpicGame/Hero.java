package EpicGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Hero {

    protected Vector2f position;
    protected Image image;

    public Hero(float x, float y, Image image) {
        position = new Vector2f(x, y);
        this.image = image;
    }

    public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, int tileWidth, int tileHeight) {
        Vector2f trans = new Vector2f(0, 0);
        Input input = gc.getInput();

        /**
         * With these little snippets of code we enable the possibility to move on the map.
         */
        if (input.isKeyDown(Input.KEY_UP)) {
            trans.y = -0.5f * delta;
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            trans.y = 0.5f * delta;
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            trans.x = 0.5f * delta;
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            trans.x = -0.5f * delta;
        }

        if (trans.x != 0 && trans.y != 0) {
            trans.set(trans.x / 1.5f, trans.y / 1.5f);
        }

        /**
         * With the next two if statements we make sure that Hero stayes within the borders of the map.
         */
        if (position.x + trans.x > tileWidth && position.x + trans.x < (mapWidth - (2 * tileWidth))) {
            position.x += trans.x;
        }
        if (position.y + trans.y > tileHeight && position.y + trans.y < (mapHeight - (4 * tileHeight))) {
            position.y += trans.y;
        }
    }

    /**
     * Renders the hero's image to the game.
     * This is done after the update method.
     */
    public void render() {
        image.draw(position.x, position.y);
    }

    public Vector2f getPosition() {
        return position;
    }
    
    public float getX() {
        return position.x;
    }
    
    public float getY() {
        return position.y;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }
}