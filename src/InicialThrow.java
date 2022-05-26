

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import monopoly.Dice;
import monopoly.Monopoly;
import monopoly.Player;

public class InicialThrow extends javax.swing.JFrame {

    List<Player> players = new ArrayList<Player>();
    List<Dice> dices = new ArrayList<Dice>();

    int turns = 0;

    public InicialThrow(List<Player> players, List<Dice> dices) {

        initComponents();

        this.players = players;
        this.dices = dices;

        setInicialStatus();

    }

    public ImageIcon setDiceImages(int dice) {

        ImageIcon dice_icon = null;

        switch (dice) {

            case 1:

                dice_icon = new ImageIcon(getClass().getResource("/Dados/1.png"));

                break;

            case 2:

                dice_icon = new ImageIcon(getClass().getResource("/Dados/2.png"));

                break;

            case 3:

                dice_icon = new ImageIcon(getClass().getResource("/Dados/3.png"));

                break;

            case 4:

                dice_icon = new ImageIcon(getClass().getResource("/Dados/4.png"));

                break;

            case 5:

                dice_icon = new ImageIcon(getClass().getResource("/Dados/5.png"));

                break;

            case 6:

                dice_icon = new ImageIcon(getClass().getResource("/Dados/6.png"));

                break;
        }

        return dice_icon;

    }

    public Player CurrentPlayer() {

        Player currentPlayer = players.get(turns);

        return currentPlayer;
    }

    public void noDraws() {

        if (turns > 0) {

            boolean coincidences = true;

            while (coincidences == true) {

                coincidences = false;

                for (int i = 0; i < turns; i++) {

                    if (CurrentPlayer().getDiceResult() == players.get(i).getDiceResult()) {

                        coincidences = true;
                    }

                }
                if (coincidences == true) {

                    int dice1 = CurrentPlayer().rollDice(dices.get(0));

                    int dice2 = CurrentPlayer().rollDice(dices.get(1));

                    CurrentPlayer().setDiceResult(dice1 + dice2);

                    if (turns == 1) {

                        dice1_borja.setIcon(setDiceImages(dice1));
                        dice2_borja.setIcon(setDiceImages(dice2));
                        result_borja.setText(Integer.toString(players.get(1).getDiceResult()));

                    } else if (turns == 2) {

                        dice1_viera.setIcon(setDiceImages(dice1));
                        dice2_viera.setIcon(setDiceImages(dice2));
                        result_viera.setText(Integer.toString(players.get(2).getDiceResult()));

                    } else if (turns == 3) {

                        teo1_dice.setIcon(setDiceImages(dice1));
                        teo2_dice.setIcon(setDiceImages(dice2));
                        result_teo.setText(Integer.toString(players.get(3).getDiceResult()));

                    }

                }

            }

        }

    }

    public void nextButtonEnable() {

        if (turns == players.size()) {

            organizeList();

            dice_button.setEnabled(false);

            next_button.setEnabled(true);
        }

    }

    public void backToMain() {

        Monopoly main_monopoly = new Monopoly();

        try {

            main_monopoly.createBoard(players);

        } catch (IOException ex) {
            Logger.getLogger(InicialThrow.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }

    public void turns() {

        turns += 1;
        
         if (turns < players.size()) {

            current_turn.setText(CurrentPlayer().getName());

        }

    }

    public void organizeList() {

        Collections.sort(players, Comparator.comparing(Player::getDiceResult));

        Collections.reverse(players);
    }

    public void setInicialStatus() {

        if (turns < players.size()) {

            current_turn.setText(players.get(turns).getName());

        }

        if (players.size() == 3) {

            viera_status.setText("Jugando");

        } else if (players.size() == 4) {

            viera_status.setText("Jugando");
            teo_status.setText("Jugando");

        }

        next_button.setEnabled(false);

    }

    public void throwDice() {

        java.util.Timer t = new java.util.Timer();

        TimerTask task = new TimerTask() {

            int count = 0;

            public void run() {

                int dice1 = CurrentPlayer().rollDice(dices.get(0));

                int dice2 = CurrentPlayer().rollDice(dices.get(1));

                if (turns == 0) {

                    dice1_hinestroza.setIcon(setDiceImages(dice1));
                    dice2_hinestroza.setIcon(setDiceImages(dice2));

                } else if (turns == 1) {

                    dice1_borja.setIcon(setDiceImages(dice1));
                    dice2_borja.setIcon(setDiceImages(dice2));

                } else if (turns == 2) {

                    dice1_viera.setIcon(setDiceImages(dice1));
                    dice2_viera.setIcon(setDiceImages(dice2));

                } else if (turns == 3) {

                    teo1_dice.setIcon(setDiceImages(dice1));
                    teo2_dice.setIcon(setDiceImages(dice2));

                }

                CurrentPlayer().setDiceResult(dice1 + dice2);

                count += 1;

                if (count == 10) {

                    if (turns == 0) {

                        result_hinestroza.setText(Integer.toString(players.get(0).getDiceResult()));

                    } else if (turns == 1) {

                        result_borja.setText(Integer.toString(players.get(1).getDiceResult()));

                    } else if (turns == 2) {

                        result_viera.setText(Integer.toString(players.get(2).getDiceResult()));

                    } else if (turns == 3) {

                        result_teo.setText(Integer.toString(players.get(3).getDiceResult()));

                    }
                    noDraws();

                    turns();

                    dice_button.setEnabled(true);
                    
                    nextButtonEnable();
                     
                    t.cancel();

                }

            }

        };

        t.schedule(task, 0, 100);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        current_turn = new javax.swing.JLabel();
        dice_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        borja_panel = new javax.swing.JPanel();
        borja_icon = new javax.swing.JLabel();
        dice1_borja = new javax.swing.JLabel();
        dice2_borja = new javax.swing.JLabel();
        total1 = new javax.swing.JLabel();
        result_borja = new javax.swing.JLabel();
        borja_name = new javax.swing.JLabel();
        borja_status = new javax.swing.JLabel();
        viera_panel = new javax.swing.JPanel();
        viera_icon = new javax.swing.JLabel();
        dice1_viera = new javax.swing.JLabel();
        dice2_viera = new javax.swing.JLabel();
        total2 = new javax.swing.JLabel();
        result_viera = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        viera_status = new javax.swing.JLabel();
        teo_panel = new javax.swing.JPanel();
        teo_icon = new javax.swing.JLabel();
        teo1_dice = new javax.swing.JLabel();
        teo2_dice = new javax.swing.JLabel();
        total3 = new javax.swing.JLabel();
        result_teo = new javax.swing.JLabel();
        teo_name = new javax.swing.JLabel();
        teo_status = new javax.swing.JLabel();
        hinestroza_panels = new javax.swing.JPanel();
        hinestroza_icon = new javax.swing.JLabel();
        dice2_hinestroza = new javax.swing.JLabel();
        dice1_hinestroza = new javax.swing.JLabel();
        hinestroza_status = new javax.swing.JLabel();
        result_hinestroza = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        hinestroza_name = new javax.swing.JLabel();
        next_button = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        current_turn.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        current_turn.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(current_turn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 270, 50));

        dice_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MenuDados/buttons/1.png"))); // NOI18N
        dice_button.setBorder(null);
        dice_button.setBorderPainted(false);
        dice_button.setContentAreaFilled(false);
        dice_button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dice_button.setFocusPainted(false);
        dice_button.setFocusable(false);
        dice_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dice_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(dice_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 270, 120));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TURNO: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, 61));

        borja_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        borja_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/2.png"))); // NOI18N
        borja_panel.add(borja_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 16, -1, -1));
        borja_panel.add(dice1_borja, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));
        borja_panel.add(dice2_borja, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        total1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        total1.setText("Total:");
        borja_panel.add(total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        result_borja.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        result_borja.setText("---");
        borja_panel.add(result_borja, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        borja_name.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        borja_name.setText("Borja");
        borja_panel.add(borja_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 41, -1, -1));

        borja_status.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        borja_status.setText("Jugando");
        borja_panel.add(borja_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        getContentPane().add(borja_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 550, 110));

        viera_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viera_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/3.png"))); // NOI18N
        viera_panel.add(viera_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 16, -1, -1));
        viera_panel.add(dice1_viera, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));
        viera_panel.add(dice2_viera, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        total2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        total2.setText("Total:");
        viera_panel.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        result_viera.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        result_viera.setText("---");
        viera_panel.add(result_viera, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Viera");
        viera_panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 41, -1, -1));

        viera_status.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        viera_status.setText("En banca");
        viera_panel.add(viera_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        getContentPane().add(viera_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 550, 110));

        teo_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        teo_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/4.png"))); // NOI18N
        teo_panel.add(teo_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 16, -1, -1));
        teo_panel.add(teo1_dice, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));
        teo_panel.add(teo2_dice, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        total3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        total3.setText("Total:");
        teo_panel.add(total3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        result_teo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        result_teo.setText("---");
        teo_panel.add(result_teo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        teo_name.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        teo_name.setText("Teo");
        teo_panel.add(teo_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 41, -1, -1));

        teo_status.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        teo_status.setText("En banca");
        teo_panel.add(teo_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        getContentPane().add(teo_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 550, 110));

        hinestroza_panels.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hinestroza_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/1.png"))); // NOI18N
        hinestroza_panels.add(hinestroza_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 16, -1, -1));
        hinestroza_panels.add(dice2_hinestroza, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));
        hinestroza_panels.add(dice1_hinestroza, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        hinestroza_status.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        hinestroza_status.setText("Jugando");
        hinestroza_panels.add(hinestroza_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        result_hinestroza.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        result_hinestroza.setText("---");
        hinestroza_panels.add(result_hinestroza, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, -1, -1));

        total.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        total.setText("Total:");
        hinestroza_panels.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        hinestroza_name.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        hinestroza_name.setText("Hinestroza");
        hinestroza_panels.add(hinestroza_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        getContentPane().add(hinestroza_panels, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 550, 110));

        next_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MenuDados/buttons/2.png"))); // NOI18N
        next_button.setBorder(null);
        next_button.setBorderPainted(false);
        next_button.setContentAreaFilled(false);
        next_button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        next_button.setFocusPainted(false);
        next_button.setFocusable(false);
        next_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(next_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 640, 260, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Dice_Monopoly.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dice_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dice_buttonActionPerformed

        dice_button.setEnabled(false);

        throwDice();

    }//GEN-LAST:event_dice_buttonActionPerformed

    private void next_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_buttonActionPerformed

        backToMain();

    }//GEN-LAST:event_next_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel borja_icon;
    private javax.swing.JLabel borja_name;
    private javax.swing.JPanel borja_panel;
    private javax.swing.JLabel borja_status;
    private javax.swing.JLabel current_turn;
    private javax.swing.JLabel dice1_borja;
    private javax.swing.JLabel dice1_hinestroza;
    private javax.swing.JLabel dice1_viera;
    private javax.swing.JLabel dice2_borja;
    private javax.swing.JLabel dice2_hinestroza;
    private javax.swing.JLabel dice2_viera;
    private javax.swing.JButton dice_button;
    private javax.swing.JLabel hinestroza_icon;
    private javax.swing.JLabel hinestroza_name;
    private javax.swing.JPanel hinestroza_panels;
    private javax.swing.JLabel hinestroza_status;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton next_button;
    private javax.swing.JLabel result_borja;
    private javax.swing.JLabel result_hinestroza;
    private javax.swing.JLabel result_teo;
    private javax.swing.JLabel result_viera;
    private javax.swing.JLabel teo1_dice;
    private javax.swing.JLabel teo2_dice;
    private javax.swing.JLabel teo_icon;
    private javax.swing.JLabel teo_name;
    private javax.swing.JPanel teo_panel;
    private javax.swing.JLabel teo_status;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total1;
    private javax.swing.JLabel total2;
    private javax.swing.JLabel total3;
    private javax.swing.JLabel viera_icon;
    private javax.swing.JPanel viera_panel;
    private javax.swing.JLabel viera_status;
    // End of variables declaration//GEN-END:variables
}
