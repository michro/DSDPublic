package algorithm.kai.imageProcess;

import java.util.ArrayList;

//以下两个需要自己导入，下载网址可见如下：
//https://commons.apache.org/proper/commons-math/download_math.cgi
//教程可见: https://blog.csdn.net/u010603342/article/details/41748957?utm_medium=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase&depth_1-utm_source=distribute.pc_relevant_download.none-task-blog-baidujs-1.nonecase
//注意解压之后有多个jar包，不能都导入，不然会出现NoClassDefFoundError
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import algorithm.kai.utilities.MyPoint;

public class PolynomialLines {

	ArrayList<Integer> divisionNum1;
	int highestPower1 = 3;

	public static ArrayList<Double> generatePolynomialLines(ArrayList<MyPoint> edge1, int highestPower){

		ArrayList<Double> parameterOfEdge1 = new ArrayList<Double>();
	  
		WeightedObservedPoints points = new WeightedObservedPoints();

		for(int i = 0; i < edge1.size(); i++) { 

			points.add(edge1.get(i).getX(), edge1.get(i).getY());
		}

		PolynomialCurveFitter fitter = PolynomialCurveFitter.create(highestPower);  

		double[] result = fitter.fit(points.toList());  

		for(int i = 0; i < result.length; i++) {
			parameterOfEdge1.add(result[i]);
		}
		
		return parameterOfEdge1;
		
	}

	void prepareDivisionNum1() {
		
		divisionNum1 = new ArrayList<Integer>();

		divisionNum1.add(3);
		divisionNum1.add(2);
		
		divisionNum1.add(1);
	}

	ArrayList<ArrayList<MyPoint>> divideEdge1(ArrayList<MyPoint> sortedEdge, int divisionNum){
		ArrayList<ArrayList<MyPoint>> dividedEdge1 = new ArrayList<ArrayList<MyPoint>>();

		for(int i = 0; i < divisionNum; i ++) {
			dividedEdge1.add(new ArrayList<MyPoint>());
		}

		int groupNum = sortedEdge.size() / divisionNum;
		
		for(int i = 0, tempIndex = 0; i < sortedEdge.size(); i ++) {
			
			tempIndex = i / groupNum;

			if(tempIndex < dividedEdge1.size()) {
				
				MyPoint pointTemp = new MyPoint();
				pointTemp.setX(sortedEdge.get(i).getX());
				pointTemp.setY(sortedEdge.get(i).getY());
				
				dividedEdge1.get(tempIndex).add(pointTemp);
			}

		}
		
		return dividedEdge1;
	}

	double generateCobb1(ArrayList<Double> parameter1, ArrayList<MyPoint> twoPoints) {
		
		double cobb1 = -1;

		ArrayList<Double> degrees1 = new ArrayList<Double>();

		for(double i = twoPoints.get(0).getX(); i < twoPoints.get(1).getX() + 1; i ++) {

			double slopeTemp = generateSlope(parameter1, i);

			double degreeTemp = generateProperDegreeBySlope(slopeTemp);
			
			degrees1.add(degreeTemp);
		}
		
		cobb1 =  generateCobbTemp1(degrees1);
		
		return cobb1;
		
	}

	double generateSlope(ArrayList<Double> parameter1, double xValue) {
		
		double slope1 = 0;

		for(int i = 0, coefficient1 = 0; i < parameter1.size(); i ++) {

			coefficient1 = i;
			
			double xValueNow = 1;

			for(int j = 0; j < i - 1; j ++) {
				xValueNow *= xValue;
			}
			
			slope1 += parameter1.get(i) * coefficient1 * xValueNow;
		}
		
		return slope1;
		
	}

	double generateProperDegreeBySlope(double slope1) {
		
		double degree1 = -1;
		
		degree1 = Math.toDegrees(Math.atan(slope1));
		
		if(degree1 < 0) {
			degree1 = 0 - degree1;
		}
		
		if(degree1 > 90) {
			degree1 = 180 - degree1;
		}

		return degree1;
		
	}

	double generateCobbTemp1(ArrayList<Double> degrees1) {
		
		double cobbTemp1 = 180;
		
		double leastDegree1 = 180;
		double leastDegree2 = 180;

		if(degrees1.size() == 0) {
			return -1;
		}
		
		if(degrees1.size() == 1) {
			leastDegree1 = degrees1.get(0);
			leastDegree2 = degrees1.get(0);
		}else {
			leastDegree1 = degrees1.get(0);
			leastDegree2 = degrees1.get(1);

			if(leastDegree2 < leastDegree1) {
				double temp  = leastDegree2;
				leastDegree2 = leastDegree1;
				leastDegree1 = temp;
			}
			
			for(int i = 2; i < degrees1.size(); i ++) {
				if(degrees1.get(i) <= leastDegree1) {
					leastDegree2 = leastDegree1;
					leastDegree1 = degrees1.get(i);
				}else {
					if(degrees1.get(i) < leastDegree2) {
						leastDegree2 = degrees1.get(i);
					}
				}
				
			}
		}
		
		cobbTemp1 = 180 - (leastDegree1 + leastDegree2);

		return cobbTemp1;
	}

    public ArrayList<Double> findPossibleCobb1(ArrayList<MyPoint> sortedEdge1){

    	prepareDivisionNum1();

    	ArrayList<Double> possibleCobbs = new ArrayList<Double>();
    	
    	for(int i = 0; i < divisionNum1.size(); i ++) {
    		
    		ArrayList<ArrayList<MyPoint>> dividedEdge1 = divideEdge1(sortedEdge1, divisionNum1.get(i));
    		
    		for(int j = 0; j < dividedEdge1.size(); j ++) {

    			ArrayList<MyPoint> twoPoints = new ArrayList<MyPoint>();
    			
    			ArrayList<MyPoint> thisGroup = dividedEdge1.get(j);
    			
    			twoPoints.add(new MyPoint(thisGroup.get(0)));
    			twoPoints.add(new MyPoint(thisGroup.get(thisGroup.size() - 1)));
    			
    			ArrayList<Double> parameter1 = generatePolynomialLines(thisGroup, highestPower1);
    			
    			possibleCobbs.add(generateCobb1(parameter1, twoPoints));
    		}
    	}
    	
    	return possibleCobbs;
    }

    public double findFinalCobb1(ArrayList<Double> possibleCobbs1) {
    	
    	double finalCobb = -1;

    	for(int i = 0; i <possibleCobbs1.size(); i ++) {

    		if(possibleCobbs1.get(i) > finalCobb && possibleCobbs1.get(i) < 180) {
    			finalCobb = possibleCobbs1.get(i);
    		}
    	}

    	return finalCobb;
    	
    }

}
