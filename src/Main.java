import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(
                    System.getenv("connection"),
                    System.getenv("username"),
                    System.getenv("password"));
            System.out.println(conn);

            String sql = "select * from Movie";
            var statement = conn.prepareStatement(sql);
            var result = statement.executeQuery();

            while(result.next()) {
                System.out.println(result.getString("title") + ": " + result.getString("year"));
            }
            addMovie(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void addMovie(Connection conn) throws SQLException {
        String sql = "insert into Movie(title, year, price) values('Back to the future', 1985, 6.99)";
        var statement = conn.prepareStatement(sql);
        int result = statement.executeUpdate();
        if (result>0) {
            System.out.println("Update successful");
        }

    }
}
