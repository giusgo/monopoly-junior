package monopoly;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Player {

    private String name;
    private List<Property> properties;
    private int money;
    private boolean injail;
    private List<Card> cards;
    private boolean bankrupt;
    private int dice_result;
    private JLabel icon;
    private JTextField money_icon;
    private int consecutive_doubles;
    
   

    private CircularLinkedList.Node current_position;

    public Player(String name,  int money, int dice1_result, int dice2_result, boolean injail, boolean bankrupt,
            CircularLinkedList.Node current_position) {

        this.name = name;
        this.properties = new ArrayList<Property>();
        this.money = money;
        this.injail = injail;
        this.cards = new ArrayList<Card>();
        this.bankrupt = bankrupt;
        this.current_position = current_position;
        this.icon = null;
        this.money_icon = null;
        this.consecutive_doubles = 0;
        
    }

    public String getName() {

        return this.name;
    }

    public List<Property> getProperties() {

        return this.properties;
    }

    public int getMoney() {

        return this.money;
    }

    public boolean isInJail() {

        return this.injail;
    }

    public List<Card> getCards() {

        return this.cards;
    }
    
     public CircularLinkedList.Node getCurrentPosition() {

        return this.current_position;
    }
     
     
     public int getX() {

        return this.current_position.pos_x;
        
    }

    public int getY() {

        return this.current_position.pos_y;
        
    }
    
    public JTextField getMoneyIcon(){
        
        return this.money_icon;
        
    }

    public int getDiceResult() {

        return this.dice_result;
        
    }

    public boolean isBankrupt() {

        return this.bankrupt;

    }
    
    public int getConsecutiveDoubles(){
        
        return this.consecutive_doubles;
        
    }

    public JLabel getIcon(){
        
        return this.icon;
        
    }
    
    public void setMoney(int value) {

        this.money = value;
    }
    
    public void setConsecutiveDoubles(int value){
        
        this.consecutive_doubles =  value;
        
    }
    
    public void setMoneyIcon(JTextField money_icon){
        
        this.money_icon = money_icon;
        
    }

    public void setProperty(Property property) {

        this.properties.add(property);
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setBankrupt(boolean status) {

        this.bankrupt = status;
    }

    public void setJail(boolean status) {

        this.injail = status;

    }
    
    public void setIcon(JLabel icon){
        
        this.icon = icon;
    }

    public void setCurrentPosition(CircularLinkedList.Node position) {

        this.current_position = position;
    }
    
    public void prevPosition(){
        
        this.current_position = current_position.prev;
    }

    public void setDiceResult(int sum) {

        this.dice_result = sum;
    }

    public int rollDice(Dice dice) {

        return dice.roll();
    }
    
    public void updatePosition() {

        this.current_position = current_position.next;
    }

    public void keepCard(Card card) {

        this.cards.add(card);
    }

    public void useCard() {

        //
    }

    public void addProperty(Property property) {

        this.properties.add(property);
    }

    public void removeProperty(Property property) {

        this.properties.remove(property);
    }

}
