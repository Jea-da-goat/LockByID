public class locallock {


    private final LockObject obj;
    public locallock(String key) {
        obj = new LockObject(key);
    }

    public LockObject getLock() {
        return obj;
    }
}
