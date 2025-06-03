package luxnet.client;

import luxnet.net.LuxAddress;
import luxnet.net.LuxPacket;
import java.io.*;
import java.net.*;

public class LuxClient {
    private String routerHost;
    private int routerPort;

    public LuxClient(String host, int port) {
        this.routerHost = host;
        this.routerPort = port;
    }

    public void sendPacket(LuxPacket packet) throws IOException {
        try(Socket socket = new Socket(routerHost, routerPort)) {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(packet.toBytes());
            out.flush();

            byte[] response = in.readAllBytes();
            System.out.println("Réponse du router : " + new String(response));
        }
    }

    public static void main(String[] args) throws IOException {
        LuxAddress dest = LuxAddress.fromString("lux.1.2.3");
        byte[] data = "Bonjour depuis luxnet.client.LuxClient".getBytes();
        LuxPacket packet = new LuxPacket(dest, 80, data);

        LuxClient client = new LuxClient("localhost", 9000);
        client.sendPacket(packet);
    }
}
