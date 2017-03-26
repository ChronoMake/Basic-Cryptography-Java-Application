/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_sys;


import javax.crypto.Cipher;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
import java.io.File;
import javax.imageio.*;
import java.awt.image.*;
import java.io.FileInputStream;
import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.security.Key;


import javax.crypto.BadPaddingException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



/**
 *
 * @author varun
 */
public class AES_Image 
{

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    static Key secretKey =null;
    static Cipher cipher=null;
    static byte[] pixels=null;
    static byte[] outputBytes=null;
    static BufferedImage bufferedImage=null;
    
    static int height;
    static int width;
    
    static File encryptedFile = new File("document.encrypted");
   static File decryptedFile = new File("document.decrypted");

    public static void encrypt(String key,File outputFile) throws CryptoException {
        AES_Image a = new AES_Image();
        byte[] image_data=null;
        String ImageName = "yin-yang-vector_642491.jpg";
        try {
            image_data = a.extractBytes(ImageName);
        } catch (IOException err) {
            System.out.println(err);
        }
        doCrypto(Cipher.ENCRYPT_MODE, key, image_data,outputFile);
    }

    public static void decrypt( String key, File inputFile, File outputFile)throws CryptoException 
    {
        byte[] inputBytes=null;
        try{
            FileInputStream inputStream = new FileInputStream(inputFile);
            inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
        }
        catch(IOException ex){System.out.println(ex);}
            doCrypto1( Cipher.DECRYPT_MODE, inputBytes, outputFile);
    }

    byte[] extractBytes(String ImageName) throws IOException 
    {
        // open image
        File imgPath = new File(ImageName);
        bufferedImage = ImageIO.read(imgPath);
        height=bufferedImage.getHeight();
        width=bufferedImage.getWidth();
        
       // pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();    
       			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", baos);
			baos.flush();
			pixels = baos.toByteArray();
			baos.close(); 
       
       return (pixels);
    }

    private static void doCrypto(int cipherMode, String key, byte[] img_data,File outputFile) throws CryptoException 
    {
        try {
            secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            byte[] inputBytes = img_data;

            outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

    public static void main(String Args[])
    {
        System.out.println("FuckYou");


             try{
                 SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
                 String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
                 encrypt(encodedKey,encryptedFile);
                 decrypt(encodedKey,encryptedFile,decryptedFile);
                 
                 InputStream in = new ByteArrayInputStream(outputBytes);
                 BufferedImage buff_image= ImageIO.read(in);

                 ImageIO.write(buff_image, "jpg", new File("freshShit"));
                 

                /*
                 WritableRaster raster = (WritableRaster) buff_image.getData();
                 byte[] byteArray = pixels;
                 int[] intArray = new int[byteArray.length];
                 for (int i = 0; i < byteArray.length; intArray[i] = byteArray[i++]);
                 raster.setPixels(0,0,(width-10),(height-10),intArray);
                */ 
                if(buff_image.equals(bufferedImage))System.out.println("asdasdasdasd");
                 File outputfile = new File("image.jpg");
                 ImageIO.write(buff_image, "jpg", outputfile);


             }
             catch(Exception ex){System.out.println(ex);}
    }
    
    
    
     private static void doCrypto1(int cipherMode, byte[] inputBytes, File outputFile) throws CryptoException {
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
            outputBytes = cipher.doFinal(inputBytes);
            //pixels=outputBytes;
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            outputStream.close();
             
        } catch ( NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
/*    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) 
    {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
*/

}

class CryptoException2 extends Exception 
{

    public CryptoException2() {
    }

    public CryptoException2(String message, Throwable throwable) {
        super(message, throwable);
    }
}
