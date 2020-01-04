package com.wind.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

/**
 * git工具
 */
public class GitUtil {


    /**
     * 获取克隆代码
     * @param url
     * @param filename
     * @return
     */
    public static boolean getClone(String url, String filename){
        try {
            Git.cloneRepository().setURI(url).setDirectory(new File(filename)).call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
