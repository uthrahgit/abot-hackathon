package com.hackathan.winterfell.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.im4java.core.IM4JavaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hackathan.winterfell.constants.Constants;
import com.hackathan.winterfell.service.OCRExtractService;

import net.sourceforge.tess4j.TesseractException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.imageio.ImageIO;


@Controller
public class WelcomeController {
	
	@Autowired
	OCRExtractService oCRExtractService;
	
	
	@RequestMapping("/welcome")
	public ModelAndView showUpload() {
		return new ModelAndView("WelcomeUpload");
	}

	@PostMapping("/upload")
	public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws InterruptedException, IM4JavaException, TesseractException {

		byte[] bytes = null;
		Path path = null;
		String filePath = null;
		File f = null;
		ModelAndView mov = null;
		String fileName = null;
		String trackingNumber = null;

		try {

			// Get the file and save it somewhere
			mov = new ModelAndView();
			if (file.isEmpty()) {
				redirectAttributes.addFlashAttribute("Error", "Please select a file to upload");
				mov.setViewName("WelcomeUpload");
				return mov;
			}
			bytes = file.getBytes();
			fileName = file.getOriginalFilename();
			int i = fileName.lastIndexOf('.');
			fileName = fileName.substring(0, i);
			fileName = fileName+".png";
			filePath = Constants.UPLOADFOLDERPATH + fileName;
			if (new File(filePath).exists()) {
				filePath = Constants.UPLOADFOLDERPATH + new Date().getTime() + fileName;
			}
			path = Paths.get(filePath);
			Files.write(path, bytes);

			f = new File(filePath);
			trackingNumber = oCRExtractService.getThumbnailFile(f.getAbsolutePath());
			mov.addObject("fileName", file.getOriginalFilename());
			mov.addObject("trackingNumber", trackingNumber);
			mov.addObject("Message", "File scanned and found the informations from the uploaded image");
			mov.setViewName("WelcomeUpload");
		} catch (Exception e) {
			mov.setViewName("WelcomeUpload");
			mov.addObject("Message", "File scanned, But no informations found in the uploaded image");
			mov.addObject("trackingNumber", "Not Found");
			return mov;
		}
		return mov;
	}

}
