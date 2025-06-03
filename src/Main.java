public class Main {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Usage : java Main server|client");
            System.exit(1);
        }

        String mode = args[0];

        try {
            if(mode.equalsIgnoreCase("server")) {
                // Démarrer le router sur le port 9000
                LuxRouter router = new LuxRouter(9000);
                router.start();

            } else if(mode.equalsIgnoreCase("client")) {
                // Créer et envoyer un paquet exemple
                LuxAddress dest = LuxAddress.fromString("lux.1.2.3");
                byte[] data = "Message depuis LuxClient via Main".getBytes();
                LuxPacket packet = new LuxPacket(dest, 80, data);

                LuxClient client = new LuxClient("localhost", 9000);
                client.sendPacket(packet);

            } else {
                System.out.println("Argument inconnu : " + mode);
                System.out.println("Usage : java Main server|client");
                System.exit(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
