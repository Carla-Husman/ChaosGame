package Main;

import Entity.Entity;
import Entity.Player;
import Exception.GameException;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    //setarile LUMII
    public final int maxWorldCol = 105;
    public final int maxWorldRow = 12;

    //Game state
    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;
    public final int rulesState = 2;
    public final int storyState = 3;
    public final int gameOverState = 4;
    public final int gameCompleted = 5;

    // setarile ecranului
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48*48
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576

    //UI
    public UI ui = new UI(this);

    //Collision
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    //Entities and Objects
    public Player player;
    public ArrayList<Entity> entityList = new ArrayList<>();
    public Entity[] enemies = new Entity[7];
    public Entity[] obj = new Entity[28]; //putem afisa pana la 10 obiecte pe ecran

    //Baza de date
    public DataBase dataBase = new DataBase(this);

    //Informations
    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    //Thread
    Thread gameThread;

    //FPS
    int FPS = 60;

    public GamePanel() {
        dataBase.openDataBase();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setEnemy();
        gameState = menuState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //call run()
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // 1 UPDATE
            update();
            // 2 DRAW cu noua informatie
            repaint();//call paintComponent
            /*Game loop
            <- - -  - - - - -
            |                |
            update           |
            |                |  60 FPS
            repaint          |
            |                |
            - - - - - - - - ->
             */

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime = nextDrawTime + drawInterval;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dataBase.closeDataBase();
    }

    public void update() {
        if (obj == null) {
            obj = new Entity[28];
            assetSetter.setObject();
        }
        if (player == null) {
            player = new Player(this, keyH);
            dataBase.time1 = LocalDateTime.now();
        }

        if (gameState == playState) {

            player.update();

            for (Entity enemy : enemies) {
                if (enemy != null) {
                    enemy.update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == menuState ||
                gameState == storyState ||
                gameState == rulesState ||
                gameState == gameOverState ||
                gameState == gameCompleted) {
            ui.draw(g2);
        } else if (gameState == playState) {
            g2.drawImage(ui.background, 0, 0, screenWidth, screenHeight, null);

            //TILE
            try {
                tileManager.draw(g2);
            } catch (GameException e) {
                System.out.println(e.getMessage());
            }

            //ADD ENTITIES TO THE LIST
            entityList.add(player);
            for (Entity entity : obj) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity enemy : enemies) {
                if (enemy != null) {
                    entityList.add(enemy);
                }
            }

            //SORT
            entityList.sort(Comparator.comparingInt((Entity e) -> e.worldY));

            //DRAW ENTITIES
            for (Entity entity : entityList) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            //UI
            ui.draw(g2);

        }
        g2.dispose();
    }

}
