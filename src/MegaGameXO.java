
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class MegaGameXO extends JFrame {
        JButton[][] btns;

        public MegaGameXO(){
        setBounds(300,200,600,600);
        setTitle("MegaGameXO");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font font = new Font("Arial",Font.BOLD,24);
        btns = new JButton[Logic.SIZE][Logic.SIZE];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(Logic.SIZE, Logic.SIZE));
        for (int i = 0; i < Logic.SIZE ; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                btns[i][j] = new JButton();
                btns[i][j].setFont(font);
                final int y = i;
                final int x = j;
                btns[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Logic.isCellValid(x, y)) {
                            System.out.println(x + " " + y);
                            Logic.map[y][x] = Logic.DOT_X;
                            Logic.humanWent = true;
                        }
                    }
                });

                panel.add(btns[i][j]);
            }
        }
        add(panel);
        setVisible(true);
        start();
            }

    public void start() {
        Logic.initMap();
        Logic.printMap();
        printMap();
        System.out.println("start....");
        while (true) {
            do {
                try {
                    sleep(100);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    }
        while (!Logic.humanWent);
            Logic.humanWent = false;
            System.out.println("human turned");
            Logic.printMap();
            printMap();
            if (Logic.checkWin(Logic.DOT_X)) {
                System.out.println("Игрок победил!!!");
                return;
            }
            if (Logic.isFull()) {
                System.out.println("Ничья, не осталось места ходить!");
                break;
            }

            Logic.aiTurn();
            System.out.println("ai turned");
            Logic.printMap();
            printMap();
            if (Logic.checkWin(Logic.DOT_O)) {
                System.out.println("Компьютер победил!!!");
                break;
            }
            if (Logic.isFull()) {
                System.out.println("Ничья, не осталось места ходить!");
                break;
            }
        }


    }

    public void printMap() {
        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                btns[i][j].setText(""+ Logic.map[i][j]);
                
            }
            
        }
    }
}

