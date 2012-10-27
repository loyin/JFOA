package net.loyin.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PinyinUtil {
	private static HanyuPinyinOutputFormat spellFormat = new HanyuPinyinOutputFormat();    
    //初始化信息  
    static{
        spellFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    
        spellFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);    
        spellFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }
    /**使用PinYin4j.jar将汉字转换为拼音
     * @param chineseStr
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
	public String chineneToSpell(String chineseStr) throws Exception{
        return PinyinHelper.toHanyuPinyinString(chineseStr , spellFormat,"");
    }
}
