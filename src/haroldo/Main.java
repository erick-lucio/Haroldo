/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haroldo;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Erick
 */
public class Main {
    







    public static void main(String[] args) throws Exception {
       AnalizeImgs analizer = new AnalizeImgs();
       Paint printedImgs = new Paint();
       ImageConverterToImgPlus imgConvert = new ImageConverterToImgPlus();
       //printedImgs.takeSnapshot("The Perfect World Brasil - Rate Baixa");
      // analizer.compare(printedImgs.takeSnapshot("The Perfect World Brasil - Rate Baixa"), printedImgs.takeSnapshot("The Perfect World Brasil - Rate Baixa"));
       // new Paint("The Perfect World Brasil - Rate Baixa");
       //imgConvert.convertToimgPlus();
    
    
    


}
}
