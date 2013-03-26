package slick2dcameratutorial;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {

        static int WIDTH = 1024;
	static int HEIGHT = 760;
	static boolean fullscreen = false;
	static boolean showFPS = true;
	static String title = "Slick2D Camera Tutorial";
	static int fpslimit = 60;
	TiledMap map;
	Hero player;
	Camera camera;
	int mapHeight, mapWidth;
        int tileHeight, tileWidth;
 
	public Game(String title) {
		super(title);
	}
 
	public void init(GameContainer gc) throws SlickException {
		map = new TiledMap("tiledmaps/map.tmx");
		mapWidth = map.getWidth() * map.getTileWidth();
		mapHeight = map.getHeight() * map.getTileHeight();
		tileHeight = map.getTileHeight();
                tileWidth = map.getTileWidth();
                player = new Hero(tileWidth*4, tileHeight*4, 32, 32, new Image("images/hero.png"));
		camera = new Camera(map, mapWidth, mapHeight);
	}
 
	public void update(GameContainer gc, int delta) throws SlickException {
		player.update(gc, mapWidth, mapHeight, delta, tileWidth, tileHeight);
	}
 
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