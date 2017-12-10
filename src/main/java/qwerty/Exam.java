package qwerty;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;




@SuppressWarnings("rawtypes")
@XmlRootElement(name="Exam")

public class Exam implements Comparable{
	
	public static enum Type{
		EXAM,
		ZALIK;
	};
	
	private String title;
	private Type type;
	private LocalDate writingDate;
	@JacksonXmlProperty
	//getters
	public String getTitle() {
		return title;
	}
	
	public Type getType() {
		return type;
	}
	
	public LocalDate getwritingDate() {
		return writingDate;
	}
	/////////////////////////////	
	//setters	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setType(String type) {
		switch (type) {
		case "EXAM":
			this.type = Exam.Type.EXAM;
			break;
		case "ZALIK":
			this.type = Exam.Type.ZALIK;
			break;
		}
	}
	
	public void setwritingDate(LocalDate writingDate) {
		this.writingDate = writingDate;
	}
	/////////////////////////////
	
	public Exam() {
		this.title="";
		this.writingDate = LocalDate.of(1990, 01, 01);
	}

	public Exam(String name) {
		this.title=name;
		this.writingDate = LocalDate.of(1990, 01, 01);
	}

	public Exam(String name, String type,LocalDate writingDate) {
		this.title=name;
		this.setType(type);;
		this.writingDate = writingDate;
	}
	
	public void swaptype(Type type) {
		switch(type) {
		case EXAM:
			this.type=Exam.Type.ZALIK;
			break;
		case ZALIK:
			this.type=Exam.Type.EXAM;
			break;			
		}
	}
	//@Override
	public String toString() {
		String s = "Exam name=" + title + ",Type: " + type +", Writing date=" + writingDate  ;
		return s;
	}
	
	public int compareTo(Object obj) {
		Exam one = (Exam)obj;
		if(one.title.length()<this.title.length())
			return -1;
		else return 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Exam other = (Exam) obj;
		if (title.equals(other.title))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


////////////////////////////////////////////////////
	Exam(ExamBuilder builder) {
		this.title=builder.title;
		this.writingDate=builder.writingDate;
	}

	public static class ExamBuilder {
		String type;
		String title;
		LocalDate writingDate;

		final static String ARTICLE_NAME_PATERN="^[A-Z][a-z -]+$";

		public ExamBuilder title (String name) {
			Pattern pattern = Pattern.compile(ARTICLE_NAME_PATERN);
			Matcher matcher = pattern.matcher(name);
			if(matcher.matches())
				this.title=name;
			else throw new IllegalArgumentException("Name is invalid");
			return this;
		}

		public ExamBuilder type(String type) {
			this.type=type;
			return this;
		}

		public ExamBuilder writingDate(LocalDate writingDate) {
			this.writingDate = writingDate;
			return this;
		}

		public String getTitle() {
			return title;
		}
		public LocalDate getwritingDate() {
			return writingDate;
		}
	/*	@JacksonXmlProperty
		public Type getType() {
			return type;
		}
*/
		public Exam build() {
			return new Exam(this);
		}
	}

	
	public class EmployeeJson implements Serialize<Exam> {

		@Override
		public void serialize(Exam obj, String title) throws IOException {

			ObjectMapper mapper = new ObjectMapper();

			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File(title), obj);

		}

		
		@Override
		public Exam deserialize(String title) throws IOException {
			ObjectMapper mapper = new ObjectMapper();

			Exam e = mapper.readValue(new File(title), Exam.class);
			return e;

		}
	}

///////////////////////////////////////////////////

//	Exam.stream().noneMatch(EXAM::equals)

	public void main(String[] args) throws JsonProcessingException {
		
	Exam b = new Exam("Math","EXAM",LocalDate.of(2017, 10, 10));
	//b.setType(Exam.Type.EXAM);
	System.out.println(b.toString());
	ObjectMapper xmlMapper = new XmlMapper();
	String xml = xmlMapper.writeValueAsString(b);
	System.out.println(xml);
	//Exam a = new ExamBuilder()
	//		.title("Math")
	//		.type("EXZAM")
	//		.writingDate(LocalDate.of(2017, 10, 10))
	//		.build();
	//System.out.println(a.toString());
 }
}