package collection.set;

import java.util.*;

public class LottoGame {


    public static void main(String[] args) {
        // 로또 번호를 오름차순으로 정렬해라

        Set<Integer> lotto = new HashSet<>();
        Random random = new Random();

        // 6개가 될때 까지 계속 추가

        while (lotto.size() < 6){
            int number = random.nextInt(45) + 1; // 1 ~ 45
            lotto.add(number);

        }

        // ArrayList 생성자 안에 set 계열을 넣으면 자동으로 ArrayList 객체를 생성해 준다
        List<Integer> lottoNumber = new ArrayList<>(lotto);
        Collections.sort(lottoNumber);
        System.out.println("오름 차순 로또 번호: " + lottoNumber);

//        System.out.println("이번주 로또번호 : " + lotto);
//        System.out.println("총 " + lotto.size());

        // 로또번호를 오름차수능로 정렬하시오
        // 배열이나 list배열은 순서가 있음

    }


}
