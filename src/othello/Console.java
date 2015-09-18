package othello;

import javax.swing.*;
import java.awt.*;

/**
 * A class that functions as a simple gui to see if anything goes wrong.
 *
 * @author Sebastian Aspegren.
 */
public class Console extends JFrame {

    //The text area that prints information about what happens. Basically a console.
    private JTextArea taServerMessage = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(taServerMessage);
    private JPanel pnlServer = new JPanel();

    /**
     * The constructor for the gui. It sets up basic things like the visibility of the gui.
     */
    public Console() {
        setupGUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    /**
     * A method used in the constructor. It sets up everything regarding the gui.
     * This is in a separate method to easier separate everything.
     */
    public void setupGUI() {
        setLocation(NORMAL, NORMAL);
        setSize(new Dimension(400, 400));
        setResizable(true);
        setLayout(new GridLayout(1, 1));
        taServerMessage.setEditable(false);
        pnlServer.setLayout(new GridLayout(1, 1));
        pnlServer.add(scrollPane);
        add(pnlServer);
    }

    /**
     * A method used to write text to the text area taServerMessage.
     *
     * @param inStr the text we wish to add to the "console".
     */
    public void appendText(String inStr) {
        taServerMessage.append(inStr + "\n");
        taServerMessage.setCaretPosition(taServerMessage.getDocument().getLength());
    }
}
