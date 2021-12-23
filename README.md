# Project J2ME

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ngawung/project-j2me/Building?style=for-the-badge)
![LICENSE](https://img.shields.io/github/license/ngawung/project-j2me?style=for-the-badge)

im using old sunwtk version (now its called java me sdk)
i tried using newer java me version but it just crash...
i tried using linux and window... tried different java version...
it still crash... only sun wtk 2.5.2_01 work perfectly fine on both windows and linux

im also using older eclipse version (eclipse indigo). you could use newer eclipse but in my experience it kinda tricky to make all the plugins work as intended... while the older eclipse version just work out of the box

there is also ant build if you didnt really like using full IDE like eclipse...

## PROJECT SETUP

##### 1. Clone this repo obviously
```
https://github.com/ngawung/project-j2me.git
```
##### 2. Install [java jdk 8 32-bit (1.8.0)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
sun wireless toolkit 2.5.2_01 only work on 32 bit version of oracle jdk8...

##### 3. Install [sun wireless toolkit 2.5.2_01](https://www.oracle.com/java/technologies/sun-java-wireless-toolkit.html)
the newer java me sdk crash on start on my machine both linux/windows... so im using the older version

##### 4. Download latest version of [ProGuard](https://github.com/Guardsquare/proguard)

##### 5. (optional) Download [Antenna.jar](http://antenna.sourceforge.net/)

##### 6. (optional) Download latest version of Apache Ant


## SETUP ECLIPSE

#### 1. download [eclipse indigo](https://www.eclipse.org/downloads/packages/release/indigo/sr2)
you could use the newer eclipse but it kinda tricky to install old plugin.
just use indigo version... it work out of the box

#### 2. install mobile tool for java plugins
- open eclipse
- select Help -> Install New Software...
- select **Indigo** from drop down menu
- type `mobile` in filter
- check Mobile and Device Development option
- click next -> accept license -> finish
- restart eclipse
- select Window -> Open Perspective -> Other -> Java Me

#### 3. setup Device Management
- select Window -> Preferences -> Java Me -> Device Management
- click **Manual Install**
- click browse and located the SunWTK bin folder
- click ok

#### 4. setup Proguard path
- select Window -> Preferences -> Java Me
- click browse on the Proguard Settings
- located the Proguard root path
- click ok

#### 5. Import Project
- select File -> New -> Midlet Project
- select existing source
- dont forget to include library in `res` folder 

from this point you should already know what to do...
have fun :D

## SETUP Ant (OPTIONAL)
didn't like full IDE? yeah me too... but i have no choice...
im not familiar with java project... so im using eclipse just for code completion. im still using ant for manually build the project

- edit antenna, sunwtk and proguard path in `antenna/config.properties`
- type `ant` to build
- type `ant run` for emulator
- type `ant debug` for remote debug
- type `ant clean` for clean

## ADDITIONAL TOOLS

#### AMR sound format
to make amr sound work on emulator install [Quicktime Player](https://support.apple.com/kb/dl837?locale=en_US) for windows...
for linux check [chapter 8 Sun Java Wireless Toolkit User's Guide](https://docs.oracle.com/cd/E17802_01/products/products/sjwtoolkit/wtk2.5.2/docs/UserGuide-html/mmapi.html)

## USEFUL LINK
- [J2ME Docs](https://nikita36078.github.io/J2ME_Docs/)
- [Symbian Archive](https://mrrosset.github.io/Symbian-Archive/)


## LICENSE

GPLv3