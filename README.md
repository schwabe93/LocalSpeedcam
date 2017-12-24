Android Auto compatible app for displaying local speedcam feeds e.g. from radio stations

Dependencies:

- https://github.com/martoreto/aauto-sdk (The piece of code which made OEM apps for AA possible in the first place - Good Job!)
- JSoup

Add a new feed:

- Create a new module in the org.openauto.localspeedcam.modules package
- extend the abstract class TrafficModule.java
- Add the feed in the TrafficModule.java static module list

How to use:

- Build and install APK (If you want to skip the build, just use the latest apk from the apk folder)
- Enable Developer settings in Android Auto
- Enable 'Unknown sources' checkbox
- Connect phone to AA Head Unit and select the OEM tab

![alt text](https://raw.githubusercontent.com/nerone-github/LocalSpeedcam/master/images/selector.png)
![alt text](https://raw.githubusercontent.com/nerone-github/LocalSpeedcam/master/images/localspeed.png)