/**
 * Created by Fangbo on 2016/12/9.
 */
import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;

public class mqtt implements MqttCallback
{

    MqttClient client;
    private String url;
    private String username;
    private String password;

    public void setSertopic(String sertopic)
    {
        this.sertopic = sertopic;
    }

    private String sertopic;

    public void connectionLost(Throwable throwable)
    {
        System.out.println ("connection lost");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception
    {
        byte[] data = mqttMessage.getPayload();
        int len = Integer.valueOf(data[0])- 48;
        try
        {
            String strdata = new String(data, 4, len);
            System.out.print(strdata);
            System.out.flush();
        }
        catch (Exception e)
        {
            System.out.println("\r\nerr packet");
        }

    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken)
    {

    }


    public void setMqttserver (String server, int port, String username, String password)
    {
        url = "tcp://" + server + ":" + String.valueOf(port);
        this.username = username;
        this.password = password;

    }

    public void setMqttserver (String server, int port)
    {
        url = "tcp://" + server + ":" + String.valueOf(port);
        this.username = null;
        this.password = null;
        System.out.println("2args mode");
    }

    public void initial ()
    {
        Random random = new Random();

        try
        {
            String client_id = "serPrint"+random.nextInt() + random.nextInt();
            System.out.println("Client id = " + client_id);
            client = new MqttClient(url, client_id);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            //connOpts.setConnectionTimeout(60*10);
            //connOpts.setKeepAliveInterval(60*5);
            connOpts.setCleanSession(true);


            if (username != null)
            {
                connOpts.setUserName(username);
                connOpts.setPassword(password.toCharArray());
            }

            client.connect(connOpts);
            client.setCallback(this);

            if (sertopic == null)
            {
                client.subscribe("sertsert");
            }
            else
            {
                client.subscribe(sertopic);
            }

            /*
            MqttMessage message = new MqttMessage();
            message.setPayload("Start serPrint.".getBytes());
            client.publish("serPrintLog", message);
            */
        }
        catch (MqttException e)
        {
            System.out.println(e);
            System.out.println("mqtt server connect error.");

            //e.printStackTrace();
        }
        System.out.println("broker = [" + this.url + "], username = [" + username + "], password = [" + password + "], topic = [" + sertopic + "].");
        System.out.println("serPrint start :\r\n");
    }
}
