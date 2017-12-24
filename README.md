# Android Auto compatible app for displaying local speedcam feeds e.g. from radio stations

## Dependencies:

- https://github.com/martoreto/aauto-sdk (The piece of code which made OEM apps for AA possible in the first place - Good Job!)
- JSoup (https://jsoup.org/) - HTML parser

## Additional features:

- Unlock the phone during Android Auto - Please keep your eyes on the road at any time!
- Add custom shortcut to launch other apps in background (e.g. other radar warner which is incompatible with AA)

## Add a new feed:

- Create a new module in the org.openauto.localspeedcam.modules package
- extend the abstract class TrafficModule.java
- Add the feed in the TrafficModule.java static module list

## How to use:

- Build and install APK (If you want to skip the build,[download APK here](https://github.com/nerone-github/LocalSpeedcam/raw/master/apk/localspeedcam-master.apk)
- Enable Developer settings in Android Auto
- Enable 'Unknown sources' checkbox
- Connect phone to AA Head Unit and select the OEM tab

## Screenshots

![alt text](https://raw.githubusercontent.com/nerone-github/LocalSpeedcam/master/images/selector.png)
![alt text](https://raw.githubusercontent.com/nerone-github/LocalSpeedcam/master/images/menu.png)
![alt text](https://raw.githubusercontent.com/nerone-github/LocalSpeedcam/master/images/localspeed.png)