package luxnet.router;

import luxnet.net.LuxPacket;

import java.io.*;
import java.net.*;

public class LuxRouter {
    private int listenPort;

    public LuxRouter(int listenPort) {
        this.listenPort = listenPort;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(listenPort);
        System.out.println("luxnet.router.LuxRouter démarré sur le port " + listenPort);

        while(true) {
            Socket client = serverSocket.accept();
            new Thread(() -> handleClient(client)).start();
        }
    }

    private void handleClient(Socket client) {
        try(InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream()) {

            byte[] header = in.readNBytes(5); // 3 bytes adresse + 2 port
            if(header.length < 5) {
                client.close();
                return;
            }

            int dataLength = in.available();
            byte[] data = new byte[dataLength];
            in.read(data);

            byte[] packetBytes = new byte[5 + dataLength];
            System.arraycopy(header, 0, packetBytes, 0, 5);
            System.arraycopy(data, 0, packetBytes, 5, dataLength);

            LuxPacket packet = LuxPacket.fromBytes(packetBytes);
            System.out.println("Packet reçu pour " + packet.getDest() + " port " + packet.getPort());
            System.out.println("Données : " + new String(packet.getData()));

            // Pour l’instant on renvoie juste un ACK simple
            out.write("ACK luxnet.router.LuxRouter".getBytes());

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try { client.close(); } catch(IOException ignored) {}
        }
    }

    public static void main(String[] args) throws IOException {
        LuxRouter router = new LuxRouter(9000);
        router.start();
    }
}
