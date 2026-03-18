package io.ch17;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 보조기반 스트림에 대해 알아보자
 * 기반 스트림이 있어야 사용할수 있다
 */

public class ByteBufferedKeyboardConsole {

    public static void main(String[] args) {
        // System.in
        // System.out


        try (BufferedInputStream bis = new BufferedInputStream(System.in); // 보조 와 기반 스트림 생산  읽는거
             BufferedOutputStream bos = new BufferedOutputStream(System.out) // 보조 와 기반 스트림 생산  쓰는거
        ) {
            byte[] buffer = new byte[1024];// 한번에 1024 바이트씩 읽을버퍼
            int bytesRead;

            while ( (bytesRead = bis.read(buffer)) != -1 ){
                bos.write(buffer,0,bytesRead); // 읽은 만큼만 씀
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
