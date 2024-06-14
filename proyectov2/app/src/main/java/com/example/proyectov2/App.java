package u;

import android.app.Application;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static App getInstance() {
        return instance;
    }
}
