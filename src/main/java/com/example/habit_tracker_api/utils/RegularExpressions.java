package com.example.habit_tracker_api.utils;

public final class RegularExpressions {

    public static final String HABIT_AND_TASK_AND_EVENT_REGEXP = "^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]{3,15}?( +[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]{2,9})?( +[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]{2,9})?$";

    public static final String LOCATION_REGEXP = "^[a-zA-Z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ]{3,10}$";

    public static final String NAME_REGEXP = "^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]{3,10}$";

    public static final String USERNAME_REGEXP = "^[a-zA-Z0-9]{4,10}$";

}
