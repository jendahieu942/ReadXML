package xml.read.obj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoadFile {
	public static HashMap<String, List<Detail>> mapWord = new HashMap<String, List<Detail>>();
	public List<Detail> detail = new ArrayList<Detail>();
	public String[] str = new String[8];
	public Word word = new Word();

	/**
	 * 
	 * @param fileName, for example: "a-A.xml"
	 * @return HashMap
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public HashMap<String, List<Detail>> readXML(String fileName) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse("data/" + fileName);
		doc.getDocumentElement().normalize();
		
		//Load with LexicalEntry Element
		NodeList nList = doc.getElementsByTagName("LexicalEntry");
		
		for (int i = 0; i < nList.getLength(); i++) {
			
			//Call out one of the Lexical node
			Node nNode = nList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			
				// LexicalEntry Element
				Element eList = (Element) nNode;
				
				NodeList elementList = eList.getChildNodes();
				for (int j = 0; j < elementList.getLength(); j++) {
					
					// Step by Step :: Element inside of LexicalEntry
					Node eNode = elementList.item(j);
					if(eNode.getNodeType() == Node.ELEMENT_NODE) {
						
						Element elementNode = (Element) eNode;
						
						//if examining element is head word
						if(elementNode.getTagName().equals("HeadWord")) {
							str[0] = elementNode.getTextContent();
						} 
						
						//if it's not head word, it's sure detail
						else {
							NodeList dList = elementNode.getChildNodes();
							for (int k = 0; k < dList.getLength(); k++) {
								
								// step by step :: read word type, word category ...
								Node dNode = dList.item(k);
								if(dNode.getNodeType() == Node.ELEMENT_NODE) {
									
									Element eDetail = (Element) dNode;
									str[k+1] = eDetail.getTextContent();
								}
							}
						}
					}
				}
			}
			word.setHeadWord(str[0]);
			word.setDescription(str[2], str[1], str[3], str[4], str[5], str[6], str[7]);
			//Kiem tra xem trong Map da ton tai key dang xem xet them vao chua?
			Set<String> keyset = mapWord.keySet();
			List<Detail> values = new ArrayList<Detail>();
			for (String string : keyset) {
				if(string.equals(word.getHeadWord())) {
					
					//Neu thay co key ton tai thi tao ban clone cua Set do luu vao values
					values.addAll(mapWord.get(string));
				}
			}
			
			//Them set moi vao list of values
			values.add(word.getDesciption());
			
			//Put list of values into Map
			mapWord.put(word.getHeadWord(), values);
		}
		return mapWord;
	}
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		LoadFile lf = new LoadFile();
		mapWord = lf.readXML("aA.xml");
		Set<String> keyset = mapWord.keySet();
		for (String string : keyset) {
			if(string.equals("a")) {
			List<Detail> dtail = mapWord.get(string);
			for (Detail detail : dtail) {
				System.out.println("Word: " + string);
				System.out.println(detail);
			}
			}
			
		}
	}
}
