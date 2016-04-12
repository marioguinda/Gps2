package app;

public class ConversorUTM {

	private static final double RADIANES = Math.PI / 180;
	private static final double R_ECUATORIAL = 6378137.0;
	private static final double EXCENTRICIDAD_2 = 0.00669437999013;

	static void covertirUTC(String sgradosNS, String sminutosNS, String sgradosWE, String sminutosWE, String slongitud,	String slatitud) {
		// Convertimos las coordenadas a float
		float gradosNS = Float.parseFloat(sgradosNS);
		float minutosNS = Float.parseFloat(sminutosNS);
		float gradosWE = Float.parseFloat(sgradosWE);
		float minutosWE = Float.parseFloat(sminutosWE);
		
		//System.out.println("Latitud:"+ gradosNS +"/ "+minutosNS);
		//System.out.println("Longitud:"+ gradosWE +"/ "+minutosWE);

		
		// Pasamos los grados y minutos a grados
		gradosNS = gradosNS + (minutosNS/60);
		gradosWE = gradosWE + (minutosWE/60);
		
		//System.out.println("Latitud:"+ gradosNS +"/ "+minutosNS);
		//System.out.println("Longitud:"+ gradosWE +"/ "+minutosWE);
		
		if (slongitud.equals("W")) {
			gradosWE = (float) ((-1.0) * gradosWE) ;
		}
		if (slongitud.equals("S")) {
			gradosNS = (float) ((-1.0) * gradosNS);
		}

		System.out.println("Latitud: " + gradosNS + "º");
		System.out.println("Longitud: " + gradosWE + "º");
		System.out.println("----------------------------------");
		// Pasamos a radianes
		double latRad = gradosNS * RADIANES;
		double longRad = gradosWE * RADIANES;

		double excentridad = EXCENTRICIDAD_2 / (1 - EXCENTRICIDAD_2);

		// Obetenemos el huso horario
		double huso = gradosWE / 6 + 31;

		// Obtenemos el meridiano central del huso
		int meridianoCentral = (int) huso * 6 - 183;

		// Diferencia entre nuestra longitud y la del meridiano central
		// (radianes)
		double difLong = longRad - meridianoCentral * RADIANES;

		// Parámetros
		double N = R_ECUATORIAL / (Math.sqrt(1 - (EXCENTRICIDAD_2 * Math.pow(Math.sin(latRad), 2.0))));
		double T = Math.pow(Math.tan(latRad), 2.0);
		double C = excentridad * Math.pow(Math.cos(latRad), 2.0);
		double A = Math.cos(latRad) * difLong;
		double M = R_ECUATORIAL * ((1 - EXCENTRICIDAD_2 / 4 - 3 * Math.pow(EXCENTRICIDAD_2, 2.0) / 64
				- 5 * Math.pow(EXCENTRICIDAD_2, 3.0) / 256) * latRad
				- (3 * EXCENTRICIDAD_2 / 8 + 3 * Math.pow(EXCENTRICIDAD_2, 2.0) / 32
						+ 45 * Math.pow(EXCENTRICIDAD_2, 3.0) / 1024) * Math.sin(2 * latRad)
				+ (15 * Math.pow(EXCENTRICIDAD_2, 2.0) / 256 + 45 * Math.pow(EXCENTRICIDAD_2, 3.0) / 1024)
						* Math.sin(4 * latRad)
				- (35 * Math.pow(EXCENTRICIDAD_2, 3.0) / 3072) * Math.sin(6 * latRad));

		// Cálculo de la cuadrícula
		double Xcuad = 0.9996
				* N
				* (A + (1 - T + C) * Math.pow(A, 3.0) / 6 + (5 - 18 * T
						+ Math.pow(T, 2.0) + 72 * C - 58 * EXCENTRICIDAD_2)
						* Math.pow(A, 5.0) / 120) + 500000;
		double Ycuad = 0.9996 * (M + N
				* Math.tan(latRad)
				* (Math.pow(A, 2.0) / 2
						+ (5 - T + 9 * C + 4 * Math.pow(C, 2.0))
						* Math.pow(A, 4.0) / 24 + (61 - 58 * T
						+ Math.pow(T, 2.0) + 600 * C - 330 * EXCENTRICIDAD_2)
						* Math.pow(A, 6.0) / 720));

		System.out.println("Coord. X UTM: "+Xcuad);
		System.out.println("Coord. Y UTM: "+Ycuad);
		System.out.println("Huso: "+huso);
		System.out.println("Hemisferio: "+slatitud);
		System.out.println("==================================");
	}

}
