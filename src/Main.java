import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Gym Locker System =====");
            System.out.println("1. Add Member");
            System.out.println("2. Assign Locker");
            System.out.println("3. Release Locker");
            System.out.println("4. View Locker Status");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: addMember(); break;
                case 2: assignLocker(); break;
                case 3: releaseLocker(); break;
                case 4: viewLockers(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }
    static void addMember() {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.print("Name: ");
            String name = sc.next();
            System.out.print("Phone: ");
            String phone = sc.next();
            System.out.print("Plan: ");
            String plan = sc.next();
            String sql = "INSERT INTO members(name, phone, plan) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, plan);
            ps.executeUpdate();
            System.out.println("Member Added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void assignLocker() {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.print("Enter Member ID: ");
            int id = sc.nextInt();
            String sql = "UPDATE lockers SET member_id=?, assigned_on=CURDATE() WHERE member_id IS NULL LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Locker Assigned!");
            else
                System.out.println("No free lockers!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void releaseLocker() {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.print("Enter Locker No: ");
            String locker = sc.next();
            String sql = "UPDATE lockers SET member_id=NULL, assigned_on=NULL WHERE locker_no=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, locker);
            ps.executeUpdate();
            System.out.println("Locker Released!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void viewLockers() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT l.locker_no, m.name FROM lockers l LEFT JOIN members m ON l.member_id = m.member_id";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String locker = rs.getString("locker_no");
                String name = rs.getString("name");
                if (name == null)
                    System.out.println("Locker " + locker + " | FREE");
                else
                    System.out.println("Locker " + locker + " | " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
