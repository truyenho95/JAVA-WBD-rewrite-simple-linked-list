import java.util.NoSuchElementException;

public class MyLinkedList<E> {
  private int size = 0;
  private Point<E> first;
  private Point<E> last;

  public MyLinkedList() {
  }

  /**
   * Returns the (non-null) Node at the specified element index.
   */
  Point<E> point(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
      Point<E> x = first;
      for (int i = 0; i < index; i++)
        x = x.next;
      return x;
    } else {
      Point<E> x = last;
      for (int i = size - 1; i > index; i--)
        x = x.prev;
      return x;
    }
  }

  /**
   * Unlinks non-null node x.
   */
  E unlink(Point<E> x) {
    // assert x != null;
    final E element = x.item;
    final Point<E> next = x.next;
    final Point<E> prev = x.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
      x.prev = null;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
      x.next = null;
    }

    x.item = null;
    size--;
    return element;
  }

  public void add(int index, E element) {
    checkPositionIndex(index);

    if (index == size)
      linkLast(element);
    else
      linkBefore(element, point(index));
  }

  public boolean add(E element) {
    linkLast(element);
    return true;
  }

  public void addFirst(E element) {
    linkFirst(element);
  }

  /**
   * Appends the specified element to the end of this list.
   *
   * <p>This method is equivalent to {@link #add}.
   *
   * @param element the element to add
   */
  public void addLast(E element) {
    linkLast(element);
  }

  /**
   * Links e as first element.
   */
  private void linkFirst(E e) {
    final Point<E> f = first;
    final Point<E> newPoint = new Point<>(null, e, f);
    first = newPoint;
    if (f == null)
      last = newPoint;
    else
      f.prev = newPoint;
    size++;
  }

  /**
   * Links e as last element.
   */
  private void linkLast(E e) {
    final Point<E> l = last;
    final Point<E> newPoint = new Point<>(l, e, null);
    last = newPoint;
    if (l == null)
      first = newPoint;
    else
      l.next = newPoint;
    size++;
  }

  /**
   * Inserts element e before non-null Node succ.
   */
  private void linkBefore(E e, Point<E> succ) {
    // assert succ != null;
    final Point<E> pred = succ.prev;
    final Point<E> newPoint = new Point<>(pred, e, succ);
    succ.prev = newPoint;
    if (pred == null)
      first = newPoint;
    else
      pred.next = newPoint;
    size++;
  }

  private void checkPositionIndex(int index) {
    if (!isPositionIndex(index))
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  private void checkElementIndex(int index) {
    if (!isElementIndex(index))
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  /**
   * Tells if the argument is the index of an existing element.
   */
  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }

  /**
   * Tells if the argument is the index of a valid position for an
   * iterator or an add operation.
   */
  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  /**
   * Constructs an IndexOutOfBoundsException detail message.
   * Of the many possible refactorings of the error handling code,
   * this "outlining" performs best with both server and client VMs.
   */
  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  /**
   * Removes the element at the specified position in this list.  Shifts any
   * subsequent elements to the left (subtracts one from their indices).
   * Returns the element that was removed from the list.
   *
   * @param index the index of the element to be removed
   * @return the element previously at the specified position
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  public E remove(int index) {
    checkElementIndex(index);
    return unlink(point(index));
  }

  /**
   * Removes the first occurrence of the specified element from this list,
   * if it is present.  If this list does not contain the element, it is
   * unchanged.  More formally, removes the element with the lowest index
   * {@code i} such that
   * {@code Objects.equals(o, get(i))}
   * (if such an element exists).  Returns {@code true} if this list
   * contained the specified element (or equivalently, if this list
   * changed as a result of the call).
   *
   * @param o element to be removed from this list, if present
   * @return {@code true} if this list contained the specified element
   */
  public boolean remove(Object o) {
    if (o == null) {
      for (Point<E> x = first; x != null; x = x.next) {
        if (x.item == null) {
          unlink(x);
          return true;
        }
      }
    } else {
      for (Point<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item)) {
          unlink(x);
          return true;
        }
      }
    }
    return false;
  }

  public int size() {
    return this.size;
  }

  public MyLinkedList<E> clone() {
      MyLinkedList<E> clone = new MyLinkedList<E>();

      // Put clone into "virgin" state
      clone.first = clone.last = null;
      clone.size = 0;

      // Initialize clone with our elements
      for (Point<E> x = first; x != null; x = x.next)
        clone.add(x.item);
      return clone;
  }

  // Search Operations

  /**
   * Returns the index of the first occurrence of the specified element
   * in this list, or -1 if this list does not contain the element.
   * More formally, returns the lowest index {@code i} such that
   * {@code Objects.equals(o, get(i))},
   * or -1 if there is no such index.
   *
   * @param o element to search for
   * @return the index of the first occurrence of the specified element in
   * this list, or -1 if this list does not contain the element
   */
  public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
      for (Point<E> x = first; x != null; x = x.next) {
        if (x.item == null)
          return index;
        index++;
      }
    } else {
      for (Point<E> x = first; x != null; x = x.next) {
        if (o.equals(x.item))
          return index;
        index++;
      }
    }
    return -1;
  }

  public boolean contains(E o) {
    return indexOf(o) >= 0;
  }

  // Positional Access Operations

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  public E get(int index) {
    checkElementIndex(index);
    return point(index).item;
  }

  /**
   * Returns the first element in this list.
   *
   * @return the first element in this list
   * @throws NoSuchElementException if this list is empty
   */
  public E getFirst() {
    final Point<E> f = first;
    if (f == null)
      throw new NoSuchElementException();
    return f.item;
  }

  /**
   * Returns the last element in this list.
   *
   * @return the last element in this list
   * @throws NoSuchElementException if this list is empty
   */
  public E getLast() {
    final Point<E> l = last;
    if (l == null)
      throw new NoSuchElementException();
    return l.item;
  }

  /**
   * Removes all of the elements from this list.
   * The list will be empty after this call returns.
   */
  public void clear() {
    // Clearing all of the links between nodes is "unnecessary", but:
    // - helps a generational GC if the discarded nodes inhabit
    //   more than one generation
    // - is sure to free memory even if there is a reachable Iterator
    for (Point<E> x = first; x != null; ) {
      Point<E> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = last = null;
    size = 0;
  }

  private class Point<E> {
    E item;
    Point<E> next;
    Point<E> prev;

    public Point(Point<E> prev, E element, Point<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
}
