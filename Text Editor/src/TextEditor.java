import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring a properties of TextEditor
    JFrame frame;
    JMenuBar menubar;
    JMenu file, edit;

    //creating file menu items
    JMenuItem newFile, openFile, SaveFile, print;
    //creating edit menu items
    JMenuItem cut, copy, paste, seclectAll, close;

    JTextArea textArea;
    TextEditor(){
        //Initilize a frame
        frame = new JFrame("Text Editor");

        //initilize a menubar
        menubar = new JMenuBar();

        //intilize text area
        textArea = new JTextArea();

        //initilize menu
        file = new JMenu("File");


        //add items of file
        newFile = new JMenuItem("new");
        openFile = new JMenuItem("open");
        SaveFile = new JMenuItem("save");
        print = new JMenuItem("Print");


        //adding action listner to file menu item
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        SaveFile.addActionListener(this);
        print.addActionListener(this);


        //adding elements to the file
        file.add(newFile); // adding new file
        file.add(openFile); // adding open file
        file.add(SaveFile); // adding save file
        file.add(print); //adding print option

        edit = new JMenu("Edit");
        //adding items of edit file
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        seclectAll = new JMenuItem("Seclect All");
        close = new JMenuItem("close");

        //adding action listner to file menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        seclectAll.addActionListener(this);
        close.addActionListener(this);

        //adding elements to the edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(seclectAll);
        edit.add(close);


        //add menu into menubar
        menubar.add(file);
        menubar.add(edit);


        //set the menu bar insize the j frame
        frame.setJMenuBar(menubar);
        frame.add(textArea);

        //select a dimmention of a frame
        frame.setBounds(150, 150, 400, 400);
        //make it visible
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //File Actions
        if(actionEvent.getSource() == newFile){
            //performe new file operation
            TextEditor textEditor = new TextEditor();
        }
        if(actionEvent.getSource() == openFile){
            //performe open operation
            JFileChooser fileChooser = new JFileChooser("E:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting seleted file
                File file = fileChooser.getSelectedFile();
                //getting the selected file path
                String filePath = file.getPath();

                //handing Exception using try and chatch
                try{
                    //initialize the file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initialize the buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermidiate = "", output = "";
                    //read content line by line
                    while((intermidiate = bufferedReader.readLine()) != null){
                        output += intermidiate + "\n";
                    }
                    //set the output string on the text area
                    textArea.setText(output);
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == SaveFile){
            //performe save operation
            //choosing a path to save a file
            JFileChooser fileChooser = new JFileChooser("E:");
            //getting the save dialogbox
            int getDialogBox = fileChooser.showSaveDialog(null);

            if(getDialogBox == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    //write
                    fileWriter.write(textArea.getText());
                    fileWriter.flush();
                    fileWriter.close();

                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == print){
            //performe print operation
            try {
                textArea.print();
            } catch (PrinterException e) {
                throw new RuntimeException(e);
            }
        }

        //Edit Actions
        if(actionEvent.getSource() == cut){
            //performe cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //performe copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //performe paste opertion
            textArea.paste();
        }
        if(actionEvent.getSource() == seclectAll){
            //performe seclect all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            //performe close operation
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}