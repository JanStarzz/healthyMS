package com.yzy.util;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author LuBaby
 * @date 2021/4/17 10:54
 */
@Component
public class SFTPUtils {
    private static final String HOST = "121.5.232.11";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Yl990410";

    private Session session;

    private ChannelSftp sftp;

    public void Login() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(USERNAME, HOST);
            session.setPassword(PASSWORD);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("userauth.gssapi-with-mic", "no");
            session.setConfig(config);
            session.connect(1500);
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public void upload(String sftpFileName, InputStream input) throws SftpException{
        try {
            sftp.cd("/usr/resources/img/healthy_code");
        } catch (SftpException e) {
            sftp.mkdir("/usr/resources/img/healthy_code");
            sftp.cd("/usr/resources/img/healthy_code");
        }
        sftp.put(input, sftpFileName);

    }

    public void rmItem(String directory){
        try {
            sftp.rm("/usr/resources/img/healthy_code/"+directory);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
