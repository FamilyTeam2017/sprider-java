package com.maxiaogang.sprider;

import java.io.File;

/**
 * 抓取工具,提供一些抓取服务
 */
public interface FetchTool {

    /**
     * 抓取页面
     *
     * @param urlPath 请求的url地址
     * @param filePath 抓取的文件存储地址
     * @return 是否抓取成功
     */
    File fetchPage(String urlPath, String filePath);


}
