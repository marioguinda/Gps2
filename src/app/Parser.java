package app;

public class Parser {

	public static String patron = "";
	public static String datos = "";
	public static int comas = 0;
	public static boolean escribir = false; 
	public static void imprimir(String s) {

		if(s.equals("$")){
			if(patron.equals("")){
				patron = patron + s;
				
			}
		}
		if(s.equals("G")){
			if(patron.equals("$")){
				patron = patron + s;
				
			}
		}
		if(s.equals("P")){
			if(patron.equals("$G")){
				patron = patron + s;
				
			}
		}
		if(s.equals("G")){
			if(patron.equals("$GP")){
				patron = patron + s;
				
			}
		}
		if(s.equals("G")){
			if(patron.equals("$GPG")){
				patron = patron + s;
				
			}
		}
		if(s.equals("A")){
			if(patron.equals("$GPGG")){
				patron = patron + s;
				
			}
			 else {
				patron = "";
			}
		} 
		if(s.equals(",")){
			if(patron.equals("$GPGGA")){
				if(!escribir){
					escribir = true;
					datos = datos + s;

				}
				comas ++;

			}
		}
		if(!s.equals("\r")){
			if(patron.equals("$GPGGA")){
				if(escribir){
					datos = datos + s;

				}
			}
		} else{
			datos = "";
			patron = "";
			escribir = false;
			comas = 0;
		}
		if (comas == 11){
			formatear(datos);
		}
	}
	
	private static void formatear(String s) {
		//,,110036,4024.1605,N,00342.5627,W,1,10,3.3,620.1,M,
		//s = ",,110036,4024.1605,N,00342.5627,W,1,10,3.3,620.1,M,";
		String[] campos = s.split(",");
				
		String latitudGrados = campos[3].substring(0, 2);
		String latitudMin = campos[3].substring(2);
		String NS = campos[4];
		
		String longitudGrados = campos[5].substring(0, 3);
		String longitudMin = campos[5].substring(3);
		String EO = campos[6];
		
		ConversorUTM.covertirUTC(latitudGrados, latitudMin, longitudGrados, longitudMin, EO, NS);
	}

}
