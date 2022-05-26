package monopoly;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends javax.swing.JFrame {

    private List<Dice> dices;
    private List<Player> players;
    private CircularLinkedList squares;
    private List<Card> cards;
    private Player currentPlayer;
    private int turns;
    private Banker banker;
    private CircularLinkedList.Node jail;
    private CircularLinkedList.Node reten;
    private CircularLinkedList.Node transmetro;
    private CircularLinkedList.Node ua;
    private CircularLinkedList.Node troja;

    Color color = new Color(253, 28, 48);

    public Board(List<Player> players, List<Dice> dices, CircularLinkedList squares, List<Card> cards) {

        initComponents();

        this.players = players;
        this.dices = dices;
        this.squares = squares;
        this.currentPlayer = null;
        this.turns = 0;
        this.cards = cards;
        this.banker = new Banker();
        this.jail = getJailNode();
        this.reten = getRetenNode();
        this.transmetro = getTransmetroNode();
        this.ua = getUANode();
        this.troja = getTrojaNode();

        setInicialPositions();
        InicialSetup();
        updatePanelColors();

    }

    private void InicialSetup() {

        //se the inicial player name turn
        player_name.setText(players.get(0).getName());

        // set the player icon
        players.get(0).setIcon(player1);

        players.get(1).setIcon(player2);

        player3.setVisible(false);

        player4.setVisible(false);

        if (players.size() == 3) {

            players.get(2).setIcon(player3);

            player3.setVisible(true);

        } else if (players.size() == 4) {

            players.get(2).setIcon(player3);

            players.get(3).setIcon(player4);

            player3.setVisible(true);

            player4.setVisible(true);

        }

        for (int k = 0; k < players.size(); k++) {

            if (players.get(k).getName().equals("Hinestroza")) {

                Hinestroza_icon.setIcon(players.get(k).getIcon().getIcon());

                players.get(k).setMoneyIcon(hinestroza_money);

                players.get(k).getMoneyIcon().setText(Integer.toString(players.get(k).getMoney()));

                hinestroza_button.setEnabled(true);

            }
            if (players.get(k).getName().equals("Borja")) {

                borja_icon.setIcon(players.get(k).getIcon().getIcon());
                players.get(k).setMoneyIcon(borja_money);

                players.get(k).getMoneyIcon().setText(Integer.toString(players.get(k).getMoney()));

                borja_button.setEnabled(true);

            }

            if (players.get(k).getName().equals("Viera")) {

                viera_icon.setIcon(players.get(k).getIcon().getIcon());

                players.get(k).setMoneyIcon(viera_money);

                players.get(k).getMoneyIcon().setText(Integer.toString(players.get(k).getMoney()));

                viera_button.setEnabled(true);

            }

            if (players.get(k).getName().equals("Teo")) {

                teo_icon.setIcon(players.get(k).getIcon().getIcon());

                players.get(k).setMoneyIcon(teo_money);

                players.get(k).getMoneyIcon().setText(Integer.toString(players.get(k).getMoney()));

                teo_button.setEnabled(true);

            }

        }

        Dice1.setIcon(setDiceImages(1));

        Dice2.setIcon(setDiceImages(1));

    }

    // set the dices images
    private ImageIcon setDiceImages(int dice) {

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

    private void updatePanelColors() {

        if (getCurrentPlayer().getName().equals("Hinestroza")) {

            hinestroza_color.setBackground(Color.yellow);

        } else {

            hinestroza_color.setBackground(color);

        }

        if (getCurrentPlayer().getName().equals("Borja")) {

            borja_color.setBackground(Color.yellow);

        } else {

            borja_color.setBackground(color);

        }

        if (getCurrentPlayer().getName().equals("Viera")) {

            viera_panel.setBackground(Color.yellow);

        } else {

            viera_panel.setBackground(color);

        }

        if (getCurrentPlayer().getName().equals("Teo")) {

            teo_color.setBackground(Color.yellow);

        } else {

            teo_color.setBackground(color);

        }

    }

    private void setInicialPositions() {

        for (int i = 0; i < players.size(); i++) {

            players.get(i).setCurrentPosition(this.squares.head);

        }

    }

    // get the player in turn
    public Player getCurrentPlayer() {

        return this.players.get(this.turns);

    }

    // get the jail node
    public CircularLinkedList.Node getJailNode() {

        CircularLinkedList.Node jail = this.squares.head;

        while (!jail.property.getType().equals("jail")) {

            jail = jail.next;

        }

        return jail;

    }

    // get the reten node
    public CircularLinkedList.Node getRetenNode() {

        CircularLinkedList.Node reten = squares.head;

        while (!reten.property.getType().equals("reten")) {

            reten = reten.next;

        }

        return reten;

    }

    public CircularLinkedList.Node getTransmetroNode() {

        CircularLinkedList.Node transmetro = squares.head;

        while (!transmetro.property.getName().equals("el transmetro")) {

            transmetro = transmetro.next;

        }

        return transmetro;

    }

    public CircularLinkedList.Node getUANode() {

        CircularLinkedList.Node ua = squares.head;

        while (!ua.property.getName().equals("la unitranca")) {

            ua = ua.next;

        }

        return ua;

    }

    public CircularLinkedList.Node getTrojaNode() {

        CircularLinkedList.Node troja = squares.head;

        while (!troja.property.getName().equals("la troja")) {

            troja = troja.next;

        }

        return troja;

    }

    //check if the throw had a double
    public boolean checkDoubles() {

        if (this.dices.get(0).getResult() == this.dices.get(1).getResult()) {

            return true;

        }

        return false;

    }

    //check consecutive doubles
    public void setConsecutiveDoubles(boolean doubles, Player player) {

        if (doubles) {

            player.setConsecutiveDoubles(player.getConsecutiveDoubles() + 1);

        } else {

            player.setConsecutiveDoubles(0);

        }

    }

    //move turns
    public void turns(boolean doubles) {

        if (!doubles) {

            if (this.turns == players.size() - 1) {

                this.turns = 0;

            } else {

                this.turns += 1;

            }

        }

    }

    //check if the the current postion corresponds to the exit
    public boolean checkExitSquare(Player player) {

        if (player.getCurrentPosition() == this.squares.head) {

            return true;

        }

        return false;

    }

    //check if the the current postion corresponds to the reten
    public boolean checkRetenSquare(Player player) {

        if (player.getCurrentPosition() == this.reten) {

            sendToJail(player);

            return true;
        }

        return false;

    }

    //check a player´s consecutive doubles
    public boolean consecutiveDoubles(Player player) {

        return player.getConsecutiveDoubles() == 3;

    }

    //send the player to jail
    public void sendToJail(Player player) {

        player.setCurrentPosition(jail);

        player.getIcon().setLocation(jail.pos_x, jail.pos_y);

        player.setConsecutiveDoubles(0);

        player.setJail(true);

    }

    public boolean inJail(Player player) throws IOException {

        int tries = 3;

        boolean doubles = false;

        Object[] options = {"Lanzar dados"};

        ImageIcon icon1 = setDiceImages(1);
        ImageIcon icon2 = setDiceImages(1);

        JPanel gui = new JPanel(new GridLayout(1, 0, -30, 0));

        JLabel text = new JLabel();
        text.setText("Intentos: " + tries);

        JLabel icon_1 = new JLabel(icon1);
        JLabel icon_2 = new JLabel(icon2);

        gui.add(text);
        gui.add(icon_1);
        gui.add(icon_2);

        while (tries >= 1 && doubles == false) {

            int result = JOptionPane.showOptionDialog(null,
                    gui, "CÁRCEL",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {

                // roll dices
                int dice1 = player.rollDice(dices.get(0));

                int dice2 = player.rollDice(dices.get(1));

                //set the Jlabels icons           
                icon_1.setIcon(setDiceImages(dice1));

                icon_2.setIcon(setDiceImages(dice2));

                player.setDiceResult(dice1 + dice2);

                doubles = checkDoubles();

            }

            if (doubles) {

                text.setText("¡Ha sido liberado!");

                JOptionPane.showMessageDialog(null,
                        gui, "CÁRCEL",
                        JOptionPane.PLAIN_MESSAGE
                );

                player.setJail(false);

                player.setConsecutiveDoubles(0);

                return true;

            }

            tries -= 1;

            text.setText("Intentos: " + tries);
        }

        if (!doubles) {

            text.setText("Mala suerte :(");

            JOptionPane.showMessageDialog(null,
                    gui, "CÁRCEL",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
        return false;
    }

    public boolean buyProperty(Player player) {

        return banker.sellProperty(player, player.getCurrentPosition().property);
    }

    public boolean payRent(Player player) {

        return banker.payRent(player);
    }

    public Card getCommunityCard() {

        Player player = getCurrentPlayer();

        int card = (int) (Math.random() * (this.cards.size()));

        while (!this.cards.get(card).getType().equals("community")) {

            card = (int) (Math.random() * (this.cards.size()));
        }

        System.out.println("esto se ejecuta: " + this.cards.get(4).getData());
        System.out.println("esto se ejecuta: " + this.cards.get(4).getStatus());

        JOptionPane.showMessageDialog(null,
                "", "Carta de Fortuna",
                JOptionPane.INFORMATION_MESSAGE,
                this.cards.get(card).getIcon());

        if (this.cards.get(card).getStatus()) {

            player.keepCard(this.cards.get(card));

            this.cards.remove(this.cards.get(card));

            nexTurn();

        } else {

            performCardAction(player, this.cards.get(card));

            return this.cards.get(card);
        }

        return null;

    }

    public Card getFortuneCard() {

        Player player = getCurrentPlayer();

        int card = (int) (Math.random() * (this.cards.size()));

        while (!this.cards.get(card).getType().equals("fortune")) {

            card = (int) (Math.random() * (this.cards.size()));
        }

        JOptionPane.showMessageDialog(null,
                "", "Carta de Comunidad",
                JOptionPane.INFORMATION_MESSAGE,
                this.cards.get(card).getIcon());

        performCardAction(player, this.cards.get(card));

        return this.cards.get(card);
    }

    public void returnCard(Card card) {

        this.cards.add(card);

    }

    public void performCardAction(Player player, Card card) {

        System.out.println("current player: " + player.getName());
        System.out.println(card.getAction());

        if (card.getAction().equals("pay")) {

            banker.updateMoney(player, getNumber(card.getData()));

            player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

            nexTurn();

        } else if (card.getAction().equals("pay all")) {

            int money = getNumber(card.getData());

            for (int i = 0; i < this.players.size(); i++) {

                banker.updateMoney(this.players.get(i), money);

                this.players.get(i).getMoneyIcon().setText(Integer.toString(this.players.get(i).getMoney()));

            }

            nexTurn();

        } else if (card.getAction().equals("charge")) {

            if (player.getMoney() < getNumber(card.getData())) {

                if (banker.seizeProperty(player, getNumber(card.getData()), null)) {

                    banker.updateMoney(player, -getNumber(card.getData()));

                    player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

                } else {

                    player.getMoneyIcon().setText("EN BANCA");

                    JOptionPane.showMessageDialog(null,
                            player.getName() + " ha sido eliminado ( ͡°͜ʖ͡°)", "JUGADOR ELIMINADO",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    updatePlayerGraphicComponents(player);

                    //avisalo que esta fuera por perro 
                    this.players.remove(player);

                }

            } else {

                banker.updateMoney(player, -getNumber(card.getData()));

                player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

            }

            nexTurn();

        } else if (card.getAction().equals("jail")) {

            sendToJail(player);

            nexTurn();

        } else if (card.getAction().equals("move")) {

            if (card.getData().toLowerCase().contains("retroceda")) {

                int positions = getNumber(card.getData());

                for (int i = 1; i <= positions; i++) {

                    player.prevPosition();

                    getCurrentPlayer().getIcon().setLocation(getCurrentPlayer().getX(), getCurrentPlayer().getY());
                }

            }

            nexTurn();

        } else if (card.getAction().equals("move charge")) {

            if (card.getData().equals("AVANCE AL TRANSMETRO. Si está a LA VENTA, puede comprársela al banco. Si es PROPIEDAD de alguien, pague el doble de la renta marcada.")) {

                movementsForCardActions(player, transmetro);

            } else if (card.getData().equals("Avance a  \"La UA\", si pasas por salida cobra $200.")) {

                movementsForCardActions(player, ua);

            } else if (card.getData().equals("Lloralo, ve a la troja y si pasas por salida cobras $200.")) {

                movementsForCardActions(player, troja);
            }

        } else if (card.getAction().equals("none")) {

            nexTurn();

        }

    }

    public int getNumber(String data) {

        String number = "";

        for (int i = 0; i < data.length(); i++) {

            if (Character.isDigit(data.charAt(i))) {

                number += data.charAt(i);
            }
        }

        return Integer.parseInt(number);

    }

    public void movementsForCardActions(Player player, CircularLinkedList.Node target_position) {

        java.util.Timer timer = new java.util.Timer();

        TimerTask task = new TimerTask() {

            @Override

            public void run() {

                roll_button.setEnabled(false);

                player.updatePosition();

                banker.exitPay(getCurrentPlayer(), checkExitSquare(getCurrentPlayer()));

                player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

                player.getIcon().setLocation(player.getX(), player.getY());

                if (player.getCurrentPosition() == target_position) {

                    timer.cancel();

                    squareAction(player);

                }

            }

        };

        timer.schedule(task, 0, 300);

    }

    public void updatePlayerGraphicComponents(Player player) {

        if (player.getName().equals("Hinestroza")) {

            //updates player money status
            player.getMoneyIcon().setText("EN BANCA");

            //remove player icon
            Hinestroza_icon.setLocation(4000, 4000);

            //Disable button
            hinestroza_button.setEnabled(false);

            //Hide player board icon
            player.getIcon().setLocation(4000, 4000);

        } else if (player.getName().equals("Viera")) {

            //updates player money status
            player.getMoneyIcon().setText("EN BANCA");

            //remove player icon
            viera_icon.setLocation(4000, 4000);

            //Disable button
            viera_button.setEnabled(false);

            //Hide player board icon
            player.getIcon().setLocation(4000, 4000);

        } else if (player.getName().equals("Borja")) {

            //updates player money status
            player.getMoneyIcon().setText("EN BANCA");

            //remove player icon
            borja_icon.setLocation(4000, 4000);

            //Disable button
            borja_button.setEnabled(false);

            //Hide player board icon
            player.getIcon().setLocation(4000, 4000);

        } else if (player.getName().equals("Teo")) {

            //updates player money status
            player.getMoneyIcon().setText("EN BANCA");

            //remove player icon
            teo_icon.setLocation(4000, 4000);

            //Disable button
            teo_button.setEnabled(false);

            //Hide player board icon
            player.getIcon().setLocation(4000, 4000);

        }

    }

    public void squareAction(Player player) {

        if (player.getCurrentPosition().property.getType().equals("community")) {

            getCommunityCard();

        } else if (player.getCurrentPosition().property.getType().equals("fortune")) {

            getFortuneCard();

        } else if (player.getCurrentPosition().property.getType().equals("color")
                || player.getCurrentPosition().property.getType().equals("transport")
                || player.getCurrentPosition().property.getType().equals("service")) {

            if (!banker.checkOwner(player.getCurrentPosition().property)) {

                boolean result = false;

                Object[] options = {" comprar ", "  pasar "};

                int res = JOptionPane.showOptionDialog(null,
                        "¿Desea comprar esta propiedad?",
                        "Venta",
                        JOptionPane.YES_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        player.getCurrentPosition().property.getIcon(),
                        options,
                        options[0]
                );

                if (res == JOptionPane.YES_OPTION) {

                    result = true;

                }

                if (result) {

                    if (buyProperty(player)) {

                        JOptionPane.showMessageDialog(null,
                                "¡Su compra ha sido exitosa!",
                                "¡COMPRA EXITOSA!",
                                JOptionPane.INFORMATION_MESSAGE,
                                player.getCurrentPosition().property.getIcon()
                        );

                        player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

                    } else {

                        JOptionPane.showMessageDialog(null,
                                "Su compra ha sido denegada",
                                "¡COMPRA DENEGADA!",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    }

                }

            } else {

                if (player == player.getCurrentPosition().property.getOwner()) {

                    nexTurn();
                    return;
                }

                if (payRent(player)) {

                    JOptionPane.showMessageDialog(null,
                            "Esta propiedad pertenece \nal jugador " + player.getCurrentPosition().property.getOwner().getName(),
                            "Propiedad",
                            JOptionPane.INFORMATION_MESSAGE,
                            player.getCurrentPosition().property.getIcon()
                    );

                    JOptionPane.showMessageDialog(null,
                            "EL JUGADOR " + player.getName() + " ha pagado la renta a " + player.getCurrentPosition().property.getOwner().getName() + " por valor de: $"
                            + player.getCurrentPosition().property.getRent(),
                            "RENTA",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

                    player.getCurrentPosition().property.getOwner().getMoneyIcon().setText(Integer.toString(player.getCurrentPosition().property.getOwner().getMoney()));

                } else {

                    JOptionPane.showMessageDialog(null,
                            "EL JUGADOR " + player.getName() + " no ha podido saldar sus deudas",
                            "HA SIDO ELIMINADO!",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    updatePlayerGraphicComponents(player);

                    this.players.remove(player);

                }

            }

            System.out.println("turnos: " + turns);

            nexTurn();

        } else if (player.getCurrentPosition().property.getType().equals("tax")) {

            if (!(player.getMoney() >= player.getCurrentPosition().property.getRent())) {

                JOptionPane.showMessageDialog(null,
                        "EL JUGADOR " + player.getName() + " no ha podido pagar sus impuestos :( \n se procedera a vender una parte de sus propiedades",
                        "IMPUESTO!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (!(banker.seizeProperty(player, player.getCurrentPosition().property.getRent(), null))) {

                    JOptionPane.showMessageDialog(null,
                            "EL JUGADOR " + player.getName() + "QUEDA ELMINADO",
                            "ELIMINACION",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    
                }

            } else {

                JOptionPane.showMessageDialog(null,
                        "EL JUGADOR " + player.getName() + " ha pagado el impuesto",
                        "IMPUESTOS!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                banker.updateMoney(player, -200);

                player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

            }

            nexTurn();

        } else if (player.getCurrentPosition().property.getType().equals("none") || player.getCurrentPosition().property.getType().equals("jail")) {

            nexTurn();

        }

    }

    public void nexTurn() {

        if (!endGame()) {

            //update turns
            turns(checkDoubles());

            //enable button
            roll_button.setEnabled(true);

            //update the color of jpanels
            updatePanelColors();

            // display the player name in turn
            player_name.setText(getCurrentPlayer().getName());

        }

    }

    public boolean endGame() {

        if (this.players.size() == 1) {

            JOptionPane.showMessageDialog(null,
                    "El jugador " + players.get(0).getName() + " ha ganado",
                    "FIN",
                    JOptionPane.INFORMATION_MESSAGE
            );

            Monopoly monopoly = new Monopoly();

            String[] args = null;

            try {
                monopoly.main(args);
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.dispose();

            return true;
        }

        return false;
    }

    public void round() throws IOException {

        if (getCurrentPlayer().isInJail()) {

            if (getCurrentPlayer().getCards().isEmpty()) {

                boolean jail = inJail(getCurrentPlayer());

                if (jail) {

                    java.util.Timer timer = new java.util.Timer();

                    TimerTask task = new TimerTask() {

                        int movements = 0;

                        @Override

                        public void run() {

                            movements += 1;

                            getCurrentPlayer().updatePosition();

                            //updates the position on the graphic interface
                            getCurrentPlayer().getIcon().setLocation(getCurrentPlayer().getX(), getCurrentPlayer().getY());

                            if (movements == getCurrentPlayer().getDiceResult()) {

                                timer.cancel();

                                //change dice results to avoid double validation
                                dices.get(0).setResult(1);
                                dices.get(1).setResult(2);

                                squareAction(getCurrentPlayer());

                                // display the player name in turn
                                player_name.setText(getCurrentPlayer().getName());

                                //update the color of jpanels
                                updatePanelColors();

                            }

                        }

                    };

                    timer.schedule(task, 0, 300);
                } else {

                    roll_button.setEnabled(true);

                    //update turns
                    turns(false);

                    // display the player name in turn
                    player_name.setText(getCurrentPlayer().getName());

                    //update the color of jpanels
                    updatePanelColors();

                }
            } else {

                System.out.println("\ncuando la tiene");
                for (int i = 0; i < this.cards.size(); i++) {
                    if (cards.get(i).getType().equals("community")) {
                        System.out.println(cards.get(i).getData());
                    }
                }

                JOptionPane.showMessageDialog(null,
                        "",
                        "SALIDA DE CARCEL",
                        JOptionPane.INFORMATION_MESSAGE,
                        getCurrentPlayer().getCards().get(0).getIcon()
                );

                //return the special jail card to the community chest
                returnCard(getCurrentPlayer().getCards().get(0));

                //remove the special jail card from the player card list
                getCurrentPlayer().getCards().remove(0);

                //update player jail status                   
                getCurrentPlayer().setJail(false);

                System.out.println("\ncuando la tiene");
                for (int i = 0; i < this.cards.size(); i++) {
                    if (cards.get(i).getType().equals("community")) {
                        System.out.println(cards.get(i).getData());
                    }
                }

                roll_button.setEnabled(true);

                turns(false);

                // display the player name in turn
                player_name.setText(getCurrentPlayer().getName());

                //update the color of jpanels
                updatePanelColors();

            }

        } else {

            // display the player name in turn
            ////////////////////roll the dices////////////////////
            java.util.Timer t = new java.util.Timer();

            TimerTask task = new TimerTask() {

                int counter = 0;

                boolean result = false;

                public void run() {

                    int dice1 = getCurrentPlayer().rollDice(dices.get(0));

                    int dice2 = getCurrentPlayer().rollDice(dices.get(1));

                    Dice1.setIcon(setDiceImages(dice1));

                    Dice2.setIcon(setDiceImages(dice2));

                    getCurrentPlayer().setDiceResult(dice1 + dice2);

                    counter += 1;

                    ///////////////////////////// MOVE THE PLAYER ///////////////////////////////
                    if (counter == 20) {

                        boolean boolean_result = checkDoubles();

                        setConsecutiveDoubles(boolean_result, getCurrentPlayer());

                        t.cancel();

                        //if 
                        if (!consecutiveDoubles(getCurrentPlayer())) {

                            java.util.Timer t = new java.util.Timer();

                            TimerTask task = new TimerTask() {

                                int moves = 0;

                                public void run() {

                                    getCurrentPlayer().updatePosition();

                                    banker.exitPay(getCurrentPlayer(), checkExitSquare(getCurrentPlayer()));

                                    getCurrentPlayer().getMoneyIcon().setText(Integer.toString(getCurrentPlayer().getMoney()));

                                    getCurrentPlayer().getIcon().setLocation(getCurrentPlayer().getX(), getCurrentPlayer().getY());

                                    moves += 1;

                                    //System.out.println("Suma: " + player.getDiceResult());
                                    if (moves == getCurrentPlayer().getDiceResult()) {

                                        if (getCurrentPlayer().getCurrentPosition() == jail) {

                                            getCurrentPlayer().getIcon().setLocation(290, 730);

                                        }

                                        try {

                                            Thread.sleep(300);

                                        } catch (InterruptedException ex) {

                                        }

                                        player_name.setText(getCurrentPlayer().getName());

                                        // check if the last movement ends in reten
                                        boolean result = checkRetenSquare(getCurrentPlayer());

                                        if (!result) {

                                            squareAction(getCurrentPlayer());

                                        } else {

                                            roll_button.setEnabled(true);

                                            //update turns
                                            turns(boolean_result);

                                            //update the color of jpanels
                                            updatePanelColors();

                                            // display the player name in turn
                                            player_name.setText(getCurrentPlayer().getName());

                                        }

                                        t.cancel();

                                    }

                                }

                            };

                            t.schedule(task, 0, 300);

                        } else {

                            sendToJail(getCurrentPlayer());

                            //update turns
                            turns(false);

                            //update the color of jpanels
                            updatePanelColors();

                            // display the player name in turn
                            player_name.setText(getCurrentPlayer().getName());

                            //enables roll button action
                            roll_button.setEnabled(true);

                        }

                    }

                }

            };

            t.schedule(task, 0, 30);

        }

    }

    public void showProperties(String name) {

        for (int i = 0; i < players.size(); i++) {

            String message = "";

            if (players.get(i).getName().equals(name)) {

                if (players.get(i).getProperties().isEmpty()) {

                    message = "sin propiedades";

                } else {

                    for (int k = 0; k < players.get(i).getProperties().size(); k++) {

                        System.out.println(players.get(i).getName() + players.get(i).getProperties().get(0).getName());

                        message = message + "(" + (k + 1) + ") " + players.get(i).getProperties().get(k).getName() + "\n";

                    }

                }

                JOptionPane.showMessageDialog(null,
                        message,
                        "Propiedades de " + players.get(i).getName(),
                        JOptionPane.INFORMATION_MESSAGE
                );

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        turn = new javax.swing.JLabel();
        Dice1 = new javax.swing.JLabel();
        Dice2 = new javax.swing.JLabel();
        player1 = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        player3 = new javax.swing.JLabel();
        player4 = new javax.swing.JLabel();
        turn1 = new javax.swing.JLabel();
        roll_button = new javax.swing.JButton();
        player_name = new javax.swing.JTextField();
        hinestroza_color = new javax.swing.JPanel();
        hinestroza = new javax.swing.JLabel();
        hinestroza_text = new javax.swing.JLabel();
        Hinestroza_icon = new javax.swing.JLabel();
        hinestroza_button = new javax.swing.JButton();
        hinestroza_money = new javax.swing.JTextField();
        borja_color = new javax.swing.JPanel();
        borja = new javax.swing.JLabel();
        borja_icon = new javax.swing.JLabel();
        borja_text = new javax.swing.JLabel();
        borja_button = new javax.swing.JButton();
        borja_money = new javax.swing.JTextField();
        viera_panel = new javax.swing.JPanel();
        viera = new javax.swing.JLabel();
        viera_text = new javax.swing.JLabel();
        viera_icon = new javax.swing.JLabel();
        viera_button = new javax.swing.JButton();
        viera_money = new javax.swing.JTextField();
        teo_color = new javax.swing.JPanel();
        teo = new javax.swing.JLabel();
        teo_icon = new javax.swing.JLabel();
        teo_money = new javax.swing.JTextField();
        teo_text = new javax.swing.JLabel();
        teo_button = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        board = new javax.swing.JLabel();

        turn.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        turn.setForeground(new java.awt.Color(0, 0, 204));
        turn.setText("TURNO:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Dice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dados/1.png"))); // NOI18N
        getContentPane().add(Dice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 630, 50, 60));

        Dice2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dados/1.png"))); // NOI18N
        getContentPane().add(Dice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 630, 60, 60));

        player1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ship.png"))); // NOI18N
        getContentPane().add(player1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 670, -1, -1));

        player2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/car.png"))); // NOI18N
        getContentPane().add(player2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 680, -1, -1));

        player3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hat.png"))); // NOI18N
        getContentPane().add(player3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 740, -1, -1));

        player4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dog.png"))); // NOI18N
        getContentPane().add(player4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 740, -1, -1));

        turn1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        turn1.setForeground(new java.awt.Color(0, 0, 204));
        turn1.setText("TURNO:");
        getContentPane().add(turn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        roll_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dados/Boton tirar dados.png"))); // NOI18N
        roll_button.setBorder(null);
        roll_button.setBorderPainted(false);
        roll_button.setContentAreaFilled(false);
        roll_button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roll_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roll_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(roll_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, 140, 80));

        player_name.setEditable(false);
        player_name.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        player_name.setText("jTextField1");
        getContentPane().add(player_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 120, 30));

        hinestroza_color.setPreferredSize(new java.awt.Dimension(300, 122));
        hinestroza_color.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hinestroza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/1.png"))); // NOI18N
        hinestroza_color.add(hinestroza, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 38, -1, -1));

        hinestroza_text.setBackground(new java.awt.Color(0, 0, 255));
        hinestroza_text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        hinestroza_text.setForeground(new java.awt.Color(0, 0, 153));
        hinestroza_text.setText("Hinestroza");
        hinestroza_color.add(hinestroza_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 10, -1, -1));

        Hinestroza_icon.setBackground(new java.awt.Color(204, 153, 255));
        hinestroza_color.add(Hinestroza_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 60));

        hinestroza_button.setForeground(new java.awt.Color(253, 28, 48));
        hinestroza_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/property.png"))); // NOI18N
        hinestroza_button.setEnabled(false);
        hinestroza_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hinestroza_buttonActionPerformed(evt);
            }
        });
        hinestroza_color.add(hinestroza_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 90, 70));

        hinestroza_money.setEditable(false);
        hinestroza_money.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        hinestroza_money.setText("EN BANCA");
        hinestroza_color.add(hinestroza_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 90, 21));

        getContentPane().add(hinestroza_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 280, -1));

        borja_color.setPreferredSize(new java.awt.Dimension(300, 122));
        borja_color.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        borja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/2.png"))); // NOI18N
        borja_color.add(borja, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 30, -1, -1));
        borja_color.add(borja_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 63));

        borja_text.setBackground(new java.awt.Color(0, 0, 255));
        borja_text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        borja_text.setForeground(new java.awt.Color(0, 0, 153));
        borja_text.setText("Borja");
        borja_color.add(borja_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 0, -1, -1));

        borja_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/property.png"))); // NOI18N
        borja_button.setEnabled(false);
        borja_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borja_buttonActionPerformed(evt);
            }
        });
        borja_color.add(borja_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 90, 70));

        borja_money.setEditable(false);
        borja_money.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        borja_money.setText("EN BANCA");
        borja_color.add(borja_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 90, 21));

        getContentPane().add(borja_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 280, 120));

        viera_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        viera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/3.png"))); // NOI18N
        viera_panel.add(viera, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 30, -1, -1));

        viera_text.setBackground(new java.awt.Color(0, 0, 255));
        viera_text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        viera_text.setForeground(new java.awt.Color(0, 0, 153));
        viera_text.setText("Viera");
        viera_panel.add(viera_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));
        viera_panel.add(viera_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 62));

        viera_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/property.png"))); // NOI18N
        viera_button.setEnabled(false);
        viera_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viera_buttonActionPerformed(evt);
            }
        });
        viera_panel.add(viera_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 90, 70));

        viera_money.setEditable(false);
        viera_money.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        viera_money.setText("EN BANCA");
        viera_panel.add(viera_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 90, 21));

        getContentPane().add(viera_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 280, 120));

        teo_color.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        teo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Players/4.png"))); // NOI18N
        teo_color.add(teo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));
        teo_color.add(teo_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, 68));

        teo_money.setEditable(false);
        teo_money.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        teo_money.setText("EN BANCA");
        teo_color.add(teo_money, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 90, 21));

        teo_text.setBackground(new java.awt.Color(0, 0, 255));
        teo_text.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        teo_text.setForeground(new java.awt.Color(0, 0, 153));
        teo_text.setText("Teo");
        teo_color.add(teo_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 3, -1, -1));

        teo_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/property.png"))); // NOI18N
        teo_button.setEnabled(false);
        teo_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teo_buttonActionPerformed(evt);
            }
        });
        teo_color.add(teo_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 90, 70));

        getContentPane().add(teo_color, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 280, 120));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/boton_back.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 730, 50, 50));

        board.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/board.png"))); // NOI18N
        getContentPane().add(board, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roll_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roll_buttonActionPerformed

        roll_button.setEnabled(false);

        try {

            round();

        } catch (IOException ex) {

            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);

        }

    }//GEN-LAST:event_roll_buttonActionPerformed

    private void hinestroza_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hinestroza_buttonActionPerformed

        showProperties("Hinestroza");

    }//GEN-LAST:event_hinestroza_buttonActionPerformed

    private void borja_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borja_buttonActionPerformed

        showProperties("Borja");

    }//GEN-LAST:event_borja_buttonActionPerformed

    private void viera_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viera_buttonActionPerformed

        showProperties("Viera");

    }//GEN-LAST:event_viera_buttonActionPerformed

    private void teo_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teo_buttonActionPerformed

        showProperties("Teo");

    }//GEN-LAST:event_teo_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Object[] options = {"Aceptar", "Cancelar"};

        int result = JOptionPane.showOptionDialog(null,
                "¿Desea regresar al menú?", "Salir",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (result == JOptionPane.OK_OPTION) {
            Monopoly monopoly = new Monopoly();

            String[] args = null;

            try {
                monopoly.main(args);
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Dice1;
    private javax.swing.JLabel Dice2;
    private javax.swing.JLabel Hinestroza_icon;
    private javax.swing.JLabel board;
    private javax.swing.JLabel borja;
    private javax.swing.JButton borja_button;
    private javax.swing.JPanel borja_color;
    private javax.swing.JLabel borja_icon;
    private javax.swing.JTextField borja_money;
    private javax.swing.JLabel borja_text;
    private javax.swing.JLabel hinestroza;
    private javax.swing.JButton hinestroza_button;
    private javax.swing.JPanel hinestroza_color;
    private javax.swing.JTextField hinestroza_money;
    private javax.swing.JLabel hinestroza_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel player3;
    private javax.swing.JLabel player4;
    private javax.swing.JTextField player_name;
    private javax.swing.JButton roll_button;
    private javax.swing.JLabel teo;
    private javax.swing.JButton teo_button;
    private javax.swing.JPanel teo_color;
    private javax.swing.JLabel teo_icon;
    private javax.swing.JTextField teo_money;
    private javax.swing.JLabel teo_text;
    private javax.swing.JLabel turn;
    private javax.swing.JLabel turn1;
    private javax.swing.JLabel viera;
    private javax.swing.JButton viera_button;
    private javax.swing.JLabel viera_icon;
    private javax.swing.JTextField viera_money;
    private javax.swing.JPanel viera_panel;
    private javax.swing.JLabel viera_text;
    // End of variables declaration//GEN-END:variables
}
