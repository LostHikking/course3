package ru.omsu.imit.course3;

import java.sql.*;

public class JDBCBookstore {

    private static final String URL = "jdbc:mysql://localhost:3306/bookstore?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "2580";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBCBookstore Driver ");
            e.printStackTrace();
            return;
        }
        restoreBookstore();
        String query1 = "select * from books";
        String query2 = "SELECT * FROM books LIMIT 5";
        String query3 = "SELECT * FROM books WHERE id = 5";
        String query4 = "SELECT * FROM books WHERE title = \"Мёртвые души\"";
        String query5 = "SELECT * FROM books WHERE title LIKE \"Братья%\"";
        String query6 = "SELECT * FROM books WHERE title LIKE \"%Карамазовы\"";
        String query7 = "SELECT * FROM books WHERE year BETWEEN 1940 AND 1970";
        String query8 = "SELECT * FROM books WHERE pages BETWEEN 300 AND 400";
        String query9 = "SELECT * FROM books WHERE pages BETWEEN 300 AND 400 AND year BETWEEN 1940 AND 1970";
        selectFromBookstore(query1);
        selectFromBookstore(query2);
        selectFromBookstore(query3);
        selectFromBookstore(query4);
        selectFromBookstore(query5);
        selectFromBookstore(query6);
        selectFromBookstore(query7);
        selectFromBookstore(query8);
        selectFromBookstore(query9);
        String uquery1 = "UPDATE BOOKS SET pages = 300";
        String uquery2 = "UPDATE BOOKS SET pages = 350 WHERE pages > 299";
        String uquery3 = "UPDATE BOOKS SET pages = 325 WHERE pages > 299 AND YEAR < 1950";
        String uquery4 = "UPDATE BOOKS SET pages = 400 WHERE pages > 299 AND title LIKE \"М%\"";
        updateBookstore(uquery1);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(uquery2);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(uquery3);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(uquery4);
        selectFromBookstore("select * from books");
        restoreBookstore();
        String dquery1 = "DELETE FROM books WHERE pages < 400";
        String dquery2 = "DELETE FROM books WHERE title LIKE \"M%\"";
        String dquery3 = "DELETE FROM books WHERE id BETWEEN 3 AND 5";
        String dquery4 = "DELETE FROM books WHERE title = \"Собачье сердце\"";
        String dquery5 = "DELETE FROM books WHERE title = \"Мастер и Маргарита\" OR title = \"Преступление и наказание\" OR title = \"Мёртвые души\"";
        String dquery6 = "DELETE FROM books";
        updateBookstore(dquery1);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(dquery2);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(dquery3);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(dquery4);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(dquery5);
        selectFromBookstore("select * from books");
        restoreBookstore();
        updateBookstore(dquery6);
        selectFromBookstore("select * from books");
        restoreBookstore();
        String queryAddColumn1 = "ALTER TABLE BOOKS ADD COLUMN publishing_house VARCHAR(60)";
        createCovers();
        selectFromBookstore("select * from books");
        String queryAddColumn2 = "ALTER TABLE BOOKS ADD COLUMN cover VARCHAR(50)";
        String queryAddColumn3 = "ALTER TABLE BOOKS ADD FOREIGN KEY (cover) REFERENCES cover_types (cover)";
        updateBookstore(queryAddColumn1);
        selectFromBookstore("select * from books");
        updateBookstore(queryAddColumn2);
        selectFromBookstore("select * from books");
        updateBookstore(queryAddColumn3);
        selectFromBookstore("select * from books");
        String uqueryNC1 = "UPDATE BOOKS SET cover = \"без обложки\"";
        updateBookstore(uqueryNC1);
        selectFromBookstore("select * from books");
        String uqueryNC2 = "UPDATE BOOKS SET publishing_house = \"КультИздат\" WHERE id BETWEEN 1 AND 5";
        updateBookstore(uqueryNC2);
        selectFromBookstore("select * from books");
        String atquery1 = "ALTER TABLE BOOKS RENAME COLUMN title TO book_name";
        updateBookstore(atquery1);
        selectFromBookstore("select * from books");
        String atquery2 = "ALTER TABLE BOOKS RENAME bk;";
        updateBookstore(atquery2);
        selectFromBookstore("select * from bk");
        String dropquery = "DROP TABLE bk";
        updateBookstore(dropquery);
    }
    
    public static void selectFromBookstore(String querySelect){
        System.out.println("Выполнение запроса: \"" + querySelect + "\"");
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = con.createStatement();

            rs = stmt.executeQuery(querySelect);

            while (rs.next()) {
                int id  = rs.getInt("id");
                String title = "";
                try {
                    title = rs.getString("title");
                }
                catch (SQLException se){

                }
                try {
                    title = rs.getString("book_name");
                }
                catch (SQLException se){

                }
                String year = rs.getString("year");
                int pages = rs.getInt("pages");
                String publishing_house = "";
                String cover = "";
                try {
                    publishing_house = rs.getString("publishing_house");
                    cover = rs.getString("cover");
                }
                catch (SQLException se) {
                }

                System.out.print(id + " " + title + " " + year + " " + pages + " " + publishing_house + " " + cover +"\n");
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateBookstore(String queryUpdate) {
        System.out.println("Выполнение запроса: \"" + queryUpdate + "\"");
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = con.createStatement();

            stmt.executeUpdate(queryUpdate);

            con.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }





    }
    public static void restoreBookstore(){
        System.out.println("Восстановление базы данных");
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = con.createStatement();

            stmt.executeUpdate("DROP DATABASE IF EXISTS bookstore");
            stmt.executeUpdate("CREATE DATABASE bookstore");
            stmt.executeUpdate("USE bookstore");
            stmt.executeUpdate("create table books\n" +
                    "(\n" +
                    "  id    int auto_increment\n" +
                    "    primary key,\n" +
                    "  title varchar(50) not null,\n" +
                    "  year  year        null,\n" +
                    "  pages int(20)     null\n" +
                    ")");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Мастер и Маргарита\", 1929, 367)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Преступление и наказание\", 1966, 270)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Война и мир\", 1965, 1026)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Братья Карамазовы\", 1980, 590)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Идиот\", 1969, 468)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Собачье сердце\", 1925, 270)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Двенадцать стульев\", 1928, 350)");
            stmt.executeUpdate("INSERT INTO books VALUES (null, \"Мёртвые души\", 1942, 400)");
            con.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void createCovers(){
        System.out.println("Создание базы данных переплётов");
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = con.createStatement();

            stmt.executeUpdate("DROP TABLE IF EXISTS cover_types");
            stmt.executeUpdate("create table cover_types\n" +
                    "(\n" +
                    "  cover varchar(50) not null primary key\n" +
                    ")");
            stmt.executeUpdate("INSERT INTO cover_types VALUES (\"твёрдая обложка\")");
            stmt.executeUpdate("INSERT INTO cover_types VALUES (\"мягкая обложка\")");
            stmt.executeUpdate("INSERT INTO cover_types VALUES (\"без обложки\")");
            con.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}