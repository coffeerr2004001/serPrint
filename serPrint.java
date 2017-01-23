/**
 * Created by Fangbo on 2016/12/9.
 * ver 0.1
 * Follow MIT license
 */
public class serPrint
{
    public static void main(String[] args)
    {
        String mqtt_server = "iot.eclipse.org";
        int mqtt_port = 1883;
        String mqtt_username = "null";
        String mqtt_password = "null";
        String sert_topic = "sertcom_123";

        System.out.println("Start serPrint v0.1.");

        if (args.length == 0)
        {
            System.out.println("Connect to default mqtt broker.");
        }
        else
        {

            if (args.length == 1)
            {
                mqtt_server = "iot.eclipse.org";
                mqtt_port = 1883;
                mqtt_username = "null";
                mqtt_password = "null";
                sert_topic = args[0];
            }
            else if (args.length == 2)
            {
                mqtt_server = args[0];
                mqtt_port = Integer.parseInt(args[1]);
                mqtt_username = null;
                mqtt_password = null;
                sert_topic = null;
            }
            else if (args.length == 3)
            {
                mqtt_server = args[0];
                mqtt_port = Integer.parseInt(args[1]);
                mqtt_username = null;
                mqtt_password = null;
                sert_topic = args[2];
            }
            else if (args.length == 4)
            {
                mqtt_server = args[0];
                mqtt_port = Integer.parseInt(args[1]);
                mqtt_username = args[2];
                mqtt_password = args[3];
                sert_topic = null;
            }
            else if (args.length == 5)
            {
                mqtt_server = args[0];
                mqtt_port = Integer.parseInt(args[1]);
                mqtt_username = args[2];
                mqtt_password = args[3];
                sert_topic = args[4];
            }
            else
            {
                System.out.println("args parese error.");
            }
        }

        mqtt m = new mqtt();

        try
        {

            if (mqtt_username == null)
            {
                m.setMqttserver(mqtt_server, mqtt_port);
            }
            else
            {
                m.setMqttserver(mqtt_server, mqtt_port, mqtt_username, mqtt_password);
            }
            m.setSertopic(sert_topic);
            m.initial();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
