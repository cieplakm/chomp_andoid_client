package com.mmc.chomp;

public class LoginKeeper {
    private static final LoginKeeper ourInstance = new LoginKeeper();

    private final String userId = "2b9d9694-6513-43d3-84d7-bbed57b82df1";

    private final String userId2 = "36e68452-12e8-4c3e-99b5-a046c0fba42b";

    public static LoginKeeper getInstance() {
        return ourInstance;
    }

    private LoginKeeper() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUserId2() {
        return userId2;
    }

}
