package generic.ch01;

public class ThreeDPrinter2 {

    // 재료
    private Powder material;


    // 재료를 꺼낼수 있는 기능
    public Powder getPowder(){
        return material;
    }
    public void setMaterial(Powder meterial){

        this.material = meterial;

    }
    // 재료를 넣을수 있는 기능

    // 테스트 코드
    public static void main(String[] args) {

        Powder powder = new Powder();
        ThreeDPrinter2 threeDPrinter1 = new ThreeDPrinter2();

        threeDPrinter1.setMaterial(powder); // 재료 장착
        System.out.println(threeDPrinter1.getPowder());
        // threeDPrinter1.getMaterial() --> plastic 주소값 호출이다.

    }

}
