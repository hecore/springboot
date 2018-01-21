
/**
 * @author Administrator
 *
 */
package hecore.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hecore.util.YamlUtil;

@RestController
public class RestYamlController {
	public static String jsonPath = "src/main/resources/json/api.json";

	public static String yamlPath = "src/main/resources/yaml/api.yaml";
	
	public static String uploadPath="E:/hecore";

	@RequestMapping(value = "/yamlDownload", method = RequestMethod.GET)
	public void yamlDownload(HttpServletResponse res) {
		commonDownload(res, "api.yaml", yamlPath);
	}

	@RequestMapping(value = "/jsonDownload", method = RequestMethod.GET)
	public void jsonDownload(HttpServletResponse res) {
		commonDownload(res, "api.json", jsonPath);
	}

	/**
	 * 实现文件上传
	 */
	@RequestMapping("/fileUpload")
	@ResponseBody
	public String fileUpload(@RequestParam("fileName") MultipartFile file) {
		if (file.isEmpty()) {
			return "false";
		}
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		File dest = new File(uploadPath + "/" + fileName);
		if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); // 保存文件
			return "true";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 实现多文件上传
	 */
	@RequestMapping(value = "/multifileUpload", method = RequestMethod.POST)
	/**
	 * public @ResponseBody String multifileUpload(@RequestParam("fileName")List
	 * <MultipartFile> files)
	 */
	public @ResponseBody String multifileUpload(HttpServletRequest request) {

		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");

		if (files.isEmpty()) {
			return "false";
		}


		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			int size = (int) file.getSize();
			System.out.println(fileName + "-->" + size);

			if (file.isEmpty()) {
				return "false";
			} else {
				File dest = new File(uploadPath + "/" + fileName);
				if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
					dest.getParentFile().mkdir();
				}
				try {
					file.transferTo(dest);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "false";
				}
			}
		}
		return "true";
	}

	public void commonDownload(HttpServletResponse res, String filename, String yamlPath) {
		YamlUtil.jsonParse();// 文件生成
		String fileName = filename;
		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(yamlPath)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}

	public static void main(String[] args) {

	}
}
