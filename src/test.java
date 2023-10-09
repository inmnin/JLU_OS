package GUI;
import java.awt.GraphicsEnvironment;
public class test {
     public static void main(String[] args){
         GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
         String[] fontNames = ge.getAvailableFontFamilyNames();
         for (String fontName : fontNames) {
             System.out.println(fontName);
         }

     }
}
