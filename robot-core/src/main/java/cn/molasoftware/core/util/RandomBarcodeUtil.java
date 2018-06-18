package cn.molasoftware.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 随机字母，数字工具
 * @author caizx
 *
 */
public class RandomBarcodeUtil {

	//小写字母
	private static List<String> lowercases = new ArrayList<String>();
	//大写字母
	private static List<String> uppercases = new ArrayList<String>();
	//小写字母
	private static List<String> letters = new ArrayList<String>();
	//数字
	private static List<String> numbers = new ArrayList<String>();
	//数字和字母
	private static List<String> all = new ArrayList<String>();
	private static Random random = new Random();
	static{
		String[] lowercaseArray = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] uppercaseArray = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] numberArray = new String[]{"0","1","2","3","4","5","6","7","8","9"};
		lowercases.addAll(Arrays.asList(lowercaseArray));
		uppercases.addAll(Arrays.asList(uppercaseArray));
		letters.addAll(lowercases);
		letters.addAll(uppercases);
		numbers.addAll(Arrays.asList(numberArray));
		all.addAll(letters);
		all.addAll(numbers);
	}
	
	/**
	 * 随机生成由大小写字符组成的码
	 * @param length 长度
	 * @return
	 */
	public static String genLetterBarcodeStr(int length){
		if(length<=0){
			return "";
		}
		String barcode = "";
		for (int i = 0; i < length; i++) {
			//获取随机数
			int r = random.nextInt(letters.size()-1);
			barcode += letters.get(r);
		}
		return barcode;
	}
	
	/**
	 * 随机生成数字组成的码
	 * @param length 长度
	 * @return
	 */
	public static String genNumberBarcodeStr(int length){
		if(length<=0){
			return "";
		}
		String barcode = "";
		for (int i = 0; i < length; i++) {
			//获取随机数
			int r = random.nextInt(numbers.size()-1);
			barcode += numbers.get(r);
		}
		return barcode;
	}
	
	
	/**
	 * 随机生成数字和大小写字符组成的码
	 * @param length 长度
	 * @return
	 */
	public static String genBarcodeStr(int length){
		if(length<=0){
			return "";
		}
		String barcode = "";
		for (int i = 0; i < length; i++) {
			//获取随机数
			int r = random.nextInt(all.size()-1);
			barcode += all.get(r);
		}
		return barcode;
	}
}
