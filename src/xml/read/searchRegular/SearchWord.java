package xml.read.searchRegular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.read.obj.Detail;
import xml.read.obj.LoadFile;

public class SearchWord {
	/**
	 * Method tim kiem bang regular
	 * dau vao la hashmap source
	 * dau ra la hashmap ket qua 
	 */
	public boolean compare(String source, String str) {
		String regex = "(" + str.toLowerCase() + "|" + str.toUpperCase() + ")" + ".*"; 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	} 
	public HashMap<String, List<Detail>> searchRegex(HashMap<String, List<Detail>> sourceMap , String keyword) {
		HashMap<String, List<Detail>> dMap = new HashMap<String, List<Detail>>();
		
		/**
		 * tim kiem bat dau bang Str
		 * cung tim kiem luon dan xuat lowercase cua str va uppercase cua str
		 * theo sau la bat ky chu cai nao
		 */
		Set<String> keyset = sourceMap.keySet();
		for (String string : keyset) {
			if(compare(string, keyword)) {
				List<Detail> values = new ArrayList<Detail>();
				values.addAll(sourceMap.get(string));
				dMap.put(string, values);
			}
		}
		
		return dMap;
	}
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SearchWord search = new SearchWord();
		LoadFile load = new LoadFile();
		HashMap<String, List<Detail>> test = new HashMap<String,List<Detail>>();
		HashMap<String, List<Detail>> source = new HashMap<String,List<Detail>>();
		source = load.readXML("aA.xml");
		test = search.searchRegex(source, "ả");
		Set<String> keyset = test.keySet();
		if(keyset.size() != 0) {
			for (String string : keyset) {
				List<Detail> detail = test.get(string);
				for (Detail detail2 : detail) {
					System.out.println(string);
					System.out.println(detail2);
				}
			}
		}
		else {
			System.out.println("Không có kết quả tìm kiếm!");
		}
	}
}
