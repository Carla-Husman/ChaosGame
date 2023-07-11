package Object;

import Entity.Entity;
import Main.GamePanel;


public class OBJ_Portal extends Entity {

    public OBJ_Portal(GamePanel gp) {
        super(gp);

        solidArea.x = 5;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        name = "Portal";
        L1 = setup("/Objects/Portal");
        collision = true;
    }
}
