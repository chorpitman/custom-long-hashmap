package de.comparus.opensource.longmap;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class LongMapImplTest {
    private static final String VALUE_1 = "Value1";
    private static final String VALUE_2 = "Value2";
    private static final String VALUE_3 = "Value3";

    private static final int KEY_1 = 1;
    private static final int KEY_2 = 2;
    private static final int KEY_3 = 3;

    @Test
    public void shouldAddElementToMap() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_1, VALUE_1);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        //THEN
        assertEquals(3, map.size());
        assertEquals(VALUE_1, map.get(KEY_1));
        assertEquals(VALUE_2, map.get(KEY_2));
        assertEquals(VALUE_3, map.get(KEY_3));
    }

    @Test
    public void shouldGetElementByExistKEy() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(VALUE_1, map.get(KEY_1));
        assertEquals(VALUE_2, map.get(KEY_2));
        assertEquals(VALUE_3, map.get(KEY_3));
    }

    @Test
    public void shouldRemoveElementByExistKey() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        map.remove(KEY_3);
        //THEN
        assertEquals(2, map.size());
    }

    @Test
    public void shouldReturnNullWhenUserRemoveElementByNotExistKey() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(null, map.remove(777));

    }

    @Test
    public void shouldReturnFalseIfMapDoesNotEmpty() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(false, map.isEmpty());
    }

    @Test
    public void shouldReturnTrueIfMapIsEmpty() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        //THEN
        assertEquals(true, map.isEmpty());
    }

    @Test
    public void shouldReturnTrueMapContainsKey() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(true, map.containsKey(KEY_1));
        assertEquals(true, map.containsKey(KEY_2));
        assertEquals(true, map.containsKey(KEY_3));
    }

    @Test
    public void shouldReturnFalseMapDoesNotContainsKey() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(false, map.containsKey(999L)); //todo think about hash function. if we send -111222L
        assertEquals(false, map.containsKey(1111L)); //todo think about hash function. if we send -111222L
    }

    @Test
    public void shouldReturnTrueValueContains() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(true, map.containsValue(VALUE_1));
        assertEquals(true, map.containsValue(VALUE_2));
        assertEquals(true, map.containsValue(VALUE_3));
    }

    @Test
    public void shouldReturnFalseValueDoesNotContains() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        //THEN
        assertEquals(false, map.containsValue(VALUE_1));
    }

    @Test
    public void shouldReturnKeys() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        long[] keys = map.keys();
        //THEN
        assertEquals(3, keys.length);
        assertEquals(true, findKey(KEY_1, keys));
        assertEquals(true, findKey(KEY_2, keys));
        assertEquals(true, findKey(KEY_3, keys));
    }

    @Test
    public void shouldNotReturnKeys() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        long[] keys = map.keys();
        //THEN
        assertEquals(0, keys.length);
    }

    @Test
    public void values() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        Collection<String> values = map.values();
        //THEN
        System.out.println(values);
    }

    @Test
    public void shouldReturnActualMapSize() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        //THEN
        Assert.assertEquals(3, map.size());
    }

    @Test
    public void shouldRemoveAllElementsFromMap() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        assertEquals(3, map.size());
        //THEN
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void hashFunction() {
        //GIVEN
        LongMap<String> map = new LongMapImpl<>();
        //WHEN
        int i = ((LongMapImpl<String>) map).hashFunction(234L, 16);
        //THEN
    }

    private boolean findKey(long findKey, long[] keys) {
        for (int i = 0; i < keys.length; i++) {
            if (findKey == keys[i]) {
                return true;
            }
        }
        return false;
    }
}