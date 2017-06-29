package example.newsclient.util;

/**
 * 作者：国富小哥
 * 日期：2017/6/28
 * Created by Administrator
 */

public class UrlManager {

    public final String[] channelId = new String[] {
            "T1348647909107",   // 头条
            "T1348648037603",   // 社会
            "T1348649580692",   // 科技
            "T1348648756099",   // 财经
            "T1348649079062",   // 体育
            "T1348654060988",   // 汽车
    };

    /**
     * 获取一页新闻数据
     * @param newsCategoryId 新闻类别id
     *
     * @return
     */
    public static String getUrl(String newsCategoryId) {
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html

        return "http://c.m.163.com/nc/article/headline/" + newsCategoryId + "/0-20.html";
    }

    /**
     * 获取分页数据
     * @param newsCategoryId 新闻类别id
     * @param pageNo 获取第几页数据
     * @param pageSize 每页数据有多少条
     * */
    public static String getUrl(String newsCategoryId,int pageNo,int pageSize){
        // 第1页： 偏移量为 0,     1页10条,
        // 第2页： 偏移量为 10,    1页10条,
        // 第3页： 偏移量为 20,    1页10条,
        int offset=(pageNo-1)*pageSize;
        //偏移量减去每页数据的大小
        return "http://c.m.163.com/nc/article/headline/" + newsCategoryId + "/"+offset+"-"+pageSize+".html";
    }

    /**
     * 获取分页数据
     *
     * @param newsCategoryId 新闻类别id
     * @param pageNo 获取第几页数据
     * @return
     */
    public static String getUrl(String newsCategoryId, int pageNo) {
        int pageSize = 10;
        // 第1页： 偏移量为 0,     1页10条,
        // 第2页： 偏移量为 10,    1页10条,
        // 第3页： 偏移量为 20,    1页10条,
        int offset = (pageNo -1) * pageSize;
        return "http://c.m.163.com/nc/article/headline/"
                + newsCategoryId + "/"+ offset +"-"+ pageSize +".html";
    }


    // 视频url路径
    public static final String VideoURL = //
            "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-20.html";

}
