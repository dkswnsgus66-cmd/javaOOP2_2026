package io.ch17_1.array;

import java.util.AbstractList;
import java.util.ArrayList;

public class ArrayListEx2 {

    public static void main(String[] args) {
        // 정수 ,실수, boolean, 사용자 정의 객체를 담을수 있는 AttayList를 각각 만들어서 사용해 보기


        ArrayList<Integer> intList = new ArrayList<>(); //  <> 안에는 클래스 이름인 Boolean등 을 넣어야 한다
        intList.add(10);
        intList.add(20);
        intList.add(30);
        System.out.println("정수리스트" + intList); // 내부에서 toString 을 자동으로 해준다
        // 실수를 담는 리스트
        ArrayList<Double> doubles = new ArrayList<Double>();
        doubles.add(1.0);
        doubles.add(2.0);
        doubles.add(3.0);
        System.out.println(doubles);
        // 3. Boolean
        ArrayList<Boolean> booleans = new ArrayList<Boolean>();
        booleans.add(true);
        booleans.add(false);
        booleans.add(false);
        booleans.add(true);

        // 사용자 정의를 n담는 리스트
        ArrayList<Book2> book2List = new ArrayList<Book2>();
        book2List.add(new Book2("자바 책")); // 객체가 들어와야한다
        book2List.add(new Book2("RDBMS"));
        //book2List.get(0) ==> 주소값.title
        System.out.println(book2List.get(0).title);
        System.out.println(book2List.get(1).title);
        try {
            System.out.println(book2List.get(2).title);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("프로그램 정상 종료");


    } // end of main



} // end of class
class Book2 {

    String title;

    public Book2(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" + "title='" + title + '}';
    }


}
