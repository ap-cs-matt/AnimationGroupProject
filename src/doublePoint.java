package src;

public class doublePoint {

	private Double x, y;

	public doublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getIntX() {
		return x.intValue();
	}

	public int getIntY() {
		return y.intValue();
	}

	////set methods
	public void setPoints(double x, double y){
		this.x = x;
		this.y = y;
	}
}