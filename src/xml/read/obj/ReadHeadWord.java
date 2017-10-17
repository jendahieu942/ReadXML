package xml.read.obj;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadHeadWord {
	private static Word word = new Word("");
	private static ArrayList<Word> WordList = new ArrayList<Word>();

	public ArrayList<Word> readHeadWord(String nameFile) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				Document doc = builder.parse(nameFile);
				NodeList wList = doc.getElementsByTagName("LexicalEntry");
				
				for(int i = 0; i < wList.getLength(); i++) {
					Node hword = wList.item(i);
					if(hword.getNodeType() == Node.ELEMENT_NODE) {
						Element Lexical = (Element) hword;
						NodeList hlist = Lexical.getChildNodes();
						
						for(int j = 0; j < hlist.getLength(); j++) {
							Node headword = hlist.item(j);
							if(headword.getNodeName().equals("HeadWord")) {
								Element tmp = (Element) headword;
								String txt = tmp.getTextContent();
								word.setHeadWord(txt);
								System.out.println(word.getHeadWord());
								WordList.add(word);
							}
						}
					}
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return WordList;
	}
}
