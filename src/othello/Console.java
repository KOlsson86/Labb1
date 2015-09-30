package othello;

import javax.swing.*;
import java.awt.*;

/**
 * A class that functions as a console.
 *
 * @author Sebastian Aspegren.
 */
class Console extends JFrame {

    //The text area that prints information.
    private final JTextArea textArea = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane(textArea);
    private final JPanel pnlServer = new JPanel();

    /**
     * The constructor for the console.
     */
    public Console() {
        setupGUI();
        //noinspection MagicConstant
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * A method used in the constructor. It sets up everything regarding the console.
     */
    private void setupGUI() {
        setLocation(NORMAL, NORMAL);
        setSize(new Dimension(400, 400));
        setResizable(true);
        setLayout(new GridLayout(1, 1));
        textArea.setEditable(false);
        pnlServer.setLayout(new GridLayout(1, 1));
        pnlServer.add(scrollPane);
        add(pnlServer);
    }

    /**
     * A method used to write text to the text area taServerMessage.
     *
     * @param inStr the text we wish to add to the console.
     */
    public void appendText(String inStr) {
        textArea.append(inStr + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
