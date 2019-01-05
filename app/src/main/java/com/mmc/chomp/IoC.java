package com.mmc.chomp;

public class IoC {
    private static Client client;
    private static String USER_ID = LoginKeeper.getInstance().getUserId2();

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
