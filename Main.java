import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Main implements ActionListener {
  private JFrame frame;
  public JButton[] buttons;
  private JButton rollButton, newGame;
  private JButton newGameButton;
  private JLabel diceLabel1;
  private JLabel diceLabel2;
  public JLabel count;
  public int rollsCount= 0;
  private JPanel contentPane;
  public int newRoll = 0;
  public int roll2=0;
  private JButton color;
  Random x = new Random();


  public Main() {
    frame = new JFrame("Clacker");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    contentPane = new JPanel();
    contentPane.setLayout(new GridLayout(0,4, 10, 10));
    contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    diceLabel1 = new JLabel("");
    contentPane.add(diceLabel1); 
    diceLabel2 = new JLabel("");
    contentPane.add(diceLabel2);
    
    rollButton = new JButton("Roll");
    rollButton.setActionCommand("Roll");
    rollButton.addActionListener(this);
    contentPane.add(rollButton);
    
    count = new JLabel("Number of Rolls: " + String.valueOf(rollsCount));
    contentPane.add(count);

    
    buttons = new JButton[12];
    for (int i = 0; i < buttons.length;i++){
      buttons[i]= new JButton(String.valueOf(i + 1));
      buttons[i].setActionCommand(String.valueOf(i + 1));
      buttons[i].addActionListener(this);
      contentPane.add(buttons[i]);
      int r = x.nextInt(256);
      int g = x.nextInt(256);
      int b = x.nextInt(256);
      int x = 255-r;
      int y = 255-g;
      int z = 255-b;
      buttons[i].setBackground(new Color(r,g,b));
      buttons[i].setForeground(new Color(x,y,z));
    }
    newGame = new JButton("New Game");
    newGame.setActionCommand("New Game");
    newGame.addActionListener(this);
    contentPane.add(newGame);
    
    color = new JButton("Change Color");
    color.setActionCommand("Change Color");
    color.addActionListener(this);
    contentPane.add(color);

    frame.setContentPane(contentPane);
    frame.setSize(750,440);
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    Random rand = new Random();
    String eventName = event.getActionCommand();


    if (eventName.equals("New Game")){
      rollsCount = 0;
      diceLabel1.setIcon(null);
      diceLabel2.setIcon(null);
      count.setText("Number of Rolls: " + String.valueOf(rollsCount));
    for (int i = 0; i < buttons.length;i++){
      buttons[i].setText(String.valueOf(i + 1));
      }
    }

  
    if (eventName.equals("Roll")){
      newRoll = rand.nextInt(6)+1;
      roll2 = rand.nextInt(6)+1;
      rollsCount++;
      count.setText("Number of Rolls: " + String.valueOf(rollsCount));
      String temp = "die" + newRoll+".tif";
      String temp3 = "die" + roll2+".tif";
      Icon one = new ImageIcon(temp);
      Icon two = new ImageIcon(temp3);
      diceLabel1.setIcon(one);
      diceLabel2.setIcon(two);

    }

    if (eventName.equals(String.valueOf(newRoll + roll2))) {
      buttons[newRoll + roll2-1].setText(" ");
      newRoll=0;
      roll2=0;
    }

      
    else if (eventName.equals(String.valueOf(roll2)) && buttons[newRoll-1].getText() != " ") {
      buttons[roll2-1].setText(" ");
      roll2= 0;
    }

      
    else if (eventName.equals(String.valueOf(newRoll)) && buttons[roll2-1].getText() != " ") {
      buttons[newRoll-1].setText(" ");
      newRoll = 0;
    }

    if (eventName.equals("Change Color")){
      for (int i = 0; i < buttons.length;i++){
      int r = x.nextInt(256);
      int g = x.nextInt(256);
      int b = x.nextInt(256);
      int x = 255-r;
      int y = 255-g;
      int z = 255-b;
      buttons[i].setBackground(new Color(r,g,b));
      buttons[i].setForeground(new Color(x,y,z));      }
    }

  }


  

  private static void runGUI() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    Main GUI = new Main();
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        runGUI();
      }
    });
  }

}