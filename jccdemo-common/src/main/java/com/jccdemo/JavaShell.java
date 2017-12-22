package com.jccdemo;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by chenjiacheng on 2017-12-22.
 */
public class JavaShell {

    public static void main(String[] args) throws JSchException, IOException {
        String userName = "root";
        String IP = "10.28.18.103";
        String password = "smcdyanfa";
        String command = "ls /home -a";
        executeCommand(userName, IP, password, command);
    }

    private static void executeCommand(String userName, String IP, String password, String command) throws JSchException, IOException {
        JSch jsch = new JSch();
        Session e = jsch.getSession(userName, IP, 22);
        e.setPassword(password);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        e.setConfig(config);
        e.connect();
        Channel channel = e.openChannel("exec");
        ChannelExec channelExec = (ChannelExec)channel;
        channelExec.setCommand(command);
        channelExec.setInputStream((InputStream)null);
        BufferedReader input = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
        channelExec.connect();
        String line;
        while((line = input.readLine()) != null) {
            System.out.println(line);
        }

        input.close();
        if(channelExec.isClosed()) {
            int exitStatus = channelExec.getExitStatus();
            System.out.println("exitCode="+exitStatus);
        }
        channelExec.disconnect();
        e.disconnect();
    }
}
