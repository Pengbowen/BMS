package test;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lanyuan.actionapi.sst.FileResolve;
import com.lanyuan.assembly.sst.layeritems.ServiceSSTLayerItems;
import com.lanyuan.assembly.sst.layeritems.SingleSSTLayerItems;
import com.lanyuan.assembly.sst.standard.DAOStandard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring.xml", "classpath:config/spring-mybatis.xml" })
public class TestLayeritems
{   
	private DAOStandard dao;
	@Autowired
	public void setDao(DAOStandard dao) {
		this.dao = dao;
	}
    @Autowired
    private ServiceSSTLayerItems layerItemService;
    @Test
    public void test1(){
    SingleSSTLayerItems single = layerItemService.selectById("1201");
    	System.out.println("项目号码为:"+single.getLayerItemNo());
    	;
    	
    }
    @Test
    public void test2(){
    int i = 	dao.getCountByLayerItemId("13011","1101");
    	System.out.println(i);
    	
    }
    
    @Test
    public void test3(){
    	String path = "F:\\test\\test.xls";
    	try {
			List<List<Map<String, String>>> data = FileResolve.readExcelWithTitle(path);
			List<Map<String, String>> list = data.get(0);
			for(int i = 0;i<list.size();i++){
				System.out.println(list.get(i));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @Test
    public void test4() throws DocumentException, ParserConfigurationException{
    	String path = "C:\\Users\\Administrator\\Desktop\\lunyu";
    	EpubContent test = new EpubContent(path);
    }
    public static void main(String[] args) {
    	String path="E:\\test\\daomubiji\\";
    	if(path.endsWith("\\") || path.endsWith("/")){
    		System.out.println("yes!yse Yese");
    	}else{
    		System.out.println("no no no");
    	}
    	
		
	}
    public void  test5(){
    	
    	
    }

}
