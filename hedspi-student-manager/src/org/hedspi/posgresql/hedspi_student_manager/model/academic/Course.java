package org.hedspi.posgresql.hedspi_student_manager.model.academic;

import java.util.ArrayList;

import org.hedspi.posgresql.hedspi_student_manager.model.hedspi.HedspiObject;

public class Course extends HedspiObject {

	@Override
	public String getClassName() {
		return "Course";
	}

	public static final String ID_CODE = "CE#";
	public static final String TOPIC_CODE = "Topic";
	public static final String NAME_CODE = "Name";
	public static final String NCREDIT_CODE = "nCredits";
	public static final String NFEE_CODE = "nFees";
	public static final String TIME_CODE = "Times";

	private String topic;

	private String name;

	private int nCredits;

	private double nFees;

	private double times;

	private ArrayList<Course> background;

	public Course(String id) {
		super(id);
	}

	public ArrayList<Course> getBackground() {
		return background;
	}
	public String getName() {
		return name;
	}
	public int getnCredits() {
		return nCredits;
	}
	public double getnFees() {
		return nFees;
	}
	public double getTimes() {
		return times;
	}
	public String getTopic() {
		return topic;
	}

	public void setBackground(ArrayList<Course> background) {
		this.background = background;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setnCredits(int nCredits) {
		this.nCredits = nCredits;
	}

	public void setnFees(double nFees) {
		this.nFees = nFees;
	}

	public void setTimes(double times) {
		this.times = times;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
