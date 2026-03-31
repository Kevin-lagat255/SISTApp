// SISTFrame.java
// Purpose: Single window that handles departments, lecturers and courses
// All three lists display in the same window as required by the task

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.*;

public class SISTFrame extends JFrame {

    // ─── UI Components ───────────────────────────────────────
    private JList<String> departmentList;   // shows 3 departments
    private JList<String> lecturerList;     // shows lecturers of selected dept
    private JList<String> courseList;       // shows courses of selected lecturer

    private DefaultListModel<String> departmentModel = new DefaultListModel<>();
    private DefaultListModel<String> lecturerModel   = new DefaultListModel<>();
    private DefaultListModel<String> courseModel     = new DefaultListModel<>();

    // Store IDs so we can query by ID not by name
    private int[] departmentIds;
    private int[] lecturerIds;

    // ─── Constructor ─────────────────────────────────────────
    public SISTFrame() {
        setTitle("SIST — Lecturer Course Tracker");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
        setLayout(new GridLayout(1, 3, 10, 10)); // 3 equal columns

        // Build the three panels
        add(buildDepartmentPanel());
        add(buildLecturerPanel());
        add(buildCoursePanel());

        // Load departments from DB on startup
        loadDepartments();
    }

    // ─── Panel Builders ──────────────────────────────────────

    // Left panel: departments
    private JPanel buildDepartmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("📚 Departments"));

        departmentList = new JList<>(departmentModel);
        departmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departmentList.setFont(new Font("Arial", Font.PLAIN, 14));

        // When a department is selected, load its lecturers
        departmentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = departmentList.getSelectedIndex();
                if (index >= 0) {
                    loadLecturers(departmentIds[index]);
                }
            }
        });

        panel.add(new JScrollPane(departmentList), BorderLayout.CENTER);
        return panel;
    }

    // Middle panel: lecturers
    private JPanel buildLecturerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("👨‍🏫 Lecturers"));

        lecturerList = new JList<>(lecturerModel);
        lecturerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lecturerList.setFont(new Font("Arial", Font.PLAIN, 14));

        // When a lecturer is selected, load their courses
        lecturerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = lecturerList.getSelectedIndex();
                if (index >= 0) {
                    loadCourses(lecturerIds[index]);
                }
            }
        });

        panel.add(new JScrollPane(lecturerList), BorderLayout.CENTER);
        return panel;
    }

    // Right panel: courses
    private JPanel buildCoursePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("📖 Courses"));

        courseList = new JList<>(courseModel);
        courseList.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(new JScrollPane(courseList), BorderLayout.CENTER);
        return panel;
    }

    // ─── Database Methods ─────────────────────────────────────

    // Load all departments from sist_db into the left panel
    private void loadDepartments() {
        departmentModel.clear();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT dept_id, dept_name FROM departments ORDER BY dept_name")) {

            // Count rows to size the ID array
            rs.last();
            departmentIds = new int[rs.getRow()];
            rs.beforeFirst();

            int i = 0;
            while (rs.next()) {
                departmentIds[i] = rs.getInt("dept_id");
                departmentModel.addElement(rs.getString("dept_name"));
                i++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load departments:\n" + e.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load lecturers belonging to the selected department
    private void loadLecturers(int deptId) {
        lecturerModel.clear();
        courseModel.clear(); // reset courses when dept changes

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT lecturer_id, lecturer_name FROM lecturers WHERE dept_id = ? ORDER BY lecturer_name")) {

            ps.setInt(1, deptId);
            ResultSet rs = ps.executeQuery();

            // Temporary list to size the ID array dynamically
            java.util.List<Integer> ids = new java.util.ArrayList<>();

            while (rs.next()) {
                ids.add(rs.getInt("lecturer_id"));
                lecturerModel.addElement(rs.getString("lecturer_name"));
            }

            // Convert list to array
            lecturerIds = ids.stream().mapToInt(Integer::intValue).toArray();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load lecturers:\n" + e.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load courses taught by the selected lecturer
    private void loadCourses(int lecturerId) {
        courseModel.clear();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT course_name FROM courses WHERE lecturer_id = ? ORDER BY course_name")) {

            ps.setInt(1, lecturerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                courseModel.addElement(rs.getString("course_name"));
            }

            // Inform user if lecturer has no courses assigned
            if (courseModel.isEmpty()) {
                courseModel.addElement("No courses assigned.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to load courses:\n" + e.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}