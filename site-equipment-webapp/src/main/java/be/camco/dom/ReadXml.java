package be.camco.dom;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import be.camco.entities.Area;
import be.camco.entities.Device;
import be.camco.entities.DeviceGroup;
import be.camco.entities.RemoteAccessibleDevice;
import be.camco.entities.Wago;
import be.camco.enums.DeviceType;
import be.camco.enums.IOType;
import be.camco.enums.RemoteAccessibleType;
import be.camco.valueobjects.DeviceProperty;
import be.camco.valueobjects.IO;




public class ReadXml {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/container.xml");
	private List<Area> areaList = new ArrayList<>();
	private List<DeviceGroup> deviceGroupList = new ArrayList<>();
	private List<Device> deviceList = new ArrayList<>();
	Site site = context.getBean(Site.class);
	public String urlString = site.getUrlString();
	public String locationString=site.getLocationString();
	//public String versionUrlString = site.getVersionUrlString();
	public Date date = new Date();
	String version=null;
	VersionClientSocket versionClientSocket;
	
	
	public ReadXml(){
		this.parseXml();
//		this.sendGet();
//		this.sendGetVersion();
	}
	
	private Document parseXML(InputStream stream) 
		    {
		        DocumentBuilderFactory documentBuilderFactory = null;
		        DocumentBuilder documentBuilder = null;
		        Document document = null;
		        try
		        {
		            documentBuilderFactory = DocumentBuilderFactory.newInstance();
		            documentBuilder = documentBuilderFactory.newDocumentBuilder();
		            document = documentBuilder.parse(stream);
		        }
		        catch(Exception ex)
		        {
		           ex.printStackTrace();
		        }       

		        return document;
		    }

	public void parseXml(){
		areaList=new ArrayList<>();
		deviceGroupList=new ArrayList<>();
		deviceList = new ArrayList<>();
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/container.xml")){
//			XmlClientSocket xmlClientSocket = new XmlClientSocket();
//			xmlClientSocket.connect();
//			xmlClientSocket.readResponse();
//			Document document =  loadDocument(xmlClientSocket.receivedXml);
			//URL url = new URL(urlString);
			Document document = loadDocument(new File("D:\\site-equipment.xml"));//this.getDocument(url);
			Element siteEquipment=document.getDocumentElement();
			System.out.println(siteEquipment.getAttribute("site")+ " " +siteEquipment.getAttribute("version"));
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++){
				Node node=nodeList.item(i);
				if (node instanceof Element){
					switch (((Element) node).getTagName()){
					case "areas":
						NodeList areaNodes = node.getChildNodes();
						for (int h = 0; h < areaNodes.getLength(); h++){
							Node areaNode = areaNodes.item(h);
							if (areaNode instanceof Element){
								Area area = new Area(((Element) areaNode).getAttribute("name"), ((Element) areaNode).getAttribute("description"));
								areaList.add(area);
							}
						}
						break;
					case "device-group":
						DeviceGroup deviceGroup = new DeviceGroup(((Element) node).getAttribute("name"));
						NodeList deviceNodes = node.getChildNodes();
						for (int j = 0; j < deviceNodes.getLength(); j++){
							Node cNode = deviceNodes.item(j);
							if (cNode instanceof Element){ 
								int remoteAccessInt=0;
								NodeList deviceTypeNodes = cNode.getChildNodes();
								for (int s = 0; s < deviceTypeNodes.getLength(); s++){
									Node deviceTypeNode = deviceTypeNodes.item(s);
									if (deviceTypeNode.getNodeName() == "remote-access"){
										remoteAccessInt++;
									}
								}
								if (remoteAccessInt > 0){
									for (int r = 0; r<deviceNodes.getLength();r++){
									if (((Element) cNode).getAttribute("type").contains("wago")){
										Wago wago = new Wago();
										wago.setName(((Element) cNode).getAttribute("name"));
										wago.setAddress(((Element) cNode).getAttribute("address"));
										wago.setDeviceType(DeviceType.WAGO);
										wago.setParent(((Element) cNode).getAttribute("parent"));
										NodeList wagoNodes = cNode.getChildNodes();
										for (int k = 0; k < wagoNodes.getLength();k++){
											
											Node wagoNode = wagoNodes.item(k);
											if (wagoNode instanceof Element){
												switch (((Element) wagoNode).getTagName()){
												case "remote-access":
													wago.setRemoteAccessibleType(this.getRemoteAccessibleType(wagoNode));
													wago.setPort(((Element) wagoNode).getAttribute("port"));
													NodeList wagoAuthenticationNodes = wagoNode.getChildNodes();
													for (int w = 0; w < wagoAuthenticationNodes.getLength(); w++){
														Node wagoAuthenticationNode=wagoAuthenticationNodes.item(w);
														if (wagoAuthenticationNode instanceof Element){
															wago.setUsername(((Element) wagoAuthenticationNode).getAttribute("username"));
															wago.setPassword(((Element) wagoAuthenticationNode).getAttribute("password"));
														}
													}
													break;
												case "comments":
													wago.setComments(this.setComments(wagoNode));
													break;
												case "properties":
													wago.setProperties(this.setDeviceProperties(wagoNode));
													break;
												case "inputs":
													wago.setInputsEnOutputs(this.setIOs(wagoNode, IOType.INPUT));
													break;
												case "outputs":
													wago.setInputsEnOutputs(this.setIOs(wagoNode, IOType.OUTPUT));
													break;
												}
											}
										}
										deviceList.add(wago);
										System.out.println("wago added");
										deviceGroup.addDevice(wago);
									}
									else if (((Element) cNode).getAttribute("type") != null){
										RemoteAccessibleDevice remoteAccessibleDevice = new RemoteAccessibleDevice();
										remoteAccessibleDevice.setName(((Element) cNode).getAttribute("name"));
										remoteAccessibleDevice.setAddress(((Element) cNode).getAttribute("address"));
										remoteAccessibleDevice.setDeviceType(this.setDeviceType(cNode));
										remoteAccessibleDevice.setParent(((Element) cNode).getAttribute("parent"));
										NodeList remoteAccessibleNodes = cNode.getChildNodes();
										for (int u = 0; u < remoteAccessibleNodes.getLength(); u++){
											Node remoteAccessibleNode = remoteAccessibleNodes.item(u);
											if (remoteAccessibleNode instanceof Element){
												switch (((Element) remoteAccessibleNode).getTagName()){
												case "remote-access":
													remoteAccessibleDevice.setRemoteAccessibleType(this.getRemoteAccessibleType(remoteAccessibleNode));
													remoteAccessibleDevice.setPort(((Element) remoteAccessibleNode).getAttribute("port"));
													NodeList authenticationNodes = remoteAccessibleNode.getChildNodes();
													for (int o = 0; o < authenticationNodes.getLength(); o++){
														Node authenticationNode=authenticationNodes.item(o);
														if (authenticationNode instanceof Element){//childNode van remoteAccessibleNode == authentication
															remoteAccessibleDevice.setUsername(((Element) authenticationNode).getAttribute("username"));
															remoteAccessibleDevice.setPassword(((Element) authenticationNode).getAttribute("password"));
														}
													}
													break;
												case "comments":
													remoteAccessibleDevice.setComments(this.setComments(remoteAccessibleNode)); 
													break;
												case "properties":
													remoteAccessibleDevice.setProperties(this.setDeviceProperties(remoteAccessibleNode));
													break;
												}
											}
										}
										deviceList.add(remoteAccessibleDevice);
										System.out.println("remoteAccessibleDevice added");
										deviceGroup.addDevice(remoteAccessibleDevice);
										}
									}
								}else {
										Device device = new Device();
										device.setName(((Element) cNode).getAttribute("name"));
										device.setAddress(((Element) cNode).getAttribute("adress"));
										device.setDeviceType(this.setDeviceType(cNode));
										device.setParent(((Element) cNode).getAttribute("parent"));
										switch (((Element)cNode).getTagName()){
										case "comments":
											device.setComments(this.setComments(cNode));
											break;
										case "properties":
											device.setProperties(this.setDeviceProperties(cNode));
											break;
										}
										deviceGroup.addDevice(device);
										System.out.println("device added");
										deviceList.add(device);
									}
							}
						
						}
						System.out.println("all devices added to devicegroup");
						deviceGroupList.add(deviceGroup);
						if (!((Element) node).getAttribute("area").isEmpty()){
							for (Area area:areaList){
								if (area.getName().equals(((Element) node).getAttribute("area")))
								{
										area.addDeviceGroup(deviceGroup);
								}
							}
						}
						break;
					}
				}
			}
			
		}catch (Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	public Document loadDocument(File file) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
               documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
               e.printStackTrace();
        }
        Document document = null;
        try {
               document = documentBuilder.parse(file);
        } catch (SAXException e) {
               e.printStackTrace();
        } catch (IOException e) {
               e.printStackTrace();
        }
        return document;
	}
	
	private RemoteAccessibleType getRemoteAccessibleType(Node node){
		if (node instanceof Element){
			switch (((Element) node).getAttribute("type")){
			case "http":
				return RemoteAccessibleType.HTTP;
			case "radmin":
				return RemoteAccessibleType.RADMIN;
			case "vnc":
				return RemoteAccessibleType.VNC;
			}
		}
		return null;
	}
	
	private DeviceType setDeviceType(Node node){
		if (node instanceof Element){
			switch (((Element) node).getAttribute("type")){
			case "barrier":
				return DeviceType.BARRIER;
			case "kiosk":
				return DeviceType.KIOSK;
			case "intercom":
				return DeviceType.INTERCOM;
			case "server":
				return DeviceType.SERVER;
			}
		}
		return null;
	}
	
	private List<String> setComments(Node node){
		List<String> comments = new ArrayList<>();
		NodeList commentNodes = node.getChildNodes();
		for (int i = 0; i < commentNodes.getLength(); i++){
			Node commentNode = commentNodes.item(i);
			if (commentNode instanceof Element){
				String  content = commentNode.getTextContent().toString();
				comments.add(content);
			}
		}
		return comments;
	}
	
	private SortedSet<DeviceProperty> setDeviceProperties(Node node){
		SortedSet<DeviceProperty> deviceProperties = new TreeSet<>();
		NodeList propertyNodes = node.getChildNodes();
		for (int i = 0; i < propertyNodes.getLength(); i++){
			Node propertyNode = propertyNodes.item(i);
			if (propertyNode instanceof Element){
				DeviceProperty deviceProperty = new DeviceProperty(((Element) propertyNode).getAttribute("key"), ((Element) propertyNode).getAttribute("value"));
				deviceProperties.add(deviceProperty);
			}
		}
		return deviceProperties;
	}
	
	private SortedSet<IO> setIOs(Node node, IOType ioType){
		SortedSet<IO> ios = new TreeSet<>();
		NodeList ioNodes = node.getChildNodes();
		for (int i = 0; i < ioNodes.getLength(); i++){
			Node ioNode = ioNodes.item(i);
			if (ioNode instanceof Element){
				IO io = new IO(ioType, Integer.parseInt(((Element) ioNode).getAttribute("number")), ((Element) ioNode).getAttribute("name"),
						((Element) ioNode).getAttribute("on"), ((Element) ioNode).getAttribute("off"));
				ios.add(io);
			}
		}
		return ios;
	}
	
	public Document getDocument(URL url){
		Document document = null;
		try{
			if (url.getContent() != null){
				URLConnection connection = url.openConnection();
				document = parseXML(connection.getInputStream());
				//
			} else {
				document=loadDocument(new File(locationString));
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
			document=loadDocument(new File(locationString));
		}
		return document;
	}
	
	public void setLocalVersion(){
		try{
			URL url = new URL(urlString);
			org.apache.commons.io.FileUtils.copyURLToFile(url, new File(locationString));
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public boolean checkVersion(){
		try{
//			versionClientSocket = new VersionClientSocket(this.version);
//			versionClientSocket.connect();
//			versionClientSocket.sendRequest();
//			String versieControle=versionClientSocket.versieControle;
//			if (versieControle.equals("dezelfde versie")){
//				return true;
//			} else {
//				return false;
//			}
			System.out.println("checking version");
			URL url = new URL(urlString);
			Document document = this.getDocument(url);
			Document cacheDocument = this.loadDocument(new File(locationString));
			Element siteEquipment=document.getDocumentElement();
			Element cacheSiteEquipment = cacheDocument.getDocumentElement();
			String version = siteEquipment.getAttribute("version");
			String cacheVersion = cacheSiteEquipment.getAttribute("version");
			if (version.equals(cacheVersion)){
				System.out.println("same version checkVersion");
				return true;
				
			} else {
				System.out.println("different version checkVersion");
				return false;
			}
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
//	private void sendGetVersion(){
//		try{
//			URL url = new URL(versionUrlString);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET");
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' response to URL: "+urlString);
//			System.out.println("Response code: "+responseCode);
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while((inputLine=in.readLine())!=null){
//				response.append(inputLine);
//			}
//			in.close();
//			System.out.println(response.toString());
//			this.version=response.toString();
//		} catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
//	private void sendGet() {
//		try{
//		URL obj = new URL(urlString);
//		HttpURLConnection con=(HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("GET");
//		//con.setRequestProperty("version", version);
//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'GET' response to URL: "+urlString);
//		System.out.println("Response code: "+responseCode);
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//		while((inputLine=in.readLine())!=null){
//			response.append(inputLine);
//		}
//		in.close();
//		System.out.println(response.toString());
//		}catch (Exception ex){
//			ex.printStackTrace();
//		}
//	}
	
	public List<DeviceGroup> getDeviceGroups(){
		return deviceGroupList;
	}
	public List<Device> getDevices(){
		return deviceList;
	}
	public List<Area> getAreas(){
		return areaList;
	}	
}
	

