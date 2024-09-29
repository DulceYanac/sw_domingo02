package com.semana02.ejercicio06;

import java.io.File;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;


public class ClienteZipCatalogo {

     public static void main(String[] args) {
        new ClienteZipCatalogo();
    }

    private final int PORT = 13;
    private final String HOST = "localhost";


    public ClienteZipCatalogo(){
        //PASO 1 Generar los archivos
        System.out.println("1 Generando archivos JSON, XML y ZIP");
        GeneratetZipCatalogo generateZipCatalogo = new GeneratetZipCatalogo();
        generateZipCatalogo.procesar();
        

        //PASO 2 Crear el Socket CLiente
        try {
            System.out.println("2 Client started");
            Socket socket = new Socket(HOST, PORT);
            System.out.println("3 Connected to server");

        //PASO 3 Enviar el archivo ZIP

            //Fllujos para enviar y recibir archivos
            File file = new File("C:/cliente/catalogoComprimido.zip");
            FileInputStream fis = new FileInputStream(file);
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            
            //enviar archivo
            int byteLeidos = 0;
            while ((byteLeidos = fis.read()) != -1) {
                salida.write(byteLeidos);
            }
            System.out.println("3 File sent");
            fis.close();
            salida.close();
            System.out.println("4 Client finished");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }    

    }

}