package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapEx {

    public static void main(String[] args) {

        // 학생이름 , 점수
        Map<String,Integer> scores = new HashMap<>();

        // 추가 (put)
        scores.put("철수",90);
        scores.put("영희",85);
        scores.put("민준",92);

        // 조회 (get)
        System.out.println(scores.get("철수")); // 90 - 키값으로 접근하면 value 가 나옴
        System.out.println(scores.get("없는값")); // null

        // 포함여부
        System.out.println(scores.containsKey("영희")); // 키 값이 존재한다면 true
        System.out.println(scores.containsValue(85)); // 있는 값 true
        System.out.println(scores.containsValue(10)); // 없는값 false

        // 삭제
        scores.remove("민준");

        // 크기
        scores.size();

        // put 은 덮어쓰기도 합니다
        scores.put("철수",0); //덮어 쓰기 됨

        System.out.println(">>> : "+scores.keySet());
        System.out.println(scores);
        for (String name : scores.keySet()){ // 모든 key를 가져온다 Set으로 변환해서
            System.out.println(name + ": "+scores.get(name) + "점수");
        }

    } // end of main

} // end of class
