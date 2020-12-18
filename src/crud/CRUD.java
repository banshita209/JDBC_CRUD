package crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Operation {

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Operation() {
		// TODO Auto-generated constructor stub
		conn = DB_connection.getConnection();
		stmt = null;
		rs = null;
	}

	void insert(String name, String course, int age, int sem) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into student(`name`,`course`,`semester`,`age`)values('" + name + "','" + course
					+ "'," + sem + "," + age + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void update(String field, String value, int id) {
		
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate("UPDATE student SET "+field+" = '"+value+"' WHERE id ="+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	void displayALL() {

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from student;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (rs != null) {
				System.out.println("ID--||--NAME--||--COURSE--||--SEMESTER--||--AGE");
				while (rs.next()) {
					System.out.println(rs.getLong("id") + "\t" + rs.getString("name") + "\t" + rs.getString("course")
							+ "\t" + rs.getInt("semester") + "\t" + rs.getInt("age"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	boolean delete(int id) {

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from student where id =" + id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

public class CRUD {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Operation op = new Operation();

		int ch = 5;
		do {
			System.out.println("--------Menu---------");
			System.out.println(
					"1 for Insert \n2 for Update \n3 for Display All \n4 for Delete \n5 for exit \nEnter your choice : ");
			ch = s.nextInt();
			switch (ch) {
			case 1:
				// ---insert---

				System.out.println(
						"*********************INSERT OPERATION********************** \nEnter following details :");
				System.out.println("Name : ");
				String name = s.next();
				System.out.println("Course : ");
				String course = s.next();
				System.out.println("Semester : ");
				int sem = s.nextInt();
				System.out.println("Age : ");
				int age = s.nextInt();
				op.insert(name, course, age, sem);
				op.displayALL();
				break;

			case 2:
				// ----update----
				System.out.println("*********************UPDATE OPERATION*********************");
				System.out.println("enter the ID of the record you want to update ");
				int id = s.nextInt();
				System.out.println(
						"What You want to update ???\n choose \n 1 for NAME \n 2 for Course \n 3 for Semester \n 4 for age");
				int ch2 = s.nextInt();

				switch (ch2) {
				case 1:
					System.out.println("Enter new name : ");
					String new_name = s.next();
					op.update("name", new_name, id);
					break;

				case 2:
					System.out.println("Enter new course : ");
					String new_course = s.next();
					op.update("course", new_course, id);
					break;

				case 3:
					System.out.println("Enter new semester : ");
					String new_sem = s.next();
					op.update("semester", new_sem, id);
					break;
				case 4:
					System.out.println("Enter new age : ");
					String new_age = s.next();
					op.update("age", new_age, id);
					break;

				default:
					System.out.println("you enter wrong choice...");
					System.out.println("bye");
					System.exit(0);
					break;
				}
				op.displayALL();
				break;

			case 3:
				System.out.println("***************************DISPLAY OPERATION******************************");
				op.displayALL();
				break;

			case 4:
				System.out.println("****************************DELETE OPERATION************************");
				op.displayALL();
				System.out.println("enter the id of the record you want to delete :");
				int ID = s.nextInt();
				op.delete(ID);
				break;

			case 5:
				System.out.println("***************BYE**************");
				System.exit(0);
				break;

			default:
				System.out.println("Enter a proper choice.........");
				break;
			}
		} while (ch != 5);
	}

}
