package org.scoula.common.util;



public class UploadFileName {

    public static String getUniqueName(String fileName){

        // 파일명과 확장자 분리
        int ix = fileName.lastIndexOf(".");
        String name = fileName.substring(0, ix);              // 파일명
        String ext = fileName.substring(ix + 1);    // 확장자명

        // 현재 시간을 밀리초로 변환해 고유성 보장
        return String.format("%s-%d.%s", name, System.currentTimeMillis(), ext);
    }
}
