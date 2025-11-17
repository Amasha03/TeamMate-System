public class Organizer extends User{
    public Organizer(String id, String email) {
        super(id, email);
    }

    @Override
    public void showMenu() {
        System.out.println("===== Organizer Menu =====");
        System.out.println("1. Load CSV");
        System.out.println("2. Form Teams");
        System.out.println("3. Logout");
    }
}
