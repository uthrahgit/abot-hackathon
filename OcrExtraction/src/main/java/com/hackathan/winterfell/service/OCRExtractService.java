package com.hackathan.winterfell.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.stereotype.Component;

import com.hackathan.winterfell.constants.Constants;

import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

@Component
public class OCRExtractService {



    public String getThumbnailFile(String path) throws IOException, InterruptedException, IM4JavaException, TesseractException {
    	ConvertCmd cmd = null;
    	IMOperation op = null;
    	String outPutImagePath = null;
    	String imageTxtOut = null;
    	List<String> matchedNumbers = null;
    	String trackingNumber = null;
    	BufferedImage img = null;
    	try {
			//f2.deleteOnExit();
			//ProcessStarter.setGlobalSearchPath(imPath);
			//File f2 = new File("C:\\Users\\kammurugan\\Desktop\\outt\\path-out.jpg");
    		
    		img = ImageIO.read(new File(path));

			int width = img.getWidth();
			int height = img.getHeight();
			
			
			
    		
    		outPutImagePath = Constants.OUTPUTFOLDERPATH+new Date().getTime()+".png";
			cmd = new ConvertCmd();
			cmd.setSearchPath(Constants.iMagixCmdPath);
			op = new IMOperation();
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
			op.addImage(outPutImagePath);

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
			imageTxtOut = getImgText(outPutImagePath);
			matchedNumbers = getTrackingIds(imageTxtOut);
			if(matchedNumbers != null && matchedNumbers.size() > 0){
				trackingNumber = matchedNumbers.get(0);	
			}else{
				throw new RuntimeException("Not able to find information from image, Please upload clearly");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        return trackingNumber;
    }


    public String getImgText(String imageLocation) {
        ITesseract instance = null;
        String imgText = null;
        try {
        	instance = new Tesseract();
            imgText = instance.doOCR(new File(imageLocation));
            if (imgText != null)
                getTrackingNumbers(imgText);
           //ImagePlus imgPlus = new ImagePlus(imageLocation);
           //ImageProcessor imgProcessor = imgPlus.getProcessor();
          // BufferedImage bufferedImage = imgProcessor.getBufferedImage();

           // List<Word> img =  instance.getWords(bufferedImage,2);
            System.out.println(imgText);
            return imgText;
        }catch (TesseractException e){
           throw new RuntimeException(e);
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
    
    
    public static List<String> getTrackingIds(String ocrData){
		List<String> matchedNumbers = new ArrayList<String>();
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("pattern.properties");
			prop.load(input);
			String[] carrierNames = prop.getProperty("CARRIERNAMES").split(",");
			for(String carrier : carrierNames){
				Boolean carrierMatch = ocrData.contains(carrier);
				if(carrierMatch){
					String values = prop.getProperty(carrier.toUpperCase());
					if(values == null){
						continue;
					}
					String[] patterns = values.split(",");
					for(String pattern : patterns){
						if(null != pattern){
							Pattern p = Pattern.compile(pattern);
							Matcher matcher = p.matcher(ocrData);
							while (matcher.find()) {
						    	String match = matcher.group();
						    	System.out.println("Carrier name: " + carrier);
					            System.out.println("Tracking number: "+ match);
					            matchedNumbers.add(match);
					        }
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return matchedNumbers;
	}

}