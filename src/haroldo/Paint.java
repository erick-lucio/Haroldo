/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haroldo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import jna.extra.GDI32Extra;
import jna.extra.User32Extra;
import jna.extra.WinGDIExtra;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.Memory;
import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinGDI.BITMAPINFO;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Date; 
import java.util.Calendar; 

public class Paint extends JFrame {
  BufferedImage image;  
  String windowName;// = "The Perfect World Brasil - Rate Baixa";     
  int windowsFound = 0; 
  AnalizeLetters analize;
public BufferedImage capture(HWND hWnd) throws Exception {
    this.windowsFound=0; 
    
    Date dateOne = new Date(); 
    int date = (int)dateOne.getTime();
    String gettime = "a";//toString().date;
    HDC hdcWindow = User32.INSTANCE.GetDC(hWnd);
    HDC hdcMemDC = GDI32.INSTANCE.CreateCompatibleDC(hdcWindow);
    RECT bounds = new RECT();
    User32Extra.INSTANCE.GetClientRect(hWnd, bounds);
    int width = bounds.right - bounds.left;
    int height = bounds.bottom - bounds.top;

    HBITMAP hBitmap = GDI32.INSTANCE.CreateCompatibleBitmap(hdcWindow, width, height);
    HANDLE hOld = GDI32.INSTANCE.SelectObject(hdcMemDC, hBitmap);
    GDI32Extra.INSTANCE.BitBlt(hdcMemDC, 0, 0, width, height, hdcWindow, 0, 0, WinGDIExtra.SRCCOPY);
    GDI32.INSTANCE.SelectObject(hdcMemDC, hOld);
    GDI32.INSTANCE.DeleteDC(hdcMemDC);
    BITMAPINFO bmi = new BITMAPINFO();
    bmi.bmiHeader.biWidth = width;
    bmi.bmiHeader.biHeight = -height;
    bmi.bmiHeader.biPlanes = 1;
    bmi.bmiHeader.biBitCount = 32;
    bmi.bmiHeader.biCompression = WinGDI.BI_RGB;
    try{
    Memory buffer = new Memory(width * height * 4);

        GDI32.INSTANCE.GetDIBits(hdcWindow, hBitmap, 0, height, buffer, bmi, WinGDI.DIB_RGB_COLORS);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, buffer.getIntArray(0, width * height), 0, width);
        GDI32.INSTANCE.DeleteObject(hBitmap);
        User32.INSTANCE.ReleaseDC(hWnd, hdcWindow);
        File outputfile = new File("C:\\Users\\Erick\\Desktop\\imgs" +"sd"+ ".jpg");
        ImageIO.write(image, "jpg", outputfile);

        return image;
    }catch(IllegalArgumentException exp){
        System.out.println("Windown "+this.windowName +" Not Found!");
        this.windowsFound = 1;
    }
    return image;
}
public BufferedImage takeSnapshot(String windowName) throws Exception {
    this.windowName = windowName;
    // HWND hWnd = User32.INSTANCE.FindWindow("launcher.exe", null);
    // HWND hWnd = User32.INSTANCE.FindWindow(null, "English");
    HWND hWnd = User32.INSTANCE.FindWindow(null,this.windowName);
        
    this.image = capture(hWnd);
    if(this.windowsFound == 0){
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    }
    

    
    return image;
}



public Paint(){


}
@Override
public void paint(Graphics g){

    if(this.windowsFound == 0){
    super.paint(g);
    g.drawImage(image, 20, 40, null);
    }
}


}