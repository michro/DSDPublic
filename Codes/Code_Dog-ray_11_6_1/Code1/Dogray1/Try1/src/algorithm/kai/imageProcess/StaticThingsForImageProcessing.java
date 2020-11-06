package algorithm.kai.imageProcess;

import java.awt.Color;
import java.util.ArrayList;

import algorithm.kai.utilities.MyPoint;
import algorithm.kai.utilities.MySpecialColors;

public class StaticThingsForImageProcessing {
	
	public static int redThresholdMin1 = 0;
	public static int greenThresholdMin1 = 0;
	public static int blueThresholdMin1 = 0;
	
	public static int redThresholdMax1 = 47;
	public static int greenThresholdMax1 = 47;
	public static int blueThresholdMax1 = 47;
	
	public static int offset1 = 9;
	public static int deeper1 = 0;
	
	public static int offset2_1 = 50;

	public static int offset2_2 = 50; 
	
	public static int deeper2 = 5;

	public static ArrayList<Integer> offset3_1 = new ArrayList<Integer>();
	public static ArrayList<Integer> offset3_2 = new ArrayList<Integer>();
	public static ArrayList<Integer> deeper3 = new ArrayList<Integer>();
	
	public static int deepMax1 = 100000;
	public static int deepMin1 = 5000;
	public static int distance1 = 14;
	
	public static int deepMax2 = 10000;
	public static int deepMin2 = -1;
	public static int distance2 = 4;
	
	public static int numberMax1 = 100000;

	public static int numberMin1 = 119;
	public static int distance3_1 = 10;  
	public static int distance3_2 = 3;  
	
	public static int deepMax3 = 7 * 255 - 3;
	public static int deepMin3 = -1;
	public static int distance3 = 2;  
	
	public static ArrayList<Integer> exploreDistance = new ArrayList<Integer>();
	
	public StaticThingsForImageProcessing() {
		deeper3.add(3);
		deeper3.add(3);
		
		exploreDistance.add(0);
		for(int i = 1; i < 200; i ++) {
			exploreDistance.add(-i);
			exploreDistance.add(i);
		}

	}
	
	public static int headColor = 1350 * 255 - 50;

	public static int exploreDistance1 = 3;
	
	public static int leftLungPositon1 = 591;
	public static int leftLungRange1 = 15;

	public static boolean ifSatisfied1(int pixelColor) {
		
		boolean ifBones = false;
		
		Color color1 = new Color(pixelColor, true);
		
		if(color1.getRed() > redThresholdMin1 && color1.getRed() < redThresholdMax1) {
			if(color1.getGreen() > greenThresholdMin1 && color1.getGreen() < greenThresholdMax1) {
				if(color1.getBlue() > blueThresholdMin1 && color1.getBlue() < blueThresholdMax1) {
					ifBones = true;
				}
			}
		}

		return ifBones;
	}

    public static boolean ifSatisfied2(int pixelI, int pixelJ, int[][] imageInfo) {
		
		boolean ifBones = false;
		
		if(pixelI - offset1 < 0 || pixelI + offset1 >= imageInfo.length 
				|| pixelJ - offset1 < 0 || pixelJ + offset1 >= imageInfo[0].length) {
			return false;
		}
		
		Color color1 = new Color(imageInfo[pixelI][pixelJ], true);

		Color colorLeft1 = new Color(imageInfo[pixelI][pixelJ - offset1], true);
		Color colorRight1 = new Color(imageInfo[pixelI][pixelJ + offset1], true);

		Color colorUp1 = new Color(imageInfo[pixelI - offset1][pixelJ], true);
		Color colorDown1 = new Color(imageInfo[pixelI + offset1][pixelJ], true);

		if(

				(color1.getRed() + deeper1 < colorLeft1.getRed() && color1.getRed() + deeper1 < colorRight1.getRed())

				|| (color1.getRed() + deeper1 < colorUp1.getRed() && color1.getRed() + deeper1 < colorDown1.getRed())

				) {
			
			ifBones = true;
					
		}

		return ifBones;
	}

  	public static boolean ifSatisfied3(int pixelColor, int maxValue) {
  		
  		boolean ifBones = false;
  		
  		Color color1 = new Color(pixelColor, true);
  		
  		if(color1.getRed() > redThresholdMin1 && color1.getRed() < maxValue) {
  			if(color1.getGreen() > greenThresholdMin1 && color1.getGreen() < maxValue) {
  				if(color1.getBlue() > blueThresholdMin1 && color1.getBlue() < maxValue) {
  					ifBones = true;
  				}
  			}
  		}

  		return ifBones;
  	}

      public static boolean ifSatisfied4(int pixelI, int pixelJ, int[][] imageInfo) {
  		
  		boolean ifBones = false;

  		if(pixelI - offset2_1 < 0 || pixelI + offset2_1 >= imageInfo.length 
  				|| pixelJ - offset2_2 < 0 || pixelJ + offset2_2 >= imageInfo[0].length) {
  			return false;
  		}
  		
  		Color color1 = new Color(imageInfo[pixelI][pixelJ], true);

  		Color colorLeft1 = new Color(imageInfo[pixelI][pixelJ - offset1], true);
  		Color colorLeft2 = new Color(imageInfo[pixelI + 10][pixelJ - offset1], true);
  		Color colorLeft3 = new Color(imageInfo[pixelI - 10][pixelJ - offset1], true);
  		Color colorLeft4 = new Color(imageInfo[pixelI + 20][pixelJ - offset1], true);
  		Color colorLeft5 = new Color(imageInfo[pixelI - 20][pixelJ - offset1], true);
  		
  		double leftAverage = (double) (colorLeft1.getRed() + colorLeft2.getRed() + colorLeft3.getRed() + colorLeft4.getRed() + colorLeft5.getRed()) / 5.0;

  		Color colorRight1 = new Color(imageInfo[pixelI][pixelJ + offset1], true);
  		Color colorRight2 = new Color(imageInfo[pixelI + 10][pixelJ + offset1], true);
  		Color colorRight3 = new Color(imageInfo[pixelI - 10][pixelJ + offset1], true);
  		Color colorRight4 = new Color(imageInfo[pixelI + 20][pixelJ + offset1], true);
  		Color colorRight5 = new Color(imageInfo[pixelI - 20][pixelJ + offset1], true);
  		
  		double rightAverage = (double) (colorRight1.getRed() + colorRight2.getRed() + colorRight3.getRed() + colorRight4.getRed() + colorRight5.getRed()) / 5.0;

  		if(
  				(color1.getRed() + deeper2 < leftAverage)

  				|| (color1.getRed() + deeper2 < rightAverage)

  				) {
  			
  			ifBones = true;
  					
  		}

  		return ifBones;
  	}
      
    public static void initOffset3_0() {
    	
    	offset3_1 = new ArrayList<Integer>();  //Ë®Æ½
    	offset3_2 = new ArrayList<Integer>();  //ÊúÖ±

    	offset3_1.add(-5);
    	offset3_1.add(5);
    	
    	offset3_1.add(-10);
    	offset3_1.add(10);
    	
    	offset3_1.add(-20);
    	offset3_1.add(20);
    	
    	offset3_1.add(-30);
    	offset3_1.add(30);

    	offset3_2.add(-5);
    	offset3_2.add(5);
    	
    	offset3_2.add(-10);
    	offset3_2.add(10);
    	
    }
    
    public static void initOffset3_1() {
    	
    	offset3_1 = new ArrayList<Integer>();
    	offset3_2 = new ArrayList<Integer>();

    	offset3_1.add(1);

    	offset3_2.add(1);

    }

    public static boolean ifSatisfied5(int pixelI, int pixelJ, int[][] imageInfo, int type1) {
    	
    	int deeperNow = 0;
    	
    	if(type1 == 0) {
    		initOffset3_0();

    		deeperNow = 3;

    	}else if(type1 == 1) {
    		initOffset3_1();
    		deeperNow = 2;
    	}

		boolean ifBones = false;

		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;

		Color colorAround;
		double averageColor = 0;

		Color color1 = new Color(imageInfo[pixelI][pixelJ], true);
		
		for(int i = 0; i < offset3_1.size(); i ++) {
			for(int j = 0; j < offset3_2.size(); j ++) {

				if(pixelI + offset3_1.get(i) < 0 || pixelI + offset3_1.get(i) >= maxLines) {
					return false;
				}

				if(pixelJ + offset3_2.get(j) < 0 || pixelJ + offset3_2.get(j) >= maxColunms) {
					return false;
				}
				
				colorAround = new Color(imageInfo[pixelI + offset3_1.get(i)][pixelJ + offset3_2.get(j)], true);
				averageColor += colorAround.getRed();
			}
		}
		
		averageColor /= (offset3_1.size() * offset3_2.size());

		if(
				(color1.getRed() + deeperNow < averageColor)
				) {
			
			ifBones = true;
					
		}

		return ifBones;
	}

    public static boolean ifSatisfied6(int pixelI, int pixelJ, int[][] imageInfo, int type1) {
    	
    	int distanceNow = distance1;
    	int deepMinNow = deepMin1;
    	int deepMaxNow = deepMax1;
	
	   if(type1 == 1) {
		    distanceNow = distance2;
	    	deepMinNow = deepMin2;
	    	deepMaxNow = deepMax2;
	   }else if(type1 == 2) {
		    distanceNow = distance3;
	    	deepMinNow = deepMin3;
	    	deepMaxNow = deepMax3;
	   }
		
		boolean ifBones = false;

		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;

		Color colorAround;
		double allColor = 0;

		Color color1 = new Color(imageInfo[pixelI][pixelJ], true);

		if(color1.getRed() == 0xff) {
			return false;
		}
		
		for(int i = 0 - distanceNow; i < distanceNow; i ++) {
			for(int j = 0 - distanceNow; j < distanceNow; j ++) {

				if(pixelI + i < 0 || pixelI + i >= maxLines) {
					return false;
				}

				if(pixelJ + j < 0 || pixelJ + j >= maxColunms) {
					return false;
				}
				
				colorAround = new Color(imageInfo[pixelI + i][pixelJ + j], true);
				allColor += colorAround.getRed();
			}
		}

		if(allColor > deepMinNow && allColor < deepMaxNow) {
			
			ifBones = true;
					
		}

		return ifBones;
	}

    public static boolean ifSatisfied7(int pixelI, int pixelJ, int[][] imageInfo, int type1) {
		
		boolean ifBones = false;

		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;

		Color colorAround;
		double bonesAroundNum = 0;

		Color color1 = new Color(imageInfo[pixelI][pixelJ], true);

		if(color1.getRed() == 0xff) {
			return false;
		}
		
		for(int i = 0 - distance3_1; i < distance3_1; i ++) {
			for(int j = 0 - distance3_2; j < distance3_2; j ++) {

				if(pixelI + i < 0 || pixelI + i >= maxLines) {
					return false;
				}

				if(pixelJ + j < 0 || pixelJ + j >= maxColunms) {
					return false;
				}
				
				colorAround = new Color(imageInfo[pixelI + i][pixelJ + j], true);
				if (colorAround.getRed() != 0xff) {
					bonesAroundNum += 1;
				}
			}
		}

		if(bonesAroundNum > numberMin1 && bonesAroundNum < numberMax1) {
			
			ifBones = true;
					
		}

		return ifBones;
	}

    public static int[][] cutHead1(int[][] imageInfo, int type1) {
		
		boolean ifBones = false;
		
		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;
		
		Color colorNow;
		int colorTemp = 0;

		headColor = 0;
        for(int j = 10; j < 15; j ++) {
			
			for(int i = 0; i < maxLines; i ++) {
				
				colorNow = new Color(imageInfo[i][j], true);
				colorTemp += colorNow.getRed();
			}
		}
        
        headColor = (int) colorTemp / 5 - 255 * 5;

		Color colorAround;
		double colorPerLine = 0;
		
		ArrayList<Integer> cutLines = new ArrayList<Integer>();

		for(int j = 0; j < maxColunms; j ++) {
			
			for(int i = 0; i < maxLines; i ++) {
				
				colorAround = new Color(imageInfo[i][j], true);
				colorPerLine += colorAround.getRed();
			}
			
			if(colorPerLine < headColor) {
				cutLines.add(j);
			}
			
			colorPerLine = 0;
			
		}

		for(int j = 0; j < cutLines.size(); j ++) {
			for(int i = 0; i < maxLines; i ++) {
				imageInfo[i][j] = 0xffffffff;
			}
		}

		return imageInfo;
	}

    public static int[][] findLine1(int[][] imageInfo, int type1, int[][] colorData) {
		
		boolean ifBones = false;

		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;
		
		Color colorNow;
		int colorTemp = 0;
		
		int firstI = 0;
		int firstJ = 0;
		
		int flag1 = 0;

		headColor = 0;
        for(int j = 0; j < maxColunms && flag1 == 0; j ++) {
			
			for(int i = 0; i < maxLines && flag1 == 0; i ++) {
				
				colorNow = new Color(imageInfo[i][j], true);
				if(colorNow.getRed() != 0xff) {
					firstI = i;
					firstJ = j;
					flag1 = 1;
				}
			}
		}
        
        int nowI = firstI;
        
        for(int j = firstJ; j < maxColunms; j ++) {

			for(int i =  0 - exploreDistance1, k = 0; i < exploreDistance1; i ++, k ++) {
				
				if(nowI + exploreDistance.get(k) < 0 || nowI + exploreDistance.get(k) > maxLines) {
					continue;
				}
				
				colorNow = new Color(imageInfo[nowI + exploreDistance.get(k)][j], true);
				if(colorNow.getRed() != 0xff) {
					imageInfo[nowI + exploreDistance.get(k)][j] = MySpecialColors.MY_EDGE_COLOR;
					colorData[nowI + exploreDistance.get(k)][j] = MySpecialColors.MY_EDGE_COLOR;
					nowI = nowI + exploreDistance.get(k);
					break;
				}
				
			}
		}

		return colorData;
		
	}

    public static ArrayList<MyPoint> findLine2(int[][] imageInfo, int type1) {

    	ArrayList<MyPoint> edge1 = new ArrayList<MyPoint>();
		
		boolean ifBones = false;

		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;
		
		Color colorNow;
		int colorTemp = 0;
		
		int firstI = 0;
		int firstJ = 0;
		
		int flag1 = 0;

        for(int j = 0; j < maxColunms && flag1 == 0; j ++) {
			
			for(int i = 0; i < maxLines && flag1 == 0; i ++) {
				
				colorNow = new Color(imageInfo[i][j], true);
				if(colorNow.getRed() != 0xff) {

					firstI = i;
					firstJ = j;

					flag1 = 1;
				}
			}
		}
        
        int nowI = firstI;

        for(int j = firstJ, distanceChange = 1; j < maxColunms; j ++, distanceChange *= -1) {

			for(int i =  0 - exploreDistance1, k = 0; i < exploreDistance1; i ++, k ++) {

				if(nowI + exploreDistance.get(k) * distanceChange < 0 || nowI + exploreDistance.get(k) * distanceChange > maxLines) {
					continue;
				}

				colorNow = new Color(imageInfo[nowI + exploreDistance.get(k) * distanceChange][j], true);
				
				if(colorNow.getRed() != 0xff) {

					MyPoint myPoint1 = new MyPoint();
					myPoint1.setX(nowI + exploreDistance.get(k) * distanceChange);
					myPoint1.setY(j);

					edge1.add(myPoint1);
					
					nowI = nowI + exploreDistance.get(k) * distanceChange;
					break;
				}
				
			}
		}
        
		return edge1;
	}

    public static int[][] markEdge1(ArrayList<MyPoint> edge1, int[][] colorInfo){
    	
    	int[][] colorDataMarkedByEdge1 = new int[colorInfo.length][colorInfo[0].length];

    	for(int i = 0; i < colorInfo.length; i ++) {
    		for(int j = 0; j < colorInfo[0].length; j ++) {
    			colorDataMarkedByEdge1[i][j] = colorInfo[i][j];
    		}
    	}

    	for(int i = 0; i < edge1.size(); i ++) {
    		colorDataMarkedByEdge1[(int) edge1.get(i).getX()][(int) edge1.get(i).getY()] = MySpecialColors.MY_EDGE_COLOR;
    	}
    	
    	return colorDataMarkedByEdge1;
    }

    public static int[][] cutLeftLung1(int[][] imageInfo, int type1) {

		int maxLines = imageInfo.length;
		int maxColunms = imageInfo[0].length;

		if(leftLungPositon1 - leftLungRange1 < 0 || leftLungPositon1 + leftLungRange1 > maxColunms) {
			return imageInfo;
		}

		for(int j = leftLungPositon1 - leftLungRange1; j < leftLungPositon1 + leftLungRange1; j ++) {
			for(int i = 0; i < maxLines; i ++) {
				imageInfo[i][j] = 0xffffffff;
			}
		}
		
		return imageInfo;
	}

}
