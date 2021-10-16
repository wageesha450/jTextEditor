import java.awt.*;

public class Function_Color {

    GUI gui;

    public Function_Color(GUI gui){

        this.gui = gui;
    }
    public void changeColor(String color){

        switch (color){
            case "White":
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.red);
                break;

            case "Red":
                gui.window.getContentPane().setBackground(Color.red);
                gui.textArea.setBackground(Color.red);
                gui.textArea.setForeground(Color.white);
                break;
        }
    }
}

