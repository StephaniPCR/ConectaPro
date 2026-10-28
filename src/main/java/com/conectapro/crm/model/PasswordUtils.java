package com.conectapro.crm.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*Validación y generación de hashes seguros de contraseñas*/

public class PasswordUtils{
    private static final int MIN_LENGTH = 8;
    private static final int MAXIMO_LENGTH = 12;


    public static void validarLongitud(String plainTextPassword){
        if (plainTextPassword == null){
            throw new IllegalArgumentException("La contraseña no puede ser nula.");
        }
        int longitud = plainTextPassword.length();
        if (longitud < MIN_LENGTH || longitud > MAXIMO_LENGTH) {
            throw new IllegalArgumentException (String.format("La contraseña debe tener entre %d y %d caracteres. Valor actual %d.",
                MIN_LENGTH, MAXIMO_LENGTH, longitud)
                );
        }
    }

    public static String generarHash(String plainTextPassword) {
        //1.Validamos longitud.
        validarLongitud(plainTextPassword);

        try {
            //2. Obtenemos instancia del algoritmo SHA-256
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(plainTextPassword.getBytes(StandardCharsets.UTF_8));

            // 3. Convertir el array de bytes a una cadena Hexadecimal
            StringBuilder hexString = new StringBuilder();
            for(byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append('0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al inicializar el algoritmo de encriptación SHA-256", e);
        }
    }
}
