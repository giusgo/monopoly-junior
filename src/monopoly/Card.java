package monopoly;

import javax.swing.ImageIcon;

public class Card {

    private String data;
    private boolean collectable;
    private String type;
    private ImageIcon icon;
    private String action;

    public Card (String data, boolean collectable, String type, String action, String path) {

        this.data = data;
        this.collectable = collectable;
        this.type = type;
        this.action = action;
        this.icon = new ImageIcon(path);

    }

    public String getData() {

        return this.data;
    }

    public boolean getStatus() {

        return this.collectable;
        
    }

    public String getType() {

        return this.type;
        
    }

    public ImageIcon getIcon() {

        return this.icon;
    }

    public String getAction() {

        return this.action;
    }
    
}
