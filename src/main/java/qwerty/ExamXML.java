package qwerty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.TreeSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ExamXML implements Serialize<Exam> {
	
  

		@Override
		public void serialize(Exam obj, String title) throws IOException {
			File file = new File(title);
			String xmlString = "";
			try {
				JAXBContext context = JAXBContext.newInstance(Exam.class);
				Marshaller m = context.createMarshaller();

				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

				StringWriter sw = new StringWriter();
				m.marshal(obj, sw);
				xmlString = sw.toString();

			} catch (JAXBException e) {
				e.printStackTrace();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(xmlString);
			fw.close();
		}

		@Override
		public Exam deserialize(String title) throws IOException {
			
			Exam e = null;
			try {

				File file = new File(title);
				JAXBContext jaxbContext = JAXBContext.newInstance(Exam.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				e = (Exam) jaxbUnmarshaller.unmarshal(file);

			} catch (JAXBException err) {
				err.printStackTrace();
			}

			return e;

		}

}
