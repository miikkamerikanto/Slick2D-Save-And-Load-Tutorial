package EpicGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.Vector2f;

public class Game extends BasicGame {

    static final int WIDTH = 1024;
    static final int HEIGHT = 760;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static String title = "Slick2D Camera Tutorial";
    static int fpslimit = 60;
    TiledMap map;
    Hero player;
    Camera camera;
    int mapHeight, mapWidth;
    int tileHeight, tileWidth;
    private FileIO fileio;
    private Vector2f coordinates  = new Vector2f(128, 128);

    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("tiledmaps/map.tmx");
        mapWidth = map.getWidth() * map.getTileWidth();
        mapHeight = map.getHeight() * map.getTileHeight();
        tileHeight = map.getTileHeight();
        tileWidth = map.getTileWidth();
        player = new Hero(coordinates.x, coordinates.y, new Image("images/hero.png"));
        camera = new Camera(map, mapWidth, mapHeight);
        fileio = new FileIO();
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        player.update(gc, mapWidth, mapHeight, delta, tileWidth, tileHeight);
        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_S)) {
            fileio.save(player.getPosition());
        } else if (input.isKeyDown(Input.KEY_L)) {
            player.setPosition(fileio.loadSave());
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        camera.translate(g, player);
        map.render(0, 0);
        player.render();
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game(title));
        app.setDisplayMode(WIDTH, HEIGHT, fullscreen);
        app.setTargetFrameRate(fpslimit);
        app.setVSync(true);
        app.setShowFPS(showFPS);
        app.start();
    }
}