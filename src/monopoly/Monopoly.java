package monopoly;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Monopoly {

    public static void main(String[] args) throws IOException {

        Start start = new Start();

        start.setSize(600, 800);

        start.setResizable(false);

        start.setLocationRelativeTo(null);

        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start.setVisible(true);

    }

    public void playerNumberWidnow() {

        PlayerNumber window1 = new PlayerNumber();

        window1.setSize(600, 800);

        window1.setResizable(false);

        window1.setLocationRelativeTo(null);

        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window1.setVisible(true);

    }

    public List<Dice> createDices() {

        List<Dice> dices = new ArrayList<Dice>();

        Dice dice1 = new Dice();

        Dice dice2 = new Dice();

        dices.add(dice1);

        dices.add(dice2);

        return dices;

    }

    public CircularLinkedList createSquares() throws IOException {

        CircularLinkedList squares = new CircularLinkedList();

        File casillas = new File("src/monopoly/casillas.txt");

        try {

            BufferedReader read = new BufferedReader(new FileReader(casillas));

            String line;

            read.readLine();

            while ((line = read.readLine()) != null) {

                String[] data = line.split(",");

                squares.addNode(Integer.parseInt(data[0]), Integer.parseInt(data[1]), new Property(data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), null, data[7]));

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

        return squares;

    }

    public void createPlayers(int numbers) throws IOException {

        List<Player> players = new ArrayList<Player>();

        Player player1 = new Player("Hinestroza",  500, 0, 0, false, false, null);

        Player player2 = new Player("Borja", 500, 0, 0, false, false, null);

        players.add(player1);

        players.add(player2);

        if (numbers == 3) {

            Player player3 = new Player("Viera", 500, 0, 0, false, false, null);

            players.add(player3);

        } else if (numbers == 4) {

            Player player3 = new Player("Viera",  500, 0, 0, false, false, null);

            Player player4 = new Player("Teo",  500, 0, 0, false, false, null);

            players.add(player3);

            players.add(player4);

        }

        InicialThrow window2 = new InicialThrow(players, createDices());

        window2.setSize(600, 800);

        window2.setResizable(false);

        window2.setLocationRelativeTo(null);

        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window2.setVisible(true);

    }

    public List<Card> createCards() throws IOException {

        List<Card> card = new ArrayList<Card>();

        File cards = new File("src/monopoly/cartas.txt");

        try {

            BufferedReader read = new BufferedReader(new FileReader(cards));

            String line;

            read.readLine();

            while ((line = read.readLine()) != null) {

                String[] data = line.split(";");

                card.add(new Card(data[0], Boolean.parseBoolean(data[1]), data[2], data[3], data[4]));

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return card;
    }

    public void createBoard(List<Player> players) throws IOException {

        Board window3 = new Board(players, createDices(), createSquares(), createCards());

        window3.setResizable(false);

        window3.setLocationRelativeTo(null);

        window3.setSize(1115, 840);

        window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window3.setVisible(true);

        //board(argumentos)
        //inicia ventana tablero(board)
    }

}
