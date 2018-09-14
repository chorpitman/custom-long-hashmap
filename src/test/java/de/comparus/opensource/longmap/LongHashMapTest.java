package de.comparus.opensource.longmap;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class LongHashMapTest {
    private static final String VALUE_1 = "Value1";
    private static final String VALUE_2 = "Value2";
    private static final String VALUE_3 = "Value3";

    private static final long KEY_1 = 5740330960816732701L;
    private static final long KEY_2 = 7913592731860455008L;
    private static final long KEY_3 = -7574202685399910360L;

    private static final long KEY = -1119999904145558222L;
    private static final int HASH_TABLE_LENGTH = 16;

    @Test
    public void shouldAddElementToMap() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
    public void shouldRewriteValueIfKeySame() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        map.put(KEY_1, VALUE_1);
        map.put(KEY_1, VALUE_2);
        map.put(KEY_1, VALUE_3);
        //THEN
        assertEquals(1, map.size());
        assertEquals(VALUE_3, map.get(KEY_1));
    }

    @Test
    public void shouldGetElementByExistKEy() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(VALUE_1, map.get(KEY_1));
        assertEquals(VALUE_2, map.get(KEY_2));
        assertEquals(VALUE_3, map.get(KEY_3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWneGetElementByNotExistKEy() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        //THEN
        assertEquals(VALUE_1, map.get(KEY_1));
    }

    @Test
    public void shouldRemoveElementByExistKey() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(null, map.remove(0));

    }

    @Test
    public void shouldReturnFalseIfMapDoesNotEmpty() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        //THEN
        assertEquals(true, map.isEmpty());
    }

    @Test
    public void shouldReturnTrueMapContainsKey() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
    public void shouldReturnFalseIfMapDoesNotContainsKey() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        map.put(KEY_3, VALUE_3);
        map.put(KEY_2, VALUE_2);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(false, map.containsKey(999L)); //todo think about hash function. if we send -111222L
        assertEquals(false, map.containsKey(1111L)); //todo think about hash function. if we send -111222L
    }

    @Test
    public void shouldReturnTrueIfValueContains() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
    public void shouldReturnTrueIfValueNull() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        map.put(KEY_2, null);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(true, map.containsValue(null));
    }

    @Test
    public void shouldReturnFalseValueDoesNotContains() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        //THEN
        assertEquals(false, map.containsValue(VALUE_1));
    }

    @Test
    public void shouldReturnKeys() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        long[] keys = map.keys();
        //THEN
        assertEquals(0, keys.length);
    }

    @Test
    public void shouldReturnExistValues() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        map.put(KEY_2, VALUE_2);
        map.put(KEY_3, VALUE_3);
        map.put(KEY_1, VALUE_1);
        //THEN
        assertEquals(3, map.values().size());
    }

    @Test
    public void shouldReturnZeoValues() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        //THEN
        assertEquals(0, map.values().size());
    }

    @Test
    public void shouldReturnActualMapSize() {
        //GIVEN
        LongMap<String> map = new LongHashMap<>();
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
        LongMap<String> map = new LongHashMap<>();
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
        LongMap<String> map = new LongHashMap<>();
        //WHEN
        int firstResult = ((LongHashMap<String>) map).hashFunction(KEY, HASH_TABLE_LENGTH);
        int secondResult = ((LongHashMap<String>) map).hashFunction(KEY, HASH_TABLE_LENGTH);
        //THEN
        assertNotSame(KEY, firstResult);
        assertNotSame(KEY, secondResult);
        assertEquals(firstResult, secondResult);
    }

    @Test
    public void shouldCreateMapWithCustomSize() {
        int customMapSize = 7;
        //GIVEN
        LongMap<String> map = new LongHashMap<>(customMapSize);
        //WHEN
        for (int i = 0; i < customMapSize; i++) {
            map.put(i, "Value: " + 1);
        }
        //THEN
        assertEquals(customMapSize, map.size());
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