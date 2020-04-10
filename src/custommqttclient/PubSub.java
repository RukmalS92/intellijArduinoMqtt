package custommqttclient;
import custommqttclient.MqttConfig;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

public class PubSub extends MqttConfig{

    public PubSub() throws Exception {
        super();
    }
    public PubSub(String broker) throws Exception {
        super(broker);
    }
    public PubSub(String broker, String client) throws Exception {
        super(broker, client);
    }
    public PubSub(String broker, String client, int qos) throws Exception {
        super(broker, client, qos);
    }




}
