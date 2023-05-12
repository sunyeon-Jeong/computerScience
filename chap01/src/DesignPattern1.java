import java.util.ArrayList;
import java.util.List;

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


// 1.2 팩토리패턴 : 객체생성부분 -> 떼어내 추상화, 상위클래스 : 뼈대결정 + 하위클래스 : 객체  생성 구체적내용 결정
// super class -> Lattee, Americano, DefaultCoffee의 부모클래스
abstract class Coffee { // 객체생성부분 추상화
    public abstract int getPrice(); // 메서드

    @Override
    public String toString() { // 메서드
        return "Hi this coffee is " + this.getPrice();
    }
}

class DefaultCoffee extends Coffee {
    private int price; // 멤버변수

    public DefaultCoffee() { // 생성자
        this.price = -1;
    }

    // Coffee 클래스 getPrice() 메서드 -> 생성자로 설정한 값 덮어씌움
    @Override
    public int getPrice() {
        return this.price;
    }
}

class Latte extends Coffee {
    private int price; // 멤버변수

    public Latte(int price) { // 생성자
        this.price = price;
    }

    // Coffee 클래스 getPrice() 메서드 -> 생성자로 설정한 값 덮어씌움
    @Override
    public int getPrice() {
        return this.price;
    }
}

class Americano extends Coffee {
    private int price; // 멤버변수

    public Americano(int price) { // 생성자
        this.price = price;
    }

    // Coffee 클래스 getPrice() 메서드 -> 생성자로 설정한 값 덮어씌움
    @Override
    public int getPrice() {
        return this.price;
    }
}

class CoffeeFactory {
    public static Coffee getCoffee(String type, int price) { // 메서드
        if ("Latte".equalsIgnoreCase(type)) { // 입력값(type) = "Latte"
            return new Latte(price);
        }
        else if ("Americano".equalsIgnoreCase(type)) { // 입력값(type) = "Americano"
            return new Americano(price);
        }
        else {
            return new DefaultCoffee();
        }
    }
}

class DesignPattern1_3 {
    public static void main(String[] args) {
        Coffee latte = CoffeeFactory.getCoffee("Latte", 4000);
        Coffee ame = CoffeeFactory.getCoffee("Americano", 3000);

        System.out.println("Factory latte :: " + latte);
        System.out.println("Factory ame :: " + ame);
    }
}


// 1.3 전략패턴 : 객체 행위 변경 시, 직접 수정 X
// -> 전략(캡슐화한 알고리즘) 컨텍스트 안에서 변경
// 결제 interface
interface PaymentStrategy {
    public void pay(int amount); // pay 메서드
}

// (결제) 전략 1
class KAKAOCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    // 생성자
    public KAKAOCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    // PaymentStrategy interface pay 메서드
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using KAKAOCard.");
    }
}

// (결제) 전략 2
class LUNACardStrategy implements PaymentStrategy {
    private String email;
    private String password;

    // 생성자
    public LUNACardStrategy (String email, String password) {
        this.email = email;
        this.password = password;
    }

    // PaymentStrategy interface pay 메서드
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using LUNACard.");
    }
}

// 상품 클래스
class Item {
    private String name;
    private int price;

    // 생성자
    public Item (String name, int price) {
        this.name = name;
        this.price = price;
    }

    // 메서드
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

// 장바구니 클래스
class ShoppingCart {
    // Item 클래스 리스트 객체
    List<Item> items;

    // 생성자
    public ShoppingCart() {
        this.items = new ArrayList<Item>();
    }

    // 메서드
    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public int calculateTotal() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void pay(PaymentStrategy paymentStrategy) {
        int amount = calculateTotal();
        paymentStrategy.pay(amount);
    }
}

class DesignPattern1_4 {
    public static void main(String[] args) {
        // 객체 생성
        ShoppingCart cart = new ShoppingCart();

        Item A = new Item("mallang", 1000);
        Item B = new Item("quokka", 1000);

        cart.addItem(A);
        cart.addItem(B);

        // pay by LUNACard
        cart.pay(new LUNACardStrategy("quokka@gmail.com", "quokka"));

        // pay by KAKAOCard
        cart.pay(new KAKAOCardStrategy("mallang", "mallang123", "123", "04/12"));
    }
}


// 1.4 옵저버패턴 : 주체가 객체의 상태변화 관찰 -> 상태변화 감지 -> 메서드통해 옵저버에게 변화 notification
interface Subject {
    public void register(Observer obj);
    public void unregister(Observer obj);
    public void notifyObservers();
    public Object getUpdate(Observer obj);
}

interface Observer {
    public void update();
}

// 자바 구현방식 : 부모 interface -> 자식클래스에서 재정의 하여 구현
// 반드시 부모클래스의 메서드를 재정의해야함 (!= 상속 extends)
class Topic implements Subject {
    private List<Observer> observers;
    private String message;

    // 생성자
    public Topic() {
        this.observers = new ArrayList<>();
        this.message = "";
    }

    // 부모클래스 메서드 오버라이드(재정의)
    @Override
    public void register(Observer obj) {
        if (!observers.contains(obj)) observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach(Observer::update);
    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message;
    }

    public void postMessage(String msg) {
        System.out.println("Message sended to Topic: " + msg);
        this.message = msg;
        notifyObservers();
    }
}

class TopicSubscriber implements Observer {
    private String name;
    private Subject topic;

    // 생성자
    public TopicSubscriber(String name, Subject topic) {
        this.name = name;
        this.topic = topic;
    }

    // 부모클래스 메서드 오버라이드(재정의)
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        System.out.println(name + ":: got message >> " + msg);
    }
}

class DesignPattern1_5 {
    public static void main(String[] args) {
        // 객체생성
        Topic topic = new Topic();
        Observer a = new TopicSubscriber("a", topic);
        Observer b = new TopicSubscriber("b", topic);
        Observer c = new TopicSubscriber("c", topic);

        topic.register(a);
        topic.register(b);
        topic.register(c);

        topic.postMessage("mallang is crossfit champion!");
    }
}
// topic은 주체이자 객체