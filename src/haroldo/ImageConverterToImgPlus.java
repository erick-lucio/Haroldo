/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haroldo;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.awt.image.BufferedImage;
/**

/**
 *
 * @author Erick
 */
public class ImageConverterToImgPlus {
    
    public void convertToimgPlus() throws Exception{
        
            Paint printedImgs = new Paint();
    ImagePlus imgPlus = new ImagePlus("RGB", printedImgs.takeSnapshot("The Perfect World Brasil - Rate Baixa"));

    ImageProcessor imgProcessor = imgPlus.getProcessor();
    imgProcessor.invert();
    FileSaver fs = new FileSaver(imgPlus);

        
    BufferedImage bufferedImage = imgProcessor.getBufferedImage();
    for(int y=0;y<bufferedImage.getHeight();y++){
    for(int x=0;x<bufferedImage.getWidth();x++)
    {
        Color color = new Color(bufferedImage.getRGB(x, y));
        int grayLevel = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        int r = grayLevel;
        int g = grayLevel;
        int b = grayLevel;
        int rgb = (r<<16)  | (g<<8)  | b;
        bufferedImage.setRGB(x, y, rgb);
    }
}   
    ImagePlus grayImg = new ImagePlus("gray", bufferedImage);
    fs = new FileSaver(grayImg);
    fs.saveAsJpeg("C:\\Users\\Erick\\Desktop\\imgs\\igf.jpg");
        
    }
    public ImageConverterToImgPlus() throws Exception {
        

    }
    

}
