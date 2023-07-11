package Tile;

import Exception.GameException;
import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    public Tile[] tile;
    public int[][] mapTileNum;
    GamePanel gp;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[9];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/Maps/map1.txt");
    }

    public void getTileImage() {
        setup(0, "Ground1", true);
        setup(1, "Ground11", true);
        setup(2, "Trash", true);
        setup(3, "Box", true);
        setup(4, "Stone1", true);
        setup(5, "Stone2", true);
        setup(6, "Start", false);
        setup(7, "Finish", false);
        tile[8] = new Tile();
        tile[8].collision = false;
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            System.out.println("Nu s-au putut incarca tile urile. Index: " + index);
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            assert is != null;
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Nu s-a reusit incarcarea mapei.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) throws GameException {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (tileNum != 8) {
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY + gp.tileSize) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

            //worldCol = -1;
            if (worldCol < 0 || worldCol > gp.maxWorldCol ||
                    worldRow > gp.maxWorldRow || worldRow < 0) {
                throw new GameException("Indexul este gresit: worldcol=" + worldCol + "  worldrow=" + worldRow);
            }

        }
    }

}
