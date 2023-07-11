package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Bomb extends Entity {

    public OBJ_Bomb(GamePanel gp) {
        super(gp);
        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.width = gp.tileSize - 5;
        solidArea.height = gp.tileSize - 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        name = "Bomb";
        L1 = setup("/Objects/Bomb");
        collision = true;
    }
}
