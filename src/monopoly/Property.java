package monopoly;

import javax.swing.ImageIcon;

public class Property {

    private String name;
    private int rent;
    private int price;
    private Player owner;
    private String type;
    private ImageIcon icon;

    public Property(String type, String name, int rent, int price, Player owner, String path) {

        this.name = name;
        this.rent = rent;
        this.price = price;
        this.owner = owner;
        this.type = type;

        this.icon = new ImageIcon(path);

    }

    public String getName() {

        return this.name;
    }

    public Player getOwner() {
        
        return this.owner;
    }

    public void setOwner(Player player) {

        this.owner = player;
    }

    public int getRent() {

        return this.rent;
    }

    public int getPrice() {

        return this.price;
    }

    public String getType() {

        return this.type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public ImageIcon getIcon() {

        return this.icon;
    }

}
