package xml.read.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xml.read.noAccent.UnAccent;
import xml.read.obj.CreatXML;
import xml.read.obj.Detail;
import xml.read.obj.Word;
/**
 * Chuong trinh nay dung de chia file xml theo ky tu dau tien
 * Do tu dien co the lap lai nhieu tu cho nen dung list de luu value
 * khi load du lieu ra can luu y la value la list<Detail> chu k phai la Detail
 * @author tranh
 * @author Tran Van Hieu
 * @since Oct - 17
 *
 */
public class ReadXML {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		HashMap<String, List<Detail>> MapWord = new HashMap<String,List<Detail>>();
		Word wordtmp = new Word();
		String[] str = new String[8];
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data/onetofive.xml");
			doc.getDocumentElement().normalize();
			
			// Load NodeList
			NodeList nList = doc.getElementsByTagName("LexicalEntry");
			
			// Doc tung Node
			
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i); //Hien tai moi Node nNode co name la: LexicalEntry
				if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					//Coi moi node bay gio la mot phan tu xml
					Element eElement = (Element) nNode;
					
					// Kiem tra HeadWord truoc
					if(UnAccent.convert(eElement.getElementsByTagName("HeadWord").item(0).getTextContent()).toUpperCase().startsWith("C") ) {
						NodeList eList = eElement.getChildNodes();
						
						//Bat dau doc noi dung cua node Lexical dau tien
						for(int j = 0; j < eList.getLength(); j++) {
							
							Node h = eList.item(j);
							if (h.getNodeType() == Node.ELEMENT_NODE) {

								if (h.getNodeName().equals("HeadWord")) {
									Element head = (Element) h;
									
									//Read HeadWord
									str[0] = head.getTextContent();
								} else if (h.getNodeName().equals("Morphology")) {
									NodeList mlist = h.getChildNodes();
									for (int k = 0; k < mlist.getLength(); k++) {
										Node wordtype = mlist.item(k);
										if (wordtype.getNodeType() == Node.ELEMENT_NODE) {
											Element wordType = (Element) wordtype;
									
											//read TypeWord
											str[1] = wordType.getTextContent();
										}
									}
								} else if (h.getNodeName().equals("Syntactic")) {
									NodeList slist = h.getChildNodes();
									for (int k = 0; k < slist.getLength(); k++) {
										Node s = slist.item(k);
										if (s.getNodeType() == Node.ELEMENT_NODE) {
											Element syn = (Element) s;
											if (syn.getTagName().equals("Category")) {
												
												//read Category Word
												str[2] = syn.getTextContent();
												continue;
											}
											if (syn.getTagName().equals("Subcategory")) {
												
												//read SubCateWord
												str[3] = syn.getTextContent();
												continue;
											}
											if (syn.getTagName().equals("VerbPattern")) {
												
												//read VerbPattern
												str[4] = syn.getTextContent();
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
															
															//read Category Meaning
															str[5] = cate.getTextContent();
															break;
														}
													}
												}
											} else if (semantic.getTagName().equals("SemanticConstrain"))
												continue;
											else if (semantic.getTagName().equals("Definition")) {
												
												//read Definition
												str[6] = semantic.getTextContent();
												continue;
											} else if (semantic.getTagName().equals("Example")) {
												
												//read Example
												str[7] = semantic.getTextContent();
											}
										}
									}
								}
							}
						}
						wordtmp.setHeadWord(str[0]);
						wordtmp.setDescription(str[2], str[1], str[3], str[4], str[5], str[6], str[7]);
						
						//Kiem tra xem trong Map da ton tai key dang xem xet them vao chua?
						Set<String> keyset = MapWord.keySet();
						List<Detail> values = new ArrayList<Detail>();
						for (String string : keyset) {
							if(string.equals(wordtmp.getHeadWord())) {
								
								//Neu thay co key ton tai thi tao ban clone cua Set do luu vao values
								values.addAll(MapWord.get(string));
							}
						}
						
						//Them set moi vao list of values
						values.add(wordtmp.getDesciption());
						
						//Put list of values into Map
						MapWord.put(wordtmp.getHeadWord(), values);
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Kiem tra lai source file");
			e.printStackTrace();
		}
		CreatXML exp = new CreatXML();
		exp.CreatFileXML(MapWord, "cC.xml");
	}
}
