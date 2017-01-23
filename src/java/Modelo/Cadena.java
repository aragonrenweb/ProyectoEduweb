package Modelo;

import BasesDeDatos.PostgreSQL.PaisBD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Cadena {
	
	public static String reemplazarAsterisco(String cad){
		String aux = "";
		aux =cad.replace("*", "'");
		return aux;
	}
        	public static String reemplazarComilla(String cad){
		String aux = "";
		aux =cad.replace("'", "*");
		return aux;
	}
	public static boolean isNull(String cad){
			return (cad == null);
	}
	public static String[] quitarGuion(String cad){
		String [] aux = new String[3];
		String delimitador = "-";
		aux = cad.split(delimitador);
		return aux;
	}
	
    public static String Encriptar(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
}
    

    
    public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
}
    
    public static String getGenero(String genero){
        if(genero.equalsIgnoreCase("Hombre"))
            return "H";
        else if (genero.equalsIgnoreCase("Mujer")) 
            return "M";
        else if(genero.equalsIgnoreCase("أنثى"))
            return "M";
        else if(genero.equalsIgnoreCase("ذكر"))
            return "H";
        else if(genero.equalsIgnoreCase("Male"))
            return "H";
        else 
            return "F";
  
        
    }

    
    public static ArrayList<Ciudad> getCiudades(String cc_fips){
        ArrayList<Ciudad> c_aux = null;
      
        FileReader fr;
            try {
                  String cadena ;
              //  fr = new FileReader("/Users/shahadbawi/NetBeansProjects/WebApplication1/src/conf/ciudades.txt");
              fr = new FileReader("C:/Users/snaji/Documents/NetBeansProjects/WebApplication1/src/conf/ciudades.txt");
                 BufferedReader b = new BufferedReader(fr);
c_aux = new  ArrayList<Ciudad>();
Pais p = PaisBD.getPaisById(cc_fips);
        while ((cadena = b.readLine()) != null) {
            if(cadena.contains(cc_fips)){
                 String delimitadores = "\\t";
                    // separar los datos de una sola linea
                    String[] palabrasSeparadas = cadena.split(delimitadores);
                        for (String palabrasSeparada : palabrasSeparadas) {
                            System.out.println(palabrasSeparada);
                        }
               
                c_aux.add(new Ciudad(0, "", palabrasSeparadas[1], "", p));    
            }
            
        }
        b.close();
        fr.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Cadena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Cadena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Cadena.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        return c_aux;
    }
}
