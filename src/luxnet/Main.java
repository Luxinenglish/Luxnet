package luxnet;

import luxnet.client.LuxClient;
import luxnet.router.LuxRouter;
import luxnet.net.LuxPacket;
import luxnet.net.LuxAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            LuxRouter router = new LuxRouter(9000);
            router.start();
        } else {
            LuxAddress dest = LuxAddress.fromString("lux.1.2.3");
            byte[] data = "Hello LuxNet!".getBytes();
            LuxPacket packet = new LuxPacket(dest, 80, data);
            LuxClient client = new LuxClient("localhost", 9000);
            client.sendPacket(packet);
        }
    }
}
