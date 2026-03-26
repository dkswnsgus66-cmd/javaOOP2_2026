package generic.ch01;

public class ThreeDPrinter3 {


    // 재료
   private Object material;

    // 꺼냄
    public Object getMaterial(){
        return material;

    }
    // 재료넣기

    public void setMaterial(Object material){
        this.material = material;
    }

    // 테스트 코드
    public static void main(String[] args) {

        Plastic plastic = new Plastic();
        Powder powder = new Powder();
        ThreeDPrinter3 threeDPrinter3 = new ThreeDPrinter3();

        threeDPrinter3.setMaterial(plastic);
        System.out.println(threeDPrinter3.getMaterial());
        System.out.println("-----------------------");
        threeDPrinter3.setMaterial(powder);
        System.out.println(threeDPrinter3.getMaterial());
        System.out.println("-----------------------");

        // 타입 선언해서 저장시켜 보자
//       Plastic tempPlastic = (Plastic) threeDPrinter3.getMaterial();
        System.out.println("프로그램을 종료합니다.");
    }
}
