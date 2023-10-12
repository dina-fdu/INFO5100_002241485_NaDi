class Clock {
    String brand;
    boolean is_on;
    int hour;
    int minute;
    int second;
    boolean is_broken;
    boolean is_locked;
    boolean need_change_battery;

    Clock(String id) {
        System.out.println("Clock " + id + " created.");
    }

    void setAlarm() {
        System.out.println("Setting the alarm.");
    }

    void resetAlarm() {
        System.out.println("Resetting the alarm.");
    }

    void showTime() {
        System.out.println("Showing the time.");
    }
}
