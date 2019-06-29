package nil.ed.ssl;

public class EnvironmentConfigurer {
    private String keyStorePath;
    private String keyStorePassword;
    private String trustStorePath;
    private String trustStorePassword;

    public EnvironmentConfigurer setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
        return this;
    }

    public EnvironmentConfigurer setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
        return this;
    }

    public EnvironmentConfigurer setTrustStorePath(String trustStorePath) {
        this.trustStorePath = trustStorePath;
        return this;
    }

    public EnvironmentConfigurer setTrustStorePassword(String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
        return this;
    }

    public EnvironmentConfigurer debug(){
        System.setProperty("javax.net.debug", "ssl,handshake");
        return this;
    }

    public void config(){
        ParamChecker.checkNullAndThrows(keyStorePath,"keyStorePath is null");
        ParamChecker.checkNullAndThrows(keyStorePassword,"keyStorePassword is null");
        ParamChecker.checkNullAndThrows(trustStorePath,"trustStorePath is null");
        ParamChecker.checkNullAndThrows(trustStorePassword,"trustStorePassword is null");

        System.setProperty("javax.net.ssl.keyStore", keyStorePath);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        System.setProperty("javax.net.ssl.trustStore", trustStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    }



}
