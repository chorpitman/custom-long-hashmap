package de.comparus.opensource.longmap;

import org.junit.Test;

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
    public void isEmpty() {


    }

    @Test
    public void containsKey() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    public void containsValue() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    public void keys() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    public void values() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    public void size() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    public void clear() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    public void hashFunction() {
        //GIVEN
        //WHEN
        //THEN
    }
}