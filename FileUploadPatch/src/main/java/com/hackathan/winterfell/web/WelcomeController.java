package com.hackathan.winterfell.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.im4java.core.IM4JavaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hackathan.winterfell.service.OCRExtractService;

import net.sourceforge.tess4j.TesseractException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.imageio.ImageIO;


@Controller
public class WelcomeController {
	private static String UPLOADED_FOLDER = "C:\\Users\\sukv\\Desktop\\hackathan\\files\\";

	@Autowired
	OCRExtractService oCRExtractService;
	
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "WelcomeUpload";
    }
    
    @RequestMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws InterruptedException, IM4JavaException, TesseractException {
        
    	byte[] bytes = null;
    	Path path = null;
    	String filePath = null;
    	File f = null;
    	BufferedImage img = null;
    	try {

            // Get the file and save it somewhere
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
                return "redirect:uploadStatus";
            }
            bytes = file.getBytes();
            filePath  = UPLOADED_FOLDER + file.getOriginalFilename();
            
            if(new File(filePath).exists()){
            	filePath = UPLOADED_FOLDER + new Date().getTime()+file.getOriginalFilename();
            }
            path = Paths.get(filePath);
            Files.write(path, bytes);
            
            f = new File(filePath);
			img = ImageIO.read(f);
			
			
			
			
			oCRExtractService.getThumbnailFile(f.getAbsolutePath());
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			int width = img.getWidth();
			int height = img.getHeight();
            redirectAttributes.addFlashAttribute("fileName", file.getOriginalFilename());
            redirectAttributes.addFlashAttribute("filewidth", width);
            redirectAttributes.addFlashAttribute("fileheight", height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @RequestMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
