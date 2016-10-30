package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import  org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * epub格式的文档解析实体类
 * @author Administrator
 *
 */
public class EpubContent {
	//epub解压后的文件路径
	private String filePath;
	//epub配置文件路径
	private String containerPath;
	//epub配置文件
	private File container;
	//封面路径
	private String coverPagePath;
	
	//解析后的数据
	List<HashMap<String,String>> data;
	
	
	/**
	 * 构造方法
	 * @throws ParserConfigurationException 
	 */
	public EpubContent(String path) throws DocumentException, ParserConfigurationException {
		data = new ArrayList<HashMap<String,String>>();
		//处理路径
		if(path.endsWith("\\") || path.endsWith("/")){
			filePath = path;
		}else{
			filePath  = path+File.separator;
		}
		
		//生成主配置文件路径---在epub文件夹/META-INF/container.xml文件
		containerPath=filePath+"META-INF"+File.separator+"container.xml";
		container = new File(containerPath);
		//解析container.xml文件
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = factory.newDocumentBuilder();
		Document document;
		try {
			 document = db.parse(container);
			 NodeList nodes = document.getElementsByTagName("rootfile");
			 Element rootfile = (Element)nodes.item(0);
			//获取到页码配置文件fullPath
			 String fullPath =	 rootfile.getAttribute("full-path");
			 
			 /**
			  * 读取opf文件,获取章节配置文件
			  */
			 File opf_file = new File(filePath+fullPath);
			
			Document opf = db.parse(opf_file);
			 
			 NodeList opf_nodes = opf.getElementsByTagName("manifest");
			Element manifest =  (Element) opf_nodes.item(0);
			  NodeList  children = manifest.getElementsByTagName("item");
			  String opf_endPath = "";
			 for(int i=0;i<children.getLength();i++){
				 Element temp = (Element) children.item(i);
				 if(temp.hasAttribute("id")){
					 if(temp.getAttribute("id").equals("ncx")){
						 opf_endPath = temp.getAttribute("href");
					 }
				 }
			 }
			  
			 String tempPath = opf_file.getAbsolutePath();
			 int  tempIndex = tempPath.lastIndexOf(File.separator);
			 String wenjianjia = tempPath.substring(0, tempIndex+1);
			 String  ncxPath = wenjianjia+opf_endPath;
			 System.out.println("ncx文件路径获取成功,"+ncxPath);
//			//获取目录配置文件的路径
//			 String ncxPath = filePath+File.separator+fullPath.replace(".opf", ".ncx");
			 File fb_ncx = new File(ncxPath);
			 //解析fb.ncx文件
			 document = db.parse(fb_ncx);
			 //获取coverpage节点
			//Element coverpage  = document.getElementById("coverpage");
			 //获取content子节点
			// NodeList content =  coverpage.getElementsByTagName("content");
			// Element i_content = (Element) content.item(0);
			 //获取封面路径(src属性)
			// String coverpagePath = i_content.getAttribute("src");
			 //赋值给全局变量
			// coverPagePath =wenjianjia+ coverpagePath;
			
			 /**
			  * 获取navMap标签内容
			  */
			 NodeList listNode = document.getElementsByTagName("navMap");
			 Element nav = (Element) listNode.item(0);
			 /**
			  * 获取navMap下所有的navPoint子标签
			  */
			 NodeList list = nav.getElementsByTagName("navPoint");
			 for(int i=0;i<list.getLength();i++){
				 HashMap<String,String> map = new HashMap<String,String>();
				 
				 Element child = (Element) list.item(i);
				 //获取内容
				 Element con = getElementByName(child, "content");
				 String contentStr = con.getAttribute("src");
				 
				 Element ele =getElementByName(child, "navLabel");
				 Element ele1 = getElementByName(ele, "text");
				 String title = ele1.getTextContent();
				 String textPath =wenjianjia+contentStr;
				 System.out.println(title+":"+textPath);
				 map.put("title", title);
				 map.put("path", textPath);
				 
				 data.add(map);
			 }
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		//String fullPath = rootfile.get
	}
	
	private String readEpub(){
		return null;
	}
	/**
	 * 取一个标签下第一个名字为 tagName 的标签
	 */
	private Element getElementByName(Element ele,String tagName){
		NodeList nodeList = ele.getElementsByTagName(tagName);
		return (Element) nodeList.item(0);
	}
	/**
	 * 获取解析后的内容
	 */
	public List<HashMap<String,String>> getData(){
		return data;
	}
	/**
	 * 获取封面页地址 
	 * @return 封面页地址
	 */
	public String getCoverPagePath(){
		return coverPagePath;
	}
	
}
