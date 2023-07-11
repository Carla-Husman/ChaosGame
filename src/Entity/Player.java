package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Player extends Entity {
    public final int screenX;
    public final int screenY;
    public int points = 0;
    public int coin = 0;
    public int apple = 0;
    public String reason = "";
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        //coordanatele acestea nu se vor schimba pe parcursul jocului
        screenX = gp.screenWidth / 2 - gp.tileSize;
        screenY = gp.screenHeight / 2 - gp.tileSize;

        //dreptunghiul in care se incadreaza playerul
        solidArea = new Rectangle(0, 0, gp.tileSize - 10, gp.tileSize * 2 - 10);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImagine();
    }

    public void setDefaultValues() {
        worldX = 7 * gp.tileSize; //pozitia jucatorului pe mapa
        worldY = 5 * gp.tileSize;
        speed = 6;
        direction = "right"; //directia initiala
    }

    public void getPlayerImagine() {
        R1 = setup("/Player/Girl1R");
        R2 = setup("/Player/Girl2R");
        R3 = setup("/Player/Girl3R");
        R4 = setup("/Player/Girl4R");
        R5 = setup("/Player/Girl5R");
        RJ = setup("/Player/GirlJumpR");
        L1 = setup("/Player/Girl1L");
        L2 = setup("/Player/Girl2L");
        L3 = setup("/Player/Girl3L");
        L4 = setup("/Player/Girl4L");
        L5 = setup("/Player/Girl5L");
        LJ = setup("/Player/GirlJumpL");
    }

    //apelata de 60 de ori pe secunda
    public void update() {
        if (keyH.up || keyH.down ||
                keyH.right || keyH.left) {
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.checker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK MONSTER COLLISION
            int enemyIndex = gp.checker.checkEntity(this, gp.enemies);
            contactMonster(enemyIndex);

            //daca collision e false, atunci playerul se poate misca
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
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
    }

    public void pickUpObject(int i) {
        if (i != -1) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Coin":
                    coin++;
                    points = points + 150;
                    gp.obj[i] = null;
                    break;
                case "Apple":
                    apple++;
                    points = points + 100;
                    gp.obj[i] = null;
                    break;
                case "Bomb":
                    reason = "hit the Bomb";
                    points = points - 500;
                    gp.dataBase.time2 = LocalDateTime.now();
                    gp.gameState = gp.gameOverState;
                    break;
                case "Portal":
                    gp.dataBase.time2 = LocalDateTime.now();
                    gp.gameState = gp.gameCompleted;
                    break;
            }
        }
    }

    public void contactMonster(int i) {
        if (i != -1) {
            gp.dataBase.time2 = LocalDateTime.now();
            reason = "hit the Enemy";
            gp.gameState = gp.gameOverState;
            points = points - 500;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = RJ;
                break;
            case "down":
                image = R1;
                break;
            case "right":
                if (spriteNum == 1) {
                    image = R1;
                }
                if (spriteNum == 2) {
                    image = R2;
                }
                if (spriteNum == 3) {
                    image = R3;
                }
                if (spriteNum == 4) {
                    image = R4;
                }
                if (spriteNum == 5) {
                    image = R5;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = L1;
                }
                if (spriteNum == 2) {
                    image = L2;
                }
                if (spriteNum == 3) {
                    image = L3;
                }
                if (spriteNum == 4) {
                    image = L4;
                }
                if (spriteNum == 5) {
                    image = L5;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
