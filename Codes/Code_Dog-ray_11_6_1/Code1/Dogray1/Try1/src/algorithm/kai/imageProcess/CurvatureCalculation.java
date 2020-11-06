package algorithm.kai.imageProcess;

import java.util.ArrayList;

import algorithm.kai.utilities.MyPoint;

public class CurvatureCalculation {
	
	ArrayList<Integer> divisionNum1;

	public static double curvatureByThreePoints(ArrayList<MyPoint> points) {
		
		double myCurvature = 0;
		
		MyPoint point0 = points.get(0);
		MyPoint point1 = points.get(1);
		MyPoint point2 = points.get(2);

		if(point0.getX() == point1.getX() && point0.getX() == point2.getX()) {
			myCurvature = 0;
			return myCurvature;
		}
		
		double dis1;
		double dis2;
		double dis3;
		
        double cosA;
        double sinA;
        
        double dis;

        dis1 = Math.sqrt((point0.getX() - point1.getX()) * (point0.getX() - point1.getX()) + (point0.getY() - point1.getY()) * (point0.getY() - point1.getY()));
        dis2 = Math.sqrt((point0.getX() - point2.getX()) * (point0.getX() - point2.getX()) + (point0.getY() - point2.getY()) * (point0.getY() - point2.getY()));
        dis3 = Math.sqrt((point1.getX() - point2.getX()) * (point1.getX() - point2.getX()) + (point1.getY() - point2.getY()) * (point1.getY() - point2.getY()));
        
        dis = dis1 * dis1 + dis3 * dis3 - dis2 * dis2;

        cosA = dis / (2 * dis1 * dis3);

        sinA = Math.sqrt(1 - cosA * cosA);

        myCurvature = 0.5 * dis2 / sinA;

        myCurvature = 1 / myCurvature;
		
		return myCurvature;
	}

	public static double calculateDiatance1(MyPoint point1, MyPoint point2) {
		
		double distance = 0;
		
		double xDistance = Math.abs(point1.getX() - point2.getX());
		double yDistance = Math.abs(point1.getY() - point2.getY());
		
		distance = xDistance * xDistance + yDistance * yDistance;
		
		distance = Math.sqrt(distance);
		
		return distance;
		
	}

	public static double CircleDistanceByThreePoints(ArrayList<MyPoint> points) {
			
			double myCircleDistance = 0;
			
			MyPoint point0 = points.get(0);
			MyPoint point1 = points.get(1);
			MyPoint point2 = points.get(2);
			
			double distance1 = calculateDiatance1(point0, point1);
			double distance2 = calculateDiatance1(point1, point2);
			
			myCircleDistance = distance1 + distance2;
		
		return myCircleDistance;
	}

	void prepareDivisionNum1() {
		
		divisionNum1 = new ArrayList<Integer>();

		divisionNum1.add(14);
		divisionNum1.add(13);
		divisionNum1.add(12);
		divisionNum1.add(11);
		divisionNum1.add(10);
		
		divisionNum1.add(6);

		divisionNum1.add(4);
		divisionNum1.add(3);

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

	public ArrayList<MyPoint> sortEdge1(ArrayList<MyPoint> edge1){
		ArrayList<MyPoint> sortedEdge1 = new ArrayList<MyPoint>();

		for(int i = 0; i < edge1.size(); i ++) {
			
			MyPoint pointTemp = new MyPoint();
			pointTemp.setX(edge1.get(i).getX());
			pointTemp.setY(edge1.get(i).getY());
			
			sortedEdge1.add(pointTemp);
		}

		for(int i = 0; i < sortedEdge1.size() - 1; i ++) {
			for(int j = 0; j < sortedEdge1.size() - i - 1; j ++) {
				
				if(sortedEdge1.get(j).getY() > sortedEdge1.get(j + 1).getY()) {
					
					MyPoint tempPoint = new MyPoint();
					
					tempPoint.setX(sortedEdge1.get(j).getX());
					tempPoint.setY(sortedEdge1.get(j).getY());
					
					sortedEdge1.set(j, sortedEdge1.get(j + 1));
					
					sortedEdge1.set(j + 1, tempPoint);
				}
			}
		}
		
		return sortedEdge1;
	}

    public ArrayList<Double> findPossibleCurvatures1(ArrayList<MyPoint> sortedEdge1){

    	prepareDivisionNum1();

    	ArrayList<Double> possibleCurvatures = new ArrayList<Double>();
    	
    	for(int i = 0; i < divisionNum1.size(); i ++) {
    		
    		ArrayList<ArrayList<MyPoint>> dividedEdge1 = divideEdge1(sortedEdge1, divisionNum1.get(i));
    		
    		for(int j = 0; j < dividedEdge1.size(); j ++) {
    			
    			ArrayList<MyPoint> threePoints = new ArrayList<MyPoint>();
    			ArrayList<MyPoint> thisGroup = dividedEdge1.get(j);
    			
    			threePoints.add(new MyPoint(thisGroup.get(0)));
    			threePoints.add(new MyPoint(thisGroup.get((int) thisGroup.size() / 2)));
    			threePoints.add(new MyPoint(thisGroup.get(thisGroup.size() - 1)));
    			
    			possibleCurvatures.add(curvatureByThreePoints(threePoints));
    		}
    	}
    	
    	return possibleCurvatures;
    }

      public ArrayList<Double> findPossibleCircleDegree1(ArrayList<MyPoint> sortedEdge1){

      	prepareDivisionNum1();

      	ArrayList<Double> possibleCircleDegree1 = new ArrayList<Double>();
      	
      	for(int i = 0; i < divisionNum1.size(); i ++) {
      		
      		ArrayList<ArrayList<MyPoint>> dividedEdge1 = divideEdge1(sortedEdge1, divisionNum1.get(i));
      		
      		for(int j = 0; j < dividedEdge1.size(); j ++) {
      			
      			ArrayList<MyPoint> threePoints = new ArrayList<MyPoint>();
      			ArrayList<MyPoint> thisGroup = dividedEdge1.get(j);
      			
      			threePoints.add(new MyPoint(thisGroup.get(0)));
      			threePoints.add(new MyPoint(thisGroup.get((int) thisGroup.size() / 2)));
      			threePoints.add(new MyPoint(thisGroup.get(thisGroup.size() - 1)));

      			possibleCircleDegree1.add(CircleDistanceByThreePoints(threePoints) / curvatureByThreePoints(threePoints));
      		}
      	}
      	
      	return possibleCircleDegree1;
      }

      public double findFinalCobb2(ArrayList<Double> possibleCircleDegree1) {
      	
      	double finalCobb = -1;

      	for(int i = 0; i < possibleCircleDegree1.size(); i ++) {
	
      		if(possibleCircleDegree1.get(i) > finalCobb && possibleCircleDegree1.get(i) < 90) {
      			finalCobb = possibleCircleDegree1.get(i);
      		}
      	}

      	finalCobb = calculateCobbFromCircleDegree1(finalCobb);
      	
      	return finalCobb;
      	
      }

    public double findFinalCobb1(ArrayList<Double> possibleCurvatures1) {
    	
    	double finalCobb = -1;

    	for(int i = 0; i < possibleCurvatures1.size(); i ++) {
	
    		if(possibleCurvatures1.get(i) > finalCobb) {
    			finalCobb = possibleCurvatures1.get(i);
    		}
    	}

    	finalCobb = calculateCobbFromCurvature2(finalCobb);
    	
    	return finalCobb;
    	
    }

    double calculateCobbFromCurvature1(double curvature1) {
    	double cobb1 = -1;
    	
    	cobb1 = 180 - (curvature1 * 360);
    	
    	return cobb1;
    }

    double calculateCobbFromCurvature2(double curvature1) {
    	double cobb1 = -1;
    	
    	cobb1 = curvature1 * 180;
    	
    	return cobb1;
    }

    double calculateCobbFromCurvature3(double curvature1) {
    	double cobb1 = -1;
    	
    	cobb1 = 90 - curvature1 * 180;
    	
    	return cobb1;
    }

    double calculateCobbFromCircleDegree1(double circleDegree1) {
    	double cobb1 = -1;
    	
    	cobb1 = circleDegree1;
    	
    	return cobb1;
    }

    double calculateCobbFromCircleDegree2(double circleDegree1) {
    	double cobb1 = -1;
    	
    	cobb1 = 90 - circleDegree1;
    	
    	return cobb1;
    }
	
}
