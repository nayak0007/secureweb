package com.secureweb.web;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.secureweb.model.User;
import com.secureweb.service.FileService;
import com.secureweb.service.UserService;

@RestController
public class RestWebController {
	@Autowired
	HttpSession session;
	@Autowired
    HttpServletRequest request;
	 @Autowired
	    private UserService userService;
	 @Autowired
	    private FileService fileService;
	 
	 @RequestMapping(value = "/detail", method = RequestMethod.GET)
	    public List<User> getUserDetail(@RequestParam("userId") String userId) {
	    	System.out.println("userId>>>"+userId);
	    	List<User> userDtl=new ArrayList<User>();
	    	if(!userId.equalsIgnoreCase("superadmin")){
	    	userDtl.add(userService.findByUsername(userId));
	    	}
	    	else{
	    		userDtl=userService.getAllUser();
	    	}
	        return userDtl;
	    }
	 @RequestMapping(value = "/edituser", method = RequestMethod.GET)
	    public List<User> getUser(@RequestParam("userId") String userId) {
	    	List<User> userDtl=new ArrayList<User>();	    
	    	userDtl.add(userService.findByUsername(userId));
	        return userDtl;
	    }
	 @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	    public String updateUser(@RequestParam("userId") String userId,
	    		@RequestParam("name") String name,
	    		@RequestParam("dob") String dob,
	    		@RequestParam("state") String state,
	    		@RequestParam("city") String city,
	    		@RequestParam("mobile") String mobile,
	    		@RequestParam("email") String email) {
		 String status="";
		 try{
			 userService.updateUser(userId, name, dob, state, city, mobile, email);
			 status="S";			
		 }
		 catch(Exception e){
			 status="F";
		 }
		 return status;
	    }
	 
	 @RequestMapping(value = "/upload", method = RequestMethod.POST)
	 public ResponseEntity<?> uploadFile(
	            @RequestParam("file") MultipartFile uploadfile,
	            @RequestParam("uId") String uId) {

	        if (uploadfile.isEmpty()) {
	            return new ResponseEntity("please select a file!", HttpStatus.OK);
	        }

	        try {
	        	 String UPLOADED_FOLDER=request.getContextPath()+"/resources/images";
	            saveUploadedFiles(Arrays.asList(uploadfile),uId,UPLOADED_FOLDER);
	            

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity("Successfully uploaded"+"~#~"+uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

	    }
	 private void saveUploadedFiles(List<MultipartFile> files,String uId,String path_) throws IOException {
	
		// String UPLOADED_FOLDER="C:\\servers\\apache-tomcat-8.0.23\\webapps\\footprintuploads\\thumbnails\\";
		 String UPLOADED_FOLDER="D:\\S2\\MyOyster\\eclipse-workspace-8\\secureweb\\src\\main\\webapp\\resources\\images\\";
		
	        for (MultipartFile file : files) {

	            if (file.isEmpty()) {
	                continue; //next pls
	            }
	            String DATE_FORMAT = "ddMMyyyyhhmmss";
	    		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    		String timeStamp = sdf.format(
	    				Calendar.getInstance().getTime()).toString();
	    		//File newFile=new File(UPLOADED_FOLDER+file.getOriginalFilename()+"-"+timeStamp);
	    		//file.transferTo(newFile);
	            byte[] bytes = file.getBytes();
	            String orgFileName=file.getOriginalFilename();
	            String orgFileNameWext=orgFileName.substring(0,orgFileName.indexOf("."));
	            String fileExt=orgFileName.substring(orgFileName.indexOf("."),orgFileName.length());
	            String output_img_name=orgFileNameWext+"-"+timeStamp+fileExt;
	            Files.write(Paths.get("C:\\servers\\apache-tomcat-8.0.23\\webapps\\footprintuploads\\thumbnails\\"+file.getOriginalFilename()), bytes);
	            
	            saveScaledImage("C:\\servers\\apache-tomcat-8.0.23\\webapps\\footprintuploads\\thumbnails\\"+file.getOriginalFilename(),"C:\\servers\\apache-tomcat-8.0.23\\webapps\\footprintuploads\\thumbnails\\"+output_img_name);
	           
	           
	            
	            Path path = Paths.get(UPLOADED_FOLDER + output_img_name);
	            System.out.println("UserName>>>>"+uId);
	            System.out.println("uploaded_img_name>>>>"+UPLOADED_FOLDER + file.getOriginalFilename());
	            fileService.save(uId,UPLOADED_FOLDER + file.getOriginalFilename());
	            //fileService.save(uId,UPLOADED_FOLDER + newFile.getName());
	           Files.write(path, bytes);
	            
	            File fBlob = new File (UPLOADED_FOLDER + file.getOriginalFilename());

	        }

	    }
	 private  void saveScaledImage(String filePath,String outputFile){
		    try {

		        BufferedImage sourceImage = ImageIO.read(new File(filePath));
		        int width = sourceImage.getWidth();
		        int height = sourceImage.getHeight();
		        System.out.println("original height>>"+height);
		        System.out.println("original width>>"+width);
		        if(width>height){
		            float extraSize=    height-100;
		            float percentHight = (extraSize/height)*100;
		            System.out.println("thumb height>>"+percentHight);
		            float percentWidth = width - ((width/100)*percentHight);
		            System.out.println("thumb width>>"+percentWidth);
		            BufferedImage img = new BufferedImage((int)percentWidth, 100, BufferedImage.TYPE_INT_RGB);
		            Image scaledImage = sourceImage.getScaledInstance((int)percentWidth, 100, Image.SCALE_SMOOTH);
		            img.createGraphics().drawImage(scaledImage, 0, 0, null);
		            BufferedImage img2 = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_RGB);
		            img2 = img.getSubimage((int)((percentWidth-100)/2), 0, 100, 100);

		            ImageIO.write(img2, "png", new File("D:\\portraits_aspect.png"));    
		        }else{
		            float extraSize=    width-100;
		            float percentWidth = (extraSize/width)*100;
		            float  percentHight = height - ((height/100)*percentWidth);
		            System.out.println("thumb height>>"+percentHight);
		            System.out.println("thumb width>>"+percentWidth);
		            BufferedImage img = new BufferedImage(100, (int)percentHight, BufferedImage.TYPE_INT_RGB);
		            Image scaledImage = sourceImage.getScaledInstance(100,(int)percentHight, Image.SCALE_SMOOTH);
		            img.createGraphics().drawImage(scaledImage, 0, 0, null);
		            BufferedImage img2 = new BufferedImage(100, 100 ,BufferedImage.TYPE_INT_RGB);
		            img2 = img.getSubimage(0, (int)((percentHight-100)/2), 100, 100);

		            ImageIO.write(img2, "png", new File("D:\\portraits_aspect.png"));
		        }

		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }

		/* try {
	         FileInputStream fis = new FileInputStream(filePath);
	         InputStream bis = new BufferedInputStream(fis);
	         FileOutputStream fos = null;
	         Image image = (Image) ImageIO.read(bis);
	         int thumbWidth = 100;// Specify image width in px
	         int thumbHeight = 100;// Specify image height in px
	         
	         int imageWidth = image.getWidth(null);// get image Width
	         int imageHeight = image.getHeight(null);// get image Height
	         
	         double thumbRatio = (double) thumbWidth / (double) thumbHeight;
	         double imageRatio = (double) imageWidth / (double) imageHeight;

	         // This calculation is used to convert the image size according to the pixels mentioned above
	         if (thumbRatio < imageRatio) {
	            thumbHeight = (int) (thumbWidth / imageRatio);
	         } else {
	            thumbWidth = (int) (thumbHeight * imageRatio);
	         }

	         BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);

	         Graphics2D graphics = thumbImage.createGraphics();
	         graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	         graphics.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

	         ByteArrayOutputStream out = new ByteArrayOutputStream();
	         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	         System.out.println("Encoder" + encoder);
	         JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);

	         int quality = 300;
	         quality = Math.max(0, Math.min(quality, 500));
	         param.setQuality(0.75f, false);

	         

	         encoder.setJPEGEncodeParam(param);
	         encoder.encode(thumbImage);
	         ImageIO.write(thumbImage, "jpg", new       File("D:\\portraits_aspect.jpg"));
	      } catch (IOException ioExcep) {
	         ioExcep.printStackTrace();
	      } catch (Exception excep) {
	         excep.printStackTrace();
	      }*/
		}
	 @RequestMapping(value = "/getImg", method = RequestMethod.GET)
	    public String getImgPath(@RequestParam("userId") String userId) {
	    	String path="";	    
	    	path=fileService.findImgByUsername(userId).getFilePath();
	    	System.out.println("ImagePath>>>"+path);
	    	String imgName=path.substring(path.lastIndexOf("\\"),path.length());
	    	String finalPath=request.getContextPath()+"//resources//images//"+imgName;
	    	System.out.println("FinalImagePath>>>"+finalPath);
	    	
	        return finalPath;
	    }
	 @RequestMapping(value = "/extractusers", method = RequestMethod.GET)
	    public void extractUserInfo(@RequestParam("userId") String userId) {
		 List<User> allUser=userService.getAllUser();
		 
	    	
	    }
	 

}
