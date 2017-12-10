package qwerty;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.xml.bind.annotation.XmlRootElement;
import qwerty.Exam;


@XmlRootElement(name = "Group")

public class Group implements Comparable{
	private String name;
	private int number;
	
	
	@JacksonXmlProperty
	private TreeSet<Exam> exams=new TreeSet<>(new Comparator<Exam>(){
    	
	@Override
    public int compare(Exam obj1,Exam obj2) {
		return obj1.compareTo(obj2);
    }
    });  
/////////////////////////////
    public Group() {
    	this.name=" ";
    	this.number=0;
    }

    public Group(String name,TreeSet<Exam> exams,int number) {
    	this.name=name;
    	this.exams.addAll(exams);
    	this.number=number;
    }
    @JacksonXmlProperty
    public String getName() {
		return name;
	}
    
    public void setExam(TreeSet<Exam> exams) {
		this.exams.addAll(exams);
	}
    
    @JacksonXmlProperty
    public TreeSet<Exam> getExam() {
		return exams;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void AddExam(String name,String type,LocalDate publishDate){
		exams.add(new Exam(name,type,publishDate));
	} 
    
	@Override
	public String toString() {
		String s = "Groupe name=" + name +  ",Group number=" + number + ",  \n" + "Exam: ";
		for(Exam i: exams)
			s+=i + " ";
		return s;
	}
    
   public long getExamCountStream(Group group) {
	   long count =  group.exams.stream().filter((p) -> p.getTitle() != null && p.getTitle().equals("Math")).count();
	   return count;
   }
    
    
    
    public static void main(String[] args) throws JsonProcessingException {
    	
		Exam a = new Exam("Math","EXAM",LocalDate.of(2017, 10, 10));
		Exam d = new Exam("Math","EXAM",LocalDate.of(2017, 10, 11));
		Exam b = new Exam("Mova","ZALIK",LocalDate.of(2017, 10, 15));
		Exam c = new Exam("Java","EXAM",LocalDate.of(2017, 10, 20));
		TreeSet<Exam> Exam= new TreeSet<>();
		
		Exam.add(d);
		Exam.add(a);
		Exam.add(b);
		Exam.add(c);
		Group m = new Group("System analitic",Exam,107);
		
		System.out.println(new Group().getExamCountStream(m));
		System.out.println(m.toString());
		
		ObjectMapper xmlMapper = new XmlMapper();
		ObjectMapper objectMapper = new ObjectMapper();
		String xml = xmlMapper.writeValueAsString(m);
		String json = objectMapper.writeValueAsString(m);
		System.out.println(xml);
		System.out.println(json);

		//String result = new ObjectMapper().writeValueAsString(m);


		//System.out.println(result);
		
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exams == null) ? 0 : exams.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (exams == null) {
			if (other.exams != null)
				return false;
		} else if (!exams.equals(other.exams))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	}

