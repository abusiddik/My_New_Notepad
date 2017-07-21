/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newnotepad;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author User
 */
public class FontHelper extends JDialog implements ListSelectionListener {

    JPanel pan1, pan2, pan3;
    JLabel fontLabel, sizeLabel, typeLabel, previewLabel;
    JTextField labelField, fontField, sizeField, typeField;
    JScrollPane fontJScroll, typeJScroll, sizeJScroll;
    JList fontList, typeList, sizeList;
    JButton ok, cancel;
    GridBagLayout gbl;
    GridBagConstraints gbc;

    public FontHelper() {
        setTitle(" Choose Font.");
        setSize(300, 400);
        setResizable(false);
        gbl = new GridBagLayout();
        setLayout(gbl);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        typeLabel = new JLabel("Types");
        getContentPane().add(sizeLabel, gbc);  
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fontField = new JTextField("Arial", 12);
        getContentPane().add(fontField, gbc);  
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        sizeField = new JTextField("8", 4);
        getContentPane().add(sizeField, gbc);
        
        
        

    }

    @Override

    public void valueChanged(ListSelectionEvent e) {
    }

}
