package nil.ed.ssl;

import nil.ed.file.FileUtils;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.ServerSocket;


public class SSLSockServerImpl extends SSLSockServer {

    private String docroot;

    private static int DefaultServerPort = 12001;

    /**
     * Constructs a ClassFileServer.
     *
     * @param docroot the path where the server locates files
     */
    public SSLSockServerImpl(ServerSocket ss, String docroot) throws IOException {
        super(ss);
        this.docroot = docroot;
    }

    /**
     * Returns an array of bytes containing the bytes for
     * the file represented by the argument <b>path</b>.
     *
     * @return the bytes for the file
     * @throws FileNotFoundException if the file corresponding
     *                               to <b>path</b> could not be loaded.
     */
    public byte[] getBytes(String path)
            throws IOException, FileNotFoundException {
        System.out.println("reading: " + path);
        File f = new File(docroot + File.separator + path);
        int length = (int) (f.length());
        if (length == 0) {
            throw new IOException("File length is zero: " + path);
        } else {
            FileInputStream fin = new FileInputStream(f);
            DataInputStream in = new DataInputStream(fin);

            byte[] bytecodes = new byte[length];
            in.readFully(bytecodes);
            return bytecodes;
        }
    }

    /**
     * Main method to create the class server that reads
     * files. This takes two command line arguments, the
     * port on which the server accepts requests and the
     * root of the path. To start up the server: <br><br>
     *
     * <code>   java ClassFileServer <port> <path>
     * </code><br><br>
     *
     * <code>   new ClassFileServer(port, docroot);
     * </code>
     */
    public static void main(String args[]) {
        System.setProperty("javax.net.debug", "ssl,handshake");
        System.out.println(
                "USAGE: java ClassFileServer port docroot [TLS [true]]");
        System.out.println("");
        System.out.println(
                "If the third argument is TLS, it will start as\n" +
                        "a TLS/SSL file server, otherwise, it will be\n" +
                        "an ordinary file server. \n" +
                        "If the fourth argument is true,it will require\n" +
                        "client authentication as well.");

        int port = DefaultServerPort;
        String docroot = "";

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        if (args.length >= 2) {
            docroot = args[1];
        }
        String type = "TLS";
        if (args.length >= 3) {
            type = args[2];
        }
        try {
            ServerSocketFactory ssf =
                    SSLSockServerImpl.getServerSocketFactory(type);
            ServerSocket ss = ssf.createServerSocket(port);
            ((SSLServerSocket) ss).setNeedClientAuth(true);
            if (args.length >= 4 && args[3].equals("true")) {
                ((SSLServerSocket) ss).setNeedClientAuth(true);
            }
            new SSLSockServerImpl(ss, docroot);
        } catch (IOException e) {
            System.out.println("Unable to start ClassServer: " +
                    e.getMessage());
            e.printStackTrace();
        }
    }

    private static ServerSocketFactory getServerSocketFactory(String type) {
        if (type.equals("TLS")) {
            SSLServerSocketFactory ssf = null;
            try {
                // set up key manager to do server authentication
//                SSLContext ctx;
//                KeyManagerFactory kmf;
//                KeyStore ks;
//                char[] passphrase = "server".toCharArray();
//
//                ctx = SSLContext.getInstance("TLS");
//                kmf = KeyManagerFactory.getInstance("SunX509");
//                ks = KeyStore.getInstance("JKS");
//
//                ks.load(SSLSockServerImpl.class.getClassLoader().getResourceAsStream("/test_server.keystore"), passphrase);
//                kmf.init(ks, passphrase);
//                ctx.init(kmf.getKeyManagers(), null, null);
//
//                ssf = ctx.getServerSocketFactory();
                new EnvironmentConfigurer()
                        .debug()
                        .setKeyStorePassword("server")
                        //打包成JAR会无法访问Jar文件中的jks资源文件
                        .setKeyStorePath(FileUtils.getProjectAbsolutePath("resources/server.jks"))
                        .setTrustStorePassword("server")
                        .setTrustStorePath(FileUtils.getProjectAbsolutePath("resources/server_trust.jks"))
                        .config();

                return SSLServerSocketFactory.getDefault();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ServerSocketFactory.getDefault();
        }
        return null;
    }
}