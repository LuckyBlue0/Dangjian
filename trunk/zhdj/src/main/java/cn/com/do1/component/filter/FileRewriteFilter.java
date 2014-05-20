package cn.com.do1.component.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.util.CommonFileUtils;
import cn.com.do1.dqdp.core.ConfigLoadExcetion;

/**用于文件重写,用于访问不在web容器中的资源时
 * @author Linjj
 *
 */
public class FileRewriteFilter extends OncePerRequestFilter {

	private transient final static Logger log = LoggerFactory
			.getLogger(FileRewriteFilter.class);
	
	/**
	 * 需要重写的后缀组成的字符串.用","隔开,不分大小写.如:.jpg,.png,.css
	 */
	private String suffix;
	
	/**
	 * 不需要重写的路径段，如：/images|/image(暂时不用)
	 */
	private String path_include;
	/**
	 * 正则表达式,用于匹配请求资源路径.通过处理suffix得到,类似(:?.*\.jpg|.*\.gif|.*\.png)$
	 */
	private String regex = "";

	/**
	 * 正则表达式，用于匹配请求资源路径，通过处理path_include
	 */
	private String reg_path="";
	
	private Pattern pattern =null;
	
	/**
	 * 需要禁止访问的后缀组成的字符串.用","隔开,不分大小写.如:.jpg,.png,.css
	 */
	private String forbidden;
	
	private String regexForbidden;
	 
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public void setPath_include(String path_include) {
		this.path_include = path_include;
	}

	protected void initFilterBean() throws ServletException {
		if(!AssertUtil.isEmpty(suffix)){
			regex = "(:?.*\\.(:?" + suffix + "))$";
			System.out.println(regex);
		}
		if(StringUtils.isNotBlank(path_include)){
			//reg_path=path_include;
			reg_path=".*("+path_include+").*";
			pattern=Pattern.compile(reg_path);
		}
		
		if(StringUtils.isNotBlank(forbidden)){
			regexForbidden = "(:?.*\\.(:?" + forbidden + "))$";
		}
		
			return ;
		/*regex = "(:?"//不捕获
			+ suffix.replaceAll("\\s*", "")//替换空格
			.replaceAll(",{2,}", ",")//替换多余的逗号
			.replaceAll("^,|,$", "")//替换头尾逗号
			.replaceAll(",", "|")//将逗号替换为或运算
			.replaceAll("\\.", ".*\\\\.")//将后缀点转义，同时追加任意匹配
			+")$";//以后缀结尾*/	
	
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(this.needForbidden(request.getServletPath())){
			response.sendError(404);
			return;
		}
		if (this.needRewrite(request.getServletPath())){
			try {
				rewrite(request, response, filterChain);
			} catch (ConfigLoadExcetion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else{
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ConfigLoadExcetion 
	 */
	private void rewrite(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) throws
			IOException, ConfigLoadExcetion {
		InputStream fis = null;
		OutputStream os = null;
		//log.debug("rewrite==>" + request.getServletPath());
		try {
			os = response.getOutputStream();
			fis = CommonFileUtils.getInputStream(null, request.getServletPath());
			if(fis != null){
				this.setMime(request, response);
				byte[] b = new byte[1024*4];
				int len=0;
				while((len=fis.read(b)) > 0){
					os.write(b,0,len);
				}
			}else{
				//如果文件不存在，交给容器处理
				log.warn(request.getServletPath()+"不存在,交回容器处理");
				filterChain.doFilter(request, response);
				return;
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("error_path========>>"+request.getServletPath());
		}finally{
			if(fis != null){
				fis.close();
			}
			/*if(os != null){
				os.flush();
			}*/
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void setMime(HttpServletRequest request, HttpServletResponse response) {
		String mime = mimeMaps.get(request.getServletPath().substring(request.getServletPath().lastIndexOf(".")+1).toLowerCase());
		if(!AssertUtil.isEmpty(mime)){
			response.setContentType(mime);
		}
	}
	
	private boolean needRewrite(String path){
		if(pattern.matcher(path.toLowerCase()).matches()){
			return false;
		}
		return path.toLowerCase().matches(regex);
	}
	
	private boolean needForbidden(String path){
		return path.toLowerCase().matches(regexForbidden);
	}

	public static void main(String[] args) {
		/*String suffix = " ,  .jpg, ,.gif,.png,";
		suffix = "(:?"+suffix.replaceAll("\\s*", "").replaceAll(",{2,}", ",").replaceAll("^,|,$", "").replaceAll(",", "|").replaceAll("\\.", ".*\\\\.")+")$";
		System.out.println(suffix);
		System.out.println("xx.jpg".matches(suffix));
		System.out.println("xx.jpg".matches("(?:.*jpg|.*png)$"));*/
		
		System.out.println( ".*(/images)|(/fckeditor).*");
		 Pattern p =Pattern.compile(".*(/images).*"); // Pattern.compile("[/portalf|/portfal|/icofn]");
		 Matcher m = p.matcher("/struts/images/js/fckeditor/editor/fckeditor.html");
		 boolean b = m.matches();
		 //Pattern.matches("/images", "/images/portal/icon/quanPic02.png")
		//Pattern m=Pattern.compile("");
		 System.out.println( b);
	}
	/**
	 * MIME类型映射
	 */
	private static Map<String, String> mimeMaps = new HashMap<String, String>();
	static {
		mimeMaps.put("abs", "audio/x-mpeg");
		mimeMaps.put("ai", "application/postscript");
		mimeMaps.put("aif", "audio/x-aiff");
		mimeMaps.put("aifc", "audio/x-aiff");
		mimeMaps.put("aiff", "audio/x-aiff");
		mimeMaps.put("aim", "application/x-aim");
		mimeMaps.put("art", "image/x-jg");
		mimeMaps.put("asf", "video/x-ms-asf");
		mimeMaps.put("asx", "video/x-ms-asf");
		mimeMaps.put("au", "audio/basic");
		mimeMaps.put("avi", "video/x-msvideo");
		mimeMaps.put("avx", "video/x-rad-screenplay");
		mimeMaps.put("bcpio", "application/x-bcpio");
		mimeMaps.put("bin", "application/octet-stream");
		mimeMaps.put("bmp", "image/bmp");
		mimeMaps.put("body", "text/html");
		mimeMaps.put("cdf", "application/x-cdf");
		mimeMaps.put("cer", "application/x-x509-ca-cert");
		mimeMaps.put("class", "application/java");
		mimeMaps.put("cpio", "application/x-cpio");
		mimeMaps.put("csh", "application/x-csh");
		mimeMaps.put("css", "text/css");
		mimeMaps.put("dib", "image/bmp");
		mimeMaps.put("doc", "application/msword");
		mimeMaps.put("dtd", "application/xml-dtd");
		mimeMaps.put("dv", "video/x-dv");
		mimeMaps.put("dvi", "application/x-dvi");
		mimeMaps.put("eps", "application/postscript");
		mimeMaps.put("etx", "text/x-setext");
		mimeMaps.put("exe", "application/octet-stream");
		mimeMaps.put("gif", "image/gif");
		mimeMaps.put("gtar", "application/x-gtar");
		mimeMaps.put("gz", "application/x-gzip");
		mimeMaps.put("hdf", "application/x-hdf");
		mimeMaps.put("hqx", "application/mac-binhex40");
		mimeMaps.put("htc", "text/x-component");
		mimeMaps.put("htm", "text/html");
		mimeMaps.put("html", "text/html");
		mimeMaps.put("hqx", "application/mac-binhex40");
		mimeMaps.put("ief", "image/ief");
		mimeMaps.put("jad", "text/vnd.sun.j2me.app-descriptor");
		mimeMaps.put("jar", "application/java-archive");
		mimeMaps.put("java", "text/plain");
		mimeMaps.put("jnlp", "application/x-java-jnlp-file");
		mimeMaps.put("jpe", "image/jpeg");
		mimeMaps.put("jpeg", "image/jpeg");
		mimeMaps.put("jpg", "image/jpeg");
		mimeMaps.put("js", "text/javascript");
		mimeMaps.put("jsf", "text/plain");
		mimeMaps.put("jspf", "text/plain");
		mimeMaps.put("kar", "audio/x-midi");
		mimeMaps.put("latex", "application/x-latex");
		mimeMaps.put("m3u", "audio/x-mpegurl");
		mimeMaps.put("mac", "image/x-macpaint");
		mimeMaps.put("man", "application/x-troff-man");
		mimeMaps.put("mathml", "application/mathml+xml");
		mimeMaps.put("me", "application/x-troff-me");
		mimeMaps.put("mid", "audio/x-midi");
		mimeMaps.put("midi", "audio/x-midi");
		mimeMaps.put("mif", "application/x-mif");
		mimeMaps.put("mov", "video/quicktime");
		mimeMaps.put("movie", "video/x-sgi-movie");
		mimeMaps.put("mp1", "audio/x-mpeg");
		mimeMaps.put("mp2", "audio/x-mpeg");
		mimeMaps.put("mp3", "audio/x-mpeg");
		mimeMaps.put("mp4", "video/mp4");
		mimeMaps.put("mpa", "audio/x-mpeg");
		mimeMaps.put("mpe", "video/mpeg");
		mimeMaps.put("mpeg", "video/mpeg");
		mimeMaps.put("mpega", "audio/x-mpeg");
		mimeMaps.put("mpg", "video/mpeg");
		mimeMaps.put("mpv2", "video/mpeg2");
		mimeMaps.put("ms", "application/x-wais-source");
		mimeMaps.put("nc", "application/x-netcdf");
		mimeMaps.put("oda", "application/oda");
		mimeMaps.put("odb", "application/vnd.oasis.opendocument.database");
		mimeMaps.put("odc", "application/vnd.oasis.opendocument.chart");
		mimeMaps.put("odf", "application/vnd.oasis.opendocument.formula");
		mimeMaps.put("odg", "application/vnd.oasis.opendocument.graphics");
		mimeMaps.put("odi", "application/vnd.oasis.opendocument.image");
		mimeMaps.put("odm", "application/vnd.oasis.opendocument.text-master");
		mimeMaps.put("odp", "application/vnd.oasis.opendocument.presentation");
		mimeMaps.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
		mimeMaps.put("odt", "application/vnd.oasis.opendocument.text");
		mimeMaps.put("ogg", "application/ogg");
		mimeMaps.put("otg ", "application/vnd.oasis.opendocument.graphics-template");
		mimeMaps.put("oth", "application/vnd.oasis.opendocument.text-web");
		mimeMaps.put("otp", "application/vnd.oasis.opendocument.presentation-template");
		mimeMaps.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template ");
		mimeMaps.put("ott", "application/vnd.oasis.opendocument.text-template");
		mimeMaps.put("pbm", "image/x-portable-bitmap");
		mimeMaps.put("pct", "image/pict");
		mimeMaps.put("pdf", "application/pdf");
		mimeMaps.put("pgm", "image/x-portable-graymap");
		mimeMaps.put("pic", "image/pict");
		mimeMaps.put("pict", "image/pict");
		mimeMaps.put("pls", "audio/x-scpls");
		mimeMaps.put("png", "image/png");
		mimeMaps.put("pnm", "image/x-portable-anymap");
		mimeMaps.put("pnt", "image/x-macpaint");
		mimeMaps.put("ppm", "image/x-portable-pixmap");
		mimeMaps.put("ppt", "application/vnd.ms-powerpoint");
		mimeMaps.put("pps", "application/vnd.ms-powerpoint");
		mimeMaps.put("ps", "application/postscript");
		mimeMaps.put("psd", "image/x-photoshop");
		mimeMaps.put("qt", "video/quicktime");
		mimeMaps.put("qti", "image/x-quicktime");
		mimeMaps.put("qtif", "image/x-quicktime");
		mimeMaps.put("ras", "image/x-cmu-raster");
		mimeMaps.put("rdf", "application/rdf+xml");
		mimeMaps.put("rgb", "image/x-rgb");
		mimeMaps.put("rm", "application/vnd.rn-realmedia");
		mimeMaps.put("roff", "application/x-troff");
		mimeMaps.put("rtf", "application/rtf");
		mimeMaps.put("rtx", "text/richtext");
		mimeMaps.put("sh", "application/x-sh");
		mimeMaps.put("shar", "application/x-shar");
		mimeMaps.put("smf", "audio/x-midi");
		mimeMaps.put("sit", "application/x-stuffit");
		mimeMaps.put("snd", "audio/basic");
		mimeMaps.put("src", "application/x-wais-source");
		mimeMaps.put("sv4cpio", "application/x-sv4cpio");
		mimeMaps.put("sv4crc", "application/x-sv4crc");
		mimeMaps.put("svg", "image/svg+xml");
		mimeMaps.put("svgz", "image/svg+xml");
		mimeMaps.put("swf", "application/x-shockwave-flash");
		mimeMaps.put("t", "application/x-troff");
		mimeMaps.put("tar", "application/x-tar");
		mimeMaps.put("tcl", "application/x-tcl");
		mimeMaps.put("tex", "application/x-tex");
		mimeMaps.put("texi", "application/x-texinfo");
		mimeMaps.put("texinfo", "application/x-texinfo");
		mimeMaps.put("tif", "image/tiff");
		mimeMaps.put("tiff", "image/tiff");
		mimeMaps.put("tr", "application/x-troff");
		mimeMaps.put("tsv", "text/tab-separated-values");
		mimeMaps.put("txt", "text/plain");
		mimeMaps.put("ulw", "audio/basic");
		mimeMaps.put("ustar", "application/x-ustar");
		mimeMaps.put("vxml", "application/voicexml+xml");
		mimeMaps.put("xbm", "image/x-xbitmap");
		mimeMaps.put("xht", "application/xhtml+xml");
		mimeMaps.put("xhtml", "application/xhtml+xml");
		mimeMaps.put("xls", "application/vnd.ms-excel");
		mimeMaps.put("xml", "application/xml");
		mimeMaps.put("xpm", "image/x-xpixmap");
		mimeMaps.put("xsl", "application/xml");
		mimeMaps.put("xslt", "application/xslt+xml");
		mimeMaps.put("xul", "application/vnd.mozilla.xul+xml");
		mimeMaps.put("xwd", "image/x-xwindowdump");
		mimeMaps.put("vsd", "application/x-visio");
		mimeMaps.put("wav", "audio/x-wav");
		mimeMaps.put("wbmp", "image/vnd.wap.wbmp");
		mimeMaps.put("wml", "text/vnd.wap.wml");
		mimeMaps.put("wmlc", "application/vnd.wap.wmlc");
		mimeMaps.put("wmls", "text/vnd.wap.wmlscript");
		mimeMaps.put("wmlscriptc", "application/vnd.wap.wmlscriptc");
		mimeMaps.put("wmv", "video/x-ms-wmv");
		mimeMaps.put("wrl", "x-world/x-vrml");
		mimeMaps.put("wspolicy", "application/wspolicy+xml");
		mimeMaps.put("Z", "application/x-compress");
		mimeMaps.put("z", "application/x-compress");
		mimeMaps.put("zip", "application/zip");
		mimeMaps.put("flv", "application/octet-stream");
		mimeMaps.put("3gp", "video/3gpp");
	}
	public String getForbidden() {
		return forbidden;
	}

	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	

}