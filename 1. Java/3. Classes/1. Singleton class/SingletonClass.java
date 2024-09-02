public class SingletonClass {
    SingletonEager singletonEager = SingletonEager.getInstance();
    SingletonLazy singletonLazy = SingletonLazy.getInstance();
    SingletonThreadSafe singletonThreadSafe = SingletonThreadSafe.getInstance();
}

class SingletonEager {
    private static final SingletonEager SINGLETON_EAGER_CLASS_INSTANCE
            = new SingletonEager();

    private SingletonEager() {}

    public static SingletonEager getInstance() {
        return SINGLETON_EAGER_CLASS_INSTANCE;
    }
}

class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {}

    public static SingletonLazy getInstance() {
        if (instance == null) instance = new SingletonLazy();
        return instance;
    }
}

class SingletonThreadSafe {
    private static SingletonThreadSafe instance;

    private SingletonThreadSafe() {}

    public static synchronized SingletonThreadSafe getInstance() {
        if (instance == null) instance = new SingletonThreadSafe();
        return instance;
    }
}