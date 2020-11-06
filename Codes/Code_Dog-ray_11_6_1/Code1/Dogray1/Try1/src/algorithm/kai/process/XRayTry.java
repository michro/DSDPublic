package algorithm.kai.process;

import java.io.IOException;
import java.util.ArrayList;

import algorithm.kai.fileProcess.FileProcess;
import algorithm.kai.imageProcess.CurvatureCalculation;
import algorithm.kai.imageProcess.ImageProcess;
import algorithm.kai.imageProcess.PolynomialLines;
import algorithm.kai.imageProcess.StaticThingsForImageProcessing;
import algorithm.kai.utilities.MyPoint;

public class XRayTry {
	
	boolean ifProcessSuccessfully;
	String imagePath;

	int[][] colorDataOrigin;

	int[][] colorDataWithoutBones;

	ArrayList<MyPoint> edge1;

	int[][] colorDataOriginMarkedByEdge1;

	int[][] colorDataWithoutBonesMarkedByEdge1;

	ArrayList<MyPoint> sortedEdge1;

	ArrayList<Double> possibleCurvatures1;

	ArrayList<Double> possibleCircleDegree1;

	ArrayList<Double> possibleCobbs1;

	double finalCobb1;

	double finalCobb2;

	double finalCobb3;
	
	public boolean algorithmOfDogRayTeam1() throws IOException {

		ifProcessSuccessfully = true;

		ImageProcess imageProcess1 = new ImageProcess(imagePath);

		colorDataOrigin = imageProcess1.haveRGBInIntMatrix();
		
		FileProcess fileProcess1 = new FileProcess();
	
		StaticThingsForImageProcessing staticThingsForImageProcessing1 = new StaticThingsForImageProcessing();

		colorDataWithoutBones = imageProcess1.removeNonBones4(colorDataOrigin);

		edge1 = StaticThingsForImageProcessing.findLine2(colorDataWithoutBones, 0);

		colorDataOriginMarkedByEdge1 = StaticThingsForImageProcessing.markEdge1(edge1, colorDataOrigin);

		colorDataWithoutBonesMarkedByEdge1 = StaticThingsForImageProcessing.markEdge1(edge1, colorDataWithoutBones);

//		imageProcess1.createImage(colorDataOriginMarkedByEdge1);

//		imageProcess1.createImage(colorDataWithoutBonesMarkedByEdge1);

		CurvatureCalculation curvatureCalculation1 = new CurvatureCalculation();
		sortedEdge1 = curvatureCalculation1.sortEdge1(edge1);

		possibleCurvatures1 = curvatureCalculation1.findPossibleCurvatures1(sortedEdge1);

		possibleCircleDegree1 = curvatureCalculation1.findPossibleCircleDegree1(sortedEdge1);
		
		finalCobb1 = curvatureCalculation1.findFinalCobb1(possibleCurvatures1);

		finalCobb2 = curvatureCalculation1.findFinalCobb2(possibleCircleDegree1);

		PolynomialLines polynomialLines1 = new PolynomialLines();
		possibleCobbs1 = polynomialLines1.findPossibleCobb1(sortedEdge1);

		finalCobb3 = polynomialLines1.findFinalCobb1(possibleCobbs1);

		return ifProcessSuccessfully;
		
	}

	public static void main(String[] args) throws IOException {
		
		XRayTry xRayTry1 = new XRayTry();
		
		xRayTry1.setImagePath("src/someImages/imagesForTrying/ImageOfChangXinXin.png");
		
		xRayTry1.algorithmOfDogRayTeam1();

		System.out.println("\nHello, DSD Class!");

	}

	public boolean isIfProcessSuccessfully() {
		return ifProcessSuccessfully;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public double getFinalCobb1() {
		return finalCobb1;
	}
	
	public double getFinalCobb2() {
		return finalCobb2;
	}
	
	public double getFinalCobb3() {
		return finalCobb3;
	}
	

}
