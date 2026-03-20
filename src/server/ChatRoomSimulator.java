package server;

import java.util.Vector;

public class ChatRoomSimulator {


    // 공유자원
    private static Vector<String> userList = new Vector<>(); // 쓰레드 쓸때 안전한 ArrayList<>이다

    public static void main(String[] args) throws InterruptedException {
        // ArrayList와 사용법 동일

        Vector<String> list = new Vector<>();

//        list.add("철수");
//        list.add("영희");
//        list.remove("영희");
//        list.get(0);
//        list.size();
//        list.contains("영희");
//
//        for (String name : list){
//            System.out.println(name);
//        }

        System.out.println("===== 채팅방 접속자 시뮬레이션 =====");



        // 3명이 동시에 접속
        // 람다 표현식이다 new Runnable 과 동일
//        Thread t1 = new Thread(() -> {});

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userList.add("철수");
                System.out.println("[접속] 철수 | 현재 " + userList.size() + "명");

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                userList.add("영희");
                System.out.println("[접속] 영희 | 현재 " + userList.size() + "명");

            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                userList.add("민준");
                System.out.println("[접속] 민준 | 현재 " + userList.size() + "명");

            }
        });
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("최종 접속자 : " + userList);
        // 영희 퇴장
        userList.remove("영희");
        System.out.println("영희 퇴장후 : " +  userList);



    } // end of main
} // end of class
