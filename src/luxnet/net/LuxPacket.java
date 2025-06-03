package luxnet.net;
import java.nio.ByteBuffer;

public class LuxPacket {
    private LuxAddress dest;
    private int port;
    private byte[] data;

    public LuxPacket(LuxAddress dest, int port, byte[] data) {
        this.dest = dest;
        this.port = port;
        this.data = data;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(3 + 2 + data.length);
        buffer.put(dest.toBytes());        // 3 bytes adresse
        buffer.putShort((short) port);     // 2 bytes port
        buffer.put(data);                   // data
        return buffer.array();
    }

    public static LuxPacket fromBytes(byte[] bytes) {
        if(bytes.length < 5) throw new IllegalArgumentException("Invalid packet");
        byte[] addrBytes = new byte[3];
        System.arraycopy(bytes, 0, addrBytes, 0, 3);
        LuxAddress dest = LuxAddress.fromBytes(addrBytes);

        ByteBuffer bb = ByteBuffer.wrap(bytes, 3, 2);
        int port = bb.getShort() & 0xFFFF;

        byte[] data = new byte[bytes.length - 5];
        System.arraycopy(bytes, 5, data, 0, data.length);

        return new LuxPacket(dest, port, data);
    }

    public LuxAddress getDest() { return dest; }
    public int getPort() { return port; }
    public byte[] getData() { return data; }
}
