import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor  implements ActionListener {
    // Declaring properties of textEditor
    JFrame frame;  // It is making frame
    JMenuBar menuBar;  // It is for the menu like menubar
    JMenu file;  // It is the file menu
    JMenu edit;  // It is the edit menu
    JMenuItem newFile, openFile, saveFile;  // Menu items of the file menu
    JMenuItem cutFile, copyFile, pasteFile,undoFile,select, closeAll;
    JTextArea textArea;  // It is a text area


    TextEditor() {
        // Initializing the frame of the text Editor
        frame = new JFrame();

        // Create the menu bar
        menuBar = new JMenuBar();

        // Create the file and edit menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // File menu items
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        //add the action listner to menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add file menu items to the file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Edit menu items
        cutFile = new JMenuItem("Cut");
        copyFile = new JMenuItem("Copy");
        pasteFile = new JMenuItem("Paste");
        select = new JMenuItem("Select");

        closeAll = new JMenuItem("Close All");
        //add the action listner to edit items
        cutFile.addActionListener(this);
        copyFile.addActionListener(this);
        pasteFile.addActionListener(this);
        select.addActionListener(this);

        closeAll.addActionListener(this);




        // Add edit menu items to the edit menu
        edit.add(cutFile);
        edit.add(copyFile);
        edit.add(pasteFile);
        edit.add(select);

        edit.add(closeAll);

        // Add file and edit menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);
        // Create a JTextArea
        textArea = new JTextArea();

        // Add the JTextArea to the frame
        frame.add(textArea);

        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);
        //create contentpanel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));//horizontal gap between border
        //Add text Area Tothe Panel
        panel.add(textArea,BorderLayout.CENTER);
        //create Scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         //Add ScrollPane to Panel
        panel.add(scrollPane);
        //add panel to the frame
        frame.add(panel);




        // Set dimensions of the frame
        frame.setBounds(0, 0, 400, 400);
        //add title
        frame.setTitle("Text Editor By :-) QUESTOR");

        // Make the frame visible
        frame.setVisible(true);

        // Use a null layout manager
        frame.setLayout(null);
    }

    @Override
    public  void actionPerformed(ActionEvent actionEvent){
        //edit actionevent functionalitites
        if(actionEvent.getSource()==cutFile){
           //perform cut operation
            textArea.cut();

        }
        if(actionEvent.getSource()==copyFile){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==pasteFile){
            //perform paste operation
            textArea.paste();
        }
      
        if(actionEvent.getSource()==select){
            //perform select operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==closeAll){
            //perform close all operation
            System.exit(0);
        }
        //file action event
        if(actionEvent.getSource()==newFile){
            //perform new note pad operation

        }
        if(actionEvent.getSource()==openFile){

            JFileChooser fileChooser = new JFileChooser("C:");
            int choseOption = fileChooser.showOpenDialog(null);
            /* iff we clicked on open button */
            if(choseOption==JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                try {
                    //intialize file reader
                    FileReader fileReader = new FileReader(filePath);

                    //intialize BufferReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";//current line and complete text in output
                    //read content of file line by ine
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //set the output String to text String
                    textArea.setText(output);


                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }


        }

        if(actionEvent.getSource()==saveFile){
            //perform save peration
            JFileChooser fileChooser = new JFileChooser("C:");
            int choseOption = fileChooser.showSaveDialog(null);
            if(choseOption==JFileChooser.APPROVE_OPTION){

                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try {
                    //intialize file writer
                    FileWriter fileWriter = new FileWriter(file);

                    //intialize BufferWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write content Area tofile
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();



                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }


        }
        if(actionEvent.getSource()==newFile)
        {
            TextEditor textEditor=new TextEditor();
        }





    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
