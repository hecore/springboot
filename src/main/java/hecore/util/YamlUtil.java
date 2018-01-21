
/**
 * @author Administrator
 *
 */
package hecore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import net.minidev.json.JSONArray;
import springfox.documentation.spring.web.json.Json;

public class YamlUtil {
	// String 最多能表示 65536 个字节
	public static String jsonPath = "src/main/resources/json/api.json";

	public static String yamlPath = "src/main/resources/yaml/api.yaml";

	public static void jsonParse() {
		try {
			String jsonStr = HttpClientService.getInstance().doGet("http://127.0.0.1:8888/v2/api-docs", new HashMap<>(),
					"utf-8");
			// System.out.println(jsonStr);
			String str = formatJson(jsonStr);
			System.out.println(str);
			jsonWriter(str);
			readJsoStrnAndCreateYaml(jsonStr,yamlPath);
			//readJsonAndCreateYaml(jsonPath, yamlPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String formatJson(String jsonStr) {
		int level = 0;
		StringBuffer jsonForMatStr = new StringBuffer();
		for (int i = 0; i < jsonStr.length(); i++) {
			char c = jsonStr.charAt(i);
			if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
				jsonForMatStr.append(getLevelStr(level));
			}
			switch (c) {
			case '{':
			case '[':
				jsonForMatStr.append(c + "\n");
				level++;
				break;
			case ',':
				jsonForMatStr.append(c + "\n");
				break;
			case '}':
			case ']':
				jsonForMatStr.append("\n");
				level--;
				jsonForMatStr.append(getLevelStr(level));
				jsonForMatStr.append(c);
				break;
			default:
				jsonForMatStr.append(c);
				break;
			}
		}
		return jsonForMatStr.toString();
	}

	private static String getLevelStr(int level) {
		StringBuffer levelStr = new StringBuffer();
		for (int levelI = 0; levelI < level; levelI++) {
			levelStr.append("\t");
		}
		return levelStr.toString();
	}

	private static void jsonWriter(String jsonStr) {
		File file = new File(jsonPath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(file.getAbsolutePath());
		Writer write;
		try {
			write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			write.write(jsonStr);
			write.flush();
			write.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 讀取json并生成yaml
	 */
	public static void readJsonAndCreateYaml(String json_url, String yaml_url) {
		try {
			String param = readJson(json_url);
			createYaml(yaml_url, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 讀取json并生成yaml
	 */
	public static void readJsoStrnAndCreateYaml(String jsonStr, String yaml_url) {
		try {
			//String param = readJsonStr(jsonStr);
			createYaml(yaml_url,jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 讀取json文件并返回字符串
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String readJson(String url) throws Exception {
		File file = new File(url);
		System.out.println("读取Json文件" + file.getAbsolutePath());
		FileReader fileReader = new FileReader(file);
		BufferedReader bufReader = new BufferedReader(fileReader);
		String message = new String();
		String line = null;
		while ((line = bufReader.readLine()) != null) {
			message += line;
		}
		return message;
	}

	/**
	 * 將json轉化為yaml格式并生成yaml文件
	 * 
	 * @param jsonString
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void createYaml(String yaml_url, String jsonString) throws JsonProcessingException, IOException {
		// parse JSON
		JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
		// save it as YAML
		String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);

		Yaml yaml = new Yaml();
		Map<String, Object> map = (Map<String, Object>) yaml.load(jsonAsYaml);

		createYamlFile(yaml_url, map);
	}

	/**
	 * 将数据写入yaml文件
	 * 
	 * @param url
	 *            yaml文件路径
	 * @param data
	 *            需要写入的数据
	 */
	public static void createYamlFile(String url, Map<String, Object> data) {
		Yaml yaml = new Yaml();
		FileWriter writer;
		try {
			writer = new FileWriter(url);
			yaml.dump(data, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// public static void formatJson(String jsonStr){
	// JSONO
	// //Json json=new Json(jsonStr);
	// System.out.println("dd");
	// }

	public static void main(String[] args) {
		jsonParse();
	}
}