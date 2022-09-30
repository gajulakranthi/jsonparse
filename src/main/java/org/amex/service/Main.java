package org.amex.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.amex.model.Eligibility;
import org.amex.model.GPinEligibility;
import org.amex.model.GPinEligibilityForSource;
import org.amex.model.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "user")
public class Main {
    @Value("${user.url}")
    private static String url;

    @Value("${user.cronJobValue}")
    private static String cronJobValue;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        System.out.println("URL : "+ Main.url +" cronJobValue" +Main.cronJobValue);
        try {
            ObjectMapper om = new ObjectMapper();
            ByteArrayOutputStream bOut = getByteArrayOutputStream();
             Root root = om.readValue(new File("C:\\Users\\Kranthi\\OneDrive - Omesti Berhad\\Documents\\Projects\\Support\\JsonToJava\\Test2.json"), Root.class);// Config File Location ,Hardcoded now
          //  Root root = om.readValue(new File( bOut.toByteArray().toString()), Root.class);// Config File Location ,Hardcoded now
            ArrayList<GPinEligibility> getgPinEligibilityArray = root.getGPinEligibility();
            System.out.println("Total Count of gPinEligibility > " + getgPinEligibilityArray.size());
            extractGPinEligibility(getgPinEligibilityArray);

        } catch (FileNotFoundException fe) {
            System.out.println("File Not Found,Please choose the correct File Path");
        } catch (JsonMappingException jme) {
            System.out.println("Please check JSON mapping");
        } catch (Exception e) {
            System.out.println("YIKES!");
            e.printStackTrace();
        }
    }

//    @Scheduled(fixedDelay = 1000, initialDelay = 3000)
//    public void fixedDelaySch() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        System.out.println("Fixed Delay scheduler:: " + strDate);
//    }

    @Scheduled(cron =("${user.cronJobValue}"))
    private static ByteArrayOutputStream getByteArrayOutputStream() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        //String strDate = sdf.format(now);
        System.out.println("Fetching Config File Periodically:: " + strDate);
        System.out.println("URL : "+ url+" cronJobValue" + cronJobValue);
//        URL url = new URL("https://eob-dev.aex.com:443/api/v1/store/config/ecm/elastalert/AIM600000898/dist-auth-eng_amp-jvm/E1/amp-config-management/src/main/resources/pin/pineligibility.json?raw");
//        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//        httpConn.setRequestMethod("GET");
//        httpConn.addRequestProperty("X-Config-Token", "2c8d4867-0406-1b9b-8d73-6ccd31a6fb11");         // Client Token        httpConn.addRequestProperty("X-Ecm-Prc-Group", "sku_axp_gl");    // PRC Group        httpConn.connect();                     // Connect to centralized config store.        int code = httpConn.getResponseCode();  // Obtain the http status code.        if (code == HttpURLConnection.HTTP_OK) {
//        DataInputStream in = new DataInputStream(httpConn.getInputStream());
        ByteArrayOutputStream bOut = new ByteArrayOutputStream(1024 * 8);
//        if (in != null) {
//            byte[] tmp = new byte[1024];
//            int read = -1;
//            do {
//                read = in.read(tmp, 0, tmp.length);
//                if (read != -1) {
//                    bOut.write(tmp, 0, read);
//                }
//            }
//            while (read != -1);
//            //System.out.println("Config file:\n" + bOut.toString("UTF-8"));
//            bOut.close();
//        }
//    else{
//            System.out.println("Error fetching configuration from remote store. Code: ");
//        }
        return bOut;
    }

    private static void extractGPinEligibility(ArrayList<GPinEligibility> myObjects) {
        for (GPinEligibility gPinEligibility : myObjects) {
            System.out.println("Country is " + gPinEligibility.getCountry());
            System.out.println("*******");

            ArrayList<GPinEligibilityForSource> getgPinEligibilityForSource = gPinEligibility.getGPinEligibilityForSource();


            extractGPinEligibilityForSource(getgPinEligibilityForSource);
            System.out.println("*******");
            System.out.println("*******");

        }
    }

    private static void extractGPinEligibilityForSource(
            ArrayList<GPinEligibilityForSource> getgPinEligibilityForSource) {
        for (GPinEligibilityForSource gPinEligibilityForSource : getgPinEligibilityForSource) {
            System.out.println("SourceCode " + gPinEligibilityForSource.getSourceCode());
            System.out.println("*******");
            extractEligibility(gPinEligibilityForSource);
        }
    }

    private static void extractEligibility(GPinEligibilityForSource gPinEligibilityForSource) {
        Eligibility eg = gPinEligibilityForSource.getEligibility();
        System.out.println("gPinInq " + eg.isGPinInq());
        System.out.println("gPinView " + eg.isGPinView());
        System.out.println("gPinSelfSelect " + eg.isGPinSelfSelect());
        System.out.println("gPinReminder " + eg.isGPinReminder());
        System.out.println("gPinUnlockInitiate " + eg.isGPinUnlockInitiate());
        System.out.println("gPinPips " + eg.isGPinPips());
        System.out.println("gPinPipsReset " + eg.isGPinPipsReset());
        System.out.println("gPinUnlock " + eg.isGPinUnlock());
        System.out.println("gPinValidate " + eg.isGPinValidate());
        System.out.println("gGetGlobalPin " + eg.isGetGlobalPin());
        System.out.println("LockPin " + eg.isLockPin());
    }

}