package xml.read.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data/Test.xml");

			// Read information of a word
			// Lay ra danh sach cac node cho vao list
			NodeList wordList = doc.getElementsByTagName("LexicalEntry");

			// Doc theo tung node
			for (int i = 0; i < wordList.getLength(); i++) {

				// start with a node
				Node word = wordList.item(i);
				if (word.getNodeType() == Node.ELEMENT_NODE) {

					// Moi node la mot phan tu cua xml goc
					Element n = (Element) word;

					// lay danh sach cac node con cua node cho vao list
					NodeList nlist = n.getChildNodes();

					// bat dau duyet tung node con cua mot node
					for (int j = 0; j < nlist.getLength(); j++) {
						// node con dau tien cua node lexical entry la node headword
						Node h = nlist.item(j);
						if (h.getNodeType() == Node.ELEMENT_NODE) {

							if (h.getNodeName().equals("HeadWord")) {
								Element head = (Element) h;
								System.out.println("Head Word: " + head.getTextContent());
							} else if (h.getNodeName().equals("Morphology")) { 
								//Trong Morphology co nhieu node nen ta phai doc tung node mot cua no
								NodeList mlist = h.getChildNodes();
								for (int k = 0; k < mlist.getLength(); k++) {
									Node wordtype = mlist.item(k);
									if (wordtype.getNodeType() == Node.ELEMENT_NODE) {
										Element wordType = (Element) wordtype;
										System.out.println("Word Type: " + wordType.getTextContent());
									}
								}
							} else if (h.getNodeName().equals("Syntactic")) {
								NodeList slist = h.getChildNodes();
								for (int k = 0; k < slist.getLength(); k++) {
									Node s = slist.item(k);
									if (s.getNodeType() == Node.ELEMENT_NODE) {
										Element syn = (Element) s;
										if (syn.getTagName().equals("Collocation")) {
											NodeList ncol = syn.getChildNodes();
											for (int l = 0; l < ncol.getLength(); l++) {
												Node col = ncol.item(l);
												if (col.getNodeType() == Node.ELEMENT_NODE) {
													Element ecol = (Element) col;
													System.out
															.println(ecol.getTagName() + ": " + ecol.getTextContent());
												}
											}
										}
										if (syn.getTagName().equals("Category")) {
											System.out.println("Category: " + syn.getTextContent());
											continue;
										}
										if (syn.getTagName().equals("Subcategory")) {
											System.out.println("Subcategory: " + syn.getTextContent());
											continue;
										}
										if (syn.getTagName().equals("VerbPattern")) {
											System.out.println("VerbPattern: " + syn.getTextContent());
											continue;
										}
									}
								}
							} else if (h.getNodeName().equals("Semantic")) {
								NodeList selist = h.getChildNodes();
								for (int k = 0; k < selist.getLength(); k++) {
									Node se = selist.item(k);
									if (se.getNodeType() == Node.ELEMENT_NODE) {
										Element semantic = (Element) se;
										if (semantic.getTagName().equals("LogicalConstraint")) {
											NodeList lList = semantic.getChildNodes();
											for (int l = 0; l < lList.getLength(); l++) {
												Node cMean = lList.item(l);
												if (cMean.getNodeType() == Node.ELEMENT_NODE) {
													Element cate = (Element) cMean;
													if (cate.getTagName().equals("CategoryMeaning")) {
														System.out
																.println("Category Meaning: " + cate.getTextContent());
														break;
													}
												}
											}
										} else if (semantic.getTagName().equals("SemanticConstrain"))
											continue;
										else if (semantic.getTagName().equals("Definition")) {
											System.out.println("Definition: " + semantic.getTextContent());
											continue;
										} else if (semantic.getTagName().equals("Example")) {
											System.out.println("Example: " + semantic.getTextContent());
										}
									}
								}
							}
						}
					}
				}
				System.out.println();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Kiem tra lai source file");
			e.printStackTrace();
		}
	}
}
