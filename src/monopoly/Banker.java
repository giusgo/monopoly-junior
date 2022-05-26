package monopoly;

//validar metodos del banquero, especificamente los pagos y ventas.
import javax.swing.JOptionPane;

public class Banker {

    public void updateMoney(Player player, int value) {

        player.setMoney(player.getMoney() + value);

    }

    public void exitPay(Player player, boolean exitSquare) {

        if (exitSquare) {

            player.setMoney(player.getMoney() + 200);

        }

    }

    public boolean sellProperty(Player player, Property property) {

        int price = property.getPrice();

        if (!checkOwner(property) && player.getMoney() >= price) {

            property.setOwner(player);

            player.addProperty(property);

            updateMoney(player, -price);

            return true;

        }

        return false;
    }

    public boolean checkOwner(Property property) {

        return (property.getType().equals("color") || property.getType().equals("service")
                || property.getType().equals("transport")) && property.getOwner() != null;

    }

    public boolean seizeProperty(Player player, int rent, Player owner) {

        if (player.getProperties().isEmpty()) {
            return false;
        }

        int owneDproperties = player.getProperties().size();

        for (int i = 0; i < owneDproperties; i++) {

            if (player.getMoney() >= rent) {

                return true;

            }
            
            JOptionPane.showMessageDialog(null,
                    "Se ha vendido esta propiedad: ",
                    "PROPIEDAD VENDIDA",
                    JOptionPane.INFORMATION_MESSAGE,
                    player.getProperties().get(i).getIcon()
            );

            updateMoney(player, player.getProperties().get(i).getPrice());
            
            player.getMoneyIcon().setText(Integer.toString(player.getMoney()));

            player.getProperties().get(i).setOwner(null);

            player.getProperties().remove(i);

        }

        if (owner != null) {

            updateMoney(owner, player.getMoney());

        }

        player.setBankrupt(true);

        return false;

    }

    public boolean payRent(Player player) {

        Property property = player.getCurrentPosition().property;

        Player owner = property.getOwner();

        if (player.getMoney() >= property.getRent()) {

            player.setMoney(player.getMoney() - property.getRent());

            owner.setMoney(owner.getMoney() + property.getRent());

            return true;

        } else {

            if (seizeProperty(player, property.getRent(), owner)) {

                    
                   updateMoney(player, -property.getRent());
                    
                   return true;

            }

        }

        return false;

    }

}
