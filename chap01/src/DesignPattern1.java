// 1.1 싱글톤패턴 : 하나의 클래스에 하나의 인스턴스
class DesignPattern1_1 {
    private static class singleInstanceHolder { // 메서드(객체 생성하는)
        // 객체 INSTANCE 생성
        // private로 제한 -> 생성자를 통한 무분별 객체생성 막음
        private static final DesignPattern1_1 INSTANCE = new DesignPattern1_1();
    }
    // getInstance() 메서드 -> 객체반환 (public)
    public static DesignPattern1_1 getInstance() {
        return singleInstanceHolder.INSTANCE;
    }
}

class DesignPattern1_2 {
    public static void main(String[] args) {
        // getInstance()를 통해서 객체(인스턴스) 생성
        DesignPattern1_1 a = DesignPattern1_1.getInstance();
        DesignPattern1_1 b = DesignPattern1_1.getInstance();

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        if (a == b) {
            System.out.println(true);
        }
    }
}

// 1.1 싱글톤패턴_의존성주입 DI
// (의존성이 높은 경우, 클래스 간 관계)
class Pencil1 {}

class Store1 {
    private Pencil1 pencil1;

    public Store1() { // 생성자
        this.pencil1 = new Pencil1();
    }
}

// (의존성 주입 -> 느슨한결합형태)
interface Product {} // 다형성 (여러가지 제품을 하나로 표현하는 interface)
class Pencil2 implements Product{}

class Store2 {
    private Product product;

    public Store2(Product product) { // 생성자 (객체를 넣어주는 방식)
        this.product = product;
    }
}

class BeanFactory {
    public void store2() {
        // Bean 생성
        Product pencil2 = new Pencil2();

        // 의존성주입
        Store2 store2 = new Store2(pencil2);
    }
}