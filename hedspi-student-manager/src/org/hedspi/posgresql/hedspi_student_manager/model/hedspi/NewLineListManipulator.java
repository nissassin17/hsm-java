package org.hedspi.posgresql.hedspi_student_manager.model.hedspi;

public class NewLineListManipulator extends HedspiObjects<HedspiObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NewLineListManipulator(String endlInput){
		super();
		parse(endlInput);
	}

	public void parse(String endlInput){
		super.clear();
		String[] vals = endlInput.split("\n");
		for(String it : vals)
			super.put(new HedspiObject(it));
	}
	
	public String getEndlnString(){
		String result = "";
		for(HedspiObject it : super.values())
			result += it.getId() + "\n";
		return result;
	}
	
}
