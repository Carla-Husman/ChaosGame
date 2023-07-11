package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    //Entity pentru ca va fi folosita la orice tip de personaj
    public void checkTile(Entity entity) {
        /*
          LeftX      RightX
     TopY   ____________
            |          |
            |          |
            |          |
    BottomY |_ _ _ _ _ |      < - Dreptunghiul pentru coliziune

         */
        int LeftX = entity.worldX + entity.solidArea.x;
        int RightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int TopY = entity.worldY + entity.solidArea.y;
        int BottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int LeftCol = LeftX / gp.tileSize;
        int RightCol = RightX / gp.tileSize;
        int TopRow = TopY / gp.tileSize;
        int BottomRow = BottomY / gp.tileSize;

        int tile1, tile2;

        switch (entity.direction) {
            case "up":
                TopRow = (TopY - entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManager.mapTileNum[RightCol][TopRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision)
                    entity.collisionOn = true;
                break;

            case "down":
                BottomRow = (BottomY + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[LeftCol][BottomRow];
                tile2 = gp.tileManager.mapTileNum[RightCol][BottomRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision)
                    entity.collisionOn = true;
                break;

            case "right":
                RightCol = (RightX + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[RightCol][TopRow];
                tile2 = gp.tileManager.mapTileNum[RightCol][BottomRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision)
                    entity.collisionOn = true;
                break;

            case "left":
                LeftCol = (LeftX - entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManager.mapTileNum[LeftCol][BottomRow];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision)
                    entity.collisionOn = true;
                break;
        }

    }

    //vom verifica daca entity e un player
    public int checkObject(Entity entity, boolean player) {
        int index = -1;
        for (int i = 0; i < gp.obj.length; ++i)
            if (gp.obj[i] != null) {
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = -1;
        for (int i = 0; i < target.length; ++i)
            if (target[i] != null) {
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //Get the object's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }

        return index;
    }

    public void checkPlayer(Entity entity) {
        //Get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //Get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }

}
