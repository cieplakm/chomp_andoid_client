package com.mmc.chomp;

public class IoC {
    public static final String IP_ADDRESS = "ws://10.0.2.2:8080/chomp"; // 51.38.129.34 :: 10.0.2.2
    private static Client client;
    private static String USER_ID = LoginKeeper.getInstance().getUserId();

    public static void createClient(Game game){
        client = new Client(USER_ID, new ChompWebSocketListener(game));
    }

    public static Client getClient() {
        return client;
    }

    public static String getUserId() {
        return USER_ID;
    }
}
