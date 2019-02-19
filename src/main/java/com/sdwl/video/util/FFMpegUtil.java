package com.sdwl.video.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FFMpegUtil implements IStringGetter {
    private boolean isSupported;
    private int runtime = 0;
    private String ffmpegUri;//ffmpeg???
    private String originFileUri; //
    private enum FFMpegUtilStatus { Empty, CheckingFile, GettingRuntime };
    private FFMpegUtilStatus status = FFMpegUtilStatus.Empty;
    private List<String> cmd = new ArrayList<String>();


    public static void main(String[] args) {

        FFMpegUtil ffm = new FFMpegUtil("D:\\ffmpeg\\bin\\ffmpeg","E:\\a2.mp4");
        ///usr/local/bin/ffmpeg
        ffm.videoTransfer("E:\\IMG_0630.mp4");
    }

    /**
     * ??????
     * @Title: videoTransfer
     * @Description: TODO(???????????????)
     * @param fileSavePath ????.void ????
     * ?? ?ffmpeg -i img_0663.mov -vcodec copy -f mp4 img_0663.mp4
     * @throws
     */
    public void videoTransfer(String fileSavePath){
        cmd.clear();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(originFileUri);
        cmd.add("-vcodec");
        cmd.add("copy");
        cmd.add("-f");
        cmd.add("mp4");
        cmd.add( fileSavePath );
        CmdExecuter.exec(cmd, null);
    }

    /**
     * ffmpeg -i img_0663.mp4 -vf scale=iw*0.3:ih*0.3 output_img_0663.mp4
     * @throws
     */
    public boolean videoCompress(String fileSavePath){
        cmd.clear();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(originFileUri);
        cmd.add("-vf");
        cmd.add("scale=iw*0.5:ih*0.5");
        cmd.add( fileSavePath );
        boolean flag =   CmdExecuter.exec(cmd, null);
        return  flag;
    }


    /**
     * ????????
     * @Title: videoGripframe
     * @Description: TODO(???????????????)
     * @param fileSavePath ????.void ????
     * ???ffmpeg -i img_0663.mp4 -y -f image2 -ss 1 -t 0.1 -s 350x240 test1.jpg
     * @throws
     */
    public void videoGripframe(String fileSavePath){
        cmd.clear();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(originFileUri);
        cmd.add("-y");
        cmd.add("-f");
        cmd.add("image2");
        cmd.add("-ss");
        cmd.add("1");
        cmd.add("-t");
        cmd.add("0.1");
        cmd.add("-s");
        cmd.add("350x240");
        cmd.add( fileSavePath );
        CmdExecuter.exec(cmd, null);

    }


    public FFMpegUtil(String ffmpegUri, String originFileUri ){
        this.ffmpegUri = ffmpegUri;
        this.originFileUri = originFileUri;
    }

    public boolean isSupported(String originFileUri){
        isSupported = true;
        status = FFMpegUtilStatus.CheckingFile;
        cmd.clear();
        cmd.add(ffmpegUri);
        cmd.add("-i");
        cmd.add(originFileUri);
        CmdExecuter.exec(cmd, this);
        return isSupported;
    }


    public void dealString( String str ){

        switch( status )
        {
            case Empty:
                break;
            case CheckingFile:{
                Matcher m = Pattern.compile("Unknown format").matcher(str);
                if( m.find() )
                    this.isSupported = false;
                break;
            }
            case GettingRuntime:{
                Matcher m = Pattern.compile("Duration: //w+://w+://w+").matcher(str);
                while (m.find())
                {
                    String msg = m.group();
                    msg = msg.replace("Duration: ", "");
                    // runtime = TimeUtil.runtimeToSecond(msg);    
                }
                break;
            }
        }//switch  
    }

}

