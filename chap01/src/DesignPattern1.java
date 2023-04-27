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
        DesignPattern1_1 a = DesignPattern1_1.getInstance();
        DesignPattern1_1 b = DesignPattern1_1.getInstance();

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        if (a == b) {
            System.out.println(true);
        }
    }
}