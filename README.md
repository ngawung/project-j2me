# Project J2ME

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ngawung/project-j2me/Building?style=for-the-badge)
![LICENSE](https://img.shields.io/github/license/ngawung/project-j2me?style=for-the-badge)

this project targeting midp 2.1 devices....

i recommended using IDE like eclipse or netbeans...
there is also ant build if you didnt really like using full IDE like me...

## PROJECT SETUP

##### 1. Clone this repo obviously

##### 2. Install [java jdk 8 32-bit (1.8.0)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
sunwtk required 32-bit version of oracle jdk 8....
it work perfectly fine with the latest jdk 8

##### 3. Install [sun wireless toolkit 2.5.2_01](https://www.oracle.com/java/technologies/sun-java-wireless-toolkit.html)
the newer java me 8 and higher sdk does not include midp implementation...
java me 3.4 still have midp 2.0 if im not mistaken...
i recommended use sunwtk 2.5.2_01 instead... it cross platform linux/windows

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

## Build with Ant (OPTIONAL)
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
i never get the amr sound working on linux... 

## USEFUL LINK
- [Java ME Discussion Forum](https://community.oracle.com/tech/developers/categories/3992)
- [J2ME Docs](https://nikita36078.github.io/J2ME_Docs/)
- [Symbian Archive](https://mrrosset.github.io/Symbian-Archive/)