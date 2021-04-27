# Project J2ME

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ngawung/project-j2me/Building?style=for-the-badge)
![LICENSE](https://img.shields.io/github/license/ngawung/project-j2me?style=for-the-badge)

## PROJECT SETUP
- Clone this repo obviously
- Install [java jdk 8 32-bit (1.8.0)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html),
- Install j2me sdk you prefered. recommended [sun wireless toolkit 2.5.2](https://www.oracle.com/java/technologies/sun-java-wireless-toolkit.html)
- Install latest version Apache Ant. by the time i write this its version (1.10.5)
- Download [Antenna.jar](http://antenna.sourceforge.net/)
- Open `/antenna/` directory and change `config.properties` config

## ADDITIONAL TOOLS

### Working IDE with J2ME SDK Plugins
- [Eclipse Indigo](https://www.eclipse.org/downloads/packages/release/indigo/sr2)
- Netbeans 7

### External Emulator
- [microemulator](https://sourceforge.net/projects/microemulator/)
- [J2ME Loader](https://github.com/nikita36078/J2ME-Loader)

## BUILDING
- open `/antenna/` directory and run `ant build`
- run `ant run` to open emulator

## DEBUGING
- open `/antenna/` directory and run `ant debug`
- use remote debug with port 8000

## USEFUL LINK
- [J2ME Docs](https://nikita36078.github.io/J2ME_Docs/)
- [Symbian Archive](https://mrrosset.github.io/Symbian-Archive/)

## LICENSE

GPLv3