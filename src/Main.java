import java.sql.*;
import java.util.Scanner;
public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        while(true){
            System.out.println("\n==== Gym Locker System ====");
            System.out.println("1. Add Member");
            System.out.println("2. Assign Locker");
            System.out.println("3. Release Locker");
            System.out.println("4. View Locker Status");
            System.out.println("5. Exit");
            System.out.println("6. Total Members");
            System.out.println("7. View Members");
            System.out.print("Enter choice: ");
            int choice=sc.nextInt();
            switch(choice){
                case 1:addMember();break;
                case 2:assignLocker();break;
                case 3:releaseLocker();break;
                case 4:viewStatus();break;
                case 5:System.exit(0);
                case 6:totalMembers();break;
                case 7:viewMembers();break;
                default:System.out.println("Invalid choice");
            }
        }
    }
    static void addMember(){
        try{
            Connection con=DBConnection.getConnection();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name=sc.nextLine();
            System.out.print("Enter Phone: ");
            String phone=sc.nextLine();
            System.out.print("Enter Plan (monthly/quarterly/annual): ");
            String plan=sc.nextLine().toLowerCase();
            String checkQuery="SELECT * FROM members WHERE phone=?";
            PreparedStatement checkPs=con.prepareStatement(checkQuery);
            checkPs.setString(1,phone);
            ResultSet rs=checkPs.executeQuery();
            if(rs.next()){
                System.out.println("Member already exists!");
                return;
            }
            String insertQuery="INSERT INTO members(name,phone,plan) VALUES(?,?,?)";
            PreparedStatement ps=con.prepareStatement(insertQuery);
            ps.setString(1,name);
            ps.setString(2,phone);
            ps.setString(3,plan);
            ps.executeUpdate();
            System.out.println("Member added successfully");
        }catch(Exception e){e.printStackTrace();}
    }
    static void assignLocker(){
        try{
            Connection con=DBConnection.getConnection();
            System.out.print("Enter Member ID: ");
            int memberId=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Assigned Date (YYYY-MM-DD): ");
            String assignedDate=sc.nextLine();
            String select="SELECT locker_id FROM lockers WHERE member_id IS NULL LIMIT 1";
            PreparedStatement ps1=con.prepareStatement(select);
            ResultSet rs=ps1.executeQuery();
            if(rs.next()){
                int lockerId=rs.getInt("locker_id");
                String update="UPDATE lockers SET member_id=?,assigned_on=? WHERE locker_id=?";
                PreparedStatement ps2=con.prepareStatement(update);
                ps2.setInt(1,memberId);
                ps2.setString(2,assignedDate);
                ps2.setInt(3,lockerId);
                ps2.executeUpdate();
                System.out.println("Locker assigned successfully");
            }else{
                System.out.println("No free lockers available");
            }
        }catch(Exception e){e.printStackTrace();}
    }
    static void releaseLocker(){
        try{
            Connection con=DBConnection.getConnection();
            sc.nextLine();
            System.out.print("Enter Locker Number (e.g., L1): ");
            String lockerNo=sc.nextLine();
            String query="UPDATE lockers SET member_id=NULL,assigned_on=NULL WHERE locker_no=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,lockerNo);
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Locker released successfully");
            }else{
                System.out.println("Locker not found");
            }
        }catch(Exception e){e.printStackTrace();}
    }
    static void viewStatus(){
        try{
            Connection con=DBConnection.getConnection();
            String query="SELECT l.locker_no,m.name,m.phone,m.plan,l.assigned_on FROM lockers l LEFT JOIN members m ON l.member_id=m.member_id";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            System.out.println("\n===== Locker Status =====");
            while(rs.next()){
                String locker=rs.getString("locker_no");
                String name=rs.getString("name");
                if(name==null){
                    System.out.println(locker+" | [FREE]");
                }else{
                    System.out.println(locker+" | Member: "+name+" | Phone: "+rs.getString("phone")+" | Plan: "+rs.getString("plan")+" | Assigned: "+rs.getDate("assigned_on"));
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    static void viewMembers(){
    try{
        Connection con=DBConnection.getConnection();
        String query="SELECT * FROM members";
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        System.out.println("\n===== Members List =====");
        while(rs.next()){
                System.out.println("ID: "+rs.getInt("member_id")+" | Name: "+rs.getString("name")+" | Phone: "+rs.getString("phone")+" | Plan: "+rs.getString("plan"));
            }
        }catch(Exception e){e.printStackTrace();}
    }
    static void totalMembers(){
        try{
            Connection con=DBConnection.getConnection();
            String query="SELECT COUNT(*) AS total FROM members";
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Total Members: "+rs.getInt("total"));
            }
        }catch(Exception e){e.printStackTrace();}
    }
}
