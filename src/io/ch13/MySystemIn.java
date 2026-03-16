package io.ch13;

import java.io.IOException;

/**
 * 표준 입출력이란?
 * 자바에서 표준 입출력은 프로그램과 사용자간의 기본적인 데이터 교환 방법을 제공 합니다.
 *
 *
 */

public class MySystemIn {

    public static void main(String[] args) {

        System.out.println("알파벳 하나를 쓰고 엔터키를 누르세요.");
        int i;
        try {
            i = System.in.read();
            // 주의 : 한글은 처리불가 -> 깨진 문자난 의미없는 정수값이 출력됨
            System.out.println("----------------------------");
            System.out.println(i); // 정수로 들어감
            System.out.println((char)i); // 문자로 바꿔 출력
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
