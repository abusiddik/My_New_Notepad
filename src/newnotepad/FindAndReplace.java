/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newnotepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class FindAndReplace extends JDialog implements ActionListener {

    boolean foundOne, isReplace;
    JTextField findField, replaceField;
    JCheckBox checkCase, checkWhole;
    JRadioButton up, down;
    JLabel statusInfo;
    JFrame owner;
    JPanel north, center, south;

    public FindAndReplace(JFrame owner, boolean isReplace) {
        super(owner, true);
        this.isReplace = isReplace;
        north = new JPanel();
        center = new JPanel();
        south = new JPanel();

        if (isReplace) {
            setTitle(" Find and Replace.");
        } else {
            setTitle(" Find.");
            setFindPanel(north);
        }

    }

    private void setFindPanel(JPanel north1) {
        final JButton NEXT = new JButton("Find Next.");
        NEXT.addActionListener(this);
        NEXT.setEnabled(false);
        findField = new JTextField(20);
        findField.addActionListener(this);
        findField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                boolean state = (findField.getDocument().getLength() > 0);
                NEXT.setEnabled(state);
                foundOne = false;
            }

        });
        if (findField.getText().length() > 0) {
            NEXT.setEnabled(true);
        }
        north.add(new JLabel("Find Word: "));
        north.add(findField);
        north.add(NEXT);

    }

    private void setFindAndReplacePanel() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void process() {
        if (isReplace) {
            statusInfo.setText("Replacing " + findField.getText());
        } else {
            statusInfo.setText("Searching for " + findField.getText());

        }
        //   int carectr = NewNotepad.g
    }

    private int search(String text, String word, int carectr) {
        boolean found = false;
        int all = text.length();
        int check = word.length();
        if (isSearchDown()) {
            int add = 0;
            for (int i = carectr + 1; i < (all - check); i++) {
                String temp = text.substring(i, (i + check));
                if (temp.equals(word)) {
                    if (wholeWordIsSelected()) {
                        if (checkForWholeWord(check, text, add, carectr)) {
                            carectr = i;
                            found = true;
                            break;

                        }
                    }
                }

            }
        } else {
        }

        return -1;
    }

    private boolean isSearchDown() {

        return down.isSelected();
    }

    private boolean wholeWordIsSelected() {

        return checkWhole.isSelected();
    }

    private boolean checkForWholeWord(int check, String text, int add, int carectr) {
        int offSetLeft = (carectr + add) - 1;
        int offSetRight = (carectr + add) + check;
        if ((offSetLeft < 0) || offSetRight > text.length()) {
            return true;
        }

        return false;
    }
}
