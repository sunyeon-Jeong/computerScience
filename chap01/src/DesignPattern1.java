import java.util.ArrayList;
import java.util.List;

// 1.1 싱글톤패턴 : 하나의 클래스에 하나의 인스턴스
class Singleton {

    // 중첩클래스
    private static class singleInstanceHolder {

        // 객체 INSTANCE 생성
        // private로 제한 -> 생성자를 통한 무분별 객체생성 막음
        private static final Singleton INSTANCE = new Singleton();

    }

    // 객체반환 메서드
    public static Singleton getInstance() {
        return singleInstanceHolder.INSTANCE;
    }

}

class SingletonRun {
    public static void main(String[] args) {

        // getInstance() -> 객체(인스턴스)생성
        Singleton a = Singleton.getInstance();
        Singleton b = Singleton.getInstance();

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        if (a == b) {
            System.out.println(true);
        }

    }
}