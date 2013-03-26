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
 
	//Constants
	static int WIDTH = 1024;
	static int HEIGHT = 760;
 
	static boolean fullscreen = false;
	static boolean showFPS = true;
	static String title = "Advanced Camera tutorial";
	static int fpslimit = 60;
 
	//Variables
	TiledMap map; //The file that contain the world we are
	Hero player; //The moving entity we will follow
	Camera camera; //The camera we are going to use
	int mapHeight, mapWidth;
 
	public Game(String title) {
		super(title);
	}
 
	public void init(GameContainer gc) throws SlickException {
		map = new TiledMap("tiledmaps/map.tmx");
		mapWidth = map.getWidth() * map.getTileWidth(); // Map size = Tile Size * number of Tiles
		mapHeight = map.getHeight() * map.getTileHeight();
 
		player = new Hero(50, 50, 32, 32, new Image("images/hero.png"));
		camera = new Camera(map, mapWidth, mapHeight);
	}
 
	public void update(GameContainer gc, int delta) throws SlickException {
 
		player.update(gc, mapWidth, mapHeight, delta);
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