package nil.ed.ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class SSLSockClient {
    public static void main(String[] args) {
        try {
            System.out.println(SSLSockClient.class.getClassLoader().getResource("/"));
            new EnvironmentConfigurer()
                    .debug()
                    .setKeyStorePassword("client")
                    .setKeyStorePath(SSLSockClient.class.getClassLoader().getResource("client.jks").getFile())
                    .setTrustStorePassword("client")
                    .setTrustStorePath(SSLSockClient.class.getClassLoader().getResource("client_trust.jks").getFile())
                    .config();
            SSLSocketFactory factory =
                    (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket =
                    (SSLSocket) factory.createSocket("127.0.0.1", 12001);
            socket.startHandshake();

            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())));

            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            /*
             * Make sure there were no surprises
             */
            if (out.checkError())
                System.out.println(
                        "SSLSocketClient:  java.io.PrintWriter error");

            /* read response */
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
