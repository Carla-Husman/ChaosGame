package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

//afiseaza informatii pe ecran
public class UI {
    private final Color titleColor = new Color(15, 70, 20);
    private final Font font = new Font("SansSerif", Font.BOLD, 45);
    private final Font titleFont = new Font("CHAOS", Font.BOLD, 100);
    private final String[] options = {
            "Start",
            "Story",
            "Game's Rules",
            "Exit"
    };
    public int currentChoice = 0;
    public BufferedImage background;
    GamePanel gp;
    Font TNR_40, TNR_65;
    private Graphics2D g2;

    public UI(GamePanel gp) {
        this.gp = gp;
        TNR_40 = new Font("Times New Roman", Font.BOLD, 40);
        TNR_65 = new Font("Times New Roman", Font.BOLD, 65);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.menuState) {
            drawMenu();
        } else if (gp.gameState == gp.playState) {
            drawPlay();
        } else if (gp.gameState == gp.storyState) {
            drawAnotherState("RulesState");
        } else if (gp.gameState == gp.rulesState) {
            drawAnotherState("StoryState");
        } else if (gp.gameState == gp.gameOverState) {
            drawGameOverCompletedState(0);
        } else if (gp.gameState == gp.gameCompleted) {
            drawGameOverCompletedState(1);
        }
    }

    public void drawMenu() {

        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background/BG11.png")));
            g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, null);

            g2.setColor(titleColor);
            g2.setFont(titleFont);
            drawCenteredString("CHAOS", 750, 300, g2);

            g2.setFont(font);
            for (int i = 0; i < options.length; i++) {
                if (i == currentChoice) {
                    g2.setColor((new Color(10, 100, 100)));
                } else {
                    g2.setColor(Color.black);
                }
                drawCenteredString(options[i], 750, 550 + i * 100, g2);
            }

            gp.dataBase.writeFromDataBase();
            String text;
            if (gp.dataBase.DATE == null) {
                text = "";
            } else {
                text = "Last login: " + gp.dataBase.DATE + "  " + gp.dataBase.START_TIME;
            }
            g2.setColor((new Color(15, 70, 20)));
            g2.setFont(new Font("SansSerif", Font.BOLD, 20));
            g2.drawString(text, 5, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void drawGameOverCompletedState(int index) {
        //punem in baza de date
        if (gp.dataBase.flag) {
            gp.dataBase.writeInDataBase();
        }

        g2.setFont(TNR_65);
        g2.setColor(Color.WHITE);

        String text;
        int textLength;
        if (index == 0) {
            text = "GAME OVER!";
        } else {
            text = "GAME COMPLETED!";
        }

        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = gp.screenWidth / 2 - textLength / 2;
        int y = gp.screenHeight / 2 - gp.screenHeight / 6 - 40;
        g2.drawString(text, x, y);

        //citim din baza de date
        gp.dataBase.writeFromDataBase();

        String[] texts = {("You collected: " + gp.dataBase.COINS + " coins (+150)"),
                (gp.dataBase.APPLES + " apples (+100)"),
                ("You " + gp.dataBase.GM_REASON + " (-500 points)"),
                ("Final score: " + gp.dataBase.POINTS)};

        x = gp.screenWidth / 2 - gp.tileSize * 4 - 30;
        y = gp.screenHeight / 2 -20;

        g2.setFont(new Font("Times New Roman", Font.BOLD, 35));
        for (String s : texts) {
            g2.drawString(s, x, y);
            y = y + 40;
        }


        text = "Backspace: to the menu";
        g2.drawString(text, 10, y + 50);

        text = "Esc: Close the game";
        g2.drawString(text, 10, y + 100);

        gp.dataBase.flag = false;
    }

    public void drawPlay() {
        g2.setFont(TNR_40);
        g2.setColor(Color.BLACK);
        g2.drawString("Points = " + gp.player.points, 15, 37);
    }

    public void drawAnotherState(String imageName) {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background/" + imageName + ".png")));
            g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } catch (IOException e) {
            System.out.println("Nu s-a putut incarca " + imageName + ".png");
            e.printStackTrace();
        }
    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
}
