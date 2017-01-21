/** Fortune Teller, Reading from a File
 *  Chooses a random Fortune from a list of Strings stored in a file.
 *  Precondition:
 *    The data file exists, holding NUM_FORTUNES fortunes.  
 *    File Name: 
 *        fortunes.txt  
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.Scanner;
import java.io.*;


public class ShakespeareWindowNext extends JFrame
    implements ActionListener
{

  // Declare an array of "insult parts" (strings):
  private String[] adjective1;
  private String[] adjective2;
  private String[] noun;
  private final int NUM_WORDS = 30;

  private JTextField display;
  
  // Constructor opens a window with a JTextField and a "Next" button
  public ShakespeareWindowNext()
  {
    super("Shakespeare Insult Generator");

    display = new JTextField("  Press \"Next\" to see how I really feel about you...", 35);
    display.setBackground(Color.WHITE);
    display.setEditable(false);

    JButton go = new JButton("Next");
    go.addActionListener(this);

    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    c.add(display);
    c.add(go);
    
    // Initialize the arrays for words
    adjective1 = new String[NUM_WORDS];
    adjective2 = new String[NUM_WORDS];
    noun = new String[NUM_WORDS];
    
    // Read the words from the files
    readFile (adjective1, "shakespeare-1.txt");
    readFile (adjective2, "shakespeare-2.txt");
    readFile (noun, "shakespeare-3.txt");

  }
  
  // Reads NUM_WORDS words from a file (one word per line).
  // Stores the words in the String array wordList.
  // If the file doesn't exist, exits the program with an error message.
  private void readFile (String[] wordList, String fileName)
  {
    // open the file
    File file = new File(fileName);
    Scanner input = null;
    try
    {
        input = new Scanner(file);
    }
    catch (FileNotFoundException ex)
    {
        System.out.println("*** Cannot open " + fileName + " ***");
        System.exit(1);        // quit the program
    } 

    // read the words from the file, one per line
    for (int i = 0; i < NUM_WORDS; i++)
      wordList[i] = input.nextLine();
    input.close();
  }
   /**
   * Creates a Shakespearean insult based on three lists of 
   * insulting words.
   * @return an insult  
   */
  public String getInsult()
  {
    int i;
    String insult = "Thou art a ";
    i = (int)(Math.random() * NUM_WORDS);
    insult += adjective1[i] + " ";
    i = (int)(Math.random() * NUM_WORDS);
    insult += adjective2[i] + " ";
    i = (int)(Math.random() * NUM_WORDS);
    insult += noun[i];
    
    return insult;
  }
  
  public void actionPerformed(ActionEvent e)
  {
    // Pick and display a random insult:
    display.setText("  " + getInsult() );
  }

  
  public static void main(String[] args)
  {   
    JFrame window = new ShakespeareWindowNext();
    window.setBounds(300, 300, 500, 100);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setVisible(true); 
  }
}
