package Entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

//este clasa parinte pentru toate entitatile
////contine variabile care vor fi folosite la player, enemies
public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage R1, R2, R3, R4, R5, RJ, //Right
            L1, L2, L3, L4, L5, LJ; //Left
    public String direction = "left";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public Rectangle solidArea;
    public String name;
    public boolean collision = false;
    GamePanel gp;

    public Entity(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize - 10, gp.tileSize - 10);
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkEntity(this, gp.enemies);
        gp.checker.checkPlayer(this);
        if (!collisionOn) {
            switch (direction) {
                case "right":
                    worldX += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                default:
                    break;
            }
        }

        spriteCounter++;

        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "right":
                    image = R1;
                    break;
                case "left":
                    image = L1;
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize * 2);
        } catch (IOException e) {
            System.out.println("Nu s-au putut incarca imaginile jucatorului.");
            e.printStackTrace();
        }
        return image;
    }

}
