package com.petrichor.java.zakka.picture;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yang3wei
 */
public class JpegInfoReader {

    private static final byte TAG_START = (byte) 0xff;
    private static final byte START_OF_IMAGE = (byte) 0xd8;
    private static final byte END_OF_IMAGE = (byte) 0xd9;
    private static final byte START_OF_FRAME = (byte) 0xc0;
    private static final byte RESTART_MODULO_START = (byte) 0xd0;
    private static final byte RESTART_MODULO_END = (byte) 0xd7;
    private static final byte START_OF_SCAN = (byte) 0xda;

    public static ImageInfo getImageInfo(InputStream in) throws IOException {

        // 0-1: store JPEG tag
        // 2-3: store JPEG tag length
        // 4-5: store JPEG image height
        // 6-7: store JPEG image width
        byte[] seg = new byte[8];

        // read JPEG START_OF_IMAGE tag
        if (in.read(seg, 0, 2) == -1) {
            return null;
        }

        // if the first two bytes is not 0xff, 0xd8,
        // that is the image format is not JPEG
        if (seg[0] != TAG_START || seg[1] != START_OF_IMAGE) {
            return null;
        }

        while (true) {

            // read JPEG data tag, offset 0 must be 0xff
            if (in.read(seg, 0, 2) == -1) {
                return null;
            }

            // if tag does not start with 0xff,
            // the image format is not JPEG
            if (seg[0] != TAG_START) {
                return null;
            }

            // Ignore JPEG RESTART_MODULO tag
            if (seg[1] >= RESTART_MODULO_START && seg[1] <= RESTART_MODULO_END) {
                continue;
            }

            // find JPEG format START_OF_SCAN part,
            // data that starts with position is JPEG compression image data,
            // that never contains image meta information
            if (seg[1] == START_OF_SCAN) {
                return null;
            }

            // find JPEG format END_OF_IMAGE tag, finish scan
            if (seg[1] == END_OF_IMAGE) {
                return null;
            }

            // read JPEG data tag length
            if (in.read(seg, 2, 2) == -1) {
                return null;
            }

            // find START_OF_FRAME tag
            if (seg[1] == START_OF_FRAME) {
                break;
            }

            // skip JPEG data segment
            byte[] skip = new byte[toInt(seg, 2) - 2];
            if (in.read(skip) == -1) {
                return null;
            }
        }

        // ignore JPEG image precision byte
        if (in.read() == -1) {
            return null;
        }

        // read JPEG image height and width bytes
        if (in.read(seg, 4, 4) == -1) {
            return null;
        }
        return new ImageInfo(toInt(seg, 4), toInt(seg, 6));
    }

    private static int toInt(byte[] bys, int start) {
        return ((bys[start] & 0xff) << 8) | (bys[start + 1] & 0xff);
    }
}
