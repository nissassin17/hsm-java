package org.hedspi.posgresql.hedspi_student_manager.model.academic;

import java.util.ArrayList;

import org.hedspi.posgresql.hedspi_student_manager.model.contact.Lecturer;
import org.hedspi.posgresql.hedspi_student_manager.model.contact.Student;
import org.hedspi.posgresql.hedspi_student_manager.model.material.Room;

public class RunningClass {
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public RunningClass(Room room, Lecturer lecturer,
			ArrayList<Student> students, Course course, String result,
			boolean isFinished) {
		super();
		this.room = room;
		this.lecturer = lecturer;
		this.students = students;
		this.course = course;
		this.result = result;
		this.isFinished = isFinished;
	}

	private Room room;
	private Lecturer lecturer;
	private ArrayList<Student> students;
	private Course course;
	private String result;
	boolean isFinished;
}
