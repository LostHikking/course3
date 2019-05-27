package ru.omsu.imit.course3.lab5.server.selectioncommittee.rest.request;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Applicant;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Application;
import ru.omsu.imit.course3.lab5.server.selectioncommittee.Score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static ru.omsu.imit.course3.lab5.server.selectioncommittee.jooq.generated.Tables.*;


public class DBItemRequest {
    private static final String URL = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "2580";
    private String text;
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public Applicant getApplicantByID(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> fetch = create.select().from(APPLICANTS).where(APPLICANTS.ID.eq(id)).fetch();
            return fetch.stream().map(it -> {
                Applicant applicant = new Applicant();
                applicant.setId(it.getValue(APPLICANTS.ID));
                applicant.setFirstName(it.getValue(APPLICANTS.FIRST_NAME));
                applicant.setLastName(it.getValue(APPLICANTS.LAST_NAME));
                applicant.setBirhdate(it.getValue(APPLICANTS.BIRTHDATE));
                applicant.setApplications(
                        create.select().from(APPLICATIONS).where(APPLICATIONS.APPLICANT_ID.eq(applicant.getId())).fetch()
                                .stream()
                                .map(application -> {
                                    Application result = new Application();
                                    result.setId(application.getValue(APPLICATIONS.ID));
                                    result.setSpecialtyID(application.getValue(APPLICATIONS.SPECIALTY_ID));
                                    result.setApplicantID(application.getValue(APPLICATIONS.APPLICANT_ID));
                                    return result;
                                }).collect(Collectors.toList()));
                applicant.setScore(
                        create.select().from(RESULTSOFEXAMS).where(RESULTSOFEXAMS.APPLICANT_ID.eq(applicant.getId())).fetch()
                                .stream()
                                .map(application -> {
                                    Score result = new Score();
                                    result.setScore(application.getValue(RESULTSOFEXAMS.RESULT));
                                    result.setDisciplineID(application.getValue(RESULTSOFEXAMS.DISCIPLINE_ID));
                                    return result;
                                }).collect(Collectors.toList()));
                return applicant;
            }).findAny().get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally

//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Error loading JDBCBookstore Driver ");
//			e.printStackTrace();
//			return null;
//		}
//		try {
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//
//			stmt = con.createStatement();
//
//			rs = stmt.executeQuery("SELECT * FROM selectioncommittee.applicants WHERE ID = " + id + ";");
//			rs.next();
//				applicant.setId(rs.getInt("id"));
//				applicant.setBirhdate(rs.getDate("birthdate"));
//				applicant.setFirstName(rs.getString("first_name"));
//				applicant.setLastName(rs.getString("last_name"));
//
//		} catch (SQLException sqlEx) {
//			sqlEx.printStackTrace();
//			return null;
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//			try {
//				stmt.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//			try {
//				rs.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
    }

    public List<Applicant> getAllApplicants() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> fetch = create.select().from(APPLICANTS).fetch();
            return fetch.stream().map(it -> {
                Applicant applicant = new Applicant();
                applicant.setId(it.getValue(APPLICANTS.ID));
                applicant.setFirstName(it.getValue(APPLICANTS.FIRST_NAME));
                applicant.setLastName(it.getValue(APPLICANTS.LAST_NAME));
                applicant.setBirhdate(it.getValue(APPLICANTS.BIRTHDATE));
                applicant.setApplications(
                        create.select().from(APPLICATIONS).where(APPLICATIONS.APPLICANT_ID.eq(applicant.getId())).fetch()
                                .stream()
                                .map(application -> {
                                    Application result = new Application();
                                    result.setId(application.getValue(APPLICATIONS.ID));
                                    result.setSpecialtyID(application.getValue(APPLICATIONS.SPECIALTY_ID));
                                    result.setApplicantID(application.getValue(APPLICATIONS.APPLICANT_ID));
                                    return result;
                                }).collect(Collectors.toList()));
                applicant.setScore(
                        create.select().from(RESULTSOFEXAMS).where(RESULTSOFEXAMS.APPLICANT_ID.eq(applicant.getId())).fetch()
                                .stream()
                                .map(application -> {
                                    Score result = new Score();
                                    result.setScore(application.getValue(RESULTSOFEXAMS.RESULT));
                                    result.setDisciplineID(application.getValue(RESULTSOFEXAMS.DISCIPLINE_ID));
                                    return result;
                                }).collect(Collectors.toList()));
                return applicant;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
//			List<Applicant> applicantList = Collections.synchronizedList(new ArrayList<>());
//			Result<Record> fetch = create.select().from(APPLICANTS).leftJoin(APPLICATIONS).onKey().fetch();
//			fetch.stream().map(it -> {
//				Applicant applicant = new Applicant();
//				applicant.setId(it.getValue(APPLICANTS.ID));
//				applicant.setFirstName(it.getValue(APPLICANTS.FIRST_NAME));
//				applicant.setLastName(it.getValue(APPLICANTS.LAST_NAME));
//				applicant.setBirhdate(it.getValue(APPLICANTS.BIRTHDATE));
//				applicantList.add(applicant);
//			}).findAny().get();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
    }

    public int AddApplicant(Applicant applicant) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Record record =
                    create.insertInto(APPLICANTS, APPLICANTS.ID, APPLICANTS.FIRST_NAME, APPLICANTS.LAST_NAME, APPLICANTS.BIRTHDATE)
                            .values(applicant.getId(), applicant.getFirstName(), applicant.getLastName(), applicant.getBirhdate())
                            .returning(APPLICANTS.ID)
                            .fetchOne();
            return record.getValue(APPLICANTS.ID);
        } catch (org.jooq.exception.DataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int UpdateApplicant(Applicant applicant, int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            return create.update(APPLICANTS)
                    .set(APPLICANTS.BIRTHDATE, applicant.getBirhdate())
                    .set(APPLICANTS.FIRST_NAME, applicant.getFirstName())
                    .set(APPLICANTS.LAST_NAME, applicant.getLastName())
                    .where(APPLICANTS.ID.eq(id))
                    .returning(APPLICANTS.ID)
                    .fetchOne()
                    .getValue(APPLICANTS.ID);
        } catch (org.jooq.exception.DataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public DBItemRequest(String text) {
        super();
        this.text = text;
    }

    public DBItemRequest() {

    }

    public String getText() {
        return text;
    }


    public int DeleteApplicant(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            create.delete(APPLICANTS)
                    .where(APPLICANTS.ID.eq(id))
                    .execute();
            return 1;
        } catch (org.jooq.exception.DataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int DeleteApplication(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            create.delete(APPLICATIONS)
                    .where(APPLICATIONS.ID.eq(id))
                    .execute();
            return 1;
        } catch (org.jooq.exception.DataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int AddApplication(Application application) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Record record =
                    create.insertInto(APPLICATIONS, APPLICATIONS.ID, APPLICATIONS.APPLICANT_ID, APPLICATIONS.SPECIALTY_ID)
                            .values(application.getId(), application.getApplicantID(), application.getSpecialtyID())
                            .returning(APPLICATIONS.ID)
                            .fetchOne();
            return record.getValue(APPLICATIONS.ID);
        } catch (org.jooq.exception.DataAccessException e) {
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int UpdateApplication(Application application, int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            return create.update(APPLICATIONS)
                    .set(APPLICATIONS.ID, application.getId())
                    .set(APPLICATIONS.APPLICANT_ID, application.getApplicantID())
                    .set(APPLICATIONS.SPECIALTY_ID, application.getSpecialtyID())
                    .where(APPLICATIONS.ID.eq(id))
                    .returning(APPLICATIONS.ID)
                    .fetchOne()
                    .getValue(APPLICATIONS.ID);
        } catch (org.jooq.exception.DataAccessException e) {
            return -1;
        }
        catch (NullPointerException e){
            return -2;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Application getApplicationByID(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> fetch = create.select().from(APPLICATIONS).where(APPLICATIONS.ID.eq(id)).fetch();
            return fetch.stream().map(it -> {
                Application application = new Application();
                application.setId(it.getValue(APPLICATIONS.ID));
                application.setApplicantID(it.getValue(APPLICATIONS.APPLICANT_ID));
                return application;
            }).findAny().get();
        }
        catch (NoSuchElementException e){
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Application> getAllApplications() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> fetch = create.select().from(APPLICATIONS).fetch();
            return fetch.stream().map(it -> {
                Application application = new Application();
                application.setId(it.getValue(APPLICATIONS.ID));
                application.setApplicantID(it.getValue(APPLICATIONS.APPLICANT_ID));
                return application;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
//			List<Applicant> applicantList = Collections.synchronizedList(new ArrayList<>());
//			Result<Record> fetch = create.select().from(APPLICANTS).leftJoin(APPLICATIONS).onKey().fetch();
//			fetch.stream().map(it -> {
//				Applicant applicant = new Applicant();
//				applicant.setId(it.getValue(APPLICANTS.ID));
//				applicant.setFirstName(it.getValue(APPLICANTS.FIRST_NAME));
//				applicant.setLastName(it.getValue(APPLICANTS.LAST_NAME));
//				applicant.setBirhdate(it.getValue(APPLICANTS.BIRTHDATE));
//				applicantList.add(applicant);
//			}).findAny().get();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
    }
}