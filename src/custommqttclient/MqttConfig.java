package custommqttclient;
import org.eclipse.paho.client.mqttv3.*;
//static variables cannot be access ether this or super. coz only single copy is being shared among all instances of the class
//https://www.guru99.com/java-static-variable-methods.html
//https://beginnersbook.com/2013/04/java-static-class-block-methods-variables/

public abstract class MqttConfig {
    //vars
    private static int clientidcharlim;

    //system param
    protected String BrokerLink;
    protected String ClintID;
    protected int qos;

    protected MqttClient mqttClient;
    protected MqttConnectOptions mqttconnectoptions;
    protected MqttMessage mqttMessage;

    protected String username;
    protected char[] pass;

    static {
        clientidcharlim = 8;
    }
    public MqttConfig() throws MqttException {
        //default constructor initilizes with local host as broker
        this.BrokerLink = "tcp://localhost:1883";
        this.ClintID = this.GenrateClientID();
        this.qos = 0;
        this.mqttClient = new MqttClient(this.BrokerLink, this.ClintID);
        this.mqttconnectoptions = new MqttConnectOptions();
        this.mqttMessage = new MqttMessage();
    }
    public MqttConfig(String brokerlink) throws MqttException {
        this.BrokerLink = brokerlink;
        this.ClintID = this.GenrateClientID();
        this.qos = 0;
        this.mqttClient = new MqttClient(this.BrokerLink, this.ClintID);
        this.mqttconnectoptions = new MqttConnectOptions();
        this.mqttMessage = new MqttMessage();
    }
    public MqttConfig(String brokerlink, String clientID) throws MqttException {
        this(brokerlink, clientID, 0);
    }
    public MqttConfig(String brokerlink, String clientID, int qos) throws MqttException{
        this.BrokerLink = brokerlink;
        this.ClintID = clientID;
        this.qos = qos;
        this.mqttClient = new MqttClient(this.BrokerLink, this.ClintID);
        this.mqttconnectoptions = new MqttConnectOptions();
        this.mqttMessage = new MqttMessage();
    }

    //privtae methods
    private String GenrateClientID(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder id = new StringBuilder(clientidcharlim); //string builed class build a string with capacity of given limit
        for(int i = 0; i < clientidcharlim; i++){
            int rand = (int)(Math.random() * chars.length());
            id.append(chars.charAt(rand));
        }
        return id.toString();
    }

    //connection methods
    public void SetConnectionPassword(String _pass){
        this.pass = _pass.toCharArray();
    }
    public void setConnectionUsername(String user){
        this.username = user;
    }

    public void Connect() throws MqttException{
        this.mqttconnectoptions.setCleanSession(true);
        this.mqttconnectoptions.setAutomaticReconnect(true);
        this.mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        this.mqttClient.connect(this.mqttconnectoptions);
    }

    public void Disconnect(){

    }

}
