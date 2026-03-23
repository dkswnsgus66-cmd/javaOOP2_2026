package collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MoveMaking {

    public static void main(String[] args) {

        List<String> moves = new Vector<>();

        moves.add("범죄도시4 - ****");
        moves.add("기생충 - *****");
        moves.add("올드보이 - ****");
        moves.add("태권브이 - ***");
        System.out.println("=== 전체 영화 목록 ===");
        for (int i = 0; i < moves.size(); i++){

            System.out.println(i+ ". " + moves.get(i));

        }
        System.out.println("\n 총" + moves.size() + "편");
        // 올드보이 삭제
        moves.remove("올드보이 - ****");
        System.out.println("\n 삭제후" + moves.size() + "편");
//        moves.remove(1);
        System.out.println("\n 삭제후" + moves.size() + "편");
        // 기생충이 있는지 확인
        System.out.println("기생충 존재 여부 확인 " + moves.contains("기생충 - *****"));
    } // end of main

} // end of class
