package io.my_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {

    public static void main(String[] args) {

        try (FileOutputStream outFileOmg = new FileOutputStream("omg", true)) {

            String data = "\nWe're thieves in a world that don't want us no more.";
            outFileOmg.write(data.getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream omgRead = new FileInputStream("omg")) {

            int readData;

            while( (readData = omgRead.read()) != -1){ // readData.read(); 가 한번 실행될때 마다 1글자씩 출력후 다음 글자로 덮어씌워짐
                System.out.print((char) readData);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
