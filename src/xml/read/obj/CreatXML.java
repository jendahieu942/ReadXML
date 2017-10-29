package xml.read.obj;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreatXML {
	/**
	 * Chuong trinh se co dau vao la hashmap
	 * tu hashmap do se nhap du lieu vao trong file xml
	 * @warning HashMap<String, List<Detail>>();
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 */
	public void CreatFileXML(HashMap<String, List<Detail>> wordMap, String fileName ) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		
		//root element
		Element rootElement = doc.createElement("Words");
		doc.appendChild(rootElement);
		
		Set<String> keyset = wordMap.keySet();
		for(String key : keyset) {
			
			// Load List of detail from HashMap
			List<Detail> values = wordMap.get(key);
			
			for (Detail detail : values) {
			
				//LexicalEntry element
				Element LexicalEntry = doc.createElement("LexicalEntry");
				rootElement.appendChild(LexicalEntry);
				
				//HeadWord element
				Element HeadWord = doc.createElement("HeadWord");
				HeadWord.appendChild(doc.createTextNode(key));
				LexicalEntry.appendChild(HeadWord);
				
				//Detail element
				Element Detail = doc.createElement("Detail");
				
				// WordType element
				Element WordType = doc.createElement("WordType");
				WordType.appendChild(doc.createTextNode(detail.getTypeWord()));
				
				// CateWord element
				Element CateWord = doc.createElement("CateWord");
				CateWord.appendChild(doc.createTextNode(detail.getCateWord()));
				
				// SubCateWord element
				Element SubCateWord = doc.createElement("SubCateWord");
				SubCateWord.appendChild(doc.createTextNode(detail.getSubCateWord()));
				
				// VerbPattern element
				Element VerbPattern = doc.createElement("VerbPattern");
				VerbPattern.appendChild(doc.createTextNode(detail.getVerbPattern()));
				
				// CateMean element
				Element CateMean = doc.createElement("CateMean");
				CateMean.appendChild(doc.createTextNode(detail.getCateMean()));
				
				// Definition element
				Element Definition = doc.createElement("Definition");
				Definition.appendChild(doc.createTextNode(detail.getDefinition()));
				
				// Example element
				Element Example = doc.createElement("Example");
				Example.appendChild(doc.createTextNode(detail.getExample()));
				
				// Put in Detail Element
				Detail.appendChild(WordType);
				Detail.appendChild(CateWord);
				Detail.appendChild(SubCateWord);
				Detail.appendChild(VerbPattern);
				Detail.appendChild(CateMean);
				Detail.appendChild(Definition);
				Detail.appendChild(Example);
				
				// Add Detail into LexicalEntry
				LexicalEntry.appendChild(Detail);
			}
		}
		
		// write the content into xml file
		TransformerFactory tfmFactory = TransformerFactory.newInstance();
		Transformer transformer = tfmFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("F://GitHUB/ReadXML/data/" + fileName));
		transformer.transform(source, result);
		
//		// output testing
//		StreamResult consoleResult = new StreamResult(System.out);
//        transformer.transform(source, consoleResult);
	}
}
