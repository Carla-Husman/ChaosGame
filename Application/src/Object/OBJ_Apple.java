package Object;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Apple extends Entity {

    public OBJ_Apple(GamePanel gp) {
        super(gp);
        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.width = gp.tileSize - 5;
        solidArea.height = gp.tileSize - 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        name = "Apple";
        L1 = setup("/Objects/Apple");
        collision = true;
    }
}
