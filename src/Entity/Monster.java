package Entity;

import Main.GamePanel;

import java.util.Random;

public class Monster extends Entity {
    public Monster(GamePanel gp, String name) {
        super(gp);

        this.name = name;

        speed = 1;
        solidArea.x = 5;
        solidArea.y = 5;
        solidArea.width = gp.tileSize - 5;
        solidArea.height = gp.tileSize - 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImagine();
    }

    public void getImagine() {
        R1 = L1 = setup("/Enemies/" + name);
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 50) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "right";
            }
            if (i > 25 && i <= 50) {
                direction = "left";
            }
            if (i > 50 && i <= 75) {
                direction = "right";
            }
            if (i > 75) {
                direction = "left";
            }

            actionLockCounter = 0;
        }
    }
}
