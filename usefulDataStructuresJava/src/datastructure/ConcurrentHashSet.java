package datastructure;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A concurrent, non-blocking set, based on the 'ConcurrentHashMap'
 * implementation.
 * 
 * @author pf-miles Nov 7, 2012 2:43:37 PM
 */
public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 1909272932744087085L;

    private static final Object candy = new Serializable() {

        private static final long serialVersionUID = -4178482564633716751L;
    };

    private ConcurrentHashMap<E, Object> content = new ConcurrentHashMap<E, Object>();

    public boolean equals(Object o) {
        if (o == null || !(o instanceof ConcurrentHashSet))
            return false;
        return this.content.keySet().equals(((ConcurrentHashSet<?>) o).content.keySet());
    }

    public int hashCode() {
        return this.content.keySet().hashCode();
    }

    public boolean removeAll(Collection<?> c) {
        return this.content.keySet().removeAll(c);
    }

    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    public boolean contains(Object o) {
        return this.content.containsKey(o);
    }

    public Object[] toArray() {
        return this.content.keySet().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.content.keySet().toArray(a);
    }

    public boolean add(E e) {
        return this.content.putIfAbsent(e, candy) == null;
    }

    public boolean remove(Object o) {
        return this.content.remove(o) != null;
    }

    public boolean containsAll(Collection<?> c) {
        return this.content.keySet().containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        if (this.content.keySet().containsAll(c))
            return false;
        boolean ret = false;
        for (E e : c)
            ret |= this.add(e);
        return ret;
    }

    public boolean retainAll(Collection<?> c) {
        return this.content.keySet().retainAll(c);
    }

    public void clear() {
        this.content.clear();
    }

    public String toString() {
        return this.content.keySet().toString();
    }

    @SuppressWarnings("unchecked")
    public Object clone() throws CloneNotSupportedException {
        ConcurrentHashSet<E> newSet = (ConcurrentHashSet<E>) super.clone();
        newSet.content = new ConcurrentHashMap<E, Object>();
        newSet.content.putAll(this.content);
        return newSet;
    }

    public Iterator<E> iterator() {
        return this.content.keySet().iterator();
    }

    public int size() {
        return this.content.size();
    }

}
