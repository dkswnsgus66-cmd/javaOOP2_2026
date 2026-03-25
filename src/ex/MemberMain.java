package ex;

import java.util.*;

public class MemberMain {

    /**
     * === 회원 관리 시스템 ===
     * 전체 회원: 0명
     * 선택 : 1
     * 이름  : 김철수
     * 이메일 : chulsu@email.com
     * 나이  : 25
     * [완료] 회원 가입: 김철수 (ID: 1)
     *
     * 선택 : 1
     * 이름  : 이영희
     * 이메일 : chulsu@email.com  ← 중복 이메일
     * [오류] 이미 사용 중인 이메일입니다: chulsu@email.com
     *
     * 선택 : 1
     * 이름  : 이영희
     * 이메일 : younghee@email.com
     * 나이  : 23
     * [완료] 회원 가입: 이영희 (ID: 2)
     *
     * 선택 : 2 → ID: 1
     * 조회 결과: Member{id=1, name=김철수, email=chulsu@email.com, age=25}
     *
     * 선택 : 3 → ID: 1, 새 이름: 김철수(수정), 새 나이: 26
     * [완료] 수정됨: Member{id=1, name=김철수(수정), email=chulsu@email.com, age=26}
     *
     * 선택 : 4 → ID: 2
     * [완료] 삭제됨: 이영희
     *
     * 선택 : 5
     * === 전체 회원 목록 ===
     * Member{id=1, name=김철수(수정), email=chulsu@email.com, age=26}
     * @param args
     */




    public static void main(String[] args) {

        Map<Integer,Member> personMap = new HashMap<>();
        List<String> personList = new ArrayList<>();
        Member member = new Member();

        // 메인에서 실행해 보기
        Scanner sc = new Scanner(System.in);
        boolean plag = true;
        while (plag) {
        System.out.println("=== 회원관리 시스템 ===");
        System.out.println("기능을 선택해 주세요 1. 회원가입(이메일 중복안됨) 2.회원정보 3.회원정보 수정 4.회원 삭제 5. 전체 회원정보 6.종료 ");
        String selectNumber = sc.nextLine();
            if (selectNumber.equals("1")) {
                // 회원가입
                createPerson(sc, personMap, member);
            } else if (selectNumber.equals("2")) {
                // 회원정보 조회 (키를 통해서 구현하면 될듯)
                searchId(sc, member, personMap);
            } else if (selectNumber.equals("3")) {
                // 회원정보 수정 (키를 통해서 조회 한후 value를 수정하게 구현)
                setPerson(sc, member, personMap);
            } else if (selectNumber.equals("4")) {
                // 회원 삭제 (키를 통해 들어가 서 value 제거)
            } else if (selectNumber.equals("5")) {
                // 전체 회원정보 keySet을 활용하여 전체 value 조회
            } else if (selectNumber.equals("6")) {
                plag = false;
            }
        }
    } //  end of main

    private static void setPerson(Scanner sc, Member member,Map<Integer,Member> personMap) {

        System.out.println("회원정보 수정");
        System.out.println("수정할 회원 아이디 번호를 입력해 주세요");
        String text = sc.nextLine();
        if(personMap.containsKey(text)){ // 키와 입력한 키가 같으면

            System.out.println("수정할 정보 입력");
            System.out.print("이름 입력:");
            String text1 = sc.nextLine();
            personMap.get(text).setName(text1);
            System.out.print("나이 입력:");
            int text2 = sc.nextInt();
            personMap.get(text).setAge(text2);
            System.out.print("이메일 입력:");
            String text3 = sc.nextLine();
            personMap.get(text).setEmail(text3);
            System.out.println("수정한 이름: " + personMap.get(text).getName()); // 해당 키를 가진 멤버에 접근 set함수호출 하려는데 안됨
            System.out.println("수정한 나이: " + personMap.get(text).getAge());
            System.out.println("수정한 이메일: " +personMap.get(text).getEmail());


        }

    }

    private static void searchId(Scanner sc, Member member,Map<Integer,Member> personMap) {
        System.out.println("회원 정보 조회");
        System.out.print("회원의 아이디 번호를 입력해 주세요: ");
        String text = sc.nextLine();
        if(personMap.containsKey(text)){
            System.out.println("조회결과");
            System.out.println("이름: " + personMap.get(text).getName());
            System.out.println("나이: " + personMap.get(text).getAge());
            System.out.println("이메일: " +personMap.get(text).getEmail());

        };
    }

    private static void createPerson(Scanner sc , Map<Integer,Member> personMap,Member member) {
        int count = 1;

        System.out.print("=== 회원정보입력 란 ===");

        System.out.print("이메일 입력: ");
        String text = sc.nextLine();
        System.out.print("이름 입력: ");
        String text2 = sc.nextLine();
        System.out.print("나이 입력: ");
        int text3 = sc.nextInt();
        member.setEmail(text);
        member.setName(text2);
        member.setAge(text3);
        member.setId(count);
        // id 를 key로 넣는다
        System.out.println("회원가입 완료");
        System.out.println("이름: " + member.getName());
        System.out.println("이메일: " + member.getEmail());
        System.out.println("나이: " + member.getAge());
        System.out.println("아이디 넘버: " +member.getId());
        personMap.put(member.getId(), member);

        count++;

    }

} // end of class
