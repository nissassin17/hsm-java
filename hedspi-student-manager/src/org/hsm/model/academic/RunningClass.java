package org.hsm.model.academic;

import java.util.ArrayList;

import org.hsm.model.contact.Lecturer;
import org.hsm.model.contact.Student;
import org.hsm.model.material.Room;

public class RunningClass {
	private Room room;

	private Lecturer lecturer;

	private ArrayList<Student> students;

	private Course course;

	private String result;

	boolean isFinished;

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

	public Course getCourse() {
		return course;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public String getResult() {
		return result;
	}

	public Room getRoom() {
		return room;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
