package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx {

    public static void main(String[] args) {
        // ArrayList 는 배열에 불편함을 해결해주는 클래스 이다.
        ArrayList<String> list = new ArrayList<>(); // 배열처럼 미리 크기를 지정할 필요가 없다.

        // 추가 하는 기능
        list.add("사과"); // 내부적으로 [0] 번째 인덱스에 자동저장
        list.add("바나나"); // 내부적으로 [1] 번째 인덱스에 자동저장
        list.add("포도"); // 내부적으로 [2] 번째 인덱스에 자동저장

        // 조회하는 기능
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
//        System.out.println(list.get(100)); --> 오류발생
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        // 삭제하는 기능
//        list.remove("바나나");
//        list.remove("사과");
//        list.remove("포도111111"); // 없는 데이터라면 그냥 무시됨
//        list.remove("포도");
//        list.removeAll();
        list.clear(); // 전체삭제

        // 편의기능
        System.out.println("현재 ArrayList의 길이: "+list.size());
        System.out.println(list.contains("사과"));
        System.out.println(list.isEmpty());






    }// end of main

}
