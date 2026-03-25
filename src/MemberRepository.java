import java.util.*;

// 역할 - 데이터를 보관하고 관리하는 클래스
public class MemberRepository {

    private List<Member> memberList = new ArrayList<>(); // 전체 목록 조회할때 쓰기
    private Map<Integer, Member> memberMap = new HashMap<>(); // ID -> 회원정보 를 받고 쌓는역할
    private Set<String> emailSet = new HashSet<>(); // 이메일 중복 체크
    // 회원 가입하면 아이디가 자동으로 1씩 증가하는 방법으로 회원에게 할당할 예정
    private int nextId = 1;


    // 회원가입
    public boolean save(String name, String email, int age) {
        // 이메일 중복 확인
        if (emailSet.contains(email)) {
            System.out.println("[오류] 이미 사용중인 이메일 입니다." + email);
            return false;
        }

        Member member = new Member(nextId, name, email, age); // 중요 순서 중요하다
        memberList.add(member);
        memberMap.put(nextId, member);
        emailSet.add(email);
        nextId++;
        System.out.println("[완료] 회원가입 : " + member.getName());
        return true;
    }

    // ID로 회원조회 1 --> 입력 --< 반환(Member)

    public Member findById(int id) {
        return memberMap.get(id);
    }

    // 이름으로 회원 조회하는 기능
    // 1 김철수
    // 2 김철수
    // 3 김형
    public List<Member> findByName(String name) {
        List<Member> result = new ArrayList<>();
        for (Member member : memberList) {
            if (member.getName().equalsIgnoreCase(name)) {
                result.add(member);
            }
        }
        return result;
    }

    // 전체 회원 목록 조회 -- 받아야할 정보? 모든 Member 정보 리턴 --> List 사용

    public List<Member> findAll() {
        return memberList;
    }

    // 회원 정보 수정 - id 기준으로 회원정보 조회
    // 2. 새로운 이름정보 필요
    // 3. 새로운 나이정보 필요
    // 4. 이메일은 중복처리 때문에 수정 불가하게 막을 예정
    public boolean update(int id, String newName, int newAge) {
        // 수정을 할려면 반드시 먼저 조회를 해야한다.
        Member member = memberMap.get(id);
        if (member == null) {
            System.out.println("존재하지 않는 회원 입니다.");
            return false;
        }
        member.setName(newName);
        member.setAge(newAge);
        System.out.println("[완료] 수정됨 " + member); // toString 어노테이션 으로 해놓음
        return true;

    }


    // 회원 아이디 번호로 삭제
    public boolean delete(int id) {
        // 먼저 조회
        Member member = memberMap.get(id);
        if (member == null) {
            System.out.println("존재 하지않는 회원 입니다.");
            return false;
        }
        // list, map ,set - 동기화 처리 중요 해당되는곳에서 전부 삭제
        memberList.remove(id - 1); // list 에서 제거 인덱스 번호로 삭제하기 때문에 보이지 않는 값에서 -1
        memberMap.remove(id); // Map에서 제거
        emailSet.remove(member.getEmail()); // 이메일 제거
        System.out.println("삭제됨: " + member.getEmail());
        return true;
    }


    // 전체 회원 수
    public int count() {
        return memberList.size();
    }

    /**
     * ## 도전 과제
     *
     * ### 도전 1 - 나이 범위로 검색
     *
     * 특정 나이 범위의 회원을 검색하는 메서드를 추가해보세요. ( 메소드 명 추천 : findByAgeRange )
     * 힌트: findAll() 로 전체 목록을 가져와서 member.getAge() >= min && member.getAge() <= max 조건으로 필터링합니다.
     */
    public List<Member> findByAgeRange(int min,int max) {
        // 멤버클래스에 어떻게 접근할거냐?
        // 리스트에 있는 걸로 접근 or Member 객체로 접근?

        List<Member> result = new ArrayList<>();

        for (Member member : memberList) { // List 의 멤버를 한번씩 불러옴

            if (member.getAge() >= min && member.getAge() <= max) {
                result.add(member);
            }
        }
        return result;
    }

    public Member findByEmail(String email){

//        List<Member> result = new ArrayList<>();
//
//        for (Member member : memberList){ // 모든 리스트 호출
//
//            if(member.getEmail().equalsIgnoreCase(email)){ // 이메일 같은것만 걸러내기
//                result.add(member); // 추가
//            }
//
//        }
//        return result; //토해냄
        if(emailSet.contains(email) == false){
            // 이메일 자체가 중복이 안되기에 특정 이메일만 찾으면 됨
            // 전체 순회전에 존배 여부확인 --> 빠르게 존재 여부 확인
            for (Member member : memberList){
                if(member.getEmail().equalsIgnoreCase(email)){

                 return member;

                }
            }
        }
        return null;
    }


}
