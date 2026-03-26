package generic.ch01;

public class ThreeDPrinter1 {

    // 재료
    private Plastic material;


    // 재료를 꺼낼수 있는 기능
    public Plastic getMaterial(){

        return material;
    }
    public void setMaterial(Plastic meterial){

        this.material = meterial;

    }
    // 재료를 넣을수 있는 기능

    // 테스트 코드
    public static void main(String[] args) {

        Plastic plastic = new Plastic();
        ThreeDPrinter1 threeDPrinter1 = new ThreeDPrinter1();

        threeDPrinter1.setMaterial(plastic); // 재료 장착
        System.out.println(threeDPrinter1.getMaterial());
        // threeDPrinter1.getMaterial() --> plastic 주소값

    }

}
