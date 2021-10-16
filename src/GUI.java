import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
        JFrame window;

//        TEXT AREA
        JTextArea textArea;
        JScrollPane scrollPane;
//        TOP MENU BAR
        JMenuBar menuBar;
//        FILE MENU
        JMenu menuFile,menuEdit,menuTool,menuColor,menuTranslate;
//        FILE MENU
        JMenuItem iNew,iOpen,iSave, iSaveAs,iExit;
        JMenuItem iCopy,iCut,iPast,iUndo,iRedo;
        JMenuItem iEn, iSl;
//      COLOR MENU
        JMenuItem iColor1,iColor2,iColor3;

//write function
        Function_File file = new Function_File(this);
        Function_Color color = new Function_Color(this);
        Function_Edit edit = new Function_Edit(this);

        KeyHandler kHandler = new KeyHandler(this);

//        undo function
    UndoManager um = new UndoManager();

    public static void main(String[]args) {

        new GUI();
    }
    public GUI(){

        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createTranslate();
        createColorMenu();

        window.setVisible(true);
    }

//    create notepad
    public void createWindow(){
        window = new JFrame("Notepad");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    Create TextArea
   public void createTextArea(){
       textArea = new JTextArea();

       textArea.addKeyListener(kHandler);

       textArea.getDocument(). addUndoableEditListener(
               new UndoableEditListener() {
                   @Override
                   public void undoableEditHappened(UndoableEditEvent e) {
                       um.addEdit(e.getEdit());
                   }
               }
       );

       scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setBorder(BorderFactory.createEmptyBorder());

       window.add(scrollPane);

//     window.add(textArea);
   }

//   Create menuBar
   public void createMenuBar(){

        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuTool = new JMenu("Tool");
        menuBar.add(menuTool);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        menuTranslate = new JMenu("Translate");
        menuBar.add(menuTranslate);
   }

//   create menuItem(File)
   public void createFileMenu(){
        iNew = new JMenuItem("New");

//        New function
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");

//        open function
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);


        iSave = new JMenuItem("Save");
//       File save
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("SaveAs");
//      File saveAs
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
//        Exit
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
   }

    //   create menuItem(Edit)
    public void createEditMenu(){
        iCopy = new JMenuItem("Copy");
        menuEdit.add(iCopy);

        iCut = new JMenuItem("Cut");
        menuEdit.add(iCut);

        iPast = new JMenuItem("Past");
        menuEdit.add(iCopy);

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    //   create menuItem(Translate)
    public void createTranslate(){
        iEn = new JMenuItem("En");
        menuTranslate.add(iEn);

        iSl=new JMenuItem("Sl");
        menuTranslate.add(iSl);
}



    public void createColorMenu(){

        iColor1 = new JMenuItem("white");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("red");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("red");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("red");
        menuColor.add(iColor2);

        
    }
  //Open the file function
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command){
            case "New": file.newFile(); break;
            case "Open": file.open(); break;
            case "Save": file.save(); break;
            case "SaveAs": file.saveAs(); break;
            case "Exit": file.exit(); break;
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break;

            case "White": color.changeColor(command); break;
//            case "Red": color.changeColor(command); break;

        }
    }
}


