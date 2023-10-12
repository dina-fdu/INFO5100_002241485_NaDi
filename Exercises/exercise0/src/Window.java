class Window {
    int width;
    int height;
    boolean is_broken;
    boolean is_open;
    boolean is_locked;
    boolean is_cleaned;
    boolean need_change;
    boolean need_clean;

    Window(String id) {
        System.out.println("Window " + id + " created.");
    }

    void openWindow() {
        System.out.println("Opening the window.");
    }

    void closeWindow() {
        System.out.println("Closing the window.");
    }

    void cleanWindow() {
        System.out.println("Cleaning the window.");
    }
}
