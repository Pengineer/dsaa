package hust.plalindrome;

/**
 * 根据最长回⽂⼦序列处理字符串 
 * 给定一个字符串str和它的一个最长回文子序列strLPS，返回字符串str在任意
 * 位置添加最少字符后，整体都是回文串的其中一种结果。 
 
 * 例如： 
 * str="AB1C2DE34F3GHJ21KL"; 
 * strLPS="1234321"; 
 * 返回："ABLK1C2DEJHG3F4F3GHJED2C1KLBA"
 * 
 * 解答： 
 * 1，依次找到strLPS中的每层“洋葱圈” 
 * 2，根据每层洋葱圈在str中找到每层的外部，左侧为left，右侧为right 
 * 3，将l+r(逆序)拷贝到左侧未设值的部分，将r+l(逆序)拷贝到右侧 
 * 4，直到strLPS被剥完
 * 
 * @author 2015-11
 *
 */
public class GetPlalindromeBySubString {
	
	public String getPalindrome(String str, String strlps) {
		if (str == null || str.equals("")) {
			return "";
		}
		char[] chas = str.toCharArray();
		char[] lps = strlps.toCharArray();
		char[] res = new char[2 * chas.length - lps.length];
		int chasl = 0;
		int chasr = chas.length - 1;
		int lpsl = 0;
		int lpsr = lps.length - 1;
		int resl = 0;
		int resr = res.length - 1;
		int tmpl = 0;
		int tmpr = 0;
		while (lpsl <= lpsr) {
			tmpl = chasl;
			tmpr = chasr;
			while (chas[chasl] != lps[lpsl]) {
				chasl++;
			}
			while (chas[chasr] != lps[lpsr]) {
				chasr--;
			}
			set(res, resl, resr, chas, tmpl, chasl, chasr, tmpr);
			resl += chasl - tmpl + tmpr - chasr;
			resr -= chasl - tmpl + tmpr - chasr;
			res[resl++] = chas[chasl++];
			res[resr--] = chas[chasr--];
			lpsl++;
			lpsr--;
		}
		return String.valueOf(res);
	}
	public void set(char[] res, int resl, int resr, char[] chas, int ls, int le, int rs, int re) {
		for (int i = ls; i < le; i++) {
			res[resl++] = chas[i];
			res[resr--] = chas[i];
		}
		for (int i = re; i > rs; i--) {
			res[resl++] = chas[i];
			res[resr--] = chas[i];
		}
	}
}
