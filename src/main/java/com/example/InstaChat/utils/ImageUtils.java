package com.example.InstaChat.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    public static byte[] compressImage(byte[] data){
        Deflater deflater=new Deflater();
        System.out.println("log for uplad 1");
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outPutStream=new ByteArrayOutputStream(data.length);
        byte[] tmp=new byte[4*1024];
        while(!deflater.finished()){
            int size=deflater.deflate(tmp);
            outPutStream.write(tmp,0,size);
        }

        System.out.println("log for uplad 4");
        try{
            outPutStream.close();
        }catch(Exception e){

        }
        return outPutStream.toByteArray();
    }
    public static byte[] decompressImage(byte[] data){
        Inflater inflater=new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outPutStream=new ByteArrayOutputStream(data.length);
        byte[] tmp=new byte[4*1024];
        try{
            while(!inflater.finished()){
                int count=inflater.inflate(tmp);
                outPutStream.write(tmp,0,count);
            }
            outPutStream.close();
        }catch(Exception e){

        }
        return outPutStream.toByteArray();
    }
}
