package cn.com.do1.component.util;

/**
 * 提供字符串处理相关方法的工具类.
 *
 * @author 刘宇明
 */
public class StringUtil {

    /**
     * 检查字符串是否为空字符("")或<code>null</code>.
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre></blockquote></p>
     * <p/>
     * <p>提示: 此方法不对输入字符串做trim操作,如果需要此功能,请查看{@link #isBlank(String)}.</p>
     *
     * @param str 被检查的字符串, 可以为<code>null</code>.
     * @return 当被检查的字符串为空字符("")或<code>null</code>时, 返回<code>true</code>,否则返回<code>false</code>.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检查字符串是否包含字符(字符包括空字符{@link Character#isWhitespace(char)}).
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.isNotEmpty(null)      = false
     * StringUtil.isNotEmpty("")        = false
     * StringUtil.isNotEmpty(" ")       = true
     * StringUtil.isNotEmpty("bob")     = true
     * StringUtil.isNotEmpty("  bob  ") = true
     * </pre></blockquote></p>
     *
     * @param str 被检查的字符串, 可以为<code>null</code>
     * @return 当被检查的字符串包含字符时, 返回<code>true</code>,否则返回<code>false</code>
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 检查字符串是否为空字符("")、<code>null</code>或全为空白字符({@link Character#isWhitespace(char)}).
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("   ")     = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre></blockquote></p>
     *
     * @param str 被检查的字符串, 可以为<code>null</code>
     * @return 当被检查的字符串为空字符("")、<code>null</code>或全为空白字符({@link Character#isWhitespace(char)})时, 返回<code>true</code>,否则返回<code>false</code>
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查字符串是否包含非空白字符({@link Character#isWhitespace(char)}).
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.isNotBlank(null)      = false
     * StringUtil.isNotBlank("")        = false
     * StringUtil.isNotBlank(" ")       = false
     * StringUtil.isNotBlank("   ")     = false
     * StringUtil.isNotBlank("bob")     = true
     * StringUtil.isNotBlank("  bob  ") = true
     * </pre></blockquote></p>
     *
     * @param str 被检查的字符串, 可以为<code>null</code>
     * @return 当被检查的字符串包含非空白字符({@linkCharacter#isWhitespace(char)})时, 返回<code>true</code>,否则返回<code>false</code>
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    // Trim
    //-----------------------------------------------------------------------
    /**
     * 删除输入字符串头和尾的控制字符(char &lt;= 32), 输入<code>null</code>的话, 直接返回<code>null</code>.
     * <p/>
     * <p>使用{@link String#trim()}来实现删除字符串头和尾的控制字符.</p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim("")            = ""
     * StringUtil.trim("     ")       = ""
     * StringUtil.trim("abc")         = "abc"
     * StringUtil.trim("    abc    ") = "abc"
     * </pre></blockquote></p>
     *
     * @param str 被trim的字符串, 可以为<code>null</code>
     * @return 被trim后的字符串,输入为<code>null</code>的话, 直接返回<code>null</code>
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 删除输入字符串头和尾的控制字符(char &lt;= 32), 输入全为空白的字符串或<code>null</code>的话, 返回<code>null</code>.
     * <p/>
     * <p>使用{@link String#trim()}来实现删除字符串头和尾的控制字符.</p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.trimToNull(null)          = null
     * StringUtil.trimToNull("")            = null
     * StringUtil.trimToNull("     ")       = null
     * StringUtil.trimToNull("abc")         = "abc"
     * StringUtil.trimToNull("    abc    ") = "abc"
     * </pre></blockquote></p>
     *
     * @param str 被trim的字符串, 可以为<code>null</code>
     * @return 被trim后的字符串,输入全为空白的字符串或<code>null</code>的话, 返回<code>null</code>
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * 删除输入字符串头和尾的控制字符(char &lt;= 32), 输入<code>null</code>的话, 返回<code>""</code>.
     * <p/>
     * <p>使用{@link String#trim()}来实现删除字符串头和尾的控制字符.</p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.trimToEmpty(null)          = ""
     * StringUtil.trimToEmpty("")            = ""
     * StringUtil.trimToEmpty("     ")       = ""
     * StringUtil.trimToEmpty("abc")         = "abc"
     * StringUtil.trimToEmpty("    abc    ") = "abc"
     * </pre></blockquote></p>
     *
     * @param str 被trim的字符串, 可以为<code>null</code>
     * @return 被trim后的字符串,输入<code>null</code>的话, 返回<code>""</code>.
     */
    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    // Equals
    //-----------------------------------------------------------------------
    /**
     * 比较两个字符串, 当两个字符串相等({@link String#equals(Object)}),或都为<code>null</code>时,返回<code>true</code>.
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.equals(null, null)   = true
     * StringUtil.equals(null, "abc")  = false
     * StringUtil.equals("abc", null)  = false
     * StringUtil.equals("abc", "abc") = true
     * StringUtil.equals("abc", "ABC") = false
     * </pre></blockquote></p>
     *
     * @param str1 第一个字符串, 可以为<code>null</code>
     * @param str2 第二个字符串, 可以为<code>null</code>
     * @return 当两个字符串相等({@linkString#equals(Object)}),或都为<code>null</code>时,返回<code>true</code>,否则返回<code>false</code>
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 比较两个字符串, 当两个字符串忽略大小写后相等({@link String#equalsIgnoreCase(String)}),或都为<code>null</code>时,返回<code>true</code>.
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre></blockquote></p>
     *
     * @param str1 第一个字符串, 可以为<code>null</code>
     * @param str2 第二个字符串, 可以为<code>null</code>
     * @return 当两个字符串忽略大小写后相等({@linkString#equalsIgnoreCase(String)}),或都为<code>null</code>时,返回<code>true</code>,否则返回<code>false</code>
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    // Replacing
    //-----------------------------------------------------------------------
    /**
     * 把在源字符串内出现的指定字符串全部用新字符串替换.
     * <p/>
     * <p>任何一个输入参数的值为<code>null</code>的话,不处理,返回源字符串</p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace("", *, *)          = ""
     * StringUtil.replace("any", null, *)    = "any"
     * StringUtil.replace("any", *, null)    = "any"
     * StringUtil.replace("any", "", *)      = "any"
     * StringUtil.replace("aba", "a", null)  = "aba"
     * StringUtil.replace("aba", "a", "")    = "b"
     * StringUtil.replace("aba", "a", "z")   = "zbz"
     * StringUtil.replace("uuu", "uu", "u")  = "uu"
     * </pre></blockquote></p>
     *
     * @param text         源字符串, 可以为<code>null</code>.
     * @param searchString 源字符串内,要被替换的字符串, 可以为<code>null</code>.
     * @param replacement  新字符串,可以为<code>null</code>.
     * @return 替换后的字符串,如任何一个输入参数的值为<code>null</code>的话,不处理,返回源字符串.
     */
    public static String replace(String text, String searchString,
                                 String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    /**
     * 把在源字符串内出现的指定字符串用新字符串替换, 最大的替换个数由max值指定, max值等于-1的话, 替换全部.
     * <p/>
     * <p>任何一个输入参数的值为<code>null</code>的话,不处理,返回源字符串</p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace("", *, *, *)           = ""
     * StringUtil.replace("any", null, *, *)     = "any"
     * StringUtil.replace("any", *, null, *)     = "any"
     * StringUtil.replace("any", "", *, *)       = "any"
     * StringUtil.replace("any", *, *, 0)        = "any"
     * StringUtil.replace("abaa", "a", null, -1) = "abaa"
     * StringUtil.replace("abaa", "a", "", -1)   = "b"
     * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
     * StringUtil.replace("uuu", "uu", "u", -1)  = "uu"
     * </pre></blockquote></p>
     *
     * @param text         源字符串, 可以为<code>null</code>.
     * @param searchString 源字符串内,要被替换的字符串, 可以为<code>null</code>.
     * @param replacement  新字符串,可以为<code>null</code>.
     * @param max          最大的替换个数,等于-1的话,替换全部
     * @return 替换后的字符串,如任何一个输入参数的值为<code>null</code>的话,不处理,返回源字符串.
     */
    public static String replace(String text, String searchString,
                                 String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null
                || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    // Remove
    //-----------------------------------------------------------------------
    /**
     * 从源字符串内去除指定的字符串.
     * <p/>
     * <p>如果源字符串或者要被去除的字符串均为<code>null</code>的话, 不处理, 返回源字符串.</p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.remove(null, *)        = null
     * StringUtil.remove(*, null)        = *
     * StringUtil.remove("", *)          = ""
     * StringUtil.remove(*, "")          = *
     * StringUtil.remove("queued", "ue") = "qd"
     * StringUtil.remove("queued", "zz") = "queued"
     * StringUtil.remove("uuu", "uu")    = "u"
     * </pre></blockquote></p>
     *
     * @param str    源字符串, 可以为<code>null</code>.
     * @param remove 要被去除的字符串, 可以为<code>null</code>.
     * @return 去除指定字符串后的字符串, 如果源字符串或者要被去除的字符串均为<code>null</code>的话, 不处理, 返回源字符串.
     */
    public static String remove(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        return replace(str, remove, "", -1);
    }

    // Abbreviating
    //-----------------------------------------------------------------------
    /**
     * 方法{@link #abbreviate(String, int)}的简写.
     */
    public static String abbr(String str, int maxWidth) {
        return abbreviate(str, maxWidth);
    }

    /**
     * 返回字符串的缩写,如在<code>maxWidth==10</code>时,字符串"这也算是成立一周年的庆祝活动"的缩写为"这也算是成立一...".
     * 你可以指定缩写最多包含的字符数, 缩写的提示部分用三个点的省略号(...)代替.
     * <p/>
     * <p>注意:
     * <ul>
     * <li>如果输入字符串<code>str</code> 的字符数少于或等于<code>maxWidth</code>指定的字符数,则返回输入字符串</li>
     * <li>如果多于<code>maxWidth</code>指定的字符数,则返回 输入字符串<code>str</code>的头<code>maxWidth-3</code>个字符 + 三个点的省略号(...) 后的字符串</li>
     * <li>如果<code>maxWidth</code>的值小于<code>4</code>,则抛出异常<code>IllegalArgumentException</code>.</li>
     * <li>在任何情况下, 返回字符串的字符数均不会多于<code>maxWidth</code>指定的字符数.</li>
     * </ul>
     * </p>
     * <p/>
     * <p><blockquote><pre>
     * StringUtil.abbreviate(null, *)      = null
     * StringUtil.abbreviate("a", 3)       = IllegalArgumentException
     * StringUtil.abbreviate("ab", 4)      = "ab"
     * StringUtil.abbreviate("", 4)        = ""
     * StringUtil.abbreviate("abcdefg", 6) = "abc..."
     * StringUtil.abbreviate("abcdefg", 7) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 8) = "abcdefg"
     * StringUtil.abbreviate("abcdefg", 4) = "a..."
     * StringUtil.abbreviate("abcdefg", 3) = IllegalArgumentException
     * </pre></blockquote></p>
     *
     * @param str      源字符串,可以为<code>null</code>.
     * @param maxWidth 返回字符串最多可包含字符数的值,必须大于或等于<code>4</code>.
     * @return 源字符串的缩写形式,如果源字符串为<code>null</code>的话,返回<code>null</code>.
     * @throws IllegalArgumentException 如果<code>maxWidth</code>的值小于<code>4</code>.
     */
    public static String abbreviate(String str, int maxWidth) {
        return abbreviate(str, 0, maxWidth);
    }

    private static String abbreviate(String str, int offset, int maxWidth) {
        if (str == null) {
            return null;
        }
        if (maxWidth < 4) {
			throw new IllegalArgumentException("maxWidth的值最小必须为4");
		}
		if (str.length() <= maxWidth) {
			return str;
		}
		if (offset > str.length()) {
			offset = str.length();
		}
		if ((str.length() - offset) < (maxWidth - 3)) {
			offset = str.length() - (maxWidth - 3);
		}
		if (offset <= 4) {
			return str.substring(0, maxWidth - 3) + "...";
		}
		if (maxWidth < 7) {
			throw new IllegalArgumentException(
					"Minimum abbreviation width with offset is 7");
		}
		if ((offset + (maxWidth - 3)) < str.length()) {
			return "..." + abbreviate(str.substring(offset), maxWidth - 3);
		}
		return "..." + str.substring(str.length() - (maxWidth - 3));
	}
}
