# Simple Discord Chat
## Step 1
Head over to [discord.com/developer](https://www.discord.com/developer) and create a new application.
![Creating Application](images/Creating%20Application.gif)

## Step 2
Click on "Bot" and click "add bot".
![Creating Bot](images/Creating%20Bot.gif)

## Step 3
Scroll down and enable these privileged intents.
![Privileged Intents](images/Privileged%20Intents.gif)

## Step 4
Click on "Reset Token" and copy the token somewhere, maybe a text file.

## Step 5
Click on "OAuth2" then click on "URL Generator"

## Step 6
- In Scopes, select "bot"
- In Bot Permissions, select "Administrator"
- Copy the generated URL and paste it into a web browser and invite the bot to your server.
![Scopes and Permissions](images/Scopes%20and%20Permissions.gif)

## Step 7
- Open Discord.
- Go to "Settings -> Advanced -> Developer Mode" and turn it on.
![Discord Developer Mode](images/Enabling%20Developer%20Mode.gif)

## Step 8
- Go to the server you want to use the bot on, and right click the icon and click "Copy ID". Save this somewhere in a notepad for now.
- Next, find the text channel you want to use the bot on, and right click on it and click copy IP.
![Copying IDs](images/IDs.gif)

## Step 9
- Run the server once then stop it.
- Go to the plugin files and update the `config.yml` for the plugin using the things you've saved before. Now it should work!