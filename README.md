Appium 2.9.0 server configuration: 

appium --address 127.0.0.1 --port 4723 --base-path /wd/hub --allow-insecure 

Setuped:
$ANDROID_HOME                                      
$JAVA_HOME

To run Android Chrome tests:

run Pixel 3a API 34, with Android 14.0

from project folder run: 
gradle test -Pos=android_chrome



To run iPhone Safari tests:

run iPhone 15 with iOS 17.5

from project folder run:
gradle test -Pos=ios_safari