import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {
    GUI gui;
    String fileName;
    String fileAddress;

    public Function_File(GUI gui){

        this.gui = gui;

    }

//    Get new Interface
    public void newFile(){

        gui.textArea.setText("");
        gui.window.setTitle("New");

        fileName = null;
        fileAddress = null;
    }

//    Get Open function
    public void open(){

        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);

        }

//Loading file path
        System.out.println("File address and File name" + fileAddress + fileName);

//        Load file in the editor
        try {
            BufferedReader br = new BufferedReader( new FileReader(fileAddress + fileName));

            gui.textArea.setText("");

            String line = null;
            while ((line = br.readLine())!= null){

                gui.textArea.append(line + "\n");
            }
            br.close();

        }catch (Exception e){
            System.out.println("FILE NOT OPENED!");

        }
    }

//    File save function
    public void save(){
        if (fileName == null){
            saveAs();
        }
        else {
            try{
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }catch (Exception e){

                System.out.println("SOMETHING WRONG");
            }
        }

    }

    public void saveAs(){

        FileDialog fd = new FileDialog(gui.window, "save", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try{
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();

        }catch (Exception e){

            System.out.println("SOMETHING WRONG");

        }
    }

    public void exit(){
        System.exit(0);
    }
}

