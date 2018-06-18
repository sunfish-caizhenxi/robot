package cn.molasoftware.core.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 
 * 这个类提供了一些根据类的class文件位置来定位的方法。
 */
public class PathUtil {
	/**
	 * 通过具体的路径找到相关的资源
	 * 
	 * @param locationPattern
	 * @return
	 */
	public static Resource getResourceByPath(String location) {
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		return pathResolver.getResource(location);
	}

	/**
	 * 通过模糊的路径找到相关的资源
	 * 
	 * @param locationPattern
	 * @return
	 */
	public static Resource[] getResourcesByPath(String locationPattern) {
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		try {
			return pathResolver.getResources(locationPattern);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 这个方法可以通过与某个类的class文件的相对路径来获取文件或目录的绝对路径。 通常在程序中很难定位某个相对路径，特别是在B/S应用中。
	 * 通过这个方法，我们可以根据我们程序自身的类文件的位置来定位某个相对路径。
	 * 比如：某个txt文件相对于程序的Test类文件的路径是../../resource/test.txt，
	 * 那么使用本方法Path.getFullPathRelateClass("../../resource/test.txt",Test.class)
	 * 得到的结果是txt文件的在系统中的绝对路径。
	 * 
	 * @param relatedPath
	 *            相对路径
	 * @param cls
	 *            用来定位的类
	 * @return 相对路径所对应的绝对路径
	 * @throws IOException
	 *             因为本方法将查询文件系统，所以可能抛出IO异常
	 */
	@SuppressWarnings("rawtypes")
	public static URL getURLRelateClass(String relatedPath, Class cls) throws IOException {
		String path = null;
		if (relatedPath == null) {
			throw new NullPointerException();
		}
		if (cls == null) {
			throw new IllegalArgumentException("null input: cls");
		}
		URL clsURL = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		// java.lang.Class contract does not specify
		// if 'pd' can ever be null;
		// it is not the case for Sun's implementations,
		// but guard against null
		// just in case:
		if (pd != null) {
			final CodeSource cs = pd.getCodeSource();
			// 'cs' can be null depending on
			// the classloader behavior:
			if (cs != null)
				clsURL = cs.getLocation();

			if (clsURL != null) {
				// Convert a code source location into
				// a full class file location
				// for some common cases:
				if ("file".equals(clsURL.getProtocol())) {
					try {
						if (clsURL.toExternalForm().endsWith(".jar") || clsURL.toExternalForm().endsWith(".zip"))
							clsURL = new URL("jar:".concat(clsURL.toExternalForm()).concat("!/").concat(clsAsResource));
						else if (new File(clsURL.getFile()).isDirectory())
							clsURL = new URL(clsURL, clsAsResource);
					} catch (MalformedURLException ignore) {
					}
				}
			}
		}

		if (clsURL != null) {
			// Try to find 'cls' definition as a resource;
			// this is not
			// document．d to be legal, but Sun's
			// implementations seem to //allow this:
			final ClassLoader clsLoader = cls.getClassLoader();
			clsURL = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
		}
		path = clsURL.getFile();
		int index = path.lastIndexOf("/");
		path = path.substring(0, index) + "/" + relatedPath;
		if (clsURL.getProtocol().toLowerCase().startsWith("jar")) {
			return new URL("jar:" + path);
		} else {
			return new URL("file:" + path);
		}
	}


	/**
	 * 径路匹配
	 * @param path 要匹配的路径
	 * @param pathPatterns 路径匹配模式
	 * 如：pathPattern为/*EnterDeploy*.deploy*，则路径classicEnterDeploy.deploy能匹配成功
	 * @return
	 */
	public static String match(String path, String pathPattern) {
		if(StrUtil.isBlank(path)||StrUtil.isBlank(pathPattern)){
			return null;
		}		
		List<String> pathPatterns = new ArrayList<String>();
		pathPatterns.add(pathPattern);
		return match(path, pathPatterns);
	}
	/**
	 * 径路匹配
	 * @param path 要匹配的路径
	 * @param pathPatterns 路径匹配模式
	 * 如：pathPattern为/*EnterDeploy*.deploy*，则路径classicEnterDeploy.deploy能匹配成功
	 * @return
	 */
	public static String match(String path, List<String> pathPatterns) {
		if(StrUtil.isBlank(path)||pathPatterns==null||pathPatterns.isEmpty()){
			return null;
		}
		String result = findExactKey(path, pathPatterns);
		if (result == null)
			result = findComplexKey(path, pathPatterns);
		if (result == null)
			result = findDefaultKey(pathPatterns);
		return result;
	}

	/** Check if path matches exact pattern ( /blah/blah.jsp ). */
	private static String findExactKey(String path, List<String> pathPatterns) {
		if (pathPatterns.contains(path))
			return path;
		return null;
	}

	private static String findComplexKey(String path, List<String> pathPatterns) {
		String result = null;
		for(int i=0; i<pathPatterns.size(); i++){
			String key = pathPatterns.get(i);
			if (key.length() > 1 && (key.indexOf('?') != -1 || key.indexOf('*') != -1) && match(key, path, false)) {
				if (result == null || key.length() > result.length()) {
					// longest key wins
					result = key;
				}
			}
		}
		return result;
	}

	/** Look for root pattern ( / ). */
	private static String findDefaultKey(List<String> pathPatterns) {
		String[] defaultKeys = { "/", "*", "/*" };
		for (int i = 0; i < defaultKeys.length; i++) {
			if (pathPatterns.contains(defaultKeys[i]))
				return defaultKeys[i];
		}
		return null;
	}

	private static boolean match(String pattern, String str, boolean isCaseSensitive) {
		char[] patArr = pattern.toCharArray();
		char[] strArr = str.toCharArray();
		int patIdxStart = 0;
		int patIdxEnd = patArr.length - 1;
		int strIdxStart = 0;
		int strIdxEnd = strArr.length - 1;
		char ch;

		boolean containsStar = false;
		for (int i = 0; i < patArr.length; i++) {
			if (patArr[i] == '*') {
				containsStar = true;
				break;
			}
		}

		if (!containsStar) {
			// No '*'s, so we make a shortcut
			if (patIdxEnd != strIdxEnd) {
				return false; // Pattern and string do not have the same size
			}
			for (int i = 0; i <= patIdxEnd; i++) {
				ch = patArr[i];
				if (ch != '?') {
					if (isCaseSensitive && ch != strArr[i]) {
						return false; // Character mismatch
					}
					if (!isCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[i])) {
						return false; // Character mismatch
					}
				}
			}
			return true; // String matches against pattern
		}

		if (patIdxEnd == 0) {
			return true; // Pattern contains only '*', which matches anything
		}

		// Process characters before first star
		while ((ch = patArr[patIdxStart]) != '*' && strIdxStart <= strIdxEnd) {
			if (ch != '?') {
				if (isCaseSensitive && ch != strArr[strIdxStart]) {
					return false; // Character mismatch
				}
				if (!isCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxStart])) {
					return false; // Character mismatch
				}
			}
			patIdxStart++;
			strIdxStart++;
		}
		if (strIdxStart > strIdxEnd) {
			// All characters in the string are used. Check if only '*'s are
			// left in the pattern. If so, we succeeded. Otherwise failure.
			for (int i = patIdxStart; i <= patIdxEnd; i++) {
				if (patArr[i] != '*') {
					return false;
				}
			}
			return true;
		}

		// Process characters after last star
		while ((ch = patArr[patIdxEnd]) != '*' && strIdxStart <= strIdxEnd) {
			if (ch != '?') {
				if (isCaseSensitive && ch != strArr[strIdxEnd]) {
					return false; // Character mismatch
				}
				if (!isCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxEnd])) {
					return false; // Character mismatch
				}
			}
			patIdxEnd--;
			strIdxEnd--;
		}
		if (strIdxStart > strIdxEnd) {
			// All characters in the string are used. Check if only '*'s are
			// left in the pattern. If so, we succeeded. Otherwise failure.
			for (int i = patIdxStart; i <= patIdxEnd; i++) {
				if (patArr[i] != '*') {
					return false;
				}
			}
			return true;
		}

		// process pattern between stars. padIdxStart and patIdxEnd point
		// always to a '*'.
		while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
			int patIdxTmp = -1;
			for (int i = patIdxStart + 1; i <= patIdxEnd; i++) {
				if (patArr[i] == '*') {
					patIdxTmp = i;
					break;
				}
			}
			if (patIdxTmp == patIdxStart + 1) {
				// Two stars next to each other, skip the first one.
				patIdxStart++;
				continue;
			}
			// Find the pattern between padIdxStart & padIdxTmp in str between
			// strIdxStart & strIdxEnd
			int patLength = (patIdxTmp - patIdxStart - 1);
			int strLength = (strIdxEnd - strIdxStart + 1);
			int foundIdx = -1;
			strLoop: for (int i = 0; i <= strLength - patLength; i++) {
				for (int j = 0; j < patLength; j++) {
					ch = patArr[patIdxStart + j + 1];
					if (ch != '?') {
						if (isCaseSensitive && ch != strArr[strIdxStart + i + j]) {
							continue strLoop;
						}
						if (!isCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxStart + i + j])) {
							continue strLoop;
						}
					}
				}

				foundIdx = strIdxStart + i;
				break;
			}

			if (foundIdx == -1) {
				return false;
			}

			patIdxStart = patIdxTmp;
			strIdxStart = foundIdx + patLength;
		}

		// All characters in the string are used. Check if only '*'s are left
		// in the pattern. If so, we succeeded. Otherwise failure.
		for (int i = patIdxStart; i <= patIdxEnd; i++) {
			if (patArr[i] != '*') {
				return false;
			}
		}
		return true;
	}

}
