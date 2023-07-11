package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // returneaza codul tastei apasate
        //navigare meniu
        if (gp.gameState == gp.menuState) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.currentChoice--;
                if (gp.ui.currentChoice < 0)
                    gp.ui.currentChoice = 3;
            } else if (code == KeyEvent.VK_DOWN) {
                gp.ui.currentChoice++;
                if (gp.ui.currentChoice > 3)
                    gp.ui.currentChoice = 0;
            }
        } else if (gp.gameState == gp.rulesState || gp.gameState == gp.storyState || gp.gameState == gp.gameOverState || gp.gameState == gp.gameCompleted) {
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (gp.gameState == gp.gameOverState || gp.gameState == gp.gameCompleted) {
                    gp.dataBase.flag = true;
                }
                gp.gameState = gp.menuState;
                gp.player = null;
                gp.obj = null;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.dataBase.closeDataBase();
                System.exit(0);
            }
        }
        //actualizare state
        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.currentChoice) {
                case 0:
                    gp.gameState = gp.playState;
                    break;
                case 1:
                    gp.gameState = gp.rulesState;
                    break;
                case 2:
                    gp.gameState = gp.storyState;
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

        if (code == KeyEvent.VK_UP) {
            up = true;
        }

        if (code == KeyEvent.VK_DOWN) {
            down = true;
        }

        if (code == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (code == KeyEvent.VK_RIGHT) {
            right = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = false;
        }

        if (code == KeyEvent.VK_DOWN) {
            down = false;
        }

        if (code == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (code == KeyEvent.VK_RIGHT) {
            right = false;
        }

    }
}