
package com.dicoding.temanngoding;

import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.client.LineSignatureValidator;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(value="/linebot")
public class LineBotController
{
    @Autowired
    @Qualifier("com.linecorp.channel_secret")
    String lChannelSecret;
    
    @Autowired
    @Qualifier("com.linecorp.channel_access_token")
    String lChannelAccessToken;

    @RequestMapping(value="/callback", method=RequestMethod.POST)
    public ResponseEntity<String> callback(
        @RequestHeader("X-Line-Signature") String aXLineSignature,
        @RequestBody String aPayload)
    {
         // compose body
        final String text=String.format("The Signature is: %s",
            (aXLineSignature!=null && aXLineSignature.length() > 0) ? aXLineSignature : "N/A");
        
        System.out.println(text);
        
        final boolean valid=new LineSignatureValidator(lChannelSecret.getBytes()).validateSignature(aPayload.getBytes(), aXLineSignature);
        
        System.out.println("The signature is: " + (valid ? "valid" : "tidak valid"));
        
        //Get events from source
        if(aPayload!=null && aPayload.length() > 0)
        {
            System.out.println("Payload: " + aPayload);
        }
        
        Gson gson = new Gson();
        Payload payload = gson.fromJson(aPayload, Payload.class);
        
        //Variable initialization
        String msgText = " ";
        String upload_url = " ";
        String mJSON = " ";
        String idTarget = " ";
        String eventType = payload.events[0].type;
        
        //Get event's type
        if (eventType.equals("join")){
            if (payload.events[0].source.type.equals("group")){
                replyToUser(payload.events[0].replyToken, "Hello Group");
            }
            if (payload.events[0].source.type.equals("room")){
                replyToUser(payload.events[0].replyToken, "Hello Room");
            }
        } else if (eventType.equals("message")){    //Event's type is message
            if (payload.events[0].source.type.equals("group")){
                idTarget = payload.events[0].source.groupId;
            } else if (payload.events[0].source.type.equals("room")){
                idTarget = payload.events[0].source.roomId;
            } else if (payload.events[0].source.type.equals("user")){
                idTarget = payload.events[0].source.userId;
            }
            
            //Parsing message from user
            if (!payload.events[0].message.type.equals("text")){
                replyToUser(payload.events[0].replyToken, "Unknown message");
            } else {
                //Get movie data from OMDb API
                msgText = payload.events[0].message.text;
                msgText = msgText.toLowerCase();
                
                if (!msgText.contains("bot leave")){
                    try {
                        getMessageData(msgText, idTarget);
                    } catch (IOException e) {
                        System.out.println("Exception is raised ");
                        e.printStackTrace();
                    }
                } else {
                    if (payload.events[0].source.type.equals("group")){
                        leaveGR(payload.events[0].source.groupId, "group");
                    } else if (payload.events[0].source.type.equals("room")){
                        leaveGR(payload.events[0].source.roomId, "room");
                    }
                }
                
//                pushType(idTarget, msgText + " - " + payload.events[0].source.type);
            }
        }
         
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    private void getMessageData(String title, String targetID) throws IOException{
        if (title!=null){
            pushMessage(targetID, title);
        }
    }

    //Method for reply user's message
    private void replyToUser(String rToken, String messageToUser){
        TextMessage textMessage = new TextMessage(messageToUser);
        ReplyMessage replyMessage = new ReplyMessage(rToken, textMessage);
        try {
            Response<BotApiResponse> response = LineMessagingServiceBuilder
                .create(lChannelAccessToken)
                .build()
                .replyMessage(replyMessage)
                .execute();
            System.out.println("Reply Message: " + response.code() + " " + response.message());
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }
    
    //Method for send movie's poster to user
    private void pushPoster(String sourceId, String poster_url){
        ImageMessage imageMessage = new ImageMessage(poster_url, poster_url);
        PushMessage pushMessage = new PushMessage(sourceId,imageMessage);
        try {
            Response<BotApiResponse> response = LineMessagingServiceBuilder
                .create(lChannelAccessToken)
                .build()
                .pushMessage(pushMessage)
                .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }
    
    //Method for push message to user
    private void pushMessage(String sourceId, String txt){
        TextMessage textMessage = new TextMessage(txt);
        PushMessage pushMessage = new PushMessage(sourceId,textMessage);
        try {
            Response<BotApiResponse> response = LineMessagingServiceBuilder
            .create(lChannelAccessToken)
            .build()
            .pushMessage(pushMessage)
            .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }
    
    //Method for send caraousel template message to user
    private void carouselForUser(String poster_url, String sourceId, String title, String uri){
        CarouselTemplate carouselTemplate = new CarouselTemplate(
                    Arrays.asList(new CarouselColumn
                                    (poster_url, title, "Select one for more info", Arrays.asList
                                        (new MessageAction("Summary", "summary"),
                                         new MessageAction("Description", "description"),
                                         new URIAction("Link", uri))),
                                  new CarouselColumn
                                    (poster_url, title, "Select one for more info", Arrays.asList
                                        (new MessageAction("Time", "time"),
                                         new MessageAction("Address", "address"),
                                         new MessageAction("Owner", "owner")))));
        TemplateMessage templateMessage = new TemplateMessage("Your search result", carouselTemplate);
        PushMessage pushMessage = new PushMessage(sourceId,templateMessage);
        try {
            Response<BotApiResponse> response = LineMessagingServiceBuilder
                .create(lChannelAccessToken)
                .build()
                .pushMessage(pushMessage)
                .execute();
            System.out.println(response.code() + " " + response.message());
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }
    
    //Method for leave group or room
    private void leaveGR(String id, String type){
        try {
            if (type.equals("group")){
                Response<BotApiResponse> response = LineMessagingServiceBuilder
                    .create(lChannelAccessToken)
                    .build()
                    .leaveGroup(id)
                    .execute();
                System.out.println(response.code() + " " + response.message());
            } else if (type.equals("room")){
                Response<BotApiResponse> response = LineMessagingServiceBuilder
                    .create(lChannelAccessToken)
                    .build()
                    .leaveRoom(id)
                    .execute();
                System.out.println(response.code() + " " + response.message());
            }
        } catch (IOException e) {
            System.out.println("Exception is raised ");
            e.printStackTrace();
        }
    }
}
