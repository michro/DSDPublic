package algorithm.kai.imageProcess;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.text.html.HTMLDocument.Iterator;

public class ImageProcess {
	
	static BufferedImage image1;
	static String imagePath;

	public ImageProcess(){
		System.out.println("You didn't give a picture to process!");
	}

	public ImageProcess(BufferedImage image1) {
		this.image1 = image1;
	}

	public ImageProcess(String imagePath) throws IOException {
		this.imagePath = imagePath;
		haveImageThroughPath();
	}

	public static void haveImageThroughPath() throws IOException {
		image1 = ImageIO.read(new File(imagePath));
	}

    public static byte[] haveRGBInByteStack(){
        if(image1.getType() != BufferedImage.TYPE_3BYTE_BGR){

            BufferedImage rgbImage = new BufferedImage(
            		image1.getWidth(), 
            		image1.getHeight(),  
                        BufferedImage.TYPE_3BYTE_BGR);
            new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null).filter(image1, rgbImage);

            return (byte[]) rgbImage.getData().getDataElements(0, 0, rgbImage.getWidth(), rgbImage.getHeight(), null);
        }else{
            return (byte[]) image1.getData().getDataElements(0, 0, image1.getWidth(), image1.getHeight(), null);
        }
    }
    
    public static int[][] haveRGBInIntMatrix(){
    	
    	int [][] data = new int[image1.getWidth()][image1.getHeight()];

        for(int i = 0;i < image1.getWidth();i ++){
            for(int j = 0;j < image1.getHeight();j ++){
                data[i][j] = image1.getRGB(i,j);
            }
        }
        
        return data;
    }

    public static int[][] haveRInIntMatrix(int[][] infoRGB){
    	
    	
    	int [][] rValue = new int[infoRGB.length][infoRGB[0].length];

    	Color color1;
    	
        for(int i = 0;i < image1.getWidth();i ++){
            for(int j = 0;j < image1.getHeight();j ++){
            	color1 = new Color(infoRGB[i][j], true);
            	rValue[i][j] = color1.getRed();
            }
        }
        
        return rValue;
    }

    public static int[][] haveGInIntMatrix(int[][] infoRGB){
    	
    	
    	int [][] gValue = new int[infoRGB.length][infoRGB[0].length];

    	Color color1;
    	
        for(int i = 0;i < image1.getWidth();i ++){
            for(int j = 0;j < image1.getHeight();j ++){
            	color1 = new Color(infoRGB[i][j], true);
            	gValue[i][j] = color1.getGreen();
            }
        }
        
        return gValue;
    }

    public static int[][] haveBInIntMatrix(int[][] infoRGB){

    	int [][] bValue = new int[infoRGB.length][infoRGB[0].length];

    	Color color1;
    	
        for(int i = 0;i < image1.getWidth();i ++){
            for(int j = 0;j < image1.getHeight();j ++){
            	color1 = new Color(infoRGB[i][j], true);
            	bValue[i][j] = color1.getBlue();
            }
        }
        
        return bValue;
    }

    public static void createImage(int[][] colorInfo) throws IOException{
    	
    	int width = colorInfo.length;
    	int height = colorInfo[0].length;
    	
    	int nameTemp = 1;
    	
    	String fileName = "src/someImages/imagesForTrying/try_0";
    	String fileType = "png";
    	String fileNameAll = fileName + "." + fileType;
    	
        BufferedImage bufferedImage1 = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                  bufferedImage1.setRGB(i, j, colorInfo[i][j]);
            }
       }
        
        java.util.Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        
        File file1 = new File(fileNameAll);
        
        while(file1.exists()){

        	fileName = "src/someImages/imagesForTrying/try" + "_" + nameTemp;

        	fileType = "png";
        	
        	fileNameAll = fileName + "." + fileType;
        	
        	file1 = new File(fileNameAll);
        	
        	nameTemp += 1;

        }
        
        ImageOutputStream ios = ImageIO.createImageOutputStream(file1);
        writer.setOutput(ios);
        
        writer.write(bufferedImage1);
    }

    public static int[][] removeNonBones1(int[][] imageInfo){
    	
    	int[][] imageNow = new int[imageInfo.length][imageInfo[0].length];

    	Color color1 = new Color(0xffffffff, true);

    	for(int i = 0; i < imageInfo.length; i ++) {
    		for(int j = 0; j < imageInfo[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied1(imageInfo[i][j])) {
    				imageNow[i][j] = imageInfo[i][j];

    			}else if(StaticThingsForImageProcessing.ifSatisfied2(i, j, imageInfo)){
    				imageNow[i][j] = imageInfo[i][j];
    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	return imageNow;
    }

    public static int[][] removeNonBones2(int[][] imageInfo){
    	
    	int[][] imageNow = new int[imageInfo.length][imageInfo[0].length];

    	Color color1 = new Color(0xffffffff, true);

    	for(int i = 0; i < imageInfo.length; i ++) {
    		for(int j = 0; j < imageInfo[0].length; j ++) {

    				if(StaticThingsForImageProcessing.ifSatisfied5(i, j, imageInfo, 0)){
    				imageNow[i][j] = imageInfo[i][j];
    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied5(i, j, imageInfo, 1)) {
    				imageNow[i][j] = imageNow[i][j];

    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}
    	
    	return imageNow;
    }

    public static int[][] removeNonBones3(int[][] imageInfo){
    	
    	int[][] imageNow = new int[imageInfo.length][imageInfo[0].length];

    	Color color1 = new Color(0xffffffff, true);

    	for(int i = 0; i < imageInfo.length; i ++) {
    		for(int j = 0; j < imageInfo[0].length; j ++) {

    				if(StaticThingsForImageProcessing.ifSatisfied5(i, j, imageInfo, 0)){
    				imageNow[i][j] = imageInfo[i][j];
    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied5(i, j, imageInfo, 1)) {
    				imageNow[i][j] = imageNow[i][j];

    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied6(i, j, imageInfo, 0)) {
    				imageNow[i][j] = imageNow[i][j];

    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied7(i, j, imageInfo, 0)) {
    				imageNow[i][j] = imageNow[i][j];
    				
    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}
    	
    	return imageNow;
    }

    public static int[][] removeNonBones4(int[][] imageInfo){
    	
    	int[][] imageNow = new int[imageInfo.length][imageInfo[0].length];

    	Color color1 = new Color(0xffffffff, true);

    	for(int i = 0; i < imageInfo.length; i ++) {
    		for(int j = 0; j < imageInfo[0].length; j ++) {

    				if(StaticThingsForImageProcessing.ifSatisfied5(i, j, imageInfo, 0)){
    				imageNow[i][j] = imageInfo[i][j];
    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	imageNow = StaticThingsForImageProcessing.cutLeftLung1(imageNow, 0);

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied5(i, j, imageInfo, 1)) {
    				imageNow[i][j] = imageNow[i][j];

    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied6(i, j, imageInfo, 0)) {
    				imageNow[i][j] = imageNow[i][j];

    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied6(i, j, imageInfo, 2)) {
    				imageNow[i][j] = imageNow[i][j];
    				
    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}

    	imageNow = StaticThingsForImageProcessing.cutHead1(imageNow, 0);

    	for(int i = 0; i < imageNow.length; i ++) {
    		for(int j = 0; j < imageNow[0].length; j ++) {

    			if(StaticThingsForImageProcessing.ifSatisfied7(i, j, imageInfo, 0)) {
    				imageNow[i][j] = imageNow[i][j];

    			}else {
    				imageNow[i][j] = color1.getRGB();
    			}
    		}
    	}
    	
    	return imageNow;
    }
    

}


