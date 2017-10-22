package com.example.jean.webservice.Helpers;


public class Base {
    public final static String LOGIN_PREFS = "login_session";

    public static class Routes {
        public final static String login = "login2";

        public static String getAllGuests(int event) {
            return "events/" + event + "/guests/getAll";
        }

        public static String getAllEvents(String user) {
            return "user/" + user + "/parties";
        }
    }
}
