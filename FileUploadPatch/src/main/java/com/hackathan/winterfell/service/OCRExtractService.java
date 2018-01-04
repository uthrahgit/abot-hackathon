package com.hackathan.winterfell.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OCRExtractService {



    public String getThumbnailFile(String path) throws IOException, InterruptedException, IM4JavaException, TesseractException {
        File f1 = new File(path);
        
        System.out.println(" path ::" + path);
        
        //f2.deleteOnExit();
        String imPath="C:\\Program Files\\ImageMagick-7.0.7-Q16";
        //ProcessStarter.setGlobalSearchPath(imPath);
        //File f2 = new File("C:\\Users\\kammurugan\\Desktop\\outt\\path-out.jpg");
        ConvertCmd cmd = new ConvertCmd();
        cmd.setSearchPath(imPath);
        IMOperation op = new IMOperation();
        op.stretch("Expanded");
        op.enhance();
        op.addImage(path);
        op.adaptiveSharpen(3.0,3.0);
        //op.autoGamma();
        op.sharpen(3.0,3.0);
        //op.blackPointCompensation();
        //op.unsharp(1.0);
        //op.resize(1200,1200);
       // op.floodfill(50,50);//ter("25");
        //op.tileOffset(2);
       // op.sharpen(6.0,1.0);
        //op.sigmoidalContrast(1.0);
        //op.segment(6,1.0);
        op.autoOrient();
        //op.extract(1);
        //op.font("Copperplate Gothic Bold");
        op.font().family().weight(800);
        op.font().family().style("Normal");

        //op.sample(5000);
        //op.font().family().stretch("UltraCondensed");
       // op.font().textFont(OPTION1);
        //op.rotate(90.0);
        //op.units("PixelsPerInch");
        //op.magnify(2.00);
        //op.resize(2000); -g -e stretch -f 15 -o 5 -s 1


        //op.magnify();
        //op.scale(700);
        //op.thumbnail(width, height);
        //op.transparent("inverse");

        //op.gamma(2.00);
        op.addImage("C:\\Users\\sukv\\Desktop\\outt\\path-out.jpg");

        /*if (MediaFilterManager.isVerbose) {
            System.out.println("IM Thumbnail Param: "+op);
        }*/
       // cmd.setCommand("-g -e stretch -f 25 -o 10 -s 1");
        //cmd.setCommand("-g"); -g -e stretch -f 25 -o 20 -s 1
        //-g -e stretch -f 25 -o 10 -u -s 1 -T -p 10
        //cmd.setCommand( new String[]{"-g","-e","stretch","-f","25","-o", 20, -t 30, -u,-s 1,-T,-p 20"});
        //op.distort();
        cmd.run(op);
        //ITesseract instance = new Tesseract();
        //String imgText = instance.doOCR(new File(f2.getAbsolutePath()));
        getImgText("C:\\Users\\sukv\\Desktop\\outt\\path-out.jpg");
        return "ok";
    }


    public String getImgText(String imageLocation) {
        ITesseract instance = new Tesseract();
        try {
            String imgText = instance.doOCR(new File(imageLocation));
            if (imgText != null)
                getTrackingNumbers(imgText);
           //ImagePlus imgPlus = new ImagePlus(imageLocation);
           //ImageProcessor imgProcessor = imgPlus.getProcessor();
          // BufferedImage bufferedImage = imgProcessor.getBufferedImage();

           // List<Word> img =  instance.getWords(bufferedImage,2);
            System.out.println(imgText);
            return "OK";
        }
        catch (TesseractException e)
        {
            e.getMessage();
            return "Error while reading image";
        }
    }

    public static void main ( String[] args) throws InterruptedException, IM4JavaException, TesseractException, IOException {
        OCRExtractService app = new OCRExtractService();
       // app.invertImage("C:\\Users\\kammurugan\\Desktop\\outt\\sample2.jpg");
       //System.out.println(app.getImgText("C:\\Users\\kammurugan\\Desktop\\outt\\sample2.jpg"));
        System.out.println(app.getThumbnailFile("C:\\Users\\kammurugan\\Desktop\\outt\\sample2.jpg"));
    }

    public void getTrackingNumbers(String ocrData){
        List<String> matchedNumbers = new ArrayList<String>();
        //Pattern p = Pattern.compile("[0-9]{1,4}\\S*\\s\\S*\\d{6}");   // the pattern to search for
        Pattern p = Pattern.compile("(\\d{4}[\\s]\\d{4}[\\s]\\d{4}[\\s]\\d{4}[\\s](\\d{6}))");
        Pattern p1 = Pattern.compile("(\\d{10})");
        Pattern p2 = Pattern.compile("(\\d{16})");
        Pattern p3 = Pattern.compile("((CH|CX|CHO)(\\d{8,11})(US|OS|CA))");
        Matcher matcher = p.matcher(ocrData);
        Matcher matcher1 = p1.matcher(ocrData);
        Matcher matcher2 = p2.matcher(ocrData);
        Matcher matcher3 = p3.matcher(ocrData);
       /* if (m.find())
        {
          String theMatch = m.group(1);
          System.out.format("'%s'\n", theMatch);
          matchedNumbers.add(theMatch);
        }*/
        while (matcher.find()) {
            String match = matcher.group();
            System.out.println("Tracking number pattern: "+ match);
            matchedNumbers.add(match);
        }
        while (matcher1.find()) {
            String match1 = matcher1.group();
            System.out.println("Tracking number pattern1: "+ match1);
            matchedNumbers.add(match1);
        }
        while (matcher2.find()) {
            String match2 = matcher2.group();
            System.out.println("Tracking number pattern2: "+ match2);
            matchedNumbers.add(match2);
        }
        while (matcher3.find()) {
            String match3 = matcher3.group();
            System.out.println("Tracking number pattern3: "+ match3);
            matchedNumbers.add(match3);
        }
    }


}