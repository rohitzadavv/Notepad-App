import java.awt.*;
import java.awt.print.PrinterException;
import javax.swing.*; // [Note-1]
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileReader;

public class NotepadApp extends JFrame implements ActionListener{
    // Menue Bar content
    JMenuBar menu = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");

    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem print = new JMenuItem("Print");
    JMenuItem exit = new JMenuItem("Exit");

    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectAll = new JMenuItem("Select All");

    JMenuItem about = new JMenuItem("About");
    // end of menu bar 
    
    // text area content
    JTextArea textArea = new JTextArea();
    File FileSelecter; 
    JScrollPane scrollPane;
    JFrame jf;

    

    NotepadApp(){ // constructor
        //
        setTitle("Note by Rohit"); // title
        setBounds(100,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("myLogo1.png"));
        setIconImage(icon.getImage());

        //
        setJMenuBar(menu);
        menu.add(file);
        menu.add(edit);
        menu.add(help);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(print);
        file.add(exit);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        help.add(about);
        
        // Text Area in Notepad
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18)); 
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        about.addActionListener(this);

        // Short cut keys
        newFile.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
    }
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new NotepadApp().setVisible(true); 

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("New")){
            textArea.setText(null);            
        }
        else if(event.getActionCommand().equals("Open")){
            // JFileChooser fileChooser = new JFileChooser();
            
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser();
            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);
            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    // String
                    String s1 = "", sl = "";
                    // File reader
                    FileReader fr = new FileReader(fi);
                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);
                    // Initialize sl
                    sl = br.readLine();
                    // Take the input from the file
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }
                    // Set the text
                    textArea.setText(sl);
                    br.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(scrollPane, evt.getMessage());
                }
                
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(scrollPane, "the user cancelled the operation");

        }
        else if(event.getActionCommand().equals("Save")){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter1 = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter1);
            int action = fileChooser.showSaveDialog(null);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(!fileName.contains(".txt"))
                    fileName = fileName+".txt";
                    try{
                        BufferedWriter write = new BufferedWriter( new FileWriter(fileName));
                        textArea.write(write);
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
            }
        }
        else if(event.getActionCommand().equals("Print")){
            try {
                textArea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(NotepadApp.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if(event.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(event.getActionCommand().equals("Cut")){
            textArea.cut();
        }
        else if(event.getActionCommand().equals("Copy")){
            textArea.copy();
        }
        else if(event.getActionCommand().equals("Paste")){
            textArea.paste();
        }
        else if(event.getActionCommand().equals("Select All")){
            textArea.selectAll();
        }
        else if(event.getActionCommand().equals("About")){
            new About().setVisible(true);
            new test();
        }
    }
}

/*

[Note-1]: javax : standard Java extensions... 




 */