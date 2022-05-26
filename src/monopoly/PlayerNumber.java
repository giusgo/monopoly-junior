package monopoly;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import monopoly.Monopoly;

public class PlayerNumber extends javax.swing.JFrame {

    Monopoly main_monopoly = new Monopoly();

    public PlayerNumber() {

        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        two_players = new javax.swing.JButton();
        three_players = new javax.swing.JButton();
        four_players = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        two_players.setBackground(new java.awt.Color(207, 250, 250));
        two_players.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu1/buttons/1.png"))); // NOI18N
        two_players.setBorder(null);
        two_players.setBorderPainted(false);
        two_players.setContentAreaFilled(false);
        two_players.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        two_players.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_playersActionPerformed(evt);
            }
        });
        getContentPane().add(two_players, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 230, 60));

        three_players.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu1/buttons/2.png"))); // NOI18N
        three_players.setBorder(null);
        three_players.setBorderPainted(false);
        three_players.setContentAreaFilled(false);
        three_players.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        three_players.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                three_playersActionPerformed(evt);
            }
        });
        getContentPane().add(three_players, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 230, 60));

        four_players.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu1/buttons/3.png"))); // NOI18N
        four_players.setBorder(null);
        four_players.setBorderPainted(false);
        four_players.setContentAreaFilled(false);
        four_players.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        four_players.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                four_playersActionPerformed(evt);
            }
        });
        getContentPane().add(four_players, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 630, 230, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/menu1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void two_playersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_playersActionPerformed
        
        try {
            
            main_monopoly.createPlayers(2);
            
        } catch (IOException ex) {
            
            Logger.getLogger(PlayerNumber.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_two_playersActionPerformed

    private void three_playersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_three_playersActionPerformed
       
        try {
            main_monopoly.createPlayers(3);
            
            this.dispose();
            
        } catch (IOException ex) {
            
            Logger.getLogger(PlayerNumber.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }//GEN-LAST:event_three_playersActionPerformed

    private void four_playersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_four_playersActionPerformed

        try {
            main_monopoly.createPlayers(4);
            
            this.dispose();
            
        } catch (IOException ex) {
            
            Logger.getLogger(PlayerNumber.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }//GEN-LAST:event_four_playersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton four_players;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton three_players;
    private javax.swing.JButton two_players;
    // End of variables declaration//GEN-END:variables
}
