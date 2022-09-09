import java.util.ArrayList;
import java.util.HashMap;

public class lock {

    public static ArrayList<String> list = new ArrayList<>();
    private static HashMap<String, locallock> map = new HashMap<>();
    private final static locallock sync = new locallock("public");

    public static locallock getLock(String key) {
        if(!map.containsKey(key)) {
            map.put(key, new locallock(key));
            return sync;
        }
        return map.get(key);
    }


    public static Boolean hasLock(String key) {
        getLock(key);
        return map.containsKey(key);
    }

    public static void AckLock(String key) {
        list.add(key);
    }

    public static Boolean CachedhasLock(String key) {
        return list.contains(key);
    }

}
