package bt.gov.ditt.dofps.common.utils;

import bt.gov.ditt.dofps.dto.WorkFlowDto;
import org.springframework.util.StringUtils;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Tshedp Gyeltshen on 6/27/2020.
 */
public class CommonNotificationUtils {

    //private EmailUtil emailSender = null;
    private SMSUtil smsSender = null;
    private static final String EMAIL_BODY_PART1 = "Your application";
    private static final String SMS_BODY_PART2 = ".Click on the link above to make payment";
    private static String SMS_URL = null;
    Properties properties = null;

    public static boolean notifyOnRejection(String appNumber, String[] contactNumbers, String reason)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtils notification =new CommonNotificationUtils();
                notification.properties = GlobalUtil.getPropertiesFromFile(ProtocolConstant.DOP_COMMON_PROPERTIES_FILE_PATH);
                //notification.properties = ResourceBundle.getBundle("documentuploads");
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);
                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent(EMAIL_BODY_PART1+" "+appNumber+" has been rejected."+reason+" ");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnApproval(String appNumber, String[] contactNumbers, String allotRange) {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtils notification =new CommonNotificationUtils();
                notification.properties = GlobalUtil.getPropertiesFromFile(ProtocolConstant.DOP_COMMON_PROPERTIES_FILE_PATH);
                //notification.properties = ResourceBundle.getBundle("documentuploads");
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);
                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent(EMAIL_BODY_PART1 + " " + appNumber + " has been approved. Please visit "+allotRange+" Office");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }
}
