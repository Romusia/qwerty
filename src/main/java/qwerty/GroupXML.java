package qwerty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;




public class GroupXML implements Serialize<Group> {

	@Override
	public void serialize(Group obj, String title) throws IOException {
		File file = new File(title);
		String xmlString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(Group.class);
			Marshaller m = context.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

			StringWriter sw = new StringWriter();
			m.marshal(obj, sw);
			xmlString = sw.toString();
			System.out.println(xmlString);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		FileWriter fw = new FileWriter(file);
		fw.write(xmlString);
		fw.close();
	}

	@Override
	public Group deserialize(String title) throws IOException {
		Group c = null;

		try {

			File file = new File(title);
			JAXBContext jaxbContext = JAXBContext.newInstance(Group.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			c = (Group) jaxbUnmarshaller.unmarshal(file);
			System.out.println(c.toString());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return c;
		
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
		Group m = new Group("System analitic",Exam,107);

		new GroupXML().serialize(m,"TestXML.xml");
	}

}
