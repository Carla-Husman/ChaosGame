package Main;

import Entity.Monster;
import Object.*;


public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Coin(gp);
        gp.obj[0].worldX = 3 * gp.tileSize;
        gp.obj[0].worldY = 4 * gp.tileSize;

        gp.obj[1] = new OBJ_Coin(gp);
        gp.obj[1].worldX = 4 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;

        gp.obj[2] = new OBJ_Coin(gp);
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;

        gp.obj[3] = new OBJ_Coin(gp);
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;

        gp.obj[5] = new OBJ_Coin(gp);
        gp.obj[5].worldX = 15 * gp.tileSize;
        gp.obj[5].worldY = 6 * gp.tileSize;

        gp.obj[6] = new OBJ_Coin(gp);
        gp.obj[6].worldX = 27 * gp.tileSize;
        gp.obj[6].worldY = 10 * gp.tileSize;

        gp.obj[7] = new OBJ_Apple(gp);
        gp.obj[7].worldX = 19 * gp.tileSize;
        gp.obj[7].worldY = 8 * gp.tileSize;

        gp.obj[8] = new OBJ_Apple(gp);
        gp.obj[8].worldX = 26 * gp.tileSize;
        gp.obj[8].worldY = 10 * gp.tileSize;

        gp.obj[9] = new OBJ_Bomb(gp);
        gp.obj[9].worldX = 29 * gp.tileSize;
        gp.obj[9].worldY = 4 * gp.tileSize;

        gp.obj[9] = new OBJ_Bomb(gp);
        gp.obj[9].worldX = 30 * gp.tileSize;
        gp.obj[9].worldY = 9 * gp.tileSize;

        gp.obj[9] = new OBJ_Bomb(gp);
        gp.obj[9].worldX = 31 * gp.tileSize;
        gp.obj[9].worldY = 10 * gp.tileSize;

        gp.obj[10] = new OBJ_Apple(gp);
        gp.obj[10].worldX = 37 * gp.tileSize;
        gp.obj[10].worldY = 5 * gp.tileSize;

        gp.obj[11] = new OBJ_Bomb(gp);
        gp.obj[11].worldX = 42 * gp.tileSize;
        gp.obj[11].worldY = 7 * gp.tileSize;

        gp.obj[12] = new OBJ_Bomb(gp);
        gp.obj[12].worldX = 41 * gp.tileSize;
        gp.obj[12].worldY = 7 * gp.tileSize;

        gp.obj[13] = new OBJ_Apple(gp);
        gp.obj[13].worldX = 47 * gp.tileSize;
        gp.obj[13].worldY = 9 * gp.tileSize;

        gp.obj[14] = new OBJ_Apple(gp);
        gp.obj[14].worldX = 46 * gp.tileSize;
        gp.obj[14].worldY = 9 * gp.tileSize;

        gp.obj[15] = new OBJ_Bomb(gp);
        gp.obj[15].worldX = 64 * gp.tileSize;
        gp.obj[15].worldY = 10 * gp.tileSize;

        gp.obj[16] = new OBJ_Bomb(gp);
        gp.obj[16].worldX = 53 * gp.tileSize;
        gp.obj[16].worldY = 10 * gp.tileSize;

        gp.obj[17] = new OBJ_Coin(gp);
        gp.obj[17].worldX = 55 * gp.tileSize;
        gp.obj[17].worldY = 6 * gp.tileSize;

        gp.obj[18] = new OBJ_Coin(gp);
        gp.obj[18].worldX = 67 * gp.tileSize;
        gp.obj[18].worldY = 6 * gp.tileSize;

        gp.obj[19] = new OBJ_Apple(gp);
        gp.obj[19].worldX = 60 * gp.tileSize;
        gp.obj[19].worldY = 4 * gp.tileSize;

        gp.obj[20] = new OBJ_Coin(gp);
        gp.obj[20].worldX = 75 * gp.tileSize;
        gp.obj[20].worldY = 6 * gp.tileSize;

        gp.obj[21] = new OBJ_Apple(gp);
        gp.obj[21].worldX = 81 * gp.tileSize;
        gp.obj[21].worldY = 5 * gp.tileSize;

        gp.obj[22] = new OBJ_Apple(gp);
        gp.obj[22].worldX = 88 * gp.tileSize;
        gp.obj[22].worldY = 4 * gp.tileSize;

        gp.obj[23] = new OBJ_Coin(gp);
        gp.obj[23].worldX = 93 * gp.tileSize;
        gp.obj[23].worldY = 9 * gp.tileSize;

        gp.obj[24] = new OBJ_Bomb(gp);
        gp.obj[24].worldX = 53 * gp.tileSize;
        gp.obj[24].worldY = 10 * gp.tileSize;

        gp.obj[25] = new OBJ_Bomb(gp);
        gp.obj[25].worldX = 97 * gp.tileSize;
        gp.obj[25].worldY = 10 * gp.tileSize;

        gp.obj[26] = new OBJ_Portal(gp);
        gp.obj[26].worldX = 103 * gp.tileSize;
        gp.obj[26].worldY = 10 * gp.tileSize;

        gp.obj[27] = new OBJ_Bomb(gp);
        gp.obj[27].worldX = 6 * gp.tileSize;
        gp.obj[27].worldY = 10 * gp.tileSize;
    }

    public void setEnemy() {
        gp.enemies[0] = new Monster(gp, "Krabba");
        gp.enemies[0].worldX = 2 * gp.tileSize;
        gp.enemies[0].worldY = 10 * gp.tileSize;

        gp.enemies[1] = new Monster(gp, "Klorofyll");
        gp.enemies[1].worldX = 15 * gp.tileSize;
        gp.enemies[1].worldY = 10 * gp.tileSize;

        gp.enemies[2] = new Monster(gp, "Krabba");
        gp.enemies[2].worldX = 24 * gp.tileSize;
        gp.enemies[2].worldY = 10 * gp.tileSize;

        gp.enemies[3] = new Monster(gp, "Klorofyll");
        gp.enemies[3].worldX = 41 * gp.tileSize;
        gp.enemies[3].worldY = 10 * gp.tileSize;

        gp.enemies[4] = new Monster(gp, "Klorofyll");
        gp.enemies[4].worldX = 60 * gp.tileSize;
        gp.enemies[4].worldY = 10 * gp.tileSize;

        gp.enemies[5] = new Monster(gp, "Krabba");
        gp.enemies[5].worldX = 77 * gp.tileSize;
        gp.enemies[5].worldY = 10 * gp.tileSize;

        gp.enemies[5] = new Monster(gp, "Krabba");
        gp.enemies[5].worldX = 85 * gp.tileSize;
        gp.enemies[5].worldY = 10 * gp.tileSize;

    }

}
