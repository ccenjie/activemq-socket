package cn.ccenjie.activemq.utils;

/**
 * @author cenjunjie
 * 2018/8/31
 */
public class ParamUtils {

    /**
     * 获取查询参数的参数值
     */
    public static String getValue(String queryString, String key) {
        if(queryString != null && queryString.length() > 0) {
            String[] arr = queryString.split("&");
            for(String s : arr) {
                String[] kv = s.split("=");
                if(kv[0].equals(key)) {
                    return kv[1];
                }
            }
        }
        return null;
    }
}
