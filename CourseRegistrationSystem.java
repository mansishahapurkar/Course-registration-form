import java.util.*;

// Course class
class Course {
    private String courseId;
    private String courseName;
    private int maxStudents;
    private int currentStudents;
    private ArrayList<Student> enrolledStudents;

    // Constructor
    public Course(String courseId, String courseName, int maxStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxStudents = maxStudents;
        this.currentStudents = 0;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getter methods
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getCurrentStudents() {
        return currentStudents;
    }

    // Method to enroll student
    public boolean enrollStudent(Student student) {
        if (currentStudents < maxStudents) {
            enrolledStudents.add(student);
            currentStudents++;
            return true;
        } else {
            System.out.println("Course is full. Cannot enroll student " + student.getName());
            return false;
        }
    }

    // Method to display enrolled students
    public void displayEnrolledStudents() {
        System.out.println("Enrolled students in course " + courseId + ":");
        for (Student student : enrolledStudents) {
            System.out.println(student.getName());
        }
    }
}

// Student class
class Student {
    private String studentId;
    private String name;

    // Constructor
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}

// Main class
public class CourseRegistrationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the Student Course Registration System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student to Course");
            System.out.println("4. Display Enrolled Students in Course");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    enrollStudentToCourse();
                    break;
                case 4:
                    displayEnrolledStudents();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
            }
        }
    }

    // Method to add student
    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        Student student = new Student(studentId, name);
        students.add(student);
        System.out.println("Student " + name + " added successfully!");
    }

    // Method to add course
    private static void addCourse() {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter maximum number of students for the course: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Course course = new Course(courseId, courseName, maxStudents);
        courses.add(course);
        System.out.println("Course " + courseName + " added successfully!");
    }

    // Method to enroll student to course
    private static void enrollStudentToCourse() {
        if (students.isEmpty() || courses.isEmpty()) {
            System.out.println("No students or courses available to enroll. Please add students and courses first.");
            return;
        }
        System.out.println("Available students:");
        for (Student student : students) {
            System.out.println(student.getStudentId() + " - " + student.getName());
        }
        System.out.print("Enter student ID to enroll: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.println("Available courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + " - " + course.getCourseName() + " (" +
                    course.getCurrentStudents() + "/" + course.getMaxStudents() + ")");
        }
        System.out.print("Enter course ID to enroll student: ");
        String courseId = scanner.nextLine();
        Course course = findCourse(courseId);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        if (course.enrollStudent(student)) {
            System.out.println("Student " + student.getName() + " enrolled in course " + course.getCourseName() + " successfully!");
        }
    }

    // Method to display enrolled students in course
    private static void displayEnrolledStudents() {
        if (courses.isEmpty()) {
            System.out.println("No courses available to display enrolled students.");
            return;
        }
        System.out.println("Available courses:");
        for (Course course : courses) {
            System.out.println(course.getCourseId() + " - " + course.getCourseName());
        }
        System.out.print("Enter course ID to display enrolled students: ");
        String courseId = scanner.nextLine();
        Course course = findCourse(courseId);
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        course.displayEnrolledStudents();
    }

    // Helper method to find student by ID
    private static Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Helper method to find course by ID
    private static Course findCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
