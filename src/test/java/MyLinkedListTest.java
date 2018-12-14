public class MyLinkedListTest {
  public static void main(String[] args) {
    MyLinkedList<String> myLinkedList = new MyLinkedList<String>();
    myLinkedList.add("a1");
    myLinkedList.addFirst("c3");
    myLinkedList.addLast("e5");
    myLinkedList.add(2,"s12");
    myLinkedList.add(4,"v21");
    MyLinkedList<String> myLinkedListClone = myLinkedList.clone();
    //    myLinkedList.add(10,35); throw error

    for (int i = 0; i < myLinkedList.size(); i++) {
      System.out.println(myLinkedList.get(i));
    }
    System.out.println("***");

    myLinkedList.remove("s12");
    myLinkedList.remove("m2");
    for (int i = 0; i < myLinkedList.size(); i++) {
      System.out.println(myLinkedList.get(i));
    }
    System.out.println("***");

    System.out.println(myLinkedList.contains("e5"));
    System.out.println(myLinkedList.contains("m2"));
    System.out.println(myLinkedList.getFirst());
    System.out.println(myLinkedList.getLast());
    System.out.println("***");

    for (int i = 0; i < myLinkedListClone.size(); i++) {
      System.out.println(myLinkedListClone.get(i));
    }
  }
}
