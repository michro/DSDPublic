package algorithm.kai.utilities;

//创造一个自己的点
public class MyPoint {
	
	//选择使用了double类型，而非int类型
	//这是因为在未来的各种计算/拟合中，我感觉double总是更加方便（无论是计算，还是传参的时候）
	double x;
	double y;
	
	public MyPoint() {
		x = 0;
		y = 0;
	}
	
	public MyPoint(MyPoint point1) {
		this.x = point1.x;
		this.y = point1.y;
	}
	
	public MyPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

}
