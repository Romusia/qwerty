package qwerty;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GroupJSON implements Serialize<Group> {

	@Override
	public void serialize(Group obj, String title) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		// Convert object to JSON string and save into a file directly
		mapper.writeValue(new File(title), obj);

	}

	
	@Override
	public Group deserialize(String title) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		Group e = mapper.readValue(new File(title), Group.class);
		return e;

	}
	public static void main(String[]args) throws IOException {
		Exam a = new Exam("Math","EXAM",LocalDate.of(2017, 10, 10));
		Exam d = new Exam("Math","EXAM",LocalDate.of(2017, 10, 11));
		Exam b = new Exam("Mova","ZALIK",LocalDate.of(2017, 10, 15));
		Exam c = new Exam("Java","EXAM",LocalDate.of(2017, 10, 20));
		TreeSet<Exam> Exam= new TreeSet<>();
		
		Exam.add(d);
		Exam.add(a);
		Exam.add(b);
		Exam.add(c);
		Group e = new Group("System analitic",Exam,107);
		new GroupJSON().serialize(e,"TestJSON.json");
	}
	
}