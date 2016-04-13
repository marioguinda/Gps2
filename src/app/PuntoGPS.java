package app;

public class PuntoGPS {
	
	public static double coordX;
	public static double coordY;

	public void setGPSPoint(double X, double Y){
		coordX = X;
		coordY = Y;
	}

	public double getX() {
		return coordX;
	}

	public double getY() {
		return coordY;
	}
}
