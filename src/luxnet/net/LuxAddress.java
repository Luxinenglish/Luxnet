package luxnet.net;

public class LuxAddress {
    private int part1;
    private int part2;
    private int part3;

    public LuxAddress(int p1, int p2, int p3) {
        if(p1 < 0 || p1 > 255 || p2 < 0 || p2 > 255 || p3 < 0 || p3 > 255)
            throw new IllegalArgumentException("Address parts must be 0-255");
        this.part1 = p1;
        this.part2 = p2;
        this.part3 = p3;
    }

    public static LuxAddress fromString(String addr) {
        // attend format lux.X.Y.Z ou X.Y.Z
        String s = addr.startsWith("lux.") ? addr.substring(4) : addr;
        String[] parts = s.split("\\.");
        if(parts.length != 3) throw new IllegalArgumentException("Address must be lux.X.Y.Z");
        return new LuxAddress(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );
    }

    @Override
    public String toString() {
        return String.format("lux.%d.%d.%d", part1, part2, part3);
    }

    public byte[] toBytes() {
        return new byte[] {(byte)part1, (byte)part2, (byte)part3};
    }

    public static LuxAddress fromBytes(byte[] bytes) {
        if(bytes.length != 3) throw new IllegalArgumentException("Must be 3 bytes");
        return new LuxAddress(
                bytes[0] & 0xFF,
                bytes[1] & 0xFF,
                bytes[2] & 0xFF
        );
    }
}