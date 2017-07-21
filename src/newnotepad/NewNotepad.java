/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newnotepad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author User
 */
public class NewNotepad extends JFrame {

    JTextArea mainArea;
    JMenuBar menuBar;
    JMenu mnuFile, mnuEdit, mnuFormat, mnuHelp;
    JMenuItem itmNew, itmOpen, itmSave, itmSaveAs, itmExit, itmCopy, itmCut, itmPaste, itmFontClr, itmFind, itmReplace;
    JCheckBoxMenuItem wordWrap;
    String fileName;
    JFileChooser fc;
    String fileContent;
    UndoManager undoM;
    UndoAction undoAction;
    RedoAction redoAction;

    public NewNotepad() {
        initComponent();
        itmSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        itmSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
        itmOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        itmNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open_new();
            }
        });
        itmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        itmCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.copy();
            }
        });
        itmCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.cut();
            }
        });
        itmPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainArea.paste();
            }
        });
        mainArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoM.addEdit(e.getEdit());
                undoAction.update();
                redoAction.update();
            }
        });
        mainArea.setLineWrap(true);
        wordWrap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wordWrap.isSelected()) {
                    mainArea.setLineWrap(true);

                    mainArea.setWrapStyleWord(true);
                } else {
                    mainArea.setLineWrap(false);

                    mainArea.setWrapStyleWord(false);

                }
            }
        });
        itmFontClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color clr = JColorChooser.showDialog(rootPane, "Choose the font color.", Color.red);
                mainArea.setForeground(clr);
            }
        });
        itmFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    mainArea.paste();
            }
        });
        itmReplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // mainArea.paste();
            }
        });
    }

    public void initComponent() {
        fc = new JFileChooser(".");
        mainArea = new JTextArea();
        undoM = new UndoManager();
        getContentPane().add(mainArea);
        getContentPane().add(new JScrollPane(mainArea), BorderLayout.CENTER);
        setTitle("Untitled Name");
        setSize(800, 600);
        menuBar = new JMenuBar();
        // menu
        mnuFile = new JMenu("File");
        mnuEdit = new JMenu("Edit");
        mnuFormat = new JMenu("Format");
        mnuHelp = new JMenu("Help");
        // Add icons to menu items
        ImageIcon icNew = new ImageIcon(getClass().getResource("/img/ic_new_32.png"));
        ImageIcon icUndo = new ImageIcon(getClass().getResource("/img/ic_new_32.png"));
        undoAction = new UndoAction(icUndo);
        ImageIcon icRedo = new ImageIcon(getClass().getResource("/img/ic_new_32.png"));
        redoAction = new RedoAction(icRedo);
        ImageIcon icOpen = new ImageIcon(getClass().getResource("/img/ic_open_32.png"));
        ImageIcon icSave = new ImageIcon(getClass().getResource("/img/ic_save_32.png"));
        ImageIcon icSaveAs = new ImageIcon(getClass().getResource("/img/ic_save_32.png"));
        ImageIcon icExit = new ImageIcon(getClass().getResource("/img/ic_save_32.png"));
        ImageIcon icCopy = new ImageIcon(getClass().getResource("/img/ic_save_32.png"));
        ImageIcon icCut = new ImageIcon(getClass().getResource("/img/ic_save_32.png"));
        ImageIcon icPaste = new ImageIcon(getClass().getResource("/img/ic_save_32.png"));
        //menuItem
        itmNew = new JMenuItem("New", icNew);
        itmOpen = new JMenuItem("Open", icOpen);
        itmSave = new JMenuItem("Save", icSave);
        itmSaveAs = new JMenuItem("Save As", icSaveAs);
        itmExit = new JMenuItem("Exit", icExit);
        itmCopy = new JMenuItem("Copy", icCopy);
        itmCut = new JMenuItem("Cut", icCut);
        itmPaste = new JMenuItem("Paste", icPaste);
        wordWrap = new JCheckBoxMenuItem("WordWrap");
        itmFontClr = new JMenuItem("Font Color.");
        itmFind = new JMenuItem("Find.");
        itmReplace = new JMenuItem("Replace.");
        // Sortcut
        itmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        itmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        itmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        itmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        itmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        itmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        itmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        itmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        itmReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        //add menu item
        mnuFile.add(itmNew);
        mnuFile.add(itmOpen);
        mnuFile.add(itmSave);
        mnuFile.add(itmSaveAs);
        mnuFile.addSeparator();
        mnuFile.add(itmExit);
        mnuEdit.add(undoAction);
        mnuEdit.add(redoAction);
        mnuEdit.addSeparator();
        mnuEdit.add(itmCopy);
        mnuEdit.add(itmCut);
        mnuEdit.add(itmPaste);
        mnuEdit.add(itmFind);
        mnuEdit.add(itmReplace);
        mnuFormat.add(wordWrap);
        mnuFormat.add(itmFontClr);

        //add menu
        menuBar.add(mnuFile);
        menuBar.add(mnuEdit);
        menuBar.add(mnuFormat);
        menuBar.add(mnuHelp);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void open() {
        int dlg = -1;
        try {
            dlg = fc.showOpenDialog(this);
            if (dlg == JFileChooser.APPROVE_OPTION) {
                mainArea.setText(null);
                Reader rd = new FileReader(fc.getSelectedFile());
                char[] buff = new char[10000000];
                int nhc;
                while ((nhc = rd.read(buff, 0, buff.length)) != -1) {
                    mainArea.append(new String(buff, 0, nhc));

                }
            }

            fileName = fc.getSelectedFile().getName();
            setTitle(fileName = fc.getSelectedFile().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void save() {
        PrintWriter pr = null;
        try {
            if (fileName == null) {
                saveAs();
            } else {
                pr = new PrintWriter(new FileWriter(fileName)
                );

                String s = mainArea.getText();
                StringTokenizer stk = new StringTokenizer(s, System.getProperty("line.separator"));
                while (stk.hasMoreElements()) {
                    pr.println(stk.nextToken());
                }
                JOptionPane.showMessageDialog(rootPane, "The file has been saved.");
                fileContent = mainArea.getText();

            }
        } catch (IOException e) {
        } finally {
            if (pr != null) {
                pr.close();
            }

        }
    }

    private void saveAs() {
        PrintWriter pr = null;
        int dlg = -1;
        try {
            dlg = fc.showSaveDialog(this);

            if (dlg == JFileChooser.APPROVE_OPTION) {
                pr = new PrintWriter(new FileWriter(fc.getSelectedFile()));
            }
            String s = mainArea.getText();
            StringTokenizer stk = new StringTokenizer(s, System.getProperty("line.separator"));
            while (stk.hasMoreElements()) {
                pr.println(stk.nextToken());
            }
            JOptionPane.showMessageDialog(rootPane, "The file has been saved.");
            fileContent = mainArea.getText();

            fileName = fc.getSelectedFile().getName();
            setTitle(fileName = fc.getSelectedFile().getName());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pr.close();

        }

    }

    private void open_new() {
        if (!mainArea.getText().equals("") && !mainArea.getText().equals(fileContent)) {
            if (mainArea == null) {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to save the changes?");
                if (option == 0) {
                    saveAs();
                    clear();
                } else if (option == 2) {
                } else {
                    clear();
                }
            } else {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to save the changes?");
                if (option == 0) {
                    save();
                    clear();
                } else if (option == 2) {
                } else {
                    clear();
                }
            }

        } else {
            clear();
        }
    }

    public void clear() {
        mainArea.setText(null);
        setTitle("Untitled Notpad.");
        fileName = null;
        fileContent = null;
    }

    class UndoAction extends AbstractAction {

        public UndoAction(ImageIcon undoIcon) {
            super("Undo", undoIcon);
            setEnabled(false);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undoM.undo();
            } catch (CannotUndoException eun) {
                eun.printStackTrace();
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undoM.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, "Undo");

            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    class RedoAction extends AbstractAction {

        public RedoAction(ImageIcon redoIcon) {
            super("Redo", redoIcon);
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undoM.redo();
            } catch (CannotRedoException redo) {
                redo.printStackTrace();
            }
            update();
            undoAction.update();
        }

        private void update() {
            if (undoM.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, "Redo");

            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }

    public static void main(String[] args) {
        NewNotepad np = new NewNotepad();
    }

}
