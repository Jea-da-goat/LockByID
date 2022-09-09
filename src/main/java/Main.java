public class Main {


    private static final Object sync = new Object();
    private static void Process(int id) {
        try {
            //Random random = new Random();random.nextInt(100, 3000)
            Thread.sleep(3000);
            System.out.println(id);
            System.out.println("-======-");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Object obj = new Object();

    public static void main(String[] args) {
        try {
            synchronized (Lock.getLock("final").getLock()) {
            Process(3566864);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");*/
        new Thread(() -> {
            for(int i = 1; i <= 100; i++) {
                final int x = i;
                new Thread(() -> {
                    if(Lock.CachedhasLock("yes")) {
                        synchronized (Lock.getLock("yes").getLock()) {
                            Process(x);
                        }
                    } else {
                        if (Lock.hasLock("yes")) {
                            synchronized (Lock.getLock("yes").getLock()) {
                                Process(x);
                                Lock.AckLock("yes");
                            }
                        } else {
                            synchronized (sync) {
                                Process(x);
                            }
                        }
                    }


                }).start();

            }
        }).start();
        new Thread(() -> {
            for(int i = 1; i <= 100; i++) {
                final int x = i;
                new Thread(() -> {
                    if(Lock.CachedhasLock("no")) {
                        synchronized (Lock.getLock("no").getLock()) {
                            Process(1000 + x);
                        }
                    } else {
                        if (Lock.hasLock("no")) {
                            synchronized (Lock.getLock("no").getLock()) {
                                Process(1000 + x);
                                Lock.AckLock("no");
                            }
                        } else {
                            synchronized (sync) {
                                Process(1000 + x);
                            }
                        }
                    }


                }).start();

            }
        }).start();

        /*for(int i = 1; i <= 100; i++) {
            final int x = i;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new Thread(() -> {
                synchronized (lock.getLock("no")) {
                    Process(x + 1000);
                }

            }).start();

        }*/
        /*list.add("D");
        System.out.println("===");
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
        list.remove(0);
        */
    }
}
