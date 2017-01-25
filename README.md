# movies-data #

This repository demonstrates how to create a bot application with **Spring framework** and integrated with **LINE Messaging API** and **LINE Bot SDK** to show the usage of LINE Messaging API's feature. Movies' data is retrieved from [OMDb API](https://www.omdbapi.com/). This application is deployed in **Heroku**.

### How do I get set up? ###
* Make LINE@ Account with Messaging API enabled
> [LINE Business Center](https://business.line.me/en/)

* Register your Webhook URL
	1. Open [LINE Developer](https://developers.line.me/)
	2. Choose your channel
	3. Edit "Basic Information"

* Add `application.properties` file in *src/main/resources* directory, and fill it with your channel secret and channel access token, like the following:

	```ini
com.linecorp.channel_secret=<your_channel_secret>
com.linecorp.channel_access_token=<your_channel_access_token>
	```

* Reply user's message

	```java
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
	```

* Push message to user

	```java
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
	```

* Construct carousel template message

	```java
	CarouselTemplate carouselTemplate = new CarouselTemplate(
                    Arrays.asList(new CarouselColumn
                                    (poster_url, title, "Select one for more info", Arrays.asList
                                        (new MessageAction("Full Data", "Title \"" + title + "\""),
                                         new MessageAction("Summary", "Plot \"" + title + "\""),
                                         new MessageAction("Poster", "Poster \"" + title + "\""))),
                                  new CarouselColumn
                                    (poster_url, title, "Select one for more info", Arrays.asList
                                        (new MessageAction("Released Date", "Released \"" + title + "\""),
                                         new MessageAction("Actors", "Actors \"" + title + "\""),
                                         new MessageAction("Awards", "Awards \"" + title + "\"")))));
   TemplateMessage templateMessage = new TemplateMessage("Your search result", carouselTemplate);
    PushMessage pushMessage = new PushMessage(sourceId,templateMessage);
	```

* Leave group or room

	```java
	Response<BotApiResponse> response = LineMessagingServiceBuilder
                    .create(lChannelAccessToken)
                    .build()
                    .leaveGroup(id)
                    .execute();
    System.out.println(response.code() + " " + response.message());
	```

* Compile

`gradle clean build`

* Add to Git Repositories

`git add .`

* Commit changes

`git commit -m "Your Messages"`

* Deploy

`git push heroku master`

* Run Server

`$ heroku ps:scale web=1`

* Open logs

`heroku logs --tail`


### How do I contribute? ###

* Add your name and e-mail address into CONTRIBUTORS.txt
