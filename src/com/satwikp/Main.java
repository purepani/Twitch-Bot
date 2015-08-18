package com.satwikp;

import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // The server to connect to.
        String server = "irc.twitch.tv";
        int port = 6667;

        //Login details
        String nick = "satwikpbot";
        String pass = "oauth:4pv8gf4k8xpsfxt42zq5tgyt56gyqn";

        // The channel which the bot will join.
        String channel = "#Soul_trader";

        // Connect directly to the IRC server.
        Socket socket = new Socket(server, port);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream( )));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream( )));

        // Log on to the server.
        writer.write("PASS " + pass + "\r\n");
        writer.write("NICK " + nick + "\r\n");
        writer.flush();

        // Read lines from the server until it tells us we have connected.
        String line = null;
        while ((line = reader.readLine( )) != null) {
            if (line.indexOf("004") >= 0) {
                // We are now logged in.
                break;
            }
            else if (line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;
            }
        }
        writer.write("CAP REQ :twitch.tv/membership\r\n");
        writer.flush();
        // Join the channel.
        writer.write("JOIN " + channel + "\r\n");
        writer.flush();

        writer.write("PRIVMSG " + channel + " :Test 1\r\n");
        writer.flush();
        // Keep reading lines from the server.
        reader.read();
        while ((line = reader.readLine( )) != null) {
            if (line.toLowerCase( ).startsWith("PING ")) {
                // We must respond to PINGs to avoid being disconnected.
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.flush();
            }
            else {
                // Print the raw line received by the bot.
                System.out.println(line);
            }
        }
    }

}